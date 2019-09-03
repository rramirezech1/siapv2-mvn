/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo.view;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Infosoft
 */
public class VwGarantiasGrupo {

    private Integer identificadorGarantia;
    private String razonSocial;
    private String idDocumento;
    private String noLicitacion;
    private String noGarantia;
    private String descripcionTipoGarantia;
    private BigDecimal montoGarantia;
    private Date fechaVencimiento;
    private String estadoGarantia;
    private String descripcionGarantia;
    private Integer estadoDeEliminacion;
    private Integer identificadorPrimarioOferente;

    public VwGarantiasGrupo() {
    }

    public Integer getIdentificadorGarantia() {
        return identificadorGarantia;
    }

    public void setIdentificadorGarantia(Integer identificadorGarantia) {
        this.identificadorGarantia = identificadorGarantia;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getDescripcionTipoGarantia() {
        return descripcionTipoGarantia;
    }

    public void setDescripcionTipoGarantia(String descripcionTipoGarantia) {
        this.descripcionTipoGarantia = descripcionTipoGarantia;
    }

    public BigDecimal getMontoGarantia() {
        return montoGarantia;
    }

    public void setMontoGarantia(BigDecimal montoGarantia) {
        this.montoGarantia = montoGarantia;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstadoGarantia() {
        return estadoGarantia;
    }

    public void setEstadoGarantia(String estadoGarantia) {
        this.estadoGarantia = estadoGarantia;
    }

    public String getDescripcionGarantia() {
        return descripcionGarantia;
    }

    public void setDescripcionGarantia(String descripcionGarantia) {
        this.descripcionGarantia = descripcionGarantia;
    }

    public Integer getEstadoDeEliminacion() {
        return estadoDeEliminacion;
    }

    public void setEstadoDeEliminacion(Integer estadoDeEliminacion) {
        this.estadoDeEliminacion = estadoDeEliminacion;
    }

    public Integer getIdentificadorPrimarioOferente() {
        return identificadorPrimarioOferente;
    }

    public void setIdentificadorPrimarioOferente(Integer identificadorPrimarioOferente) {
        this.identificadorPrimarioOferente = identificadorPrimarioOferente;
    }

    public String getNoLicitacion() {
        return noLicitacion;
    }

    public void setNoLicitacion(String noLicitacion) {
        this.noLicitacion = noLicitacion;
    }

    public String getNoGarantia() {
        return noGarantia;
    }

    public void setNoGarantia(String noGarantia) {
        this.noGarantia = noGarantia;
    }
    
}