package pe.com.segurmar.dao;

import pe.com.segurmar.config.ConexionBD;
import pe.com.segurmar.model.Cotizacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** DAO: toda consulta usa parámetros; registro atómico en una transacción. */
public class CotizacionDAO {
    private static final Set<String> ESTADOS = Set.of("PENDIENTE", "EN PROCESO", "ATENDIDA");

    public void registrar(String nombre, String telefono, String correo,
                          int idServicio, String descripcion) throws SQLException {
        if (nombre == null || nombre.isBlank() || telefono == null || telefono.isBlank()
                || descripcion == null || descripcion.isBlank() || idServicio <= 0) {
            throw new IllegalArgumentException("Faltan campos obligatorios.");
        }
        try (Connection c = ConexionBD.getConexion()) {
            c.setAutoCommit(false);
            try {
                int idCliente;
                try (PreparedStatement p = c.prepareStatement(
                        "INSERT INTO cliente (nombre_empresa, telefono, correo) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    p.setString(1, nombre.trim());
                    p.setString(2, telefono.trim());
                    p.setString(3, correo == null ? "" : correo.trim());
                    p.executeUpdate();
                    try (ResultSet keys = p.getGeneratedKeys()) {
                        if (!keys.next()) throw new SQLException("No se generó el ID del cliente.");
                        idCliente = keys.getInt(1);
                    }
                }
                try (PreparedStatement p = c.prepareStatement(
                        "INSERT INTO cotizacion (id_cliente, id_servicio, descripcion_proyecto, estado) "
                        + "VALUES (?, ?, ?, 'PENDIENTE')")) {
                    p.setInt(1, idCliente);
                    p.setInt(2, idServicio);
                    p.setString(3, descripcion.trim());
                    p.executeUpdate();
                }
                c.commit();
            } catch (SQLException | RuntimeException ex) {
                c.rollback();
                throw ex;
            }
        }
    }

    public List<Cotizacion> listar(String texto, String estado) throws SQLException {
        String sql = "SELECT c.id_cotizacion, cl.nombre_empresa, cl.telefono, cl.correo, "
                + "s.nombre AS servicio, c.descripcion_proyecto, c.estado, c.fecha_solicitud "
                + "FROM cotizacion c INNER JOIN cliente cl ON cl.id_cliente=c.id_cliente "
                + "INNER JOIN servicio s ON s.id_servicio=c.id_servicio "
                + "WHERE (? = '' OR LOWER(CONCAT(cl.nombre_empresa,' ',cl.telefono,' ',cl.correo,' ',s.nombre)) LIKE ?) "
                + "AND (? = '' OR c.estado=?) ORDER BY c.id_cotizacion DESC";
        String filtro = texto == null ? "" : texto.trim().toLowerCase();
        String estadoSeguro = estado == null || !ESTADOS.contains(estado) ? "" : estado;
        List<Cotizacion> lista = new ArrayList<>();
        try (Connection c = ConexionBD.getConexion(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, filtro);
            p.setString(2, "%" + filtro.replace("\\", "\\\\").replace("%", "\\%")
                    .replace("_", "\\_") + "%");
            p.setString(3, estadoSeguro);
            p.setString(4, estadoSeguro);
            try (ResultSet rs = p.executeQuery()) {
                while (rs.next()) {
                    Cotizacion x = new Cotizacion();
                    x.setIdCotizacion(rs.getInt("id_cotizacion"));
                    x.setNombreEmpresa(rs.getString("nombre_empresa"));
                    x.setTelefono(rs.getString("telefono"));
                    x.setCorreo(rs.getString("correo"));
                    x.setServicio(rs.getString("servicio"));
                    x.setDescripcionProyecto(rs.getString("descripcion_proyecto"));
                    x.setEstado(rs.getString("estado"));
                    Timestamp fecha = rs.getTimestamp("fecha_solicitud");
                    if (fecha != null) x.setFechaSolicitud(fecha.toLocalDateTime());
                    lista.add(x);
                }
            }
        }
        return lista;
    }

    public boolean actualizarEstado(int idCotizacion, String nuevoEstado) throws SQLException {
        if (!ESTADOS.contains(nuevoEstado)) throw new IllegalArgumentException("Estado inválido.");
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement p = c.prepareStatement(
                     "UPDATE cotizacion SET estado=? WHERE id_cotizacion=?")) {
            p.setString(1, nuevoEstado);
            p.setInt(2, idCotizacion);
            return p.executeUpdate() == 1;
        }
    }
}
