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
public class SecurityGroup implements PersistenciaDao{

    private Integer idGrupo;
    private String grupo;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;
    private Integer activo;
    
    
    public SecurityGroup() {
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
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

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getEstadoDeEliminacion() {
        return this.estadoDeEliminacion;
    }

    @Override
    public void setEstadoDeEliminacion(Integer valor) {
        this.estadoDeEliminacion = valor;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecurityGroup)) {
            return false;
        }
        SecurityGroup other = (SecurityGroup) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return "SecurityGroup{" + "grupo=" + grupo + ", fecha_insercion=" + fechaDeInsercion + ", fechaModificacion=" + fechaDeModificacion + ", fecha_eliminacion=" + fechaDeEliminacion + ", activo=" + activo + ", name=" + name + '}';
    }
    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.security_group (grupo, fecha_insercion, fecha_modificacion, fecha_eliminacion, activo, name) VALUES (?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{grupo, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, activo, name};
    }

    @Override
    public String generarUpdateSQL() {
        return "update dbo.security_group set grupo=?, fecha_insercion=?, fecha_modificacion=?, fecha_eliminacion=?, activo=?, name=? where id_grupo = ?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{grupo, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, activo, name};
    }
}
