package pe.com.segurmar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import pe.com.segurmar.config.ConexionBD;
import pe.com.segurmar.model.Usuario;
import pe.com.segurmar.seguridad.Contrasenas;

/** Autenticación por correo, solo cuentas activas y comparación criptográfica. */
public final class UsuarioDAO {
    // Evita una diferencia grande de tiempo cuando el correo no existe.
    private static final String HASH_FICTICIO = Contrasenas.crear("comprobacion-interna-no-es-usuario".toCharArray());
    public Usuario autenticar(String correo, char[] clave) throws SQLException {
        if (correo == null || correo.isBlank() || clave == null || clave.length == 0) return null;
        String sql = "SELECT id_usuario, nombre_usuario, correo, clave_hash, activo "
                + "FROM usuario WHERE correo = ? LIMIT 1";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, correo.strip().toLowerCase(Locale.ROOT));
            try (ResultSet rs = sentencia.executeQuery()) {
                if (!rs.next()) {
                    Contrasenas.verificar(clave, HASH_FICTICIO);
                    return null;
                }
                boolean coincide = Contrasenas.verificar(clave, rs.getString("clave_hash"));
                if (!coincide || !rs.getBoolean("activo")) return null;
                return new Usuario(rs.getInt("id_usuario"), rs.getString("nombre_usuario"), rs.getString("correo"));
            }
        }
    }
}
