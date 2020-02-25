/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

import java.util.Date;
import java.util.Objects;
import sv.gob.mined.apps.siapv2.mvn.dao.PersistenciaDao;

/**
 *
 * @author Infosoft
 */
public class SecurityUserGroup implements PersistenciaDao{

    private Integer idUserGroup;
    private String userName;
    private Integer idGrupo;
    private Date fechaDeInsercion;
    private Date fechaDeModificacion;
    private Date fechaDeEliminacion;
    private Integer estadoDeEliminacion;
    private String name;
    private Integer activo;

    public SecurityUserGroup() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
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

    public Integer getIdUserGroup() {
        return idUserGroup;
    }

    public void setIdUserGroup(Integer idUserGroup) {
        this.idUserGroup = idUserGroup;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SecurityUserGroup other = (SecurityUserGroup) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.idGrupo, other.idGrupo)) {
            return false;
        }
        return true;
    }

   
     @Override
    public String toString() {
        return "security_user_group{" + "idGrupo=" + idGrupo + ", userName=" + userName + ", fechaDeInsercion=" + fechaDeInsercion + ", fechaDeModificacion=" + fechaDeModificacion + ", fechaDeEliminacion=" + fechaDeEliminacion + ", estadoDeEliminacion=" + estadoDeEliminacion + ", name=" + name + '}';
    }
    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO dbo.security_user_group (idGrupo, userName, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idGrupo, userName, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name};
    }

    @Override
    public String generarUpdateSQL() {
        return "update dbo.security_user_group set idGrupo=?, userName=?, fechaDeInsercion=?, fechaDeModificacion=?, fechaDeEliminacion=?, estadoDeEliminacion=?, name=? where id_user_group = ?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idGrupo, userName, fechaDeInsercion, fechaDeModificacion, fechaDeEliminacion, estadoDeEliminacion, name};
    }
}
