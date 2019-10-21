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
public class SecurityRole implements PersistenciaDao{

    private Integer idRole;
    private String role;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;
    private Integer activo;
    
    
    public SecurityRole() {
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
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
        if (!(object instanceof SecurityRole)) {
            return false;
        }
        SecurityRole other = (SecurityRole) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return "SecurityRole{" + "role=" + role + ", estadoDeEliminacion=" + estadoDeEliminacion + ", fechaDeInsercion=" + fechaDeInsercion + ", fechaDeModificacion=" + fechaDeModificacion + ", fechaDeEliminacion=" + fechaDeEliminacion + ", activo=" + activo + ", name=" + name + '}';
    }
    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.security_role (role, estadoDeEliminacion, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, activo, name) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{role, estadoDeEliminacion, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, activo, name};
    }

    @Override
    public String generarUpdateSQL() {
        return "update dbo.security_role set role=?, estadoDeEliminacion=?, fecha_insercion=?, fecha_modificacion=?, fecha_eliminacion=?, activo=?, name=? where idRole = ?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{role, estadoDeEliminacion, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, activo, name};
    }
}
