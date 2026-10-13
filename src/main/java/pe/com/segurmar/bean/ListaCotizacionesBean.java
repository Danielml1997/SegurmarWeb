package pe.com.segurmar.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import pe.com.segurmar.dao.CotizacionDAO;
import pe.com.segurmar.model.Cotizacion;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Managed Bean para h:dataTable, filtros y paginación. */
@Named("listaBean")
@ViewScoped
public class ListaCotizacionesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int TAMANO_PAGINA = 8;
    private List<Cotizacion> registros = new ArrayList<>();
    private String filtro = "";
    private String filtroEstado = "";
    private int paginaActual = 1;

    @PostConstruct
    public void inicializar() { recargar(); }

    private void recargar() {
        try {
            registros = new CotizacionDAO().listar(filtro, filtroEstado);
            if (paginaActual > getTotalPaginas()) paginaActual = getTotalPaginas();
        } catch (SQLException ex) {
            registros = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se pudieron consultar las cotizaciones. Intenta nuevamente.", null));
        }
    }

    public void filtrar() { paginaActual = 1; recargar(); }
    public void limpiar() { filtro = ""; filtroEstado = ""; paginaActual = 1; recargar(); }
    public void siguiente() { if (paginaActual < getTotalPaginas()) paginaActual++; }
    public void anterior() { if (paginaActual > 1) paginaActual--; }

    public void guardarEstado(Cotizacion c) {
        try {
            if (new CotizacionDAO().actualizarEstado(c.getIdCotizacion(), c.getEstado())) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado actualizado.", null));
            }
            recargar();
        } catch (SQLException | IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo actualizar el estado.", null));
            recargar();
        }
    }

    public List<Cotizacion> getRegistrosPagina() {
        int inicio = (paginaActual - 1) * TAMANO_PAGINA;
        if (inicio >= registros.size()) return List.of();
        return registros.subList(inicio, Math.min(inicio + TAMANO_PAGINA, registros.size()));
    }
    public int getTotal() { return registros.size(); }
    public int getTotalPaginas() { return Math.max(1, (registros.size() + TAMANO_PAGINA - 1) / TAMANO_PAGINA); }
    public int getPaginaActual() { return paginaActual; }
    public boolean isPrimera() { return paginaActual <= 1; }
    public boolean isUltima() { return paginaActual >= getTotalPaginas(); }
    public String getFiltro() { return filtro; }
    public void setFiltro(String filtro) { this.filtro = filtro; }
    public String getFiltroEstado() { return filtroEstado; }
    public void setFiltroEstado(String filtroEstado) { this.filtroEstado = filtroEstado; }
}
