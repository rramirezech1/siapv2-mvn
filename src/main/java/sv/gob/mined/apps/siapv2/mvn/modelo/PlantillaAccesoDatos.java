/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

import java.util.Date;
import sv.gob.mined.apps.siapv2.mvn.dao.PersistenciaDao;

/**
 *
 * @author
 */
public class PlantillaAccesoDatos implements PersistenciaDao {

    private Integer idPlantillaAccesoDatos;
    private Integer idNivelRealizacion;
    private Integer idNivelOrganigrama;
    private Integer idPlantillaSeguridad;
    private String descPlantillaDatos;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;

    public PlantillaAccesoDatos() {
    }

    public Integer getIdPlantillaAccesoDatos() {
        return idPlantillaAccesoDatos;
    }

    public void setIdPlantillaAccesoDatos(Integer idPlantillaAccesoDatos) {
        this.idPlantillaAccesoDatos = idPlantillaAccesoDatos;
    }

    public Integer getIdNivelRealizacion() {
        return idNivelRealizacion;
    }

    public void setIdNivelRealizacion(Integer idNivelRealizacion) {
        this.idNivelRealizacion = idNivelRealizacion;
    }

    public Integer getIdNivelOrganigrama() {
        return idNivelOrganigrama;
    }

    public void setIdNivelOrganigrama(Integer idNivelOrganigrama) {
        this.idNivelOrganigrama = idNivelOrganigrama;
    }

    public Integer getIdPlantillaSeguridad() {
        return idPlantillaSeguridad;
    }

    public void setIdPlantillaSeguridad(Integer idPlantillaSeguridad) {
        this.idPlantillaSeguridad = idPlantillaSeguridad;
    }

    public String getDescPlantillaDatos() {
        return descPlantillaDatos;
    }

    public void setDescPlantillaDatos(String descPlantillaDatos) {
        this.descPlantillaDatos = descPlantillaDatos;
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

    @Override
    public String toString() {
        return "PlantillaAccesoDatos{" + "idPlantillaAccesoDatos=" + idPlantillaAccesoDatos + ", idNivelRealizacion=" + idNivelRealizacion + ", idNivelOrganigrama=" + idNivelOrganigrama + ", idPlantillaSeguridad=" + idPlantillaSeguridad + ", descPlantillaDatos=" + descPlantillaDatos + ", fechaDeInsercion=" + fechaDeInsercion + ", fechaDeModificacion=" + fechaDeModificacion + ", fechaDeEliminacion=" + fechaDeEliminacion + ", estadoDeEliminacion=" + estadoDeEliminacion + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.PlantillaAccesoDatos (idNivelRealizacion, idNivelOrganigrama, idPlantillaSeguridad, descPlantillaDatos, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idNivelRealizacion, idNivelOrganigrama, idPlantillaSeguridad, descPlantillaDatos, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE dbo.PlantillaAccesoDatos SET idNivelRealizacion=?, idNivelOrganigrama=?, idPlantillaSeguridad=?, descPlantillaDatos=?, fechaDeInsercion=?, fechaDeModificacion=?, fechaDeEliminacion=?, estadoDeEliminacion=?, name=? WHERE idPlantillaAccesoDatos=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idNivelRealizacion, idNivelOrganigrama, idPlantillaSeguridad, descPlantillaDatos, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name, idPlantillaAccesoDatos};
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
