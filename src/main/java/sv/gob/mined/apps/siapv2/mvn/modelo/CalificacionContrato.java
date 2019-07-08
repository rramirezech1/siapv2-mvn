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
public class CalificacionContrato implements PersistenciaDao {

    private Integer identificadorCalificacion;
    private Integer identificadorPrimarioOferente;
    private Integer idTipoDeCalificacion;
    private String numeroContrato;
    private String observacion;
    private Date fechaCalificacion;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;

    public CalificacionContrato() {
    }

    public Integer getIdentificadorCalificacion() {
        return identificadorCalificacion;
    }

    public void setIdentificadorCalificacion(Integer identificadorCalificacion) {
        this.identificadorCalificacion = identificadorCalificacion;
    }

    public Integer getIdentificadorPrimarioOferente() {
        return identificadorPrimarioOferente;
    }

    public void setIdentificadorPrimarioOferente(Integer identificadorPrimarioOferente) {
        this.identificadorPrimarioOferente = identificadorPrimarioOferente;
    }

    public Integer getIdTipoDeCalificacion() {
        return idTipoDeCalificacion;
    }

    public void setIdTipoDeCalificacion(Integer idTipoDeCalificacion) {
        this.idTipoDeCalificacion = idTipoDeCalificacion;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
    public String generarInsertSQL() {
        return "INSERT INTO dbo.CalificacionContrato (identificadorPrimarioOferente,idTipoDeCalificacion,numeroContrato,observacion,fechaCalificacion,fechaDeInsercion,fechaDeModificacion,fechaDeEliminacion,estadoDeEliminacion,name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{identificadorPrimarioOferente,idTipoDeCalificacion,numeroContrato,observacion,fechaCalificacion,fechaDeInsercion,fechaDeModificacion,fechaDeEliminacion,estadoDeEliminacion,name};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE dbo.CalificacionContrato SET identificadorPrimarioOferente=?, idTipoDeCalificacion=?, numeroContrato=?, observacion=?, fechaCalificacion=?, fechaDeInsercion=?, fechaDeModificacion=?, fechaDeEliminacion=?, estadoDeEliminacion=?, name=? WHERE identificadorCalificacion=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{identificadorPrimarioOferente, idTipoDeCalificacion, numeroContrato, observacion, fechaCalificacion, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name, identificadorCalificacion};
    }
}