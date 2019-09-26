/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

import java.math.BigDecimal;
import java.util.Date;
import sv.gob.mined.apps.siapv2.mvn.dao.PersistenciaDao;

/**
 *
 * @author Infosoft
 */
public class GarantiasOferente implements PersistenciaDao {

    private Integer identificadorGarantia;
    private Integer identificadorPrimarioOferente;
    private Integer idTipoGarantia;
    private Integer idFormaGarantia;
    private String documento;
    private Integer grupoSiap;
    private String noLicitacion;
    private String noGarantia;
    private String descripcionGarantia;
    private Date fechaPresentacion;
    private Date fechaAccionEstado;
    private Date fechaEmision;
    private Date fechaVencimiento;
    private BigDecimal montoGarantia;
    private Integer estadoGarantia;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;
    private Integer autorizaAccionEstado;
    private String razonSocial;
    private Integer idEntidadEmisora;
    
    public GarantiasOferente() {
    }

    public GarantiasOferente(Integer identificadorGarantia) {
        this.identificadorGarantia = identificadorGarantia;
    }

    public Integer getIdentificadorGarantia() {
        return identificadorGarantia;
    }

    public void setIdentificadorGarantia(Integer identificadorGarantia) {
        this.identificadorGarantia = identificadorGarantia;
    }

    public Integer getIdentificadorPrimarioOferente() {
        return identificadorPrimarioOferente;
    }

    public void setIdentificadorPrimarioOferente(Integer identificadorPrimarioOferente) {
        this.identificadorPrimarioOferente = identificadorPrimarioOferente;
    }

    public Integer getIdTipoGarantia() {
        return idTipoGarantia;
    }

    public void setIdTipoGarantia(Integer idTipoGarantia) {
        this.idTipoGarantia = idTipoGarantia;
    }
    
     @Override
    public Integer getEstadoDeEliminacion() {
        return estadoDeEliminacion;
    }

    public void setEstadoDeEliminacion(Integer estadoDeEliminacion) {
        this.estadoDeEliminacion = estadoDeEliminacion;
    }

    public String getNoGarantia() {
        return noGarantia;
    }

    public void setNoGarantia(String noGarantia) {
        this.noGarantia = noGarantia;
    }
    
    public String getDescripcionGarantia() {
        return descripcionGarantia;
    }

    public void setDescripcionGarantia(String descripcionGarantia) {
        this.descripcionGarantia = descripcionGarantia;
    }

    public Integer getIdFormaGarantia() {
        return idFormaGarantia;
    }

    public void setIdFormaGarantia(Integer idFormaGarantia) {
        this.idFormaGarantia = idFormaGarantia;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getGrupoSiap() {
        return grupoSiap;
    }

    public void setGrupoSiap(Integer grupoSiap) {
        this.grupoSiap = grupoSiap;
    }

    public String getNoLicitacion() {
        return noLicitacion;
    }

    public void setNoLicitacion(String noLicitacion) {
        this.noLicitacion = noLicitacion;
    }

    
    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

        
    public Integer getAutorizaAccionEstado() {
        return autorizaAccionEstado;
    }

    public void setAutorizaAccionEstado(Integer autorizaAccionEstado) {
        this.autorizaAccionEstado = autorizaAccionEstado;
    }
    
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

      
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMontoGarantia() {
        return montoGarantia;
    }

    public void setMontoGarantia(BigDecimal montoGarantia) {
        this.montoGarantia = montoGarantia;
    }

    public Integer getEstadoGarantia() {
        return estadoGarantia;
    }

    public void setEstadoGarantia(Integer estadoGarantia) {
        this.estadoGarantia = estadoGarantia;
    }

    public Date getFechaAccionEstado() {
        return fechaAccionEstado;
    }

    public void setFechaAccionEstado(Date fechaAccionEstado) {
        this.fechaAccionEstado = fechaAccionEstado;
    }
    
    public Date getFechaDeInsercion() {
        return fechaDeInsercion;
    }

    @Override
    public void setFechaDeInsercion(Date fechaDeInsercion) {
        this.fechaDeInsercion = fechaDeInsercion;
    }

    public Date getFechaDeModificacion() {
        return fechaDeModificacion;
    }

    @Override
    public void setFechaDeModificacion(Date fechaDeModificacion) {
        this.fechaDeModificacion = fechaDeModificacion;
    }

    public Date getFechaDeEliminacion() {
        return fechaDeEliminacion;
    }

    @Override
    public void setFechaDeEliminacion(Date fechaDeEliminacion) {
        this.fechaDeEliminacion = fechaDeEliminacion;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdEntidadEmisora() {
        return idEntidadEmisora;
    }

    public void setIdEntidadEmisora(Integer idEntidadEmisora) {
        this.idEntidadEmisora = idEntidadEmisora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificadorGarantia != null ? identificadorGarantia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GarantiasOferente)) {
            return false;
        }
        GarantiasOferente other = (GarantiasOferente) object;
        if ((this.identificadorGarantia == null && other.identificadorGarantia != null) || (this.identificadorGarantia != null && !this.identificadorGarantia.equals(other.identificadorGarantia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.apps.siapv2.mvn.modelo.GarantiasOferente[ identificadorGarantia=" + identificadorGarantia + " ]";
    } 
    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.GarantiasOferente (identificadorPrimarioOferente, idTipoGarantia, idFormaGarantia, documento, grupoSiap, noLicitacion, noGarantia, descripcionGarantia, fechaPresentacion, fechaEmision, fechaVencimiento, montoGarantia, fechaAccionEstado, autorizaAccionEstado, estadoGarantia,  fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name, idEntidadEmisora) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{identificadorPrimarioOferente, idTipoGarantia, idFormaGarantia, documento, grupoSiap, noLicitacion, noGarantia, descripcionGarantia, fechaPresentacion, fechaEmision, fechaVencimiento, montoGarantia, fechaAccionEstado, autorizaAccionEstado, estadoGarantia,  fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name, idEntidadEmisora};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE dbo.GarantiasOferente SET identificadorPrimarioOferente=?, idTipoGarantia=?, idFormaGarantia=?, idEntidadEmisora=?, documento=?, grupoSiap=?, noLicitacion=?, noGarantia=?, descripcionGarantia=?, fechaPresentacion=?, fechaEmision=?, fechaVencimiento=?, montoGarantia=?, fechaAccionEstado=?, autorizaAccionEstado=?, estadoGarantia=?, fechaDeInsercion=?, fechaDeModificacion=?, fechaDeEliminacion=?, estadoDeEliminacion=?, name=?"
                + " WHERE identificadorGarantia=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{identificadorPrimarioOferente, idTipoGarantia, idFormaGarantia, idEntidadEmisora, documento, grupoSiap, noLicitacion, noGarantia, descripcionGarantia, fechaPresentacion, fechaEmision, fechaVencimiento, montoGarantia, fechaAccionEstado, autorizaAccionEstado, estadoGarantia,  fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name, identificadorGarantia};
    }
}