/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Infosoft
 */
public class VwGarantiasGrupo {

    private Integer identificadorGarantia;
    private String razonSocial;
    private String documento;
    private String noLicitacion;
    private String metodoAdquisicion;
    private String noGarantia;
    private String descripcionTipoGarantia;
    private BigDecimal montoGarantia;
    private Date fechaVencimiento;
    private String estadoGarantia;
    private String descripcionGarantia;
    private Integer estadoDeEliminacion;
    private Integer identificadorPrimarioOferente;
    private Integer idTipoGarantia;

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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public Integer getIdTipoGarantia() {
        return idTipoGarantia;
    }

    public void setIdTipoGarantia(Integer idTipoGarantia) {
        this.idTipoGarantia = idTipoGarantia;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VwGarantiasGrupo other = (VwGarantiasGrupo) obj;
        if (!Objects.equals(this.identificadorGarantia, other.identificadorGarantia)) {
            return false;
        }
        return true;
    }
    
}