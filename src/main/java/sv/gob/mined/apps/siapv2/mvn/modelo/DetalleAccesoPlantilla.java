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
public class DetalleAccesoPlantilla implements PersistenciaDao{

    private Integer idDetalleAccesoDatos;
    private Integer idPlantillaAccesoDatos;
    private Integer idEstructuraEstrategia;
    private String cadenaFiltradoNivel;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;

    public DetalleAccesoPlantilla() {
    }

    public Integer getIdDetalleAccesoDatos() {
        return idDetalleAccesoDatos;
    }

    public void setIdDetalleAccesoDatos(Integer idDetalleAccesoDatos) {
        this.idDetalleAccesoDatos = idDetalleAccesoDatos;
    }

    public Integer getIdPlantillaAccesoDatos() {
        return idPlantillaAccesoDatos;
    }

    public void setIdPlantillaAccesoDatos(Integer idPlantillaAccesoDatos) {
        this.idPlantillaAccesoDatos = idPlantillaAccesoDatos;
    }

    public Integer getIdEstructuraEstrategia() {
        return idEstructuraEstrategia;
    }

    public void setIdEstructuraEstrategia(Integer idEstructuraEstrategia) {
        this.idEstructuraEstrategia = idEstructuraEstrategia;
    }

    public String getCadenaFiltradoNivel() {
        return cadenaFiltradoNivel;
    }

    public void setCadenaFiltradoNivel(String cadenaFiltradoNivel) {
        this.cadenaFiltradoNivel = cadenaFiltradoNivel;
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
    public String toString() {
        return "DetalleAccesoPlantilla{" + "idDetalleAccesoDatos=" + idDetalleAccesoDatos + ", idPlantillaAccesoDatos=" + idPlantillaAccesoDatos + ", idEstructuraEstrategia=" + idEstructuraEstrategia + ", cadenaFiltradoNivel=" + cadenaFiltradoNivel + ", fechaDeInsercion=" + fechaDeInsercion + ", fechaDeModificacion=" + fechaDeModificacion + ", fechaDeEliminacion=" + fechaDeEliminacion + ", estadoDeEliminacion=" + estadoDeEliminacion + ", name=" + name + '}';
    }

    @Override
    public String generarInsertSQL() {
       return "INSERT INTO dbo.DetalleAccesoPlantilla (idPlantillaAccesoDatos, idEstructuraEstrategia, cadenaFiltradoNivel, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion,name) values(?,?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idPlantillaAccesoDatos, idEstructuraEstrategia, cadenaFiltradoNivel, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion,name};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE dbo.DetalleAccesoPlantilla SET idPlantillaAccesoDatos=?, idEstructuraEstrategia=?, cadenaFiltradoNivel=?  WHERE idDetalleAccesoDatos=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idPlantillaAccesoDatos, idEstructuraEstrategia, cadenaFiltradoNivel,idDetalleAccesoDatos};
    }
}
