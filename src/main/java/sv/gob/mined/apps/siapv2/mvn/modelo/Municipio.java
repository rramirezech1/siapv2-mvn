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
public class Municipio {

    private Integer id_municipio;
    private String codigo_municipio;
    private String codigo_departamento;
    private String nombre;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String identificadorDeLaSesion;

    public Municipio() {
    }

    public String getCodigo_departamento() {
        return codigo_departamento;
    }

    public void setCodigo_departamento(String codigo_departamento) {
        this.codigo_departamento = codigo_departamento;
    }

    public String getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(String codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public Integer getEstadoDeEliminacion() {
        return estadoDeEliminacion;
    }

    public void setEstadoDeEliminacion(Integer estadoDeEliminacion) {
        this.estadoDeEliminacion = estadoDeEliminacion;
    }

    public Date getFechaDeEliminacion() {
        return fechaDeEliminacion;
    }

    public void setFechaDeEliminacion(Date fechaDeEliminacion) {
        this.fechaDeEliminacion = fechaDeEliminacion;
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

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getIdentificadorDeLaSesion() {
        return identificadorDeLaSesion;
    }

    public void setIdentificadorDeLaSesion(String identificadorDeLaSesion) {
        this.identificadorDeLaSesion = identificadorDeLaSesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
