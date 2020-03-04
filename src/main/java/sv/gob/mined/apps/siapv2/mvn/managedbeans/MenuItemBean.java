/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.RolesBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;
import sv.gob.mined.apps.siapv2.mvn.sessionbeans.VariablesSession;


/**
 *
 * @author Infosoft
 */
@Component(value = "menuItemBean")
@Scope(value = "view")
public class MenuItemBean implements Serializable {

    private OpcionMenu currentOpcionMenu = new OpcionMenu();
    private Boolean deshabilitado = true;
    private Boolean deshabilitadoEliminar = true;
    private Boolean deshabilitadoEstado = false;
    private List<OpcionMenu> lstOpcionMenu;
    private List<OpcionMenu> lstOpcionMenuPadre;
    
    
    @Autowired
    private RolesBo rolBo;
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Inject
    private VariablesSession variablesSession;
    
    public MenuItemBean() {
    }

    @PostConstruct
    public void init() {
         lstOpcionMenu = rolBo.getLstOpcionMenu();
         lstOpcionMenuPadre = rolBo.getLstOpcionMenuPadre();
    }
   
    
    public void guardarOpcionMenu() {
        Boolean valido;

        if (currentOpcionMenu != null) {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtLabelOpcion", InputText.class, currentOpcionMenu.getLabelOpcionMenu());
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtNombreOpcion", InputText.class, currentOpcionMenu.getNombreOpcionMenu()) && valido;          
        } else {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtNombreOpcion", InputText.class, null);
        }
        
        

        if (currentOpcionMenu != null && valido == true) {
            currentOpcionMenu.setName(variablesSession.getUsuario());
            rolBo.saveOpcionMenu(currentOpcionMenu);
            lstOpcionMenu = rolBo.getLstOpcionMenu();

            if (currentOpcionMenu.getIdOpcionMenu() != null) {
                nuevoOpcionMenu();
                deshabilitadoEliminar = true;
            } else {
                deshabilitadoEliminar = false;
            }

            JsfUtil.addSuccessMessage("El registro ha sido guardado");
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }
    
     public void nuevoOpcionMenu() {
        nuevo();
        currentOpcionMenu = new OpcionMenu();
    }
     
    private void nuevo() {
        deshabilitado = false;
        deshabilitadoEliminar = true;
        deshabilitadoEstado = true;
    }
    
     public void onRowSelectOpcionMenu(SelectEvent event) {
        OpcionMenu opcion = rolBo.buscarOpcionMenuById(((OpcionMenu) event.getObject()).getIdOpcionMenu());
        if (opcion != null) {
            deshabilitadoEliminar = false;
            currentOpcionMenu = opcion;
        }
    }
     
    public void logout() {
         FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
     }

    public List<OpcionMenu> getLstOpcionMenu() {
        return lstOpcionMenu;
    }

    public void setLstOpcionMenu(List<OpcionMenu> lstOpcionMenu) {
        this.lstOpcionMenu = lstOpcionMenu;
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public Boolean getDeshabilitadoEstado() {
        return deshabilitadoEstado;
    }

    public void setDeshabilitadoEstado(Boolean deshabilitadoEstado) {
        this.deshabilitadoEstado = deshabilitadoEstado;
    }

    public Boolean getDeshabilitadoEliminar() {
        return deshabilitadoEliminar;
    }

    public void setDeshabilitadoEliminar(Boolean deshabilitadoEliminar) {
        this.deshabilitadoEliminar = deshabilitadoEliminar;
    }

    public OpcionMenu getCurrentOpcionMenu() {
        return currentOpcionMenu;
    }

    public void setCurrentOpcionMenu(OpcionMenu currentOpcionMenu) {
        this.currentOpcionMenu = currentOpcionMenu;
    }

    public List<OpcionMenu> getLstOpcionMenuPadre() {
        return lstOpcionMenuPadre;
    }

    public void setLstOpcionMenuPadre(List<OpcionMenu> lstOpcionMenuPadre) {
        this.lstOpcionMenuPadre = lstOpcionMenuPadre;
    }
    
    
 }
