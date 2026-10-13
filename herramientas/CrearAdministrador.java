import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** Utilidad local sin dependencias: genera SQL para dar de alta al administrador. */
public class CrearAdministrador {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int ITERACIONES = 310_000;
    private static final Pattern EMAIL = Pattern.compile("^[^@ ]+@[^@ ]+\\.[^@ ]+$");

    public static void main(String[] args) throws Exception {
        System.out.println("=== SEGURMAR · Configuración de administrador ===");
        Console consola = System.console();
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        String nombre = solicitar(consola, scanner, "Nombre del responsable: ").strip();
        String correo = solicitar(consola, scanner, "Correo de acceso: ").strip().toLowerCase(Locale.ROOT);
        if (nombre.length() < 2 || nombre.length() > 100 || !EMAIL.matcher(correo).matches() || correo.length() > 254) {
            System.err.println("Nombre o correo inválido.");return;
        }
        char[] clave = pedirClave(consola, scanner, "Contraseña (mínimo 12 caracteres): ");
        char[] repetir = pedirClave(consola, scanner, "Repetir contraseña: ");
        try {
            if (clave.length < 12 || !Arrays.equals(clave, repetir)) {
                System.err.println("Las contraseñas no coinciden o son demasiado cortas.");return;
            }
            byte[] sal = new byte[16];RANDOM.nextBytes(sal);
            PBEKeySpec spec = new PBEKeySpec(clave, sal, ITERACIONES, 256);
            byte[] hash;
            try {hash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();}
            finally {spec.clearPassword();}
            String seguro = "pbkdf2_sha256$" + ITERACIONES + "$"
                + Base64.getEncoder().encodeToString(sal) + "$" + Base64.getEncoder().encodeToString(hash);
            String sql = "USE segurmar_db;\n"
                    + "INSERT INTO usuario (nombre_usuario,correo,clave_hash,activo) VALUES ('"
                    + escapado(nombre) + "','" + escapado(correo) + "','" + seguro + "',1)\n"
                    + "ON DUPLICATE KEY UPDATE nombre_usuario=VALUES(nombre_usuario), "
                    + "clave_hash=VALUES(clave_hash), activo=1;\n";
            Path destino = Path.of("SQL", "01_ALTA_ADMIN_GENERADO.sql");
            Files.createDirectories(destino.getParent());
            Files.writeString(destino, sql, StandardCharsets.UTF_8);
            System.out.println("\nArchivo preparado: " + destino.toAbsolutePath());
            System.out.println("Abre este archivo en MySQL Workbench y ejecútalo una vez.");
            System.out.println("Después ingresa en /SegurmarWeb/admin/login.xhtml con tu correo y contraseña.");
            System.out.println("No publiques el archivo SQL generado en repositorios públicos.");
        } finally {Arrays.fill(clave, '\0');Arrays.fill(repetir, '\0');}
    }
    private static String solicitar(Console consola, Scanner scanner, String mensaje) {
        if (consola != null) return consola.readLine("%s", mensaje);
        System.out.print(mensaje);return scanner.nextLine();
    }
    private static char[] pedirClave(Console consola, Scanner scanner, String mensaje) {
        if (consola != null) return consola.readPassword("%s", mensaje);
        System.out.print(mensaje);
        // Sin Console, la terminal puede mostrar caracteres. Mejor ejecutar desde CMD.
        return scanner.nextLine().toCharArray();
    }
    private static String escapado(String entrada) {return entrada.replace("'", "''");}
}
