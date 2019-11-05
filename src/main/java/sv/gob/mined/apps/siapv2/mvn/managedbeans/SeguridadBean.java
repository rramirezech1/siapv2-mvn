/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.SeguridadBo;
import sv.gob.mined.apps.siapv2.mvn.bo.UsuarioBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;
import sv.gob.mined.apps.siapv2.mvn.sessionbeans.VariablesSession;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;

/**
 *
 * @author infosoft
 */
@Component(value = "seguridadBean")
@Scope(value = "view")
public class SeguridadBean implements Serializable{

    private SecurityGroup currentGrupo;
    private SecurityUserGroup currentUsuarioGrupo;
    private Boolean deshabilitado = true;
    private Boolean deshabilitadoEliminar = true;
    private Boolean deshabilitadoEstado = false;
    private List<SecurityGroup> lstGrupo;
    private List<SecurityUserGroup> lstUsuarioGrupo;
    
    @Autowired
    private SeguridadBo seguridadBo;
    @Autowired
    private UsuarioBo usuBo;
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Inject
    private VariablesSession variablesSession;

    /**
     * Creates a new instance of RolesBean
     */
    public SeguridadBean() {
    }

    @PostConstruct
    public void init() {
        lstGrupo = seguridadBo.getLstSecurityGroup();
        lstUsuarioGrupo = seguridadBo.getLstSecurityUserGroup();
    }
    
    public void nuevoGrupo() {
        nuevo();
        currentGrupo = new SecurityGroup();
    }
    
    public void nuevoUsuarioGrupo() {
        nuevo();
        currentUsuarioGrupo = new SecurityUserGroup();
    }

    private void nuevo() {
        deshabilitado = false;
        deshabilitadoEliminar = true;
        deshabilitadoEstado = true;
    }

    public void guardarGrupo() {
        Boolean valido;

        if (currentGrupo != null) {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtCodGrupo", InputText.class, currentGrupo.getCodigoGrupo());
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtGrupo", InputText.class, currentGrupo.getGrupo()) && valido;
        } else {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtIdGrupo", InputText.class, null);
        }

        if (currentGrupo != null && valido == true) {
            currentGrupo.setName(variablesSession.getUsuario());
            seguridadBo.saveSecurityGroup(currentGrupo);
            lstGrupo = seguridadBo.getLstSecurityGroup();

            if (currentGrupo.getIdGrupo()== null) {
                currentGrupo = new SecurityGroup();
                deshabilitadoEliminar = true;
            } else {
                deshabilitadoEliminar = false;
            }

            JsfUtil.addSuccessMessage("El registro ha sido guardado");
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }
    
    public void guardarUsuarioGrupo() {
        Boolean valido;

        if (currentGrupo != null) {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "cbGrupo", SelectOneMenu.class, currentUsuarioGrupo.getIdGrupo());
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtUsuario", InputText.class, currentUsuarioGrupo.getUserName()) && valido;
        } else {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtIdGrupo", InputText.class, null);
        }

        if (currentGrupo != null && valido == true) {
            currentGrupo.setName(variablesSession.getUsuario());
            seguridadBo.saveSecurityGroup(currentGrupo);
            lstGrupo = seguridadBo.getLstSecurityGroup();

            if (currentGrupo.getIdGrupo()== null) {
                currentGrupo = new SecurityGroup();
                deshabilitadoEliminar = true;
            } else {
                deshabilitadoEliminar = false;
            }

            JsfUtil.addSuccessMessage("El registro ha sido guardado");
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }
    
    public void onRowSelectGrupo(SelectEvent event) {
        SecurityGroup grupo = seguridadBo.buscarSecurityGroupById(((SecurityGroup) event.getObject()).getIdGrupo());
        if (grupo != null) {
            deshabilitadoEliminar = false;
            deshabilitado = false;
            currentGrupo = grupo;
        }
    }
    
     public void onRowSelectUsuarioGrupo(SelectEvent event) {
        SecurityGroup grupo = seguridadBo.buscarSecurityGroupById(((SecurityGroup) event.getObject()).getIdGrupo());
        if (grupo != null) {
            deshabilitadoEliminar = false;
            currentGrupo = grupo;
        }
    }
    
    public SecurityGroup getCurrentGrupo() {
        return currentGrupo;
    }

    public void setCurrentGrupo(SecurityGroup currentGrupo) {
        this.currentGrupo = currentGrupo;
    }

    public SecurityUserGroup getCurrentUsuarioGrupo() {
        return currentUsuarioGrupo;
    }

    public void setCurrentUsuarioGrupo(SecurityUserGroup currentUsuarioGrupo) {
        this.currentUsuarioGrupo = currentUsuarioGrupo;
    }
    
    

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public Boolean getDeshabilitadoEliminar() {
        return deshabilitadoEliminar;
    }

    public void setDeshabilitadoEliminar(Boolean deshabilitadoEliminar) {
        this.deshabilitadoEliminar = deshabilitadoEliminar;
    }

    public Boolean getDeshabilitadoEstado() {
        return deshabilitadoEstado;
    }

    public void setDeshabilitadoEstado(Boolean deshabilitadoEstado) {
        this.deshabilitadoEstado = deshabilitadoEstado;
    }

    public List<SecurityGroup> getLstGrupo() {
        return lstGrupo;
    }

    public void setLstGrupo(List<SecurityGroup> lstGrupo) {
        this.lstGrupo = lstGrupo;
    }

    public List<SecurityUserGroup> getLstUsuarioGrupo() {
        return lstUsuarioGrupo;
    }

    public void setLstUsuarioGrupo(List<SecurityUserGroup> lstUsuarioGrupo) {
        this.lstUsuarioGrupo = lstUsuarioGrupo;
    }
    
}
