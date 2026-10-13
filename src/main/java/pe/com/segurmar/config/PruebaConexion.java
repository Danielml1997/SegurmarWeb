package pe.com.segurmar.config;

import java.sql.Connection;

/** Prueba ejecutable desde NetBeans: Run File. */
public final class PruebaConexion {
    private PruebaConexion() { }
    public static void main(String[] args) {
        try (Connection c = ConexionBD.getConexion()) {
            System.out.println("CONEXIÓN EXITOSA: " + c.getCatalog());
        } catch (Exception ex) {
            System.err.println("No se pudo conectar: " + ex.getMessage());
            System.exit(1);
        }
    }
}
