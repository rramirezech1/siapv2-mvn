/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo.view;

import java.util.Date;

/**
 *
 * @author Infosoft
 */
public class VwCalificacionContratos {

    private Integer identificadorCalificacion;
    private Integer identificadorPrimarioOferente;
    private String razonSocial;
    private String numeroContrato;
    private String observacion;
    private Date fechaCalificacion;
    private Date fechaDeInsercion;
    private Integer idTipoDeCalificacion;
    private String descripcionCalificacion;
    private Integer estadoEliminacion;
    

    public VwCalificacionContratos() {
    }

    public Integer getIdentificadorCalificacion() {
        return identificadorCalificacion;
    }

    public void setIdentificadorCalificacion(Integer identificadorCalificacion) {
        this.identificadorCalificacion = identificadorCalificacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDescripcionCalificacion() {
        return descripcionCalificacion;
    }

    public void setDescripcionCalificacion(String descripcionCalificacion) {
        this.descripcionCalificacion = descripcionCalificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getEstadoEliminacion() {
        return estadoEliminacion;
    }

    public void setEstadoEliminacion(Integer estadoEliminacion) {
        this.estadoEliminacion = estadoEliminacion;
    }

    public Integer getIdentificadorPrimarioOferente() {
        return identificadorPrimarioOferente;
    }

    public void setIdentificadorPrimarioOferente(Integer identificadorPrimarioOferente) {
        this.identificadorPrimarioOferente = identificadorPrimarioOferente;
    }

    public Date getFechaCalificacion() {
        return fechaCalificacion;
    }

    public void setFechaCalificacion(Date fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    public Date getFechaDeInsercion() {
        return fechaDeInsercion;
    }

    public void setFechaDeInsercion(Date fechaDeInsercion) {
        this.fechaDeInsercion = fechaDeInsercion;
    }

    public Integer getIdTipoDeCalificacion() {
        return idTipoDeCalificacion;
    }

    public void setIdTipoDeCalificacion(Integer idTipoDeCalificacion) {
        this.idTipoDeCalificacion = idTipoDeCalificacion;
    }
}