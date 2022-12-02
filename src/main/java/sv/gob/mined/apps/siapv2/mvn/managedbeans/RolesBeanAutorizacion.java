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
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.RolesBo;
import sv.gob.mined.apps.siapv2.mvn.dto.CadenaWhereMetasActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.DetalleAccesoPlantilla;
import sv.gob.mined.apps.siapv2.mvn.modelo.EstructuraDeLaEstrategia;
import sv.gob.mined.apps.siapv2.mvn.modelo.GEstructuraOrganizativa;
import sv.gob.mined.apps.siapv2.mvn.modelo.NivelesOrganigramaInstitucional;
import sv.gob.mined.apps.siapv2.mvn.modelo.NivelesRealizacGeograf;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgConvenio;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgMetas;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillaAccesoDatos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillasDeSeguridad;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaAnosPlanAccion;

/**
 *
 * @author infosoft
 */
@Component(value = "rolesBeanAutorizacion")
@Scope(value = "view")
public class RolesBeanAutorizacion {

    private PlantillaAccesoDatos plantilla;
    private Boolean showEstructuraOp = false;
    private Boolean showProcesosInst = false;
    private Boolean showPlantillaSel = false;
    private Boolean showPlantillayRolSel = false;
    private Boolean showProcedimiento = false;
    private Boolean showPlantillaRoles = false;
    private Boolean showAsigRolesUsuarios = false;
    private Boolean showAsigRolesPorPlantilla = false;
    private Integer nivelEdicion = 1;
    private TreeNode treePlanDeTrabajo;
    private TreeNode treeEstructuraOrganizativa;
    private TreeNode selectedNode;
    private String padretreeEstruEstra;
    private String nombreNodoSelecttreeEstruEstra;
    private List<NivelesOrganigramaInstitucional> listanivelesorganigramainstitucional = new ArrayList<NivelesOrganigramaInstitucional>();
    private List<NivelesRealizacGeograf> listanivelesRealizacGeograf = new ArrayList<NivelesRealizacGeograf>();
    private List<PlantillaAccesoDatos> listaPlantillaAccesoDatos = new ArrayList<PlantillaAccesoDatos>();
    private List<PlantillasDeSeguridad> listaPlantillasDeSeguridad = new ArrayList<PlantillasDeSeguridad>();
    private List<GEstructuraOrganizativa> listaEstructurasOrganizativas = new ArrayList<GEstructuraOrganizativa>();
    private List<PgConvenio> listaPgConvenios = new ArrayList<PgConvenio>();
    private List<PoaAnosPlanAccion> listaPoaAnosPlanAccion = new ArrayList<PoaAnosPlanAccion>();
    private List<PgMetas> listaMetas = new ArrayList<PgMetas>();
    private List<PgMetas> listaMetasPersist = new ArrayList<PgMetas>();
    private List<PgActividades> listaActividades = new ArrayList<PgActividades>();
    private List<PgActividades> listaActividadesPersist = new ArrayList<PgActividades>();
    private List<PgConvenio> listaPgConveniosMarcados = new ArrayList<PgConvenio>();
    private PlantillaAccesoDatos currentPlantilla;
    private boolean modoNuevo = false;
    private boolean modoLoad = true;
    private String desPlanDatos;
    private String desPlanSeg;
    private Boolean convenioMarcado;
    private Boolean metaMarcado;
    private PgMetas meta = new PgMetas();
    private EstructuraDeLaEstrategia estructura;
    private Boolean disabledBtnNuevo = true;
    private Boolean disableBtnGuardar = true;
    private Boolean diabledBtnModificar = false;
    private Boolean disabledBtnBorrar = true;
    private String idConvenioSelect;
    private Boolean showModificarPlantilla = false;
    private List<CadenaWhereMetasActividades> listaCadena = new ArrayList<CadenaWhereMetasActividades>();
    private Boolean modificacion = false;
    private List<Integer> metasagregadas = new ArrayList<Integer>(0);
    @Autowired
    private RolesBo rolesBo;

    /**
     * Creates a new instance of RolesBean
     */
    public RolesBeanAutorizacion() {
    }

    @PostConstruct
    public void init() {
        llenarTreePlanDeTrabajo();
        armarArbolEstructuraOrganizativa();
        this.listanivelesRealizacGeograf = rolesBo.getLstNivelesRealizacGeograf();
        this.listanivelesorganigramainstitucional = rolesBo.getLstNivelesOrganigramaInstitucional();
        this.listaPlantillaAccesoDatos = rolesBo.getLstPlantillaAccesoDatos();
        this.listaPlantillasDeSeguridad = rolesBo.getLstPlantillasDeSeguridad();
        this.listaEstructurasOrganizativas = rolesBo.getLstGEstructuraOrganizativa();
        this.listaPgConvenios = rolesBo.getListadoDeConvenio();

    }

    public PlantillaAccesoDatos getSelectedPlantillaDatos() {
        if (currentPlantilla == null) {
            this.currentPlantilla = new PlantillaAccesoDatos();
        }

        return currentPlantilla;
    }

    private TreeNode setHijoComponente(TreeNode nodoPadre, List<GEstructuraOrganizativa> subLstComponentes) {
        for (GEstructuraOrganizativa pgComponentes : subLstComponentes) {
            TreeNode node0 = new DefaultTreeNode(pgComponentes, nodoPadre);

            setHijoComponente(node0, rolesBo.getLstGEstructuraOrganizativaChilds(pgComponentes.getEstructuraOrganizativa()));
        }

        return nodoPadre;
    }

    public void llenarAnioyMetas() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String idConvenio = request.getParameter("idConvenio");
        idConvenioSelect = idConvenio;
        desmarcarchexbox(idConvenio);
        PgConvenio convenio = buscarConvenioMarcado(idConvenio);

        if (convenio.getConvenio() == null) {
            this.listaPoaAnosPlanAccion = this.rolesBo.getListadoPoaAnosPlanAccionByConvenio(Integer.parseInt(idConvenio));
            this.listaMetas = this.rolesBo.getListadodeMetasByConvenio(Integer.parseInt(idConvenio));
            marcarMetasModificacion(idConvenio);
            PgConvenio pgc = buscarConvenio(idConvenio);
            pgc.setListaMetas(listaMetas);
            pgc.setListaPoaAnosPlanAccion(listaPoaAnosPlanAccion);
            listaPgConveniosMarcados.add(pgc);

        } else {
            this.listaPoaAnosPlanAccion = convenio.getListaPoaAnosPlanAccion();
            this.listaMetas = convenio.getListaMetas();
        }
        if (!veficarConvenioMarcado()) {
            this.listaPoaAnosPlanAccion = new ArrayList<PoaAnosPlanAccion>();
            this.listaMetas = new ArrayList<PgMetas>();
            this.listaPgConveniosMarcados = new ArrayList<PgConvenio>();
        }



    }

    private void marcarMetasModificacion(String convenio) {
        if (modificacion) {
            for (PgMetas metacurrent : listaMetas) {
                for (CadenaWhereMetasActividades cadenacurrent : listaCadena) {
                    if (metacurrent.getMeta() == cadenacurrent.getMeta()) {
                        metacurrent.setSeleccionado(true);
                    }
                }

            }
            this.disableBtnGuardar = false;
            this.diabledBtnModificar = true;
            RequestContext.getCurrentInstance().update("frmPrincipal:menuBtns");
        }

    }

    private boolean veficarConvenioMarcado() {
        Boolean marcado = false;
        for (PgConvenio conv : listaPgConvenios) {
            if (conv.getMarcado()) {
                marcado = true;
            }
        }
        return marcado;
    }

    private void desmarcarchexbox(String idconvenio) {
        for (PgConvenio con : listaPgConvenios) {
            if (con.getConvenio() != Integer.parseInt(idconvenio)) {
                con.setMarcado(false);
            }
        }

    }

    public PgConvenio buscarConvenio(String idConvenio) {
        PgConvenio convenio = new PgConvenio();
        for (PgConvenio con : listaPgConvenios) {
            if (con.getConvenio() == Integer.parseInt(idConvenio)) {
                convenio = con;
            }
        }
        return convenio;
    }

    public PgConvenio buscarConvenioMarcado(String idConvenio) {
        PgConvenio convenio = new PgConvenio();
        for (PgConvenio con : listaPgConveniosMarcados) {
            if (con.getConvenio() == Integer.parseInt(idConvenio)) {
                convenio = con;
            }
        }
        return convenio;
    }

    public void agregarMeta() {
        Boolean entro = false;
        EstructuraDeLaEstrategia estructura1 = estructura;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String idMeta = request.getParameter("idMeta");
        if (estructura1.getIdEstructuraEstrategia() == 1 || estructura1.getIdEstructuraEstrategia() == 2) {
            for (PgMetas metaSelect : listaMetas) {
                if (metaSelect.getSeleccionado() != null) {
                    if (metaSelect.getSeleccionado()) {
                        entro = true;
                    }

                }


            }
            if (entro) {
                this.disableBtnGuardar = false;
                this.disabledBtnNuevo = true;
            } else {
                this.disableBtnGuardar = true;
                this.disabledBtnNuevo = false;
            }
            RequestContext.getCurrentInstance().update("frmPrincipal:menuBtns");

        } else {
            for (PgMetas metas : listaMetas) {
                metas.setSeleccionado(false);
            }
            RequestContext.getCurrentInstance().update("frmPrincipal:tblMetasPoa");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "No puede agregar metas segun estructura de la estrategia"));
        }
    }

    public Boolean verificarSiesDesmarcado(String valor) {
        Boolean entro = false;
        Boolean desmarcado = false;
        PgActividades current = new PgActividades();
        for (PgActividades actper : listaActividadesPersist) {
            if (actper.getActividad() == Integer.parseInt(valor)) {
                entro = true;
                current = actper;
            }
        }
        if (entro) {
            desmarcado = true;
            listaActividadesPersist.remove(current);
        }
        return desmarcado;
    }

    public void agregarActividades() {
        PgActividades actividadDeleted = new PgActividades();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String valor = request.getParameter("idActividad");
        Boolean desmarcado = verificarSiesDesmarcado(valor);

        for (PgActividades act : listaActividades) {
            Boolean entro = false;
            for (PgActividades actper : listaActividadesPersist) {
                if (act.getActividad() == actper.getActividad()) {

                    act.setSeleccionado(actper.getSeleccionado());
                    entro = true;

                }
            }
            if (!entro) {
                if (!desmarcado) {
                    if (act.getActividad() == Integer.parseInt(valor)) {
                        this.listaActividadesPersist.add(act);
                    }
                }
            }
        }
        Boolean bandera = false;
        for (PgActividades actv : listaActividadesPersist) {
            if (actv.getSeleccionado() != null) {
                if (actv.getSeleccionado()) {
                    bandera = true;
                }
            }
        }

        if (bandera) {
            this.disableBtnGuardar = false;
            this.disabledBtnNuevo = true;
        } else {
            this.disableBtnGuardar = true;
            this.disabledBtnNuevo = false;
        }
        RequestContext.getCurrentInstance().update("frmPrincipal:menuBtns");
    }

    private String ArmarCadenaWhere() {
        return ArmarCadenaInMetas() + " " + ArmarCadenInActividades();
    }

    private String ArmarCadenaInMetas() {
        String valor = "";
        Boolean bandera = true;
        List<String> lista = new ArrayList<String>();
        for (PgConvenio conv : listaPgConveniosMarcados) {
            List<PgMetas> listametas = conv.getListaMetas();
            for (PgMetas metas : listametas) {
                if (metas.getSeleccionado() != null) {
                    if (metas.getSeleccionado()) {
                        if (bandera) {
                            valor = valor + metas.getMeta().toString();
                            bandera = false;
                        } else {
                            valor = valor + "," + metas.getMeta().toString();
                        }

                    }

                }
            }
        }
        valor = "pg_actividades.meta in(" + valor + ")";
        return valor;
    }

    private String ArmarCadenInActividades() {
        String valor = "";
        Boolean bandera = false;
        for (PgActividades acts : listaActividadesPersist) {
            if (acts.getSeleccionado()) {
                if (bandera) {
                    valor = valor + "," + acts.getActividad().toString();
                } else {
                    if (acts.getSeleccionado()) {
                        valor = valor + acts.getActividad().toString();
                    }
                }
                bandera = true;
            }
        }
        if (!listaActividadesPersist.isEmpty()) {
            valor = "and pg_actividades.actividad in(" + valor + ")";
        } else {
            valor = "";
        }

        return valor;
    }

    private void armarArbolEstructuraOrganizativa() {
        List<GEstructuraOrganizativa> lstComponente = rolesBo.getLstGEstructuraOrganizativa();
        this.treeEstructuraOrganizativa = new DefaultTreeNode("MINED", null);
        TreeNode node00 = new DefaultTreeNode("Ministerio De Educacion", treeEstructuraOrganizativa);
        for (GEstructuraOrganizativa estructura : lstComponente) {
            TreeNode node0 = new DefaultTreeNode(estructura, node00);
            setHijoComponente(node0, rolesBo.getLstGEstructuraOrganizativaChilds(estructura.getEstructuraOrganizativa()));
            node0.setExpanded(false);
            node00.setExpanded(false);
        }
    }

    public void llenarTreePlanDeTrabajo() {
        treePlanDeTrabajo = new DefaultTreeNode("Root", null);
        List<EstructuraDeLaEstrategia> lista = rolesBo.getListadoEstructuraDeLaEstrategia();
        int i = 1;
        TreeNode node00 = new DefaultTreeNode("Plan de Trabajo", treePlanDeTrabajo);
        for (EstructuraDeLaEstrategia estructuraDeLaEstrategia : lista) {
            TreeNode node0 = new DefaultTreeNode(estructuraDeLaEstrategia, node00);
            i++;
        }
        node00.setExpanded(false);
    }

    private Boolean buscarSiMetaMarcada(String valor) {
        Boolean resultado = false;
        for (PgMetas meta : listaMetas) {
            if (meta.getMeta() == Integer.parseInt(valor)) {
                if (meta.getSeleccionado()) {
                    resultado = true;
                }
            }

        }
        return resultado;
    }

    public void agregarActividad() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String valor = request.getParameter("idMeta");
        Boolean metayactividad = true;
        if (estructura.getIdEstructuraEstrategia() == 1) {
            metayactividad = buscarSiMetaMarcada(valor);
        }
        if (metayactividad) {
            if (estructura.getIdEstructuraEstrategia() != 2) {
                this.listaActividades = rolesBo.getListadoActividadesPorMeta(Integer.parseInt(valor));
                marcarActividadesModificacion(valor);
                validarActividadesSeleccionadas();
                if (!listaActividades.isEmpty()) {
                    meta = rolesBo.getMetaById(Integer.parseInt(valor));
                    showPlantillayRolSel = false;
                    showProcedimiento = true;
                    RequestContext.getCurrentInstance().update("frmPrincipal:numMeta");
                    RequestContext.getCurrentInstance().update("frmPrincipal:nombreMeta");
                    RequestContext.getCurrentInstance().update("frmPrincipal:tblMetas");
                    RequestContext.getCurrentInstance().update("frmPrincipal:dlgPlantillayRolSel");
                    //RequestContext.getCurrentInstance().execute("dlgProcedimiento.show();");
                    PrimeFaces.current().executeScript("PF('dlgProcedimiento').show();");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "Meta no tiene actividades"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "No puede seleccionar actividades segun estructura de la estrategia"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "Tiene que seleccionar meta"));
        }
    }

    private Boolean yaEntroaMeta(String valor) {
        Boolean encontrada = true;
        for (Integer numeta : metasagregadas) {
            if (Integer.parseInt(valor) == numeta) {
                encontrada = false;
            }

        }

        return encontrada;
    }

    private void marcarActividadesModificacion(String valor) {
        if (modificacion) {
            if (yaEntroaMeta(valor)) {
                //this.listaActividadesPersist = new ArrayList<PgActividades>();
                for (PgActividades actcurrent : listaActividades) {
                    for (CadenaWhereMetasActividades cadenacurrent : listaCadena) {
                        if (actcurrent.getActividad() == cadenacurrent.getActividad()) {
                            actcurrent.setSeleccionado(true);
                        }
                    }

                }
                for (PgActividades actcurrent : listaActividades) {
                    if (actcurrent.getSeleccionado() == true) {
                        listaActividadesPersist.add(actcurrent);
                    }
                }
                metasagregadas.add(Integer.parseInt(valor));
            }
        }
    }

    private Boolean validarActividadesSeleccionadas() {
        Boolean valor = false;
        if (!listaActividadesPersist.isEmpty()) {
            for (PgActividades actividades : listaActividades) {
                for (PgActividades act : listaActividadesPersist) {
                    if (actividades.getActividad() == act.getActividad()) {
                        actividades.setSeleccionado(act.getSeleccionado());
                        valor = true;
                    }
                }

            }
        }
        return valor;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        estructura = (EstructuraDeLaEstrategia) selectedNode.getData();
        this.padretreeEstruEstra = event.getTreeNode().getParent().toString();
        this.nombreNodoSelecttreeEstruEstra = event.getTreeNode().toString();


    }

    public void cerrarPopupPlantillaSel() {
        showProcesosInst = false;
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgPlantillaSel");
        //RequestContext.getCurrentInstance().execute("dlgProcesosInst.show();");
        PrimeFaces.current().executeScript("PF('dlgProcesosInst').show();");

    }

    public void cerrarPopupProcesosInst() {
    }

    private String descripcionPlantillaSeg() {
        String valor = null;
        for (PlantillasDeSeguridad plantillaseg : listaPlantillasDeSeguridad) {
            if (plantillaseg.getIdPlantillaSeguridad() == this.currentPlantilla.getIdPlantillaSeguridad()) {
                valor = plantillaseg.getDescripcionPlantilla();
            }
        }
        return valor;

    }

    public void agregarRol() {
        Integer valor = verificarRequired();
        if (valor.equals(0)) {
            desPlanSeg = descripcionPlantillaSeg();
            desPlanDatos = this.currentPlantilla.getDescPlantillaDatos();
            RequestContext.getCurrentInstance().update("frmPrincipal:idplantilladatos");
            RequestContext.getCurrentInstance().update("frmPrincipal:plantillaDes");
            RequestContext.getCurrentInstance().update("frmPrincipal:inputDes");
            cerrarPopupPlantillayRolSel();
        } else {
            RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcesosInst");
            //RequestContext.getCurrentInstance().execute("dlgProcesosInst.show();");
            PrimeFaces.current().executeScript("PF('dlgProcesosInst').show();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "Falta llenar algunos campos..."));
        }

    }

    public void cerrarPopupPlantillayRolSel() {
        showProcesosInst = false;
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcesosInst");
        //RequestContext.getCurrentInstance().execute("dlgPlantillayRolSel.show();");
        PrimeFaces.current().executeScript("PF('dlgPlantillayRolSel').show();");
    }

    public void cerrarPopupRolSel() {

        showProcesosInst = true;
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgPlantillayRolSel");
        RequestContext.getCurrentInstance().execute("PF('dlgProcesosInst').show();");

    }

    public void cerrarPopupProcedimiento() {
        //System.out.println("lista" + this.listaActividades);
        showPlantillayRolSel = true;
        showProcedimiento = false;
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcedimiento");
        RequestContext.getCurrentInstance().execute("PF('dlgPlantillayRolSel').show();");
    }

    public void nuevo() {
        switch (nivelEdicion) {
            case 1:

                break;
            case 2:

                break;
            case 3:
                this.modoNuevo = true;
                this.modoLoad = false;
                this.disabledBtnNuevo = true;
                this.diabledBtnModificar = true;
                this.modificacion = false;
                RequestContext.getCurrentInstance().update("frmPrincipal:menuBtns");
                RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcesosInst");
                RequestContext.getCurrentInstance().execute("PF('dlgProcesosInst').show();");
                //System.out.println("enro al metodo nuevo");
                break;
        }
    }

    public void cerrarPopupEstructuraOp() {

        showEstructuraOp = true;
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

    public Boolean getShowPlantillaSel() {
        return showPlantillaSel;
    }

    public void setShowPlantillaSel(Boolean showPlantillaSel) {
        this.showPlantillaSel = showPlantillaSel;
    }

    public Boolean getShowPlantillayRolSel() {
        return showPlantillayRolSel;
    }

    public void setShowPlantillayRolSel(Boolean showPlantillayRolSel) {
        this.showPlantillayRolSel = showPlantillayRolSel;
    }

    public Boolean getShowProcedimiento() {
        return showProcedimiento;
    }

    public void setShowProcedimiento(Boolean showProcedimiento) {
        this.showProcedimiento = showProcedimiento;
    }

    public Integer getNivelEdicion() {
        return nivelEdicion;
    }

    public void setNivelEdicion(Integer nivelEdicion) {
        if (nivelEdicion == null) {
            this.nivelEdicion = nivelEdicion;
        }
    }

    public List<NivelesRealizacGeograf> getListanivelesRealizacGeograf() {
        return listanivelesRealizacGeograf;
    }

    public void setListanivelesRealizacGeograf(List<NivelesRealizacGeograf> listanivelesRealizacGeograf) {
        this.listanivelesRealizacGeograf = listanivelesRealizacGeograf;
    }

    public List<NivelesOrganigramaInstitucional> getListanivelesorganigramainstitucional() {
        return listanivelesorganigramainstitucional;
    }

    public void setListanivelesorganigramainstitucional(List<NivelesOrganigramaInstitucional> listanivelesorganigramainstitucional) {
        this.listanivelesorganigramainstitucional = listanivelesorganigramainstitucional;
    }

    public List<PlantillaAccesoDatos> getListaPlantillaAccesoDatos() {
        return listaPlantillaAccesoDatos;
    }

    public void setListaPlantillaAccesoDatos(List<PlantillaAccesoDatos> listaPlantillaAccesoDatos) {
        this.listaPlantillaAccesoDatos = listaPlantillaAccesoDatos;
    }

    public TreeNode getTreePlanDeTrabajo() {
        return treePlanDeTrabajo;
    }

    public void setTreePlanDeTrabajo(TreeNode treePlanDeTrabajo) {
        this.treePlanDeTrabajo = treePlanDeTrabajo;
    }

    public List<PlantillasDeSeguridad> getListaPlantillasDeSeguridad() {
        return listaPlantillasDeSeguridad;
    }

    public void setListaPlantillasDeSeguridad(List<PlantillasDeSeguridad> listaPlantillasDeSeguridad) {
        this.listaPlantillasDeSeguridad = listaPlantillasDeSeguridad;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public String getPadretreeEstruEstra() {
        return padretreeEstruEstra;
    }

    public void setPadretreeEstruEstra(String padretreeEstruEstra) {
        this.padretreeEstruEstra = padretreeEstruEstra;
    }

    public String getNombreNodoSelecttreeEstruEstra() {
        return nombreNodoSelecttreeEstruEstra;
    }

    public void setNombreNodoSelecttreeEstruEstra(String nombreNodoSelecttreeEstruEstra) {
        this.nombreNodoSelecttreeEstruEstra = nombreNodoSelecttreeEstruEstra;
    }

    public List<GEstructuraOrganizativa> getListaEstructurasOrganizativas() {
        return listaEstructurasOrganizativas;
    }

    public void setListaEstructurasOrganizativas(List<GEstructuraOrganizativa> listaEstructurasOrganizativas) {
        this.listaEstructurasOrganizativas = listaEstructurasOrganizativas;
    }

    public TreeNode getTreeEstructuraOrganizativa() {
        return treeEstructuraOrganizativa;
    }

    public void setTreeEstructuraOrganizativa(TreeNode treeEstructuraOrganizativa) {
        this.treeEstructuraOrganizativa = treeEstructuraOrganizativa;
    }

    public RolesBo getRolesBo() {
        return rolesBo;
    }

    public void setRolesBo(RolesBo rolesBo) {
        this.rolesBo = rolesBo;
    }

    public List<PgConvenio> getListaPgConvenios() {
        return listaPgConvenios;
    }

    public void setListaPgConvenios(List<PgConvenio> listaPgConvenios) {
        this.listaPgConvenios = listaPgConvenios;
    }

    public List<PoaAnosPlanAccion> getListaPoaAnosPlanAccion() {
        return listaPoaAnosPlanAccion;
    }

    public void setListaPoaAnosPlanAccion(List<PoaAnosPlanAccion> listaPoaAnosPlanAccion) {
        this.listaPoaAnosPlanAccion = listaPoaAnosPlanAccion;
    }

    public List<PgMetas> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(List<PgMetas> listaMetas) {
        this.listaMetas = listaMetas;
    }

    public List<PgActividades> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<PgActividades> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public void guardar() {
        //System.out.println("cadena where" + ArmarCadenaWhere());
        //System.out.println("nivel de edicion" + nivelEdicion);
        //System.out.println("plantilla" + this.getSelectedPlantillaDatos());
        Integer valor = 1;
        valor = verificarRequired();
        if (valor.equals(0)) {
            switch (nivelEdicion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    Integer id;
                    if (VerificarCreate()) {
                        id = rolesBo.createPlantillaAccesoDatos(this.currentPlantilla, false);
                        DetalleAccesoPlantilla det = llenarDetalleAccesoPlantilla(id);
                        Integer iddet = rolesBo.createDetalleAccesoPlantilla(det, false);
                    } else {
                        rolesBo.createPlantillaAccesoDatos(this.currentPlantilla, true);
                        id = this.currentPlantilla.getIdPlantillaAccesoDatos();
                        DetalleAccesoPlantilla det = llenarDetalleAccesoPlantilla(id);
                        Integer iddet = rolesBo.createDetalleAccesoPlantilla(det, true);
                    }
                    this.modificacion = false;
                    this.modoNuevo = false;
                    this.modoLoad = true;
                    this.disableBtnGuardar = true;
                    this.diabledBtnModificar = false;
                    this.disabledBtnNuevo = false;
                    this.showPlantillayRolSel = false;
                    this.showProcesosInst = true;
                    for (PgConvenio con : listaPgConveniosMarcados) {
                        con.setMarcado(false);
                        List<PgMetas> lista = con.getListaMetas();
                        for (PgMetas pgMetas : lista) {
                            pgMetas.setSeleccionado(false);
                        }
                    }

                    this.currentPlantilla = new PlantillaAccesoDatos();
                    RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcesosInst");
                    RequestContext.getCurrentInstance().update("frmPrincipal:dlgPlantillayRolSel");
                    RequestContext.getCurrentInstance().update("frmPrincipal:menuBtns");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "Se creo plantilla con id" + id));
                    break;
            }
        } else {
            //verificarRequired();
            RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcesosInst");
            RequestContext.getCurrentInstance().execute("PF('dlgProcesosInst').show();");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "Campos en rojo son obligatorios..."));

        }
    }

    private Boolean VerificarCreate() {
        Boolean bandera = false;
        if (this.currentPlantilla.getIdPlantillaAccesoDatos() == null) {
            bandera = true;
        }
        return bandera;
    }

    private DetalleAccesoPlantilla llenarDetalleAccesoPlantilla(Integer id) {
        DetalleAccesoPlantilla plantilladet = new DetalleAccesoPlantilla();
        plantilladet.setIdPlantillaAccesoDatos(id);
        plantilladet.setCadenaFiltradoNivel(ArmarCadenaWhere());
        plantilladet.setIdEstructuraEstrategia(estructura.getIdEstructuraEstrategia());
        return plantilladet;
    }

    private Integer verificarRequired() {
        Integer bandera = 0;
        Integer bandera1 = 0;
        Integer bandera2 = 0;
        Integer bandera3 = 0;
        Integer bandera4 = 0;
        if (currentPlantilla.getDescPlantillaDatos() == null || currentPlantilla.getDescPlantillaDatos().length() < 1) {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("panelgridnuevo").findComponent("descripcion");

            comp.getAttributes().put("style", "border-color: red; text-align: left");
            bandera1 = 1;
        } else {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("panelgridnuevo").findComponent("descripcion");
            comp.getAttributes().put("style", "border-color: none; text-align: left");
            bandera1 = 0;
        }
        if (currentPlantilla.getIdNivelRealizacion() == null) {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("niveles").findComponent("nivelreajerar").findComponent("cmbReaGeografica");
            comp.getAttributes().put("style", "border-color: red; text-align: left");
            bandera2 = 1;
        } else {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("niveles").findComponent("nivelreajerar").findComponent("cmbReaGeografica");
            comp.getAttributes().put("style", "border-color: none; text-align: left");
            bandera2 = 0;
        }
        if (currentPlantilla.getIdNivelOrganigrama() == null) {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("niveles").findComponent("nivelreajerar").findComponent("cmbJerarquia");
            comp.getAttributes().put("style", "border-color: red; text-align: left");
            bandera3 = 1;
        } else {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("niveles").findComponent("nivelreajerar").findComponent("cmbJerarquia");
            comp.getAttributes().put("style", "border-color: none; text-align: left");
            bandera3 = 0;
        }
        if (currentPlantilla.getIdPlantillaSeguridad() == null) {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("panelplantillaroll").findComponent("panelplantillarollin").findComponent("cmbRol");
            comp.getAttributes().put("style", "border-color: red; text-align: left");
            bandera4 = 1;
        } else {
            UIComponent view = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent comp = view.findComponent("frmPrincipal").findComponent("panelGridOrganizacion").findComponent("panelplantillaroll").findComponent("panelplantillarollin").findComponent("cmbRol");
            comp.getAttributes().put("style", "border-color: none; text-align: left");
            bandera4 = 0;
        }

        if (bandera1 == 1 || bandera2 == 1 || bandera3 == 1 || bandera4 == 1) {
            bandera = 1;
        } else {
            bandera = 0;
        }


        return bandera;
    }

    public boolean isModoNuevo() {
        return modoNuevo;
    }

    public void setModoNuevo(boolean modoNuevo) {
        this.modoNuevo = modoNuevo;
    }

    public boolean isModoLoad() {
        return modoLoad;
    }

    public void setModoLoad(boolean modoLoad) {
        this.modoLoad = modoLoad;
    }

    public void modificarOnchange() {
        cerrarPopupModificarPlantilla();
    }

    public void cerrarPopupModificarPlantilla() {
        //this.listadeUsuariosSelected = this.usuarios.getTarget();
        modificacion = true;
        nivelEdicion = 3;
        showPlantillaRoles = true;
        showModificarPlantilla = false;
        this.modoLoad = false;
        this.modoNuevo = true;
//        disabledpanelRoles = false;
        listaCadena = rolesBo.getListaCadenaWhereMetasActividades(plantilla.getIdPlantillaAccesoDatos());
        DetalleAccesoPlantilla det = rolesBo.getDepPlantillaById(plantilla.getIdPlantillaAccesoDatos());
        estructura = rolesBo.getEstructuraById(det.getIdEstructuraEstrategia());
        this.currentPlantilla = rolesBo.getPlantillaDatosModificacion(plantilla.getIdPlantillaAccesoDatos());
//        this.listaSecurityTemplate = rolesBo.getListadosSecurityTemplateModificacion(plantilla.getIdPlantillaSeguridad());
//        this.listadeUsuariosSelected = rolesBo.getListadoUsuariosPlantillaModificacion(plantilla.getIdPlantillaSeguridad());
        RequestContext.getCurrentInstance().update("frmPrincipal:panelGridOrganizacion");
        RequestContext.getCurrentInstance().update("frmPrincipal:panelgridnuevo");
        RequestContext.getCurrentInstance().update("frmPrincipal:tblPlantillas");
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcesosInst");
        RequestContext.getCurrentInstance().execute("PF('dlgProcesosInst').show();");
//        RequestContext.getCurrentInstance().execute("dlgDefPlantillaRoles.show();");
    }

    public PlantillaAccesoDatos getSelectedPlantilla() {
        if (plantilla == null) {
            this.plantilla = new PlantillaAccesoDatos();
        }

        return plantilla;
    }

    public void modificar() {
        showModificarPlantilla = true;
        showProcesosInst = false;
        this.listaActividadesPersist = new ArrayList<PgActividades>();
        this.listaPlantillaAccesoDatos = rolesBo.getLstPlantillaAccesoDatos();
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgProcesosInst");
        RequestContext.getCurrentInstance().execute("PF('dlgModificarPlantilla').show();");
    }

    public void comandoremoto() {
        if (estructura != null) {
            RequestContext.getCurrentInstance().execute("PF('dlgProcesosInst').show();");
            this.nivelEdicion = 3;
            this.disabledBtnNuevo = false;
            this.diabledBtnModificar = false;
            RequestContext.getCurrentInstance().update("frmPrincipal:menuBtns");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atencion", "Debe seleccionar estructura de la estrategia primero..."));
        }
    }

    public String getDesPlanDatos() {
        return desPlanDatos;
    }

    public void setDesPlanDatos(String desPlanDatos) {
        this.desPlanDatos = desPlanDatos;
    }

    public String getDesPlanSeg() {
        return desPlanSeg;
    }

    public void setDesPlanSeg(String desPlanSeg) {
        this.desPlanSeg = desPlanSeg;
    }

    public Boolean getConvenioMarcado() {
        return convenioMarcado;
    }

    public void setConvenioMarcado(Boolean convenioMarcado) {
        this.convenioMarcado = convenioMarcado;
    }

    public Boolean getMetaMarcado() {
        return metaMarcado;
    }

    public void setMetaMarcado(Boolean metaMarcado) {
        this.metaMarcado = metaMarcado;
    }

    public PgMetas getMeta() {
        return meta;
    }

    public void setMeta(PgMetas meta) {
        this.meta = meta;
    }

    public Boolean getDisableBtnGuardar() {
        return disableBtnGuardar;
    }

    public void setDisableBtnGuardar(Boolean disableBtnGuardar) {
        this.disableBtnGuardar = disableBtnGuardar;
    }

    public Boolean getDiabledBtnModificar() {
        return diabledBtnModificar;
    }

    public void setDiabledBtnModificar(Boolean diabledBtnModificar) {
        this.diabledBtnModificar = diabledBtnModificar;
    }

    public Boolean getDisabledBtnBorrar() {
        return disabledBtnBorrar;
    }

    public void setDisabledBtnBorrar(Boolean disabledBtnBorrar) {
        this.disabledBtnBorrar = disabledBtnBorrar;
    }

    public Boolean getDisabledBtnNuevo() {
        return disabledBtnNuevo;
    }

    public void setDisabledBtnNuevo(Boolean disabledBtnNuevo) {
        this.disabledBtnNuevo = disabledBtnNuevo;
    }

    public Boolean getShowModificarPlantilla() {
        return showModificarPlantilla;
    }

    public void setShowModificarPlantilla(Boolean showModificarPlantilla) {
        this.showModificarPlantilla = showModificarPlantilla;
    }
}
