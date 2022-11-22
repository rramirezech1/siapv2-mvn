/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.SeguridadBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.sessionbeans.VariablesSession;

/**
 *
 * @author Infosoft
 */
@Component(value = "menuTreeBean")
@Scope(value = "view")
public class MenuTreeBean implements Serializable {

    private MenuModel model;
    private String usuario;
    private List <OpcionMenu> listOpcionMenu = new ArrayList();
    
    
    @Inject
    private VariablesSession variablesSession;
    
    @Autowired
    private SeguridadBo seguridad;
    
    

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        usuario = variablesSession.getUsuario();
        
        listOpcionMenu = seguridad.getLstOpcionMenu();
        creaMenu();
        
    }

    public void creaMenu(){
    
  //      DefaultMenuItem item = DefaultMenuItem.builder().value(opcion.getLabelOpcionMenu()).build();
        
    }
    
    public Integer usuarioProcesa(){
    
        return 1;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
    }
}
