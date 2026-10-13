package pe.com.segurmar.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/** Cierra la sesión mediante POST con el token de vista de JSF. */
@Named("sesionBean")
@RequestScoped
public class SesionBean {
    public String salir() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return "/admin/login?faces-redirect=true";
    }
}
