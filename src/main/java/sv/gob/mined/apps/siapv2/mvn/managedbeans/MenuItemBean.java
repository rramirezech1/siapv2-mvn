/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import sv.gob.mined.apps.siapv2.mvn.modelo.PermisoOpcionMenu;
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
    private PermisoOpcionMenu currentPermisoOpcionMenu = new PermisoOpcionMenu();
    private Boolean deshabilitado = true;
    private Boolean deshabilitadoEliminar = true;
    private Boolean deshabilitadoEstado = false;
    private List<OpcionMenu> lstOpcionMenu;
    private List<PermisoOpcionMenu> lstPermisoOpcionMenu;
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
        lstPermisoOpcionMenu = rolBo.getLstPermisoOpcionMenu();
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

    public void guardarPermisoOpcionMenu() {
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

    public void nuevoPermisoOpcionMenu() {
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

    public List<PermisoOpcionMenu> getLstPermisoOpcionMenu() {
        return lstPermisoOpcionMenu;
    }

    public void setLstPermisoOpcionMenu(List<PermisoOpcionMenu> lstPermisoOpcionMenu) {
        this.lstPermisoOpcionMenu = lstPermisoOpcionMenu;
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

    public PermisoOpcionMenu getCurrentPermisoOpcionMenu() {
        return currentPermisoOpcionMenu;
    }

    public void setCurrentPermisoOpcionMenu(PermisoOpcionMenu currentPermisoOpcionMenu) {
        this.currentPermisoOpcionMenu = currentPermisoOpcionMenu;
    }

    public List<OpcionMenu> getLstOpcionMenuPadre() {
        return lstOpcionMenuPadre;
    }

    public void setLstOpcionMenuPadre(List<OpcionMenu> lstOpcionMenuPadre) {
        this.lstOpcionMenuPadre = lstOpcionMenuPadre;
    }

    private static Book createBook(String[] attributes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static List<OpcionMenu> cargaOpcionMenuFromCSV(String fileName) {
        List<OpcionMenu> lstOpcionMenu = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
            String line = br.readLine(); 
            while (line != null) { 
                String[] attributes = line.split(",");
                OpcionMenu opcionmenu = creaOpcionMenu(attributes); 
                line = br.readLine(); 
            } 
        } catch (IOException ioe) { 
            ioe.printStackTrace();
        } 
        
        return lstOpcionMenu;
    }
    
    private static OpcionMenu creaOpcionMenu(String[] metadata) {
        
        String nombre = metadata[0];   
        String label = metadata[1];
        Integer padre = Integer.parseInt(metadata[2]); 
        String author = metadata[2]; 

        // create and return book of this metadata 
        return new OpcionMenu (); 
        
    }


}
