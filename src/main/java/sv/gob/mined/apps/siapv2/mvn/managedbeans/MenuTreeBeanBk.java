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
@Component(value = "menuTreeBeanBk")
@Scope(value = "view")
public class MenuTreeBeanBk implements Serializable {

    private TreeNode root;
    private TreeNode rootBn;
    private TreeNode selected;

    public MenuTreeBeanBk() {
    }

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("root", null);
        TreeNode node = new DefaultTreeNode("1. POA, Reprogramaciones, Roles", root);
        TreeNode node0 = new DefaultTreeNode(new OpcionMenu("1.1. Planes Globales", "/app/regPlanGlobal2.jsf"), node);
        TreeNode node1 = new DefaultTreeNode(new OpcionMenu("1.2. Reprogramaciones POA", "/app/regReprogramacion.jsf"), node);
        TreeNode node2 = new DefaultTreeNode(new OpcionMenu("1.3. Roles", ""), node);
        TreeNode node3 = new DefaultTreeNode(new OpcionMenu("1.3.1. Admin. de Autorizacion", "/app/roles/regRoles.jsf"), node2);
        TreeNode node4 = new DefaultTreeNode(new OpcionMenu("1.3.2. Admin. de Acceso a Datos", "/app/roles/regRolesAutorizacion.jsf"), node2);
        TreeNode node34 = new DefaultTreeNode(new OpcionMenu("1.3.3. Verificacion", "/app/verificacion.jsf"), node2);
        node.setExpanded(true);
        node2.setExpanded(true);

        TreeNode node5 = new DefaultTreeNode(new OpcionMenu("2. Translado de Oferentes", "/app/control/oferentes/regTrasladoOferentes.jsf"), root);
        TreeNode node6 = new DefaultTreeNode(new OpcionMenu("3. Control de Contratistas", ""), root);
        TreeNode node7 = new DefaultTreeNode(new OpcionMenu("3.1. Registro de Usuarios", "/app/control/oferentes/regUsuario.jsf"), node6);
        TreeNode node8 = new DefaultTreeNode(new OpcionMenu("3.2. Registro de Calificaciones", "/app/control/oferentes/regCalificacion.jsf"), node6);
        TreeNode node9 = new DefaultTreeNode(new OpcionMenu("3.3. Registro de Multas", "/app/control/oferentes/regMultas.jsf"), node6);
        TreeNode node10 = new DefaultTreeNode(new OpcionMenu("3.4. Registro de Garantías", "/app/control/oferentes/regGarantias.jsf"), node6);
        TreeNode node11 = new DefaultTreeNode(new OpcionMenu("3.5. Registro de Rescisiones", "/app/control/oferentes/regRescisiones.jsf"), node6);
        node6.setExpanded(true);
        
        TreeNode node12 = new DefaultTreeNode(new OpcionMenu("4. Reportes Control Oferentes", ""), root);
        TreeNode node13 = new DefaultTreeNode(new OpcionMenu("4.1. Control de Garantias", ""), node12);
        TreeNode node14 = new DefaultTreeNode(new OpcionMenu("4.2. Control de Rescisiones", ""), node12);
        node12.setExpanded(true);
        
        TreeNode node30 = new DefaultTreeNode(new OpcionMenu("5. Consultas Control Oferentes", ""), root);
        TreeNode node31 = new DefaultTreeNode(new OpcionMenu("5.1. Oferentes Registrados", "/app/consultas/oferentesRegistrados.jsf"), node30);
        TreeNode node32 = new DefaultTreeNode(new OpcionMenu("5.2. Oferentes Contratados", "/app/consultas/oferentesContratados.jsf"), node30);
        TreeNode node33 = new DefaultTreeNode(new OpcionMenu("5.3. Busqueda de Oferentes", "/app/consultas/busquedaOferentes.jsf"), node30);
        node30.setExpanded(true);
        
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
