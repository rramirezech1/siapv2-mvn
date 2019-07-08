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
public class SecurityInfo implements PersistenciaDao {

    private String application;
    private String window;
    private String control;
    private String user_name;
    private String status;
    private Integer identificacionDelProceso;
    private Integer idPlantillaSeguridad;
    //Estos no estan en la base
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;

    public SecurityInfo() {
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdentificacionDelProceso() {
        return identificacionDelProceso;
    }

    public void setIdentificacionDelProceso(Integer identificacionDelProceso) {
        this.identificacionDelProceso = identificacionDelProceso;
    }

    public Integer getIdPlantillaSeguridad() {
        return idPlantillaSeguridad;
    }

    public void setIdPlantillaSeguridad(Integer idPlantillaSeguridad) {
        this.idPlantillaSeguridad = idPlantillaSeguridad;
    }

    @Override
    public String toString() {
        return "SecurityInfo{" + "application=" + application + ", window=" + window + ", control=" + control + ", user_name=" + user_name + ", status=" + status + ", identificacionDelProceso=" + identificacionDelProceso + ", idPlantillaSeguridad=" + idPlantillaSeguridad + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.security_info (application, window, control, user_name, status, identificacionDelProceso, idPlantillaSeguridad) 	VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{application, window, control, user_name, status, identificacionDelProceso, idPlantillaSeguridad};
    }

    @Override
    public String generarUpdateSQL() {
        return "update dbo.security_info set status=?, identificacionDelProceso=?, idPlantillaSeguridad=? where application=? and window=? and control=? and user_name=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{status, identificacionDelProceso, idPlantillaSeguridad, application, window, control, user_name};
    }

    @Override
    public void setName(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public void setFechaDeInsercion(Date fechaDeInsercion) {
        this.fechaDeInsercion = fechaDeInsercion;
    }

    @Override
    public void setFechaDeModificacion(Date fechaDeModificacion) {
        this.fechaDeModificacion = fechaDeModificacion;
    }

    @Override
    public void setFechaDeEliminacion(Date fechaDeEliminacion) {
        this.fechaDeEliminacion = fechaDeEliminacion;
    }

    @Override
    public Integer getEstadoDeEliminacion() {
        return this.estadoDeEliminacion;
    }

    @Override
    public void setEstadoDeEliminacion(Integer valor) {
        this.estadoDeEliminacion = valor;
    }
}
