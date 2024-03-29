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
public class OpcionMenu implements PersistenciaDao{

    private Integer idOpcionMenu;
    private String nombreOpcionMenu;
    private String labelOpcionMenu;
    private String direccionAccesoOpcion;
    private Integer idOpcionPadre;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;
    private String opcionPadre;
   
    
    
    
    public OpcionMenu() {
    }

    public Integer getIdOpcionMenu() {
        return idOpcionMenu;
    }

    public void setIdOpcionMenu(Integer idOpcionMenu) {
        this.idOpcionMenu = idOpcionMenu;
    }

    public String getNombreOpcionMenu() {
        return nombreOpcionMenu;
    }

    public void setNombreOpcionMenu(String nombreOpcionMenu) {
        this.nombreOpcionMenu = nombreOpcionMenu;
    }

    public String getLabelOpcionMenu() {
        return labelOpcionMenu;
    }

    public void setLabelOpcionMenu(String labelOpcionMenu) {
        this.labelOpcionMenu = labelOpcionMenu;
    }

    public String getDireccionAccesoOpcion() {
        return direccionAccesoOpcion;
    }

    public void setDireccionAccesoOpcion(String direccionAccesoOpcion) {
        this.direccionAccesoOpcion = direccionAccesoOpcion;
    }

    public Integer getIdOpcionPadre() {
        return idOpcionPadre;
    }

    public void setIdOpcionPadre(Integer idOpcionPadre) {
        this.idOpcionPadre = idOpcionPadre;
    }

    public Date getFechaDeInsercion() {
        return fechaDeInsercion;
    }

    public String getOpcionPadre() {
        return opcionPadre;
    }

    public void setOpcionPadre(String opcionPadre) {
        this.opcionPadre = opcionPadre;
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
    public String toString() {
        return "sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu[ idOpcionMenu=" + idOpcionMenu+ " ]";
    } 
    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.OpcionMenu (nombreOpcionMenu, labelOpcionMenu, direccionAccesoOpcion, idOpcionPadre, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreOpcionMenu,labelOpcionMenu, direccionAccesoOpcion, idOpcionPadre, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE dbo.OpcionMenu SET nombreOpcionMenu=?, labelOpcionMenu=?, direccionAccesoOpcion=?, idOpcionPadre=?, fechaDeInsercion=?, fechaDeModificacion=?, fechaDeEliminacion=?, estadoDeEliminacion=?, name=?"
                + " WHERE idOpcionMenu=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreOpcionMenu,labelOpcionMenu, direccionAccesoOpcion, idOpcionPadre, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name};
    }
}