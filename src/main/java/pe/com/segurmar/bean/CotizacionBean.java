package pe.com.segurmar.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import pe.com.segurmar.dao.CotizacionDAO;
import pe.com.segurmar.dao.ServicioDAO;
import pe.com.segurmar.model.Servicio;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Managed Bean CDI: enlaza cotizacion.xhtml con DAO mediante #{cotizacionBean.*}. */
@Named("cotizacionBean")
@ViewScoped
public class CotizacionBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String telefono;
    private String correo;
    private Integer idServicio;
    private String mensaje;
    private List<Servicio> servicios = new ArrayList<>();

    @PostConstruct
    public void inicializar() {
        try {
            servicios = new ServicioDAO().listar();
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No están disponibles los servicios en este momento. Inténtalo más tarde.", null));
        }
    }

    public String registrar() {
        if (idServicio == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecciona un servicio.", null));
            return null;
        }
        boolean valido = servicios.stream().anyMatch(s -> s.getIdServicio() == idServicio);
        if (!valido) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Servicio no válido.", null));
            return null;
        }
        try {
            new CotizacionDAO().registrar(nombre, telefono, correo, idServicio, mensaje);
            String nombreServicio = servicios.stream()
                .filter(s -> s.getIdServicio() == idServicio)
                .findFirst().orElseThrow().getNombre();
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("nombreCliente", nombre);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("servicioSolicitado", nombreServicio);
            return "/confirmacion?faces-redirect=true";
        } catch (SQLException | IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudo enviar la solicitud. Inténtalo nuevamente en unos minutos.", null));
            return null;
        }
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Integer getIdServicio() { return idServicio; }
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public List<Servicio> getServicios() { return servicios; }
}
