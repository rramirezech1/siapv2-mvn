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
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.SeguridadBo;
import sv.gob.mined.apps.siapv2.mvn.bo.UsuarioBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;
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
    private List<OpcionMenu> listOpcionMenu = new ArrayList();
    private List<OpcionMenu> listOpcionMenuPrincipal = new ArrayList();
    private List<OpcionMenu> listOpcionSubMenu = new ArrayList();
    private List<SecurityUserGroup> listGrupos = new ArrayList();
    private Usuario us;
    private boolean estadoMenu = true;
    @Inject
    private UsuarioBo usuBo;

    @Inject
    private VariablesSession variablesSession;

    @Autowired
    private SeguridadBo seguridad;

    @PostConstruct
    public void init() {

        model = new DefaultMenuModel();
        usuario = variablesSession.getUsuario();

        try {
            listGrupos = seguridad.getLstSecurityUserGroupByUser(usuario);
            listOpcionMenu = seguridad.getLstSecurityGroupOpciones(listGrupos); 
            creaMenu();
        } catch (Exception e) {
            estadoMenu = false;
        }
    }

    public void creaMenu() {

        //      DefaultMenuItem item = DefaultMenuItem.builder().value(opcion.getLabelOpcionMenu()).build();
        for (OpcionMenu a : listOpcionMenu) {

            if (a.getIdOpcionPadre() == null) {
                listOpcionMenuPrincipal.add(a);
                //System.out.println("Estoy en el menu principal: " + a.getNombreOpcionMenu());
            } else if (a.getIdOpcionPadre() != null) {
                listOpcionSubMenu.add(a);
                //System.out.println("Estoy en el Submenu: " + a.getNombreOpcionMenu());
            }
        }
        establecerPermisos();

    }

    public void establecerPermisos() {
        model = new DefaultMenuModel();
        DefaultMenuModel menu = new DefaultMenuModel();
        DefaultSubMenu subMenu;
        DefaultMenuItem itemMenu;

        for (OpcionMenu a : listOpcionMenuPrincipal) {
            //System.out.println("El primero for a consultar: " + a.getIdOpcionMenu());
            if (!listOpcionMenuPrincipal.isEmpty()) {
                subMenu = new DefaultSubMenu();
                subMenu.setLabel(a.getNombreOpcionMenu());
                subMenu.setId(a.getNombreOpcionMenu());

                //creamos los items del submenu
                for (OpcionMenu b : listOpcionSubMenu) {
                    //System.out.println("El segundo for a consultar: " + b.getIdOpcionPadre());
                    itemMenu = new DefaultMenuItem();
                    if (a.getIdOpcionMenu() == b.getIdOpcionPadre()) {

                        itemMenu.setValue(b.getNombreOpcionMenu());
                        itemMenu.setUrl(b.getDireccionAccesoOpcion());// + "?faces-redirect=true");
                        //itemMenu.setUrl(obj2[4].toString() + "?faces-redirect=true");
                        //itemMenu.setIcon(datos[4] != null ? datos[4].toString() : null);
                        itemMenu.setAjax(true);
                        subMenu.getElements().add((DefaultMenuItem) itemMenu);
                    }
                    //itemMenu.setId(b.getNombreOpcionMenu());

                }
                menu.addElement((DefaultSubMenu) subMenu);
            }

        }
        model = menu;
    }

    public Integer usuarioProcesa() {

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public UsuarioBo getUsuBo() {
        return usuBo;
    }

    public void setUsuBo(UsuarioBo usuBo) {
        this.usuBo = usuBo;
    }

    public List<OpcionMenu> getListOpcionMenu() {
        return listOpcionMenu;
    }

    public void setListOpcionMenu(List<OpcionMenu> listOpcionMenu) {
        this.listOpcionMenu = listOpcionMenu;
    }

    public List<OpcionMenu> getListOpcionMenuPrincipal() {
        return listOpcionMenuPrincipal;
    }

    public void setListOpcionMenuPrincipal(List<OpcionMenu> listOpcionMenuPrincipal) {
        this.listOpcionMenuPrincipal = listOpcionMenuPrincipal;
    }

    public List<OpcionMenu> getListOpcionSubMenu() {
        return listOpcionSubMenu;
    }

    public void setListOpcionSubMenu(List<OpcionMenu> listOpcionSubMenu) {
        this.listOpcionSubMenu = listOpcionSubMenu;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public boolean isEstadoMenu() {
        return estadoMenu;
    }

    public void setEstadoMenu(boolean estadoMenu) {
        this.estadoMenu = estadoMenu;
    }

}
