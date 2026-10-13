package pe.com.segurmar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Conexión JDBC centralizada. Nunca publicar credenciales en GitHub. */
public final class ConexionBD {
    private static final String URL_DEFECTO =
            "jdbc:mysql://127.0.0.1:3306/segurmar_db?useSSL=false&serverTimezone=America/Lima&allowPublicKeyRetrieval=true";

    private ConexionBD() { }

    private static String valor(String propiedad, String variable, String defecto) {
        String v = System.getProperty(propiedad);
        if (v == null || v.isBlank()) v = System.getenv(variable);
        return v == null ? defecto : v;
    }

    public static Connection getConexion() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException ex) {
        throw new SQLException(
            "No se encontró el controlador MySQL JDBC", ex
        );
    }

    return DriverManager.getConnection(
        valor("segurmar.db.url", "SEGURMAR_DB_URL", URL_DEFECTO),
        valor("segurmar.db.user", "SEGURMAR_DB_USER", "root"),
        valor("segurmar.db.password", "SEGURMAR_DB_PASSWORD", "admin1")
    );
}
}
