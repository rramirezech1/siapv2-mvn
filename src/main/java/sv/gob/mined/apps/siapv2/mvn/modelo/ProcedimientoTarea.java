/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

import java.util.Date;

/**
 *
 * @author Infosoft
 */
public class ProcedimientoTarea {

    private Integer idProcedimiento;
    private Integer identificadorDeLaTarea;
    private Integer identificacionDelProceso;
    private String descripcionDelProcedimiento;
    private Integer identificadorDeLaEntidad;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;

    public ProcedimientoTarea() {
    }

    public Integer getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Integer idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public Integer getIdentificadorDeLaTarea() {
        return identificadorDeLaTarea;
    }

    public void setIdentificadorDeLaTarea(Integer identificadorDeLaTarea) {
        this.identificadorDeLaTarea = identificadorDeLaTarea;
    }

    public Integer getIdentificacionDelProceso() {
        return identificacionDelProceso;
    }

    public void setIdentificacionDelProceso(Integer identificacionDelProceso) {
        this.identificacionDelProceso = identificacionDelProceso;
    }

    public String getDescripcionDelProcedimiento() {
        return descripcionDelProcedimiento;
    }

    public void setDescripcionDelProcedimiento(String descripcionDelProcedimiento) {
        this.descripcionDelProcedimiento = descripcionDelProcedimiento;
    }

    public Integer getIdentificadorDeLaEntidad() {
        return identificadorDeLaEntidad;
    }

    public void setIdentificadorDeLaEntidad(Integer identificadorDeLaEntidad) {
        this.identificadorDeLaEntidad = identificadorDeLaEntidad;
    }

    public Date getFechaDeInsercion() {
        return fechaDeInsercion;
    }

    public void setFechaDeInsercion(Date fechaDeInsercion) {
        this.fechaDeInsercion = fechaDeInsercion;
    }

    public Date getFechaDeModificacion() {
        return fechaDeModificacion;
    }

    public void setFechaDeModificacion(Date fechaDeModificacion) {
        this.fechaDeModificacion = fechaDeModificacion;
    }

    public Date getFechaDeEliminacion() {
        return fechaDeEliminacion;
    }

    public void setFechaDeEliminacion(Date fechaDeEliminacion) {
        this.fechaDeEliminacion = fechaDeEliminacion;
    }

    public Integer getEstadoDeEliminacion() {
        return estadoDeEliminacion;
    }

    public void setEstadoDeEliminacion(Integer estadoDeEliminacion) {
        this.estadoDeEliminacion = estadoDeEliminacion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimiento != null ? idProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcedimientoTarea)) {
            return false;
        }
        ProcedimientoTarea other = (ProcedimientoTarea) object;
        if ((this.idProcedimiento == null && other.idProcedimiento != null) || (this.idProcedimiento != null && !this.idProcedimiento.equals(other.idProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.apps.bnprove.mvn.modelo.ProcedimientoTarea[ idProcedimiento=" + idProcedimiento + " ]";
    }
}
