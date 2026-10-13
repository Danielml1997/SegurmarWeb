package pe.com.segurmar.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import pe.com.segurmar.dao.UsuarioDAO;
import pe.com.segurmar.model.Usuario;

/** Autenticación mediante formulario JSF; regeneración del identificador de sesión. */
@Named("loginBean")
@RequestScoped
public class LoginBean {
    private String correo;
    private String clave;
    public String ingresar() {
        try {
            Usuario usuario = new UsuarioDAO().autenticar(correo, clave == null ? null : clave.toCharArray());
            if (usuario == null) {
                error("Credenciales incorrectas o cuenta inactiva.");
                clave = null;
                return null;
            }
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession session = request.getSession(true);
            request.changeSessionId(); // Protección frente a fijación de sesión
            session.setAttribute("segurmarAdminId", usuario.idUsuario());
            session.setAttribute("segurmarAdminNombre", usuario.nombreUsuario());
            session.setMaxInactiveInterval(30 * 60);
            clave = null;
            return "/admin/dashboard?faces-redirect=true";
        } catch (SQLException ex) {
            ex.printStackTrace();
            error("No fue posible acceder en este momento. Inténtalo nuevamente más tarde.");
            clave = null;
            return null;
        }
    }
    private void error(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
    }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
