/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;

/**
 *
 * @author Infosoft
 */
@Component(value = "menuItemBean")
@Scope(value = "view")
public class MenuItemBean implements Serializable {

    
    public MenuItemBean() {
    }

    @PostConstruct
    public void init() {
        
    }

    
     public void logout() {
         FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
     }
}
