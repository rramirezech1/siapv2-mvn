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
public class PermisoOpcionMenu implements PersistenciaDao{

    private Integer idPermisoOpcionMenu;
    private Integer idOpcionMenu;
    private Integer idGrupo;
    private String userName;
    private String observaciones;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name; 
    
    
    public PermisoOpcionMenu() {
    }

    public Integer getIdPermisoOpcionMenu() {
        return idPermisoOpcionMenu;
    }

    public void setIdPermisoOpcionMenu(Integer idPermisoOpcionMenu) {
        this.idPermisoOpcionMenu = idPermisoOpcionMenu;
    }

    public Integer getIdOpcionMenu() {
        return idOpcionMenu;
    }

    public void setIdOpcionMenu(Integer idOpcionMenu) {
        this.idOpcionMenu = idOpcionMenu;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
    public String toString() {
        return "sv.gob.mined.apps.siapv2.mvn.modelo.PermisoOpcionMenu[ idPermisoOpcionMenu=" + idPermisoOpcionMenu+ " ]";
    } 
    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.PermisoOpcionMenu (idOpcionMenu, idGrupo, userName, observaciones, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, name, estadoDeEliminacion) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idOpcionMenu, idGrupo, userName, observaciones, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, name, estadoDeEliminacion};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE dbo.PermisoOpcionMenu SET idOpcionMenu=?, idGrupo=?, userName=?, observaciones=?, fechaDeInsercion=?, fechaDeModificacion=?, fechaDeEliminacion=?, name=?, estadoDeEliminacion=?"
                + " WHERE idPermisoOpcionMenu=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idOpcionMenu, idGrupo, userName, observaciones, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, name, estadoDeEliminacion};
    }
}