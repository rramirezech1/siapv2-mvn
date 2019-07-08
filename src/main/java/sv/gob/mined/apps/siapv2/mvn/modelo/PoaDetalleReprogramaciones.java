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
public class PoaDetalleReprogramaciones implements PersistenciaDao {

    private Integer poaDetalleReprogramacion;
    private Integer poaReprogramacion;
    private Integer recurso;
    private Integer reprogramacion;
    private Integer movimiento;
    private Integer organismo;
    private Integer tabla;
    private String operacion;
    private BigDecimal monto;
    private Integer idItem;
    private Integer goes;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;
    private Integer actividad;
    private String categoria;
    private String clasificadorPresupuestario;
    private PoaRecursos poaRecurso;

    public PoaDetalleReprogramaciones() {
    }

    public PoaDetalleReprogramaciones(Integer poaDetalleReprogramacion) {
        this.poaDetalleReprogramacion = poaDetalleReprogramacion;
    }

    public Integer getPoaDetalleReprogramacion() {
        return poaDetalleReprogramacion;
    }

    public void setPoaDetalleReprogramacion(Integer poaDetalleReprogramacion) {
        this.poaDetalleReprogramacion = poaDetalleReprogramacion;
    }

    public Integer getReprogramacion() {
        return reprogramacion;
    }

    public void setReprogramacion(Integer reprogramacion) {
        this.reprogramacion = reprogramacion;
    }

    public Integer getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Integer movimiento) {
        this.movimiento = movimiento;
    }

    public Integer getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Integer organismo) {
        this.organismo = organismo;
    }

    public Integer getTabla() {
        return tabla;
    }

    public void setTabla(Integer tabla) {
        this.tabla = tabla;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getGoes() {
        return goes;
    }

    public void setGoes(Integer goes) {
        this.goes = goes;
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

    public Integer getPoaReprogramacion() {
        return poaReprogramacion;
    }

    public void setPoaReprogramacion(Integer poaReprogramacion) {
        this.poaReprogramacion = poaReprogramacion;
    }

    public Integer getRecurso() {
        return recurso;
    }

    public void setRecurso(Integer recurso) {
        this.recurso = recurso;
    }

    public Integer getActividad() {
        return actividad;
    }

    public void setActividad(Integer actividad) {
        this.actividad = actividad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClasificadorPresupuestario() {
        return clasificadorPresupuestario;
    }

    public void setClasificadorPresupuestario(String clasificador_presupuestario) {
        this.clasificadorPresupuestario = clasificador_presupuestario;
    }

    public PoaRecursos getPoaRecurso() {
        if (poaRecurso == null) {
            poaRecurso = new PoaRecursos();
        }
        return poaRecurso;
    }

    public void setPoaRecurso(PoaRecursos poaRecurso) {
        this.poaRecurso = poaRecurso;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.poa_detalle_reprogramaciones (poa_reprogramacion, recurso, reprogramacion, movimiento, organismo, tabla, operacion, monto, id_item, goes, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, \"name\") "
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{poaReprogramacion, recurso, reprogramacion, movimiento, organismo, tabla, operacion, monto, idItem, goes, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE dbo.poa_detalle_reprogramaciones SET poa_reprogramacion=?, recurso=?, reprogramacion=?, movimiento=?, organismo=?, tabla=?, operacion=?, monto=?, id_item=?, goes=?, fechaDeInsercion=?, fechaDeModificacion=?, fechaDeEliminacion=?, estadoDeEliminacion=?, \"name\"=? "
                + "WHERE poa_detalle_reprogramacion=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{poaReprogramacion, recurso, reprogramacion, movimiento, organismo, tabla, operacion, monto, idItem, goes, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name, poaDetalleReprogramacion};
    }
}
