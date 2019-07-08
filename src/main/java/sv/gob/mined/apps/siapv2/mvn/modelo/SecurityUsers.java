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
public class SecurityUsers {

    private String name;
    private Integer idUserSeguridad;
    private String description;
    private Integer priority;
    private Integer user_type;
    private String password;
    private Date vencimiento;
    private Integer activo;
    private String usuario_crea;
    private Date fecha_actualizacion;
    private Integer tipo_acceso_datos;
    private String numeroDeDui;
    private String identificadorDeLaSesion;
    private Integer identificadorDeLaPersona;
    private Integer personalid;

//    private String name;
//    private String description;
//    private Integer user_type;
//    private String password;
//    private Date vencimiento;
//    private Integer activo;
//    private String usuario_crea;
//    private Date fecha_actualizacion;
//    private Integer tipo_acceso_datos;
//    private String numeroDeDui;
//    private String identificadorDeLaSesion;
//    private Integer identificadorDeLaPersona;
//    private Integer personalid;
    public SecurityUsers() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getUsuario_crea() {
        return usuario_crea;
    }

    public void setUsuario_crea(String usuario_crea) {
        this.usuario_crea = usuario_crea;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Integer getTipo_acceso_datos() {
        return tipo_acceso_datos;
    }

    public void setTipo_acceso_datos(Integer tipo_acceso_datos) {
        this.tipo_acceso_datos = tipo_acceso_datos;
    }

    public String getNumeroDeDui() {
        return numeroDeDui;
    }

    public void setNumeroDeDui(String numeroDeDui) {
        this.numeroDeDui = numeroDeDui;
    }

    public String getIdentificadorDeLaSesion() {
        return identificadorDeLaSesion;
    }

    public void setIdentificadorDeLaSesion(String identificadorDeLaSesion) {
        this.identificadorDeLaSesion = identificadorDeLaSesion;
    }

    public Integer getIdentificadorDeLaPersona() {
        return identificadorDeLaPersona;
    }

    public void setIdentificadorDeLaPersona(Integer identificadorDeLaPersona) {
        this.identificadorDeLaPersona = identificadorDeLaPersona;
    }

    public Integer getPersonalid() {
        return personalid;
    }

    public void setPersonalid(Integer personalid) {
        this.personalid = personalid;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIdUserSeguridad() {
        return idUserSeguridad;
    }

    public void setIdUserSeguridad(Integer idUserSeguridad) {
        this.idUserSeguridad = idUserSeguridad;
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
        if (!(object instanceof SecurityUsers)) {
            return false;
        }
        SecurityUsers other = (SecurityUsers) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Criteria: " + description;
    }
}
