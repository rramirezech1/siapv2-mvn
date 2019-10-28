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
@Component(value = "menuTreeBean")
@Scope(value = "view")
public class MenuTreeBean implements Serializable {

    private TreeNode root;
    private TreeNode rootBn;
    private TreeNode selected;

    public MenuTreeBean() {
    }

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("root", null);
        
        TreeNode node0 = new DefaultTreeNode(new OpcionMenu("1. Control de Contratistas", "#"), root);
        TreeNode node1 = new DefaultTreeNode(new OpcionMenu("1.1. Registro de Usuarios", "/app/control/oferentes/regUsuario.jsf"), node0);
        TreeNode node2 = new DefaultTreeNode(new OpcionMenu("1.2. Registro de Calificaciones", "/app/control/oferentes/regCalificacion.jsf"), node0);
        TreeNode node3 = new DefaultTreeNode(new OpcionMenu("1.3. Registro de Multas", "/app/control/oferentes/regMultas.jsf"), node0);
        TreeNode node4 = new DefaultTreeNode(new OpcionMenu("1.4. Registro de Garantías", "/app/control/oferentes/regGarantias.jsf"), node0);
        TreeNode node5 = new DefaultTreeNode(new OpcionMenu("1.5. Registro de Rescisiones", "/app/control/oferentes/regRescisiones.jsf"), node0);       
        TreeNode node6 = new DefaultTreeNode(new OpcionMenu("2. Reportes Control Oferentes", "#"), root);
        TreeNode node7 = new DefaultTreeNode(new OpcionMenu("2.1. Control de Garantias", "/app/control/oferentes/regGarantias.jsf"), node6);
        TreeNode node8 = new DefaultTreeNode(new OpcionMenu("2.2. Control de Rescisiones", "/app/control/oferentes/regRescisiones.jsf"), node6);   
        TreeNode node9 = new DefaultTreeNode(new OpcionMenu("3. Consultas Control Oferentes", "#"), root);
        TreeNode node10 = new DefaultTreeNode(new OpcionMenu("3.1. Oferentes Registrados", "/app/consultas/oferentesRegistrados.jsf"), node9);
        TreeNode node11 = new DefaultTreeNode(new OpcionMenu("3.2. Oferentes Contratados", "/app/consultas/oferentesContratados.jsf"), node9);
        TreeNode node12 = new DefaultTreeNode(new OpcionMenu("3.3. Busqueda de Oferentes", "/app/consultas/busquedaOferentes.jsf"), node9);
        TreeNode node13 = new DefaultTreeNode(new OpcionMenu("4. Translado de Oferentes", "/app/control/oferentes/regTrasladoOferentes.jsf"), root);
        TreeNode node14 = new DefaultTreeNode("5. POA, Reprogramaciones", root);
        TreeNode node15 = new DefaultTreeNode(new OpcionMenu("5.1. Planes Globales", "/app/regPlanGlobal2.jsf"), node14);
        TreeNode node16 = new DefaultTreeNode(new OpcionMenu("5.2. Reprogramaciones POA", "/app/regReprogramacion.jsf"), node14);
        TreeNode node17 = new DefaultTreeNode(new OpcionMenu("6. Seguridad", "#"), root);
        TreeNode node18 = new DefaultTreeNode(new OpcionMenu("6.1. Admin. de Grupos", "/app/seguridad/regGrupo.jsf"), node17);
        TreeNode node19 = new DefaultTreeNode(new OpcionMenu("6.2. Asignacion Usuario-Grupos", "/app/seguridad/regUsuarioGrupo.jsf"), node17);
        TreeNode node20 = new DefaultTreeNode(new OpcionMenu("6.3. Admin. de Autorizacion", "/app/seguridad/regRoles.jsf"), node17);
        TreeNode node21 = new DefaultTreeNode(new OpcionMenu("6.4. Admin. de Acceso a Datos", "/app/seguridad/regRolesAutorizacion.jsf"), node17);
        TreeNode node22 = new DefaultTreeNode(new OpcionMenu("6.5. Verificacion", "/app/verificacion.jsf"), node17);
        node0.setExpanded(true);
        node6.setExpanded(true);
        node14.setExpanded(false);
        node17.setExpanded(false);
        
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getRootBn() {
        return rootBn;
    }

    public TreeNode getSelected() {
        return selected;
    }

    public void setSelected(TreeNode selected) {
        this.selected = selected;
    }

    public void onNodeSelect(NodeSelectEvent event) throws IOException {
        selected = event.getTreeNode();

        if (!(selected.getData() instanceof java.lang.String)) {
            if (((OpcionMenu) selected.getData()).getUrl().contains("http")) {
                RequestContext.getCurrentInstance().execute("abrirSisBO()");
            } else {
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), "null", ((OpcionMenu) selected.getData()).getUrl() + "?faces-redirect=true");
            }
        }
        
    }
     public void logout() {
         FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
     }
}
