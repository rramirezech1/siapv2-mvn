/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.RolesBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.ActorDelProceso;

import sv.gob.mined.apps.siapv2.mvn.modelo.GEstructuraOrganizativa;
import sv.gob.mined.apps.siapv2.mvn.modelo.MacroProcesos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillasDeSeguridad;
import sv.gob.mined.apps.siapv2.mvn.modelo.ProcedimientoTarea;
import sv.gob.mined.apps.siapv2.mvn.modelo.Procesos;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityInfo;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRolControl;

/**
 *
 * @author infosoft
 */
@Component(value = "rolesBean")
@Scope(value = "view")
public class RolesBean {

    private PlantillasDeSeguridad plantilla;
    private Boolean showEstructuraOp = false;
    private Boolean disabledpanelRoles = true;
    private Boolean showProcesosInst = false;
    private Boolean showPlantillaRoles = false;
    private Boolean showAsigRolesUsuarios = false;
    private Boolean showAsigRolesPorPlantilla = false;
    private Boolean showModificarPlantilla = false;
    private Integer nivelEdicion = 1;
    private List<MacroProcesos> listaMacroprocesos = new ArrayList<MacroProcesos>();
    private List<GEstructuraOrganizativa> listaGestructuraOrganizativa = new ArrayList<GEstructuraOrganizativa>();
    private List<ActorDelProceso> listaActorDelProceso = new ArrayList<ActorDelProceso>();
    private List<TipoRolControl> listaTipoRolControl = new ArrayList<TipoRolControl>();
    private List<PlantillasDeSeguridad> listaplantillasdeseguridad = new ArrayList<PlantillasDeSeguridad>();
    private List<Procesos> listaProcesos = new ArrayList<Procesos>();
    private List<ProcedimientoTarea> listaProcedimientoTarea = new ArrayList<ProcedimientoTarea>();
    private List<SecurityTemplate> listaSecurityTemplate = new ArrayList<SecurityTemplate>();
    private List<SecurityUsers> listadeUsuariosSource = new ArrayList<SecurityUsers>();
    private List<SecurityUsers> listadeUsuariosSelected = new ArrayList<SecurityUsers>();
    private List<SecurityUsers> listadeUsuarios = new ArrayList<SecurityUsers>();    
    private DualListModel<SecurityUsers> usuarios;
    private Integer idPlantilla;
    @Autowired
    private RolesBo rolesBo;

    /**
     * Creates a new instance of RolesBean
     */
    public RolesBean() {
        llenarValores();


    }

    @PostConstruct
    public void init() {


        this.listaMacroprocesos = rolesBo.getLstMacroProcesos();
        this.listaGestructuraOrganizativa = rolesBo.getLstGEstructuraOrganizativa();
        this.listaActorDelProceso = rolesBo.getLstActorDelProceso();
        this.listaTipoRolControl = rolesBo.getLstTipoRolControl();
        this.listaplantillasdeseguridad = rolesBo.getLstPlantillasDeSeguridad();
        this.listaProcesos = rolesBo.getLstProcesos();
        this.listaProcedimientoTarea = rolesBo.getLstProcedimientoTarea();
        this.listaSecurityTemplate = rolesBo.getLstSecurityTemplate();
        this.listadeUsuariosSource = rolesBo.getLstSecurityUser();

        llenarPickListUsuarios();
    }

    public List<SecurityUsers> complete(String query) {
        this.listadeUsuarios = new ArrayList<SecurityUsers>(0);
        this.listadeUsuarios = rolesBo.getLstSecurityUserByCriteria(query);        
        usuarios = new DualListModel<SecurityUsers>();
        usuarios = new DualListModel<SecurityUsers>(listadeUsuarios, listadeUsuariosSelected);
        RequestContext.getCurrentInstance().update("frmPrincipal:pickList");
        return listadeUsuarios;
    }
    public PlantillasDeSeguridad getSelectedPlantilla() {
        if (plantilla == null) {
            this.plantilla = new PlantillasDeSeguridad();
        }

        return plantilla;
    }

    private void llenarValores() {
        //llenarPickListUsuarios();
        //-------llenar entidad6------------------
    }

    public void modificarOnchange() {
        cerrarPopupModificarPlantilla();
    }

    public void cerrarPopupEstructuraOp() {
        nivelEdicion -= 1;
        showEstructuraOp = true;
    }

    public void cerrarPopupProcesosInst() {
        nivelEdicion -= 1;
        showProcesosInst = true;
    }

    public void cerrarPopupPlantillaRoles() {
        nivelEdicion -= 1;
        showPlantillaRoles = true;
    }

    public void newUsers() {
        //System.out.println("este es mi metodo");

        showPlantillaRoles = false;
        showAsigRolesPorPlantilla = true;
        //RequestContext.getCurrentInstance().execute("dlgAsigRolesPorPlantilla.show();");
        PrimeFaces.current().executeScript("PF('dlgAsigRolesPorPlantilla').show();");
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgDefPlantillaRoles");
    }

    public void cerrarPopupRolesUsuario() {
        nivelEdicion -= 1;
        showAsigRolesUsuarios = true;
    }

    public void cerrarPopupModificarPlantilla() {
        //this.listadeUsuariosSelected = this.usuarios.getTarget();
        nivelEdicion = 3;
        showPlantillaRoles = true;
        showModificarPlantilla = false;
        disabledpanelRoles = false;
        this.plantilla = rolesBo.getPlantillaModificacion(plantilla.getIdPlantillaSeguridad());
        this.listaSecurityTemplate = rolesBo.getListadosSecurityTemplateModificacion(plantilla.getIdPlantillaSeguridad());
        this.listadeUsuariosSelected = rolesBo.getListadoUsuariosPlantillaModificacion(plantilla.getIdPlantillaSeguridad());
        RequestContext.getCurrentInstance().update("frmPrincipal:tblRolesusuarios");
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgDefPlantillaRoles");
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgModificarPlantilla");
        //RequestContext.getCurrentInstance().execute("dlgDefPlantillaRoles.show();");
        PrimeFaces.current().executeScript("PF('dlgDefPlantillaRoles').show();");
    }

    public void cerrarPopupRolesPorPlantilla() {
        //this.listadeUsuariosSelected = this.usuarios.getTarget();
        nivelEdicion = 3;
        showPlantillaRoles = true;

        RequestContext.getCurrentInstance().update("frmPrincipal:tblRolesusuarios");
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgDefPlantillaRoles");
        //RequestContext.getCurrentInstance().execute("dlgDefPlantillaRoles.show();");
        PrimeFaces.current().executeScript("PF('dlgDefPlantillaRoles').show();");
    }

    private void llenarPickListUsuarios() {
        listadeUsuariosSelected = new ArrayList<SecurityUsers>();
        usuarios = new DualListModel<SecurityUsers>();
        usuarios = new DualListModel<SecurityUsers>(listadeUsuariosSource, listadeUsuariosSelected);
    }

    public void nuevo() {
        switch (nivelEdicion) {
            case 1:
                showEstructuraOp = true;

                break;
            case 2:

                break;
            case 3:
                this.listadeUsuariosSelected = new ArrayList<SecurityUsers>();
                this.plantilla = new PlantillasDeSeguridad();
                this.listaSecurityTemplate = new ArrayList<SecurityTemplate>();
                this.listaSecurityTemplate = rolesBo.getLstSecurityTemplate();
                this.disabledpanelRoles = false;
                showPlantillaRoles = true;
                RequestContext.getCurrentInstance().update("frmPrincipal:dlgDefPlantillaRoles");
                break;
        }
    }

    public void modificar() {
        showModificarPlantilla = true;
        this.listaplantillasdeseguridad = rolesBo.getLstPlantillasDeSeguridad();
        RequestContext.getCurrentInstance().update("frmPrincipal:cbmPlantillamodifi");
        //RequestContext.getCurrentInstance().execute("dlgModificarPlantilla.show();");
        PrimeFaces.current().executeScript("PF('dlgModificarPlantilla').show();");
    }

    public void guardar() {
        //System.out.println("nivel de edicion" + nivelEdicion);
        switch (nivelEdicion) {
            case 1:
                showEstructuraOp = true;

                break;
            case 2:

                break;
            case 3:
                Boolean edit = isEdicionOCreate();
                if (edit) {
                    rolesBo.deleteDetallesPlantillaPorModificacion(plantilla.getIdPlantillaSeguridad());
                }                
                List<SecurityInfo> lista = prepareCreateSecurityInfo(plantilla.getIdPlantillaSeguridad());
                Integer id = rolesBo.createPlantillaSeguridad(plantilla, edit);
                for (SecurityInfo securityInfo : lista) {
                    //System.out.println("securityInfo" + securityInfo.toString());
                    rolesBo.saveSecurityInfo(securityInfo);
                }

                break;
        }
    }

    private boolean isEdicionOCreate() {
        Boolean edit = true;
        Integer id = plantilla.getIdPlantillaSeguridad();
        if (id == null) {
            edit = false;
        }
        return edit;
    }

    private List<SecurityInfo> prepareCreateSecurityInfo(Integer id) {
        String valor = "0";
        List<SecurityInfo> lista = new ArrayList<SecurityInfo>();
        for (SecurityUsers usuariosselect : listadeUsuariosSelected) {

            for (SecurityTemplate entidadUno : listaSecurityTemplate) {
                SecurityInfo security = new SecurityInfo();
                security.setApplication(entidadUno.getApplication());
                security.setControl(entidadUno.getControl());
                security.setWindow(entidadUno.getWindow());
                security.setName(usuariosselect.getName());
                security.setIdPlantillaSeguridad(id);
                if (entidadUno.getStatus()) {
                    valor = "1";
                }
                security.setStatus(valor);
                lista.add(security);
                valor = "0";
            }

        }
        return lista;
    }

    public void onCellEditMeta(CellEditEvent event) {
        DataTable tbl = (DataTable) event.getSource();
        RequestContext.getCurrentInstance().update(tbl.getClientId(FacesContext.getCurrentInstance()) + ":" + event.getRowIndex() + ":modelInput");
        RequestContext.getCurrentInstance().update("frmPrincipal:tblProcedimientos:modelInput");

    }

    public DualListModel<SecurityUsers> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(DualListModel<SecurityUsers> usuarios) {
        this.usuarios = usuarios;
    }

    public Boolean getShowEstructuraOp() {
        return showEstructuraOp;
    }

    public void setShowEstructuraOp(Boolean showEstructuraOp) {
        this.showEstructuraOp = showEstructuraOp;
    }

    public Boolean getShowProcesosInst() {
        return showProcesosInst;
    }

    public void setShowProcesosInst(Boolean showProcesosInst) {
        this.showProcesosInst = showProcesosInst;
    }

    public Boolean getShowPlantillaRoles() {
        return showPlantillaRoles;
    }

    public void setShowPlantillaRoles(Boolean showPlantillaRoles) {
        this.showPlantillaRoles = showPlantillaRoles;
    }

    public Boolean getShowAsigRolesUsuarios() {
        return showAsigRolesUsuarios;
    }

    public void setShowAsigRolesUsuarios(Boolean showAsigRolesUsuarios) {
        this.showAsigRolesUsuarios = showAsigRolesUsuarios;
    }

    public Boolean getShowAsigRolesPorPlantilla() {
        return showAsigRolesPorPlantilla;
    }

    public void setShowAsigRolesPorPlantilla(Boolean showAsigRolesPorPlantilla) {
        this.showAsigRolesPorPlantilla = showAsigRolesPorPlantilla;
    }

    public List<MacroProcesos> getListaMacroprocesos() {
        return listaMacroprocesos;
    }

    public void setListaMacroprocesos(List<MacroProcesos> listaMacroprocesos) {
        this.listaMacroprocesos = listaMacroprocesos;
    }

    public List<GEstructuraOrganizativa> getListaGestructuraOrganizativa() {
        return listaGestructuraOrganizativa;
    }

    public void setListaGestructuraOrganizativa(List<GEstructuraOrganizativa> listaGestructuraOrganizativa) {
        this.listaGestructuraOrganizativa = listaGestructuraOrganizativa;
    }

    public List<ActorDelProceso> getListaActorDelProceso() {
        return listaActorDelProceso;
    }

    public void setListaActorDelProceso(List<ActorDelProceso> listaActorDelProceso) {
        this.listaActorDelProceso = listaActorDelProceso;
    }

    public List<TipoRolControl> getListaTipoRolControl() {
        return listaTipoRolControl;
    }

    public void setListaTipoRolControl(List<TipoRolControl> listaTipoRolControl) {
        this.listaTipoRolControl = listaTipoRolControl;
    }

    public List<PlantillasDeSeguridad> getListaplantillasdeseguridad() {
        return listaplantillasdeseguridad;
    }

    public void setListaplantillasdeseguridad(List<PlantillasDeSeguridad> listaplantillasdeseguridad) {
        this.listaplantillasdeseguridad = listaplantillasdeseguridad;
    }

    public List<Procesos> getListaProcesos() {
        return listaProcesos;
    }

    public void setListaProcesos(List<Procesos> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public List<ProcedimientoTarea> getListaProcedimientoTarea() {
        return listaProcedimientoTarea;
    }

    public void setListaProcedimientoTarea(List<ProcedimientoTarea> listaProcedimientoTarea) {
        this.listaProcedimientoTarea = listaProcedimientoTarea;
    }

    public List<SecurityTemplate> getListaSecurityTemplate() {
        return listaSecurityTemplate;
    }

    public void setListaSecurityTemplate(List<SecurityTemplate> listaSecurityTemplate) {
        this.listaSecurityTemplate = listaSecurityTemplate;
    }

    public List<SecurityUsers> getListadeUsuariosSouce() {
        return listadeUsuariosSource;
    }

    public void setListadeUsuariosSouce(List<SecurityUsers> listadeUsuariosSouce) {
        this.listadeUsuariosSource = listadeUsuariosSouce;
    }

    public Boolean getDisabledpanelRoles() {
        return disabledpanelRoles;
    }

    public void setDisabledpanelRoles(Boolean disabledpanelRoles) {
        this.disabledpanelRoles = disabledpanelRoles;
    }

    public Integer getNivelEdicion() {
        return nivelEdicion;
    }

    public void setNivelEdicion(Integer nivelEdicion) {
        this.nivelEdicion = nivelEdicion;
    }

    public PlantillasDeSeguridad getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(PlantillasDeSeguridad plantilla) {
        this.plantilla = plantilla;
    }

    public List<SecurityUsers> getListadeUsuariosSelected() {
        return listadeUsuariosSelected;
    }

    public void setListadeUsuariosSelected(List<SecurityUsers> listadeUsuariosSelected) {
        this.listadeUsuariosSelected = listadeUsuariosSelected;
    }

    public Integer getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(Integer idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public Boolean getShowModificarPlantilla() {
        return showModificarPlantilla;
    }

    public void setShowModificarPlantilla(Boolean showModificarPlantilla) {
        this.showModificarPlantilla = showModificarPlantilla;
    }

    public void onTransfer(TransferEvent event) {

        String ids;
        if (event.isAdd()) {
            if (event.getItems().size() > 0) {
                ids = event.getItems().get(0).toString();
                Integer id = Integer.parseInt(ids);
                SecurityUsers user = getSecurityUserByKey(id);
                this.listadeUsuariosSelected.add(user);

            }
            StringBuilder builder = new StringBuilder();
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Usuario Agregado a la Plantilla");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if (event.isRemove()) {
            if (event.getItems().size() > 0) {
                ids = event.getItems().get(0).toString();
                Integer id = Integer.parseInt(ids);
                SecurityUsers user = getSecurityUserByKey(id);
                this.listadeUsuariosSelected.remove(user);

            }
            StringBuilder builder = new StringBuilder();
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Usuario Eliminado de Plantilla");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }




    }

    public SecurityUsers getSecurityUserByKey(Integer id) {
        return rolesBo.buscarSecurityUserById(id);
    }

    @FacesConverter(forClass = SecurityUsers.class)
    public static class SecurityUsersConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent uic, String value) {
            RolesBean pgB = (RolesBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "planGlobalBean");

            if (value == null || value.length() == 0) {
                return null;
            }

            return pgB.getSecurityUserByKey(getKey(value));
        }

        Integer getKey(String value) {
            Integer key;
            key = new Integer(value);
            return key;
        }

        String getStringKey(Integer value) {
            return value.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent uic, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SecurityUsers) {
                return getStringKey(((SecurityUsers) object).getIdUserSeguridad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SecurityUsers.class.getName());
            }
        }
    }
}
