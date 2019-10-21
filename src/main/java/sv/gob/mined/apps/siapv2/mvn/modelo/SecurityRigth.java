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
public class SecurityRigth implements PersistenciaDao{

    private Integer idSecurityRigth;
    private String name;
    private Integer idGrupo;
    private Integer idRole;
    private Integer enable;
    private String userSet;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    
    
    
    public SecurityRigth() {
    }

    public Integer getIdSecurityRigth() {
        return idSecurityRigth;
    }

    public void setIdSecurityRigth(Integer idSecurityRigth) {
        this.idSecurityRigth = idSecurityRigth;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getUserSet() {
        return userSet;
    }

    public void setUserSet(String userSet) {
        this.userSet = userSet;
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

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecurityRigth)) {
            return false;
        }
        SecurityRigth other = (SecurityRigth) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return "SecurityRigth{" + "name=" + name + ", idGrupo=" + idGrupo + ", idRole=" + idRole + ", enable=" + enable + ", userSet=" + userSet + ", fechaDeInsercion=" + fechaDeInsercion + ", fechaDeModificacion=" + fechaDeModificacion + ", fechaDeEliminacion=" + fechaDeEliminacion + ", estadoDeEliminacion=" + estadoDeEliminacion + '}';
    }
    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.security_role (role, estadoDeEliminacion, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, activo, name) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{name, idGrupo, idRole, enable, userSet, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion};
    }

    @Override
    public String generarUpdateSQL() {
        return "update dbo.security_role set role=?, estadoDeEliminacion=?, fecha_insercion=?, fecha_modificacion=?, fecha_eliminacion=?, activo=?, name=? where idRole = ?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{name, idGrupo, idRole, enable, userSet, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion};
    }
}
