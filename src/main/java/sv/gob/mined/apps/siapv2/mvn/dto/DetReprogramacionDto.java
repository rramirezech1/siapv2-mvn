/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dto;

import java.math.BigDecimal;

/**
 *
 * @author Infosoft
 */
public class DetReprogramacionDto {

    private Integer id;
    private Integer poaReprogramacion;
    private Integer actividad;
    private String numActividad;
    private Integer recurso;
    private String descripcion;
    private Integer idCategoria;
    private String categoria;
    private String clasificadorPresupuestario;
    private String operacion;
    private Integer saldoRecursoFtePpal;
    private Integer saldoRecursoFteCont;
    private BigDecimal montoPrograFtePpal = BigDecimal.ZERO;
    private BigDecimal montoPrograFteCont = BigDecimal.ZERO;
    private BigDecimal montoReproFtePpal = BigDecimal.ZERO;
    private BigDecimal montoReproFteCont = BigDecimal.ZERO;

    public DetReprogramacionDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoaReprogramacion() {
        return poaReprogramacion;
    }

    public void setPoaReprogramacion(Integer poaReprogramacion) {
        this.poaReprogramacion = poaReprogramacion;
    }

    public Integer getActividad() {
        return actividad;
    }

    public void setActividad(Integer actividad) {
        this.actividad = actividad;
    }

    public String getNumActividad() {
        return numActividad;
    }

    public void setNumActividad(String numActividad) {
        this.numActividad = numActividad;
    }

    public Integer getRecurso() {
        return recurso;
    }

    public void setRecurso(Integer recurso) {
        this.recurso = recurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
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

    public void setClasificadorPresupuestario(String clasificadorPresupuestario) {
        this.clasificadorPresupuestario = clasificadorPresupuestario;
    }

    public Integer getSaldoRecursoFtePpal() {
        return saldoRecursoFtePpal;
    }

    public void setSaldoRecursoFtePpal(Integer saldoRecursoFtePpal) {
        this.saldoRecursoFtePpal = saldoRecursoFtePpal;
    }

    public Integer getSaldoRecursoFteCont() {
        return saldoRecursoFteCont;
    }

    public void setSaldoRecursoFteCont(Integer saldoRecursoFteCont) {
        this.saldoRecursoFteCont = saldoRecursoFteCont;
    }

    public BigDecimal getMontoReproFtePpal() {
        return montoReproFtePpal;
    }

    public void setMontoReproFtePpal(BigDecimal montoReproFtePpal) {
        this.montoReproFtePpal = montoReproFtePpal;
    }

    public BigDecimal getMontoReproFteCont() {
        return montoReproFteCont;
    }

    public void setMontoReproFteCont(BigDecimal montoReproFteCont) {
        this.montoReproFteCont = montoReproFteCont;
    }

    public BigDecimal getMontoPrograFtePpal() {
        return montoPrograFtePpal;
    }

    public void setMontoPrograFtePpal(BigDecimal montoPrograFtePpal) {
        this.montoPrograFtePpal = montoPrograFtePpal;
    }

    public BigDecimal getMontoPrograFteCont() {
        return montoPrograFteCont;
    }

    public void setMontoPrograFteCont(BigDecimal montoPrograFteCont) {
        this.montoPrograFteCont = montoPrograFteCont;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
}