package pe.com.segurmar.seguridad;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/** El control de acceso se ejecuta ANTES de servir vistas y acciones JSF. */
public final class FiltroAdministracion implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        String ruta = request.getRequestURI().substring(request.getContextPath().length());
        HttpSession session = request.getSession(false);
        boolean autenticado = session != null && session.getAttribute("segurmarAdminId") instanceof Integer;
        if ("/admin/login.xhtml".equals(ruta)) {
            if (autenticado && "GET".equalsIgnoreCase(request.getMethod())) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.xhtml");
                return;
            }
            chain.doFilter(req, res);
            return;
        }
        if (!autenticado) {
            String destino = response.encodeRedirectURL(request.getContextPath() + "/admin/login.xhtml");
            // Una petición AJAX de Faces espera una redirección de tipo partial-response.
            if ("partial/ajax".equals(request.getHeader("Faces-Request"))) {
                response.setContentType("text/xml;charset=UTF-8");
                response.getWriter().write("<?xml version='1.0' encoding='UTF-8'?>"
                        + "<partial-response><redirect url='" + destino.replace("&", "&amp;").replace("'", "&apos;")
                        + "'/></partial-response>");
            } else response.sendRedirect(destino);
            return;
        }
        chain.doFilter(req, res);
    }
}
