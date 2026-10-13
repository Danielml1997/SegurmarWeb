package pe.com.segurmar.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import pe.com.segurmar.dao.CotizacionDAO;
import pe.com.segurmar.model.Cotizacion;
import java.sql.SQLException;
import java.util.List;

/** Indicadores operativos del área comercial. */
@Named("resumenBean")
@RequestScoped
public class ResumenBean {
    private int total;
    private int pendientes;
    private int enProceso;
    private int atendidas;
    private boolean conectado = true;

    @PostConstruct
    public void cargar() {
        try {
            List<Cotizacion> datos = new CotizacionDAO().listar("", "");
            total = datos.size();
            pendientes = (int) datos.stream().filter(x -> "PENDIENTE".equals(x.getEstado())).count();
            enProceso = (int) datos.stream().filter(x -> "EN PROCESO".equals(x.getEstado())).count();
            atendidas = (int) datos.stream().filter(x -> "ATENDIDA".equals(x.getEstado())).count();
        } catch (SQLException ex) {
            conectado = false;
        }
    }
    public int getTotal() { return total; }
    public int getPendientes() { return pendientes; }
    public int getEnProceso() { return enProceso; }
    public int getAtendidas() { return atendidas; }
    public boolean isConectado() { return conectado; }
}
