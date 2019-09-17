/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

/**
 *
 * @author reynaldo
 */
public class EntidadEmisora {

    private Integer idEntidadEmisora;
    private String nombreEntidadEmisora;
    private String fechaInsercion;
    private String fechaModificacion;
    private String fechaEliminacion;
    private String estado;
    
    public EntidadEmisora() {
    }

    public Integer getIdEntidadEmisora() {
        return idEntidadEmisora;
    }

    public void setIdEntidadEmisora(Integer idEntidadEmisora) {
        this.idEntidadEmisora = idEntidadEmisora;
    }

    public String getNombreEntidadEmisora() {
        return nombreEntidadEmisora;
    }

    public void setNombreEntidadEmisora(String nombreEntidadEmisora) {
        this.nombreEntidadEmisora = nombreEntidadEmisora;
    }

    public String getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(String fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(String fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
