package pe.com.segurmar.model;

import java.io.Serializable;

public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idServicio;
    private String nombre;
    private String descripcion;
    public int getIdServicio() { return idServicio; }
    public void setIdServicio(int idServicio) { this.idServicio = idServicio; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
