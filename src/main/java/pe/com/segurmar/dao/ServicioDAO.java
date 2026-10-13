package pe.com.segurmar.dao;

import pe.com.segurmar.config.ConexionBD;
import pe.com.segurmar.model.Servicio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    public List<Servicio> listar() throws SQLException {
        String sql = "SELECT id_servicio, nombre, descripcion FROM servicio ORDER BY id_servicio";
        List<Servicio> datos = new ArrayList<>();
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {
            while (rs.next()) {
                Servicio s = new Servicio();
                s.setIdServicio(rs.getInt("id_servicio"));
                s.setNombre(rs.getString("nombre"));
                s.setDescripcion(rs.getString("descripcion"));
                datos.add(s);
            }
        }
        return datos;
    }
    public int buscarIdPorNombre(Connection c, String nombre) throws SQLException {
        try (PreparedStatement p = c.prepareStatement(
                "SELECT id_servicio FROM servicio WHERE LOWER(nombre)=LOWER(?) LIMIT 1")) {
            p.setString(1, nombre);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Servicio no registrado en MySQL: " + nombre);
    }
}
