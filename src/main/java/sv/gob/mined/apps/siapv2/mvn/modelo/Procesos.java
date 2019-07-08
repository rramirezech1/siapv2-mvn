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
public class Procesos {

    private Integer identificacionDelProceso;
    private Integer idMacroProceso;
    private String descripcionDelProceso;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;

    public Procesos() {
    }

    public Integer getIdentificacionDelProceso() {
        return identificacionDelProceso;
    }

    public void setIdentificacionDelProceso(Integer identificacionDelProceso) {
        this.identificacionDelProceso = identificacionDelProceso;
    }

    public Integer getIdMacroProceso() {
        return idMacroProceso;
    }

    public void setIdMacroProceso(Integer idMacroProceso) {
        this.idMacroProceso = idMacroProceso;
    }

    public String getDescripcionDelProceso() {
        return descripcionDelProceso;
    }

    public void setDescripcionDelProceso(String descripcionDelProceso) {
        this.descripcionDelProceso = descripcionDelProceso;
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
        hash += (identificacionDelProceso != null ? identificacionDelProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesos)) {
            return false;
        }
        Procesos other = (Procesos) object;
        if ((this.identificacionDelProceso == null && other.identificacionDelProceso != null) || (this.identificacionDelProceso != null && !this.identificacionDelProceso.equals(other.identificacionDelProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.apps.bnprove.mvn.modelo.Procesos[ identificacionDelProceso=" + identificacionDelProceso + " ]";
    }
}
