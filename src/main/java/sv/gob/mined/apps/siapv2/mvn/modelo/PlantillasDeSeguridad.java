/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

import java.util.Date;
import sv.gob.mined.apps.siapv2.mvn.dao.PersistenciaDao;

/**
 *
 * @author Infosoft
 */
public class PlantillasDeSeguridad implements PersistenciaDao {

    private Integer idPlantillaSeguridad;
    private Integer idTipoRolControl;
    private Integer idActorProceso;
    private String descripcionPlantilla;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;

    public PlantillasDeSeguridad() {
    }

    public Integer getIdPlantillaSeguridad() {
        return idPlantillaSeguridad;
    }

    public void setIdPlantillaSeguridad(Integer idPlantillaSeguridad) {
        this.idPlantillaSeguridad = idPlantillaSeguridad;
    }

    public Integer getIdTipoRolControl() {
        return idTipoRolControl;
    }

    public void setIdTipoRolControl(Integer idTipoRolControl) {
        this.idTipoRolControl = idTipoRolControl;
    }

    public Integer getIdActorProceso() {
        return idActorProceso;
    }

    public void setIdActorProceso(Integer idActorProceso) {
        this.idActorProceso = idActorProceso;
    }

    public String getDescripcionPlantilla() {
        return descripcionPlantilla;
    }

    public void setDescripcionPlantilla(String descripcionPlantilla) {
        this.descripcionPlantilla = descripcionPlantilla;
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

    @Override
    public Integer getEstadoDeEliminacion() {
        return estadoDeEliminacion;
    }

    @Override
    public void setEstadoDeEliminacion(Integer estadoDeEliminacion) {
        this.estadoDeEliminacion = estadoDeEliminacion;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlantillaSeguridad != null ? idPlantillaSeguridad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantillasDeSeguridad)) {
            return false;
        }
        PlantillasDeSeguridad other = (PlantillasDeSeguridad) object;
        if ((this.idPlantillaSeguridad == null && other.idPlantillaSeguridad != null) || (this.idPlantillaSeguridad != null && !this.idPlantillaSeguridad.equals(other.idPlantillaSeguridad))) {
            return false;
        }
        return true;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.PlantillasDeSeguridad (idTipoRolControl, idActorProceso, descripcionPlantilla, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name) VALUES (?, ?, ?, ?, ?,?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idTipoRolControl, idActorProceso, descripcionPlantilla, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name};
    }

    @Override
    public String generarUpdateSQL() {
        return "update dbo.PlantillasDeSeguridad set idTipoRolControl=?,idActorProceso=?,descripcionPlantilla=?,fechaDeInsercion=?,fechaDeModificacion=?,fechaDeEliminacion=?,estadoDeEliminacion=?,name=? "
                + "where idPlantillaSeguridad=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idTipoRolControl, idActorProceso, descripcionPlantilla, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name, idPlantillaSeguridad};
    }

    @Override
    public String toString() {
        return "sv.gob.mined.apps.bnprove.mvn.modelo.PlantillasDeSeguridad[ idPlantillaSeguridad=" + idPlantillaSeguridad + " ]";
    }
}
