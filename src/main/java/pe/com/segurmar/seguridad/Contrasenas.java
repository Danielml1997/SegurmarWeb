package pe.com.segurmar.seguridad;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** Hash de contraseñas PBKDF2-HMAC-SHA256 con sal aleatoria e iteraciones. */
public final class Contrasenas {
    public static final int ITERACIONES = 310_000;
    private static final SecureRandom RANDOM = new SecureRandom();
    private Contrasenas() {}

    public static String crear(char[] clave) {
        if (clave == null || clave.length < 12) throw new IllegalArgumentException("Contraseña demasiado corta.");
        byte[] sal = new byte[16];
        RANDOM.nextBytes(sal);
        byte[] hash = derivar(clave, sal, ITERACIONES);
        return "pbkdf2_sha256$" + ITERACIONES + "$" + Base64.getEncoder().encodeToString(sal)
                + "$" + Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verificar(char[] clave, String almacenado) {
        if (clave == null || almacenado == null) return false;
        try {
            String[] partes = almacenado.split("\\$", -1);
            if (partes.length != 4 || !"pbkdf2_sha256".equals(partes[0])) return false;
            int rondas = Integer.parseInt(partes[1]);
            if (rondas < 100_000 || rondas > 2_000_000) return false;
            byte[] sal = Base64.getDecoder().decode(partes[2]);
            byte[] esperado = Base64.getDecoder().decode(partes[3]);
            if (sal.length < 16 || esperado.length != 32) return false;
            return MessageDigest.isEqual(esperado, derivar(clave, sal, rondas));
        } catch (IllegalArgumentException ex) { return false; }
    }

    private static byte[] derivar(char[] clave, byte[] sal, int rondas) {
        PBEKeySpec spec = new PBEKeySpec(clave, sal, rondas, 256);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception ex) {
            throw new IllegalStateException("No se pudo calcular el hash de la contraseña.", ex);
        } finally { spec.clearPassword(); }
    }
}
