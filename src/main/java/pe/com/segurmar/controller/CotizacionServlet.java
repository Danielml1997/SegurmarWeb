package pe.com.segurmar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.com.segurmar.config.ConexionBD;
import pe.com.segurmar.dao.CotizacionDAO;
import pe.com.segurmar.dao.ServicioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/** Compatibilidad del formulario JSP heredado, /cotizacion. */
public class CotizacionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String servicio = request.getParameter("servicio");
        String mensaje = request.getParameter("mensaje");
        if (vacio(nombre) || vacio(telefono) || vacio(servicio) || vacio(mensaje)) {
            request.setAttribute("error", "Completa todos los campos obligatorios.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        try (Connection c = ConexionBD.getConexion()) {
            int servicioId = new ServicioDAO().buscarIdPorNombre(c, servicio.trim());
            new CotizacionDAO().registrar(nombre, telefono, correo, servicioId, mensaje);
            // El JSP heredado utiliza EL sin escape: enviamos texto ya escapado.
            request.setAttribute("nombreCliente", escaparHTML(nombre.trim()));
            request.setAttribute("servicioSolicitado", escaparHTML(servicio.trim()));
            request.getRequestDispatcher("/confirmacion.jsp").forward(request, response);
        } catch (SQLException | IllegalArgumentException ex) {
            getServletContext().log("Error al registrar cotización", ex);
            request.setAttribute("error", "No se pudo registrar la solicitud. Inténtalo nuevamente en unos minutos.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    private static boolean vacio(String v) { return v == null || v.isBlank(); }
    private static String escaparHTML(String v) {
        return v.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}
