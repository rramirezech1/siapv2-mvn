/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.PlanGlobalBo;
import sv.gob.mined.apps.siapv2.mvn.dto.CategoriasDto;
import sv.gob.mined.apps.siapv2.mvn.dto.ComponentesDto;
import sv.gob.mined.apps.siapv2.mvn.dto.MontosPapDto;
import sv.gob.mined.apps.siapv2.mvn.modelo.DetalleCompPorPlantilla;
import sv.gob.mined.apps.siapv2.mvn.modelo.EstadoEjecConvenio;
import sv.gob.mined.apps.siapv2.mvn.modelo.GCatalogoProductos;
import sv.gob.mined.apps.siapv2.mvn.modelo.OrigenDeLosRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgAnosPlanGlobal;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgCategorias;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgComponentes;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgConvenio;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgMetas;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursosSaldos;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoDeMetas;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRecurso;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;

/**
 *
 * @author Infosoft
 */
@Component(value = "planGlobalBean")
@Scope(value = "view")
public class PlanGlobalBean {

    private Boolean contrapartida = true;
    private Boolean showMeta = false;
    private Boolean showVerificacionPap = false;
    private Boolean showDlgMontoPap = false;
    private Boolean showActividad = false;
    private Boolean showRecurso = false;
    private Boolean showMsjEliminar = false;
    private Boolean visibleComponentes = false;
    private Boolean visibleMetas = false;
    private Boolean visibleActividades = false;
    private Boolean visibleAnhoPap = false;
    private Boolean visibleBtnActividades = false;
    private Boolean deshabilitarRecurso = true;
    private Boolean reprogramacion = false;
    private Boolean disabledBtnNuevo = true;
    private Boolean disableBtnGuardar = true;
    private Boolean diabledBtnModificar = true;
    private Boolean disabledBtnBorrar = true;
    private Boolean diabledBtnRecuperar = true;
    private Boolean disabledBtnImprimir = true;
    private Boolean existeSubComponentes = false;
    private Boolean componenteValido = false;
    private BigDecimal porcentajeRecurso = BigDecimal.ZERO;
    private Integer nivelEdicion = 1;
    private Integer idConvenio = 0;
    private Integer idOrigen = 0;
    private Integer idComponente = 0;
    private Integer idOrganismo = 0;
    private Integer idEstadoEjeConvenio = 0;
    private Integer idPoa = 0;
    private Integer numPaso = 0;
    private String nombreComponente;
    private String clasificadorPresupuestario = "";
    private String nombreFtePpal = "";
    private String nombreFteCont = "";
    private String msjVtaConfirmacion = "";
    private List<PgMetas> lstMetas = new ArrayList<PgMetas>();
    private List<PgActividades> lstActividades = new ArrayList<PgActividades>();
    private List<PoaRecursos> lstRecuros = new ArrayList<PoaRecursos>();
    private List<TipoDeMetas> lstTipoMeta = new ArrayList<TipoDeMetas>();
    private PgMetas pgMeta;
    private PgActividades pgActividad;
    private PoaRecursos currentRecurso;
    private PoaRecursosSaldos recursoFtePpal;
    private PoaRecursosSaldos recursoFteCont;
    private DetalleCompPorPlantilla detCompPlanilla;
    private TreeNode nodoComponentes;
    @Autowired
    private PlanGlobalBo planGlobalBo;

    public PlanGlobalBean() {
    }

    @PostConstruct
    public void init() {
        lstTipoMeta = planGlobalBo.getLstTipoMetas();
    }

    public Boolean getShowMsjEliminar() {
        return showMsjEliminar;
    }

    public void setShowMsjEliminar(Boolean showMsjEliminar) {
        this.showMsjEliminar = showMsjEliminar;
    }

    public String getMsjVtaConfirmacion() {
        return msjVtaConfirmacion;
    }

    public void setMsjVtaConfirmacion(String msjVtaConfirmacion) {
        this.msjVtaConfirmacion = msjVtaConfirmacion;
    }

    public PlanGlobalBo getPlanGlobalBo() {
        return planGlobalBo;
    }

    public void cargarIdConvenio() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        setIdConvenio((Integer) sessionMap.get("idConvenio"));
        setIdOrigen((Integer) sessionMap.get("idOrigen"));
        armarTreeComponentes();
        reprogramacion = true;
        nivelEdicion = 1;
        RequestContext.getCurrentInstance().update(":frmPrincipal:dlgMeta");
    }

    public Boolean getContrapartida() {
        return contrapartida;
    }

    public void setContrapartida(Boolean contrapartida) {
        this.contrapartida = contrapartida;
    }

    public Integer getNumColSpanFuentes() {
        if (contrapartida) {
            return 3;
        } else {
            return 1;
        }
    }

    public Integer getNivelEdicion() {
        return nivelEdicion;
    }

    public void setNivelEdicion(Integer nivelEdicion) {
        this.nivelEdicion = nivelEdicion;
    }

    public void nuevoDesdeReprogramacion() {
        if (reprogramacion) {
            nuevo();
        }
    }

    public void nuevo() {
        deshabilitarRecurso = true;
        int idPag;
        DataTable d;
        switch (nivelEdicion) {
            case 1:
                if (componenteValido) {
                    showMeta = true;
                    newMeta();

                    d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrincipal:tblMetas");
                    idPag = d.getRowCount() / 3;
                    RequestContext.getCurrentInstance().execute("tblMetas.getPaginator().setPage(" + idPag + ")");
                    RequestContext.getCurrentInstance().update("frmPrincipal:tblMetas");
                }else{
                    JsfUtil.addWarningMessage("Debe de seleccionar un sub-componente.");
                }
                break;
            case 2:
                PgActividades actividad = new PgActividades(0);
                actividad.setFinanciamientoBanco(BigDecimal.ZERO);
                actividad.setFinanciamientoGoes(BigDecimal.ZERO);
                actividad.setPgMeta(pgMeta);
                actividad.setEstadoDeEliminacion(0);
                if (getLstAnhosPg().isEmpty()) {
                    JsfUtil.addWarningMessage("No se han registrados los años para este Plan Global");
                } else {
                    actividad.setPgAnosPlanGlobal(getLstAnhosPg().get(0));
                    if (pgMeta.getLstPgActividades() == null) {
                        pgMeta.setLstPgActividades(new ArrayList<PgActividades>());
                    }
                    pgActividad = null;
                    pgMeta.getLstPgActividades().add(actividad);
                }

                d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrincipal:tblActividades");
                idPag = d.getRowCount() / 3;
                RequestContext.getCurrentInstance().execute("tblActividades.getPaginator().setPage(" + idPag + ")");
                RequestContext.getCurrentInstance().update("frmPrincipal:dlgActividad");
                break;
            case 3:
                PoaRecursos recurso = new PoaRecursos(0);
                recurso.setPoa(planGlobalBo.getAnhosPgPgAnnosPlanGlobal(pgActividad.getIdentificadorAnoPap()).getPoa());
                recurso.setActividad(pgActividad.getActividad());
                recurso.setPgActividad(pgActividad);
                recurso.setPrecioUnitario(BigDecimal.ZERO);
                recurso.setCantidad(0);
                recurso.setValor(BigDecimal.ZERO);
                recurso.setEstadoDeEliminacion(0);
                currentRecurso = recurso;
                lstRecuros.add(recurso);
                deshabilitarRecurso = false;

                PoaRecursosSaldos rFtePpal = new PoaRecursosSaldos();
                rFtePpal.setMontoPreliminar(BigDecimal.ZERO);
                rFtePpal.setActivo(1);
                rFtePpal.setConvenio(idConvenio);
                rFtePpal.setEstadoDeEliminacion(0);
                rFtePpal.setOrganismo(planGlobalBo.getOrganismoByConvenio(idConvenio, true));
                rFtePpal.setMontoReprogramado(BigDecimal.ZERO);
                currentRecurso.getLstPoaRecursosSaldos().add(rFtePpal);
                recursoFtePpal = rFtePpal;

                if (contrapartida) {
                    PoaRecursosSaldos rFteCont = new PoaRecursosSaldos();
                    rFteCont.setMontoPreliminar(BigDecimal.ZERO);
                    rFteCont.setActivo(1);
                    rFteCont.setConvenio(idConvenio);
                    rFteCont.setEstadoDeEliminacion(0);
                    rFteCont.setOrganismo(planGlobalBo.getOrganismoByConvenio(idConvenio, false));
                    rFtePpal.setMontoReprogramado(BigDecimal.ZERO);
                    currentRecurso.getLstPoaRecursosSaldos().add(rFteCont);
                    recursoFteCont = rFteCont;
                }
                d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrincipal:tblRecursos");
                idPag = d.getRowCount() / 3;
                RequestContext.getCurrentInstance().execute("tblRecursos.getPaginator().setPage(" + idPag + ")");
                RequestContext.getCurrentInstance().update("frmPrincipal:dlgRecurso");
                break;
        }
    }

    private void newMeta() {
        PgMetas meta = new PgMetas(0);
        meta.setDescripcion(" ");
        meta.setActivo(1);
        meta.setMontoBanco(BigDecimal.ZERO);
        meta.setMontoGoes(BigDecimal.ZERO);
        if (visibleComponentes) {
            meta.setComponente(idComponente);
        } else {
            if (planGlobalBo.getLstComponente(idConvenio).isEmpty()) {
                JsfUtil.addWarningMessage("Este existen componentes.");
            } else {
                meta.setComponente(planGlobalBo.getLstComponente(idConvenio).get(0).getComponente());
            }
        }
        meta.setIdTipoMeta(lstTipoMeta.get(0).getIdTipoMeta());
        meta.setTipoDeMetas(lstTipoMeta.get(0));
        meta.setEstadoDeEliminacion(0);
        pgMeta = null;
        lstMetas.add(meta);
    }

    public void eliminar() {
        switch (nivelEdicion) {
            case 1:
                msjVtaConfirmacion = "¿Desea eliminar esta META junto con sus ACTIVIDADES y RECURSOS?";
                break;
            case 2:
                msjVtaConfirmacion = "¿Desea eliminar esta ACTIVIDAD junto con sus RECURSO?";
                break;
            case 3:
                msjVtaConfirmacion = "¿Desea eliminar el RECURSO seleccionado?";
                break;
        }
        showMsjEliminar = true;
        RequestContext.getCurrentInstance().update("frmPrincipal:confirmation");
    }

    public void eliminarRegistro() {
        switch (nivelEdicion) {
            case 1:
                if (pgMeta != null) {
                    pgMeta.setEstadoDeEliminacion(1);
                    for (PgActividades pgAct : pgMeta.getLstPgActividades()) {
                        pgAct.setEstadoDeEliminacion(1);
                        for (PoaRecursos poaRecursos : pgAct.getLstPoaRecursos()) {
                            poaRecursos.setEstadoDeEliminacion(1);
                        }
                    }
                }
                //pgMeta = null;
                JsfUtil.updateComponent("frmPrincipal:dlgMeta");
                break;
            case 2:
                if (pgActividad != null) {
                    pgActividad.setEstadoDeEliminacion(1);
                    for (PoaRecursos poaRecursos : pgActividad.getLstPoaRecursos()) {
                        poaRecursos.setEstadoDeEliminacion(1);
                    }
                }
                //pgActividad = null;
                JsfUtil.updateComponent("frmPrincipal:dlgActividad");
                break;
            case 3:
                if (currentRecurso != null) {
                    currentRecurso.setEstadoDeEliminacion(1);
                }
                /*currentRecurso = null;
                 recursoFtePpal = null;
                 recursoFteCont = null;*/
                clasificadorPresupuestario = "";
                JsfUtil.updateComponent("frmPrincipal:dlgRecurso");
                break;
        }
    }

    public void guardarDesdeReprogramacion() {
        if (reprogramacion) {
            guardar();
        }
    }

    public void guardar() {
        Boolean metaValida = true;
        Boolean actividadValida = true;
        String metas = "";
        String actividades = "";

        for (PgMetas pgMetas : lstMetas) {
            if (pgMetas.getCodigoMeta() != null && (pgMetas.getMetaNombre() != null && !pgMetas.getMetaNombre().isEmpty()) && metaValida) {
            } else {
                metaValida = false;
                metas = metas.concat(pgMetas.getIdTipoMeta() + ",<br />");
            }

            for (PgActividades pgAct : pgMetas.getLstPgActividades()) {
                if (pgAct.getCodigoActividad() != null && (pgAct.getActividadNombre() != null && !pgAct.getActividadNombre().isEmpty()) && actividadValida
                        && pgAct.getActividadInicioPrevisto() != null && pgAct.getActividadFinPrevisto() != null) {
                } else {
                    actividadValida = false;
                    actividades = actividades + "Id Meta: " + pgMetas.getIdTipoMeta() + " - Id Actividad: " + pgAct.getIdentificadorAnoPap() + ",<br />";
                }
            }
        }

        if (!metaValida) {
            JsfUtil.addErrorMessage("Existen METAS con datos incompletos, realizar verificación de los datos.<br />" + metas);
        }
        if (!actividadValida) {
            JsfUtil.addErrorMessage("Existen ACTIVIDADES con datos incompletos, realizar verificación de los datos.<br />" + actividades);
        }

        if (metaValida && actividadValida) {
            for (PgMetas pgMetas : lstMetas) {
                if (pgMetas.getEliminado()) {
                    continue;
                }
                BigDecimal ftePpalAct = BigDecimal.ZERO;
                BigDecimal fteContAct = BigDecimal.ZERO;

                planGlobalBo.saveMeta(pgMetas);
                //persistir actividades
                for (PgActividades pgAct : pgMetas.getLstPgActividades()) {
                    if (pgAct.getEliminado()) {
                        continue;
                    }
                    if (pgAct.getMeta() == pgMetas.getMeta()) {
                        BigDecimal ftePpalRecurso = BigDecimal.ZERO;
                        BigDecimal fteContRecurso = BigDecimal.ZERO;

                        planGlobalBo.saveActividad(pgAct);
                        //Persistir Recursos
                        for (PoaRecursos poaRecur : pgAct.getLstPoaRecursos()) {
                            if (poaRecur.getEliminado()) {
                                continue;
                            }
                            poaRecur.setActividad(pgAct.getActividad());
                            planGlobalBo.saveRecurso(poaRecur);
                            poaRecur.getRecursosSaldos(true).setRecurso(poaRecur.getRecurso());
                            planGlobalBo.saveRecursoSaldo(poaRecur.getRecursosSaldos(true));
                            ftePpalRecurso = ftePpalRecurso.add(poaRecur.getRecursosSaldos(true).getMontoPreliminar());

                            if (contrapartida) {
                                poaRecur.getRecursosSaldos(false).setRecurso(poaRecur.getRecurso());
                                planGlobalBo.saveRecursoSaldo(poaRecur.getRecursosSaldos(false));
                                fteContRecurso = fteContRecurso.add(poaRecur.getRecursosSaldos(false).getMontoPreliminar());
                            }
                        }

                        //actualizar montos de pg_actividades
                        if (idOrganismo == 5) {
                            pgAct.setFinanciamientoBanco(BigDecimal.ZERO);
                            pgAct.setFinanciamientoGoes(ftePpalRecurso);
                        } else {
                            pgAct.setFinanciamientoBanco(ftePpalRecurso);
                            pgAct.setFinanciamientoGoes(fteContRecurso);
                        }

                        planGlobalBo.saveActividad(pgAct);
                    }
                    ftePpalAct = ftePpalAct.add(pgAct.getFinanciamientoBanco());
                    fteContAct = fteContAct.add(pgAct.getFinanciamientoGoes());
                }

                if (idOrganismo == 5) {
                    pgMetas.setMontoBanco(BigDecimal.ZERO);
                    pgMetas.setMontoGoes(ftePpalAct);
                } else {
                    pgMetas.setMontoBanco(ftePpalAct);
                    pgMetas.setMontoGoes(fteContAct);
                }

                planGlobalBo.saveMeta(pgMetas);
            }

            if (showMsjEliminar) {
                cargaDeDatos();
            }
            JsfUtil.addSuccessMessage("Datos almacenados correctamente");
        }

        JsfUtil.updateComponent("frmPrincipal:menuBtns");
    }

    /*public void newActividad() {
     System.out.println("prueba nueva meta");
     if (pgMeta.getCodigoMeta() == null || (pgMeta.getMetaNombre() == null || pgMeta.getMetaNombre().isEmpty())) {
     JsfUtil.addWarningMessage("Debe de de completar la información de la META seleccionada.");
     } else {
     nivelEdicion = 2;
     if (lstMetas.isEmpty()) {
     newMeta();
     }
     pgMeta = lstMetas.get(0);
     newActividad(pgMeta);
     }
     }*/
    public void validarFechaEjecucionActividad() {
        System.out.println(pgActividad.getPgAnosPlanGlobal().getAno());
    }

    public void newActividad(ActionEvent event) {
        pgMeta = (PgMetas) event.getComponent().getAttributes().get("meta");
        if (pgMeta.getCodigoMeta() == null || (pgMeta.getMetaNombre() == null || pgMeta.getMetaNombre().isEmpty())) {
            JsfUtil.addWarningMessage("Debe de de completar la información de la META seleccionada.");
        } else {
            newActividad(pgMeta);
        }
    }

    private void newActividad(PgMetas pgMeta) {
        if (pgMeta != null) {
            showMeta = false;
            showActividad = true;
            nivelEdicion = 2;
            lstActividades = pgMeta.getLstPgActividades();
            RequestContext.getCurrentInstance().update("frmPrincipal:dlgMeta");
            if (visibleActividades) {
                RequestContext.getCurrentInstance().update("frmPrincipal:dlgActividad");
                //RequestContext.getCurrentInstance().execute("dlgActividad.show();");
                PrimeFaces.current().executeScript("PF('dlgActividad').show();");
            } else {
                if (lstActividades.isEmpty()) {
                    nuevo();
                }
                pgActividad = lstActividades.get(0);
                newRecurso();
            }
        }
        RequestContext.getCurrentInstance().update("frmPrincipal:barraEstado");
    }

    public void newRecurso() {
        if (pgActividad.getCodigoActividad() == null || pgActividad.getActividadNombre() == null || pgActividad.getActividadNombre().isEmpty()
                || pgActividad.getAnhoPg().equals("0") || pgActividad.getActividadInicioPrevisto() == null || pgActividad.getActividadFinPrevisto() == null) {
            JsfUtil.addWarningMessage("Debe de completar la información de la ACTIVIDAD seleccionada");
        } else {
            nivelEdicion = 3;
            showActividad = false;
            showRecurso = true;
            deshabilitarRecurso = true;
            lstRecuros = pgActividad.getLstPoaRecursos();
            JsfUtil.updateComponent("frmPrincipal:dlgActividad", "frmPrincipal:dlgRecurso");
            //RequestContext.getCurrentInstance().execute("dlgRecurso.show();");
            PrimeFaces.current().executeScript("PF('dlgRecurso').show();");
        }
    }

    public void cerrarPopupMeta() {
        nivelEdicion -= 1;
        showMeta = false;
    }

    public void cerrarPopupDlgMontoPap() {
        nivelEdicion -= 1;
        showMeta = true;
    }

    public void cerrarPopupVerificacionPap() {
        nivelEdicion -= 1;
        showDlgMontoPap = false;
    }

    public void cerrarPopupActividad() {
        nivelEdicion = 1;
        showMeta = true;
        showActividad = false;
        pgMeta.setLstPgActividades(lstActividades);
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgMeta");
    }

    public void cerrarPopupRecurso() {
        if (visibleActividades) {
            nivelEdicion = 2;
            showActividad = true;
            showRecurso = false;
            limpiarDatosRecurso();
            JsfUtil.updateComponent("frmPrincipal:dlgRecurso", "frmPrincipal:dlgActividad");
        } else {
            cerrarPopupActividad();
        }
    }

    private void limpiarDatosRecurso() {
        currentRecurso = null;
        clasificadorPresupuestario = "";
        recursoFtePpal = null;
        recursoFteCont = null;
    }

    public Boolean getShowMeta() {
        return showMeta;
    }

    /*public Boolean mostrarDlgMeta() {
     if (ifPopupOpen == false) {
     this.modoEdit = 1;
     if (this.modoEdit == 1) {
     this.showMeta = true;
     RequestContext.getCurrentInstance().update("frmPrincipal:dlgMeta");
     this.modoEdit = 1;
     }
     this.ifPopupOpen = true;
     }
     return showMeta;

     }*/
    public void primerPaso() {
        if (showVerificacionPap) {
            JsfUtil.addWarningMessage("Verificación y generación de los PAP individuales activo.");
        } else if (showDlgMontoPap) {
            JsfUtil.addWarningMessage("Edición de PAP individuales activo.");
        } else {
            numPaso = 1;
            showMeta = true;
            JsfUtil.updateComponent("frmPrincipal:dlgMeta");
        }
    }

    public void segundoPaso() {
        if (showMeta || showActividad || showRecurso) {
            JsfUtil.addWarningMessage("Digitación de Plan Global activo.");
        } else if (showDlgMontoPap) {
            JsfUtil.addWarningMessage("Edición de PAP individuales activo.");
        } else if (numPaso == 1 && idConvenio != null) {
            this.showVerificacionPap = true;
            JsfUtil.updateComponent("frmPrincipal:dlgVerificacionPap");
            //RequestContext.getCurrentInstance().execute("dlgMontoPap.show();");
        } else {
            JsfUtil.addWarningMessage("Debe de seleccionar un convenio.");
        }
    }

    public void tercerPaso() {
        if (showMeta || showActividad || showRecurso) {
            JsfUtil.addWarningMessage("Digitación de Plan Global activo.");
        } else if (showVerificacionPap) {
            JsfUtil.addWarningMessage("Verificación y generación de los PAP individuales activo.");
        } else if (numPaso == 1 && idConvenio != null) {
            this.showDlgMontoPap = true;
            JsfUtil.updateComponent("frmPrincipal:dlgMontoPap");
        } else {
            JsfUtil.addWarningMessage("Debe de seleccionar un convenio.");
        }
    }

    public void setShowMeta(Boolean showMeta) {
        this.showMeta = showMeta;
    }

    public Boolean getShowActividad() {
        return showActividad;
    }

    public void setShowActividad(Boolean showActividad) {
        this.showActividad = showActividad;
    }

    public Boolean getShowRecurso() {
        return showRecurso;
    }

    public void setShowRecurso(Boolean showRecurso) {
        this.showRecurso = showRecurso;
    }

    public List<PgMetas> getLstMetas() {
        return lstMetas;
    }

    public void setLstMetas(List<PgMetas> lstMetas) {
        this.lstMetas = lstMetas;
    }

    public List<PgActividades> getLstActividades() {
        return lstActividades;
    }

    public void setLstActividades(List<PgActividades> lstActividades) {
        this.lstActividades = lstActividades;
    }

    public List<PoaRecursos> getLstRecuros() {
        return lstRecuros;
    }

    public void setLstRecuros(List<PoaRecursos> lstRecuros) {
        this.lstRecuros = lstRecuros;
    }

    public PgActividades getPgActividad() {
        if (pgActividad == null) {
            pgActividad = new PgActividades();
        }
        return pgActividad;
    }

    public void setPgActividad(PgActividades pgActividad) {
        if (pgActividad != null) {
            this.pgActividad = pgActividad;
        }
    }

    public PoaRecursos getCurrentRecurso() {
        if (currentRecurso == null) {
            currentRecurso = new PoaRecursos();
            currentRecurso.setValor(BigDecimal.ZERO);
        }
        return currentRecurso;
    }

    public void validarFechaAdqRecurso() {
        if (getCurrentRecurso().getFechaAdquisicion().before(pgActividad.getActividadInicioPrevisto())
                || getCurrentRecurso().getFechaAdquisicion().after(pgActividad.getActividadFinPrevisto())) {
            JsfUtil.addWarningMessage("La fecha seleccionada esta fuera del rango de inicio y fin de la actividad selecionada.");
            getCurrentRecurso().setFechaAdquisicion(null);
        }
    }

    public void setCurrentRecurso(PoaRecursos currentRecurso) {
        if (currentRecurso != null) {
            this.currentRecurso = currentRecurso;
        }
    }

    public List<OrigenDeLosRecursos> getLstOrigenRecursos() {
        return planGlobalBo.getLstOrigenRecursos();
    }

    public List<PgConvenio> getLstConvenios() {
        return planGlobalBo.getLstConvenios(idOrigen);
    }

    public List<PgAnosPlanGlobal> getLstAnhosPg() {
        return planGlobalBo.getLstAnhosPg(idConvenio);
    }

    public List<PgCategorias> getLstPgCategorias() {
        return planGlobalBo.getLstPgCategorias();
    }

    public List<GCatalogoProductos> getLstCatalogoProductos() {
        return planGlobalBo.getLstCatalogoProductos();
    }

    public void cargarClasificador() {
        if (currentRecurso == null) {
            clasificadorPresupuestario = "";
        } else {
            if (currentRecurso.getProducto() == null) {
                clasificadorPresupuestario = "";
            } else {
                clasificadorPresupuestario = planGlobalBo.getCatalogoProductos(currentRecurso.getProducto()).getClasificadorPresupuestario();
            }
        }
    }

    public DetalleCompPorPlantilla getDetCompPlanilla() {
        if (detCompPlanilla == null) {
            detCompPlanilla = new DetalleCompPorPlantilla();
        }
        return detCompPlanilla;
    }

    public void setDetCompPlanilla(DetalleCompPorPlantilla detCompPlanilla) {
        this.detCompPlanilla = detCompPlanilla;
    }

    public List<TipoRecurso> getLstTipoRecurso() {
        return planGlobalBo.getLstTipoRecurso();
    }

    public PgAnosPlanGlobal getAnhosPgByKey(Integer id) {
        return planGlobalBo.getAnhosPgByKey(id);
    }

    public TipoDeMetas getTipoMetaByKey(Integer id) {
        return planGlobalBo.getTipoMetaByKey(id);
    }

    public List<EstadoEjecConvenio> getLstEstadoEjecConvenio() {
        return planGlobalBo.getLstEstadoEjecConvenio();
    }

    public void cargarPlantillaComportamiento() {
        detCompPlanilla = planGlobalBo.getDetCompPorPlantilla(idConvenio);
        if (detCompPlanilla != null) {
            visibleComponentes = detCompPlanilla.getAplicaNivelDeComponentes() == 1;
            visibleAnhoPap = detCompPlanilla.getDuracionPrevistaMasDeUnAno() == 1;
            visibleMetas = false;
            visibleActividades = false;
            visibleBtnActividades = false;
            switch (planGlobalBo.getNivelDesagregacionByPlanilla(detCompPlanilla.getIdPlantillaComportamiento()).getIdNivelDesagregacion()) {
                case 1:
                    visibleMetas = true;
                    visibleActividades = true;
                    break;
                case 2:
                    visibleMetas = true;
                    break;
                case 3:
                    visibleActividades = true;
                    visibleBtnActividades = true;
                    break;
            }
            contrapartida = planGlobalBo.isUnicaFuente(idConvenio);
            idOrganismo = planGlobalBo.getOrganismoByConvenio(idConvenio, true);
            if (idOrganismo == -1) {
                JsfUtil.addWarningMessage("Existe un problema en el organismo del convenio seleccionado.");
            } else {
                nombreFtePpal = planGlobalBo.getEntidadesFinanciadoras(idConvenio, true).getNombreCorto();
                nombreFteCont = planGlobalBo.getEntidadesFinanciadoras(idConvenio, false).getNombreCorto();
            }
        }
    }

    public Boolean getVisibleBtnActividades() {
        return visibleBtnActividades;
    }

    public Boolean getVisibleMetas() {
        return visibleMetas;
    }

    public Boolean getVisibleActividades() {
        return visibleActividades;
    }

    public void limpiarDesdeOrigen() {
        idConvenio = 0;
        limpiarPlanGlobal();
    }

    public void limpiarPlanGlobal() {
        detCompPlanilla = null;
        visibleComponentes = false;
        visibleMetas = false;
        lstMetas.clear();
        lstActividades.clear();
        lstRecuros.clear();
        existeSubComponentes = false;
        componenteValido = false;
    }

    public void armarTreeComponentes() {
        limpiarPlanGlobal();

        for (PgConvenio convenio : getLstConvenios()) {
            if (convenio.getConvenio() == idConvenio) {
                JsfUtil.crearVarSesion("fechaInicio", convenio.getFechaInicio());
                JsfUtil.crearVarSesion("fechaFin", convenio.getFechaFin());
                break;
            }
        }

        List<PgComponentes> lstComponente = planGlobalBo.getLstComponente(idConvenio);
        nodoComponentes = new DefaultTreeNode("Root", null);

        for (PgComponentes pgComponentes : lstComponente) {
            TreeNode node0 = new DefaultTreeNode(pgComponentes, nodoComponentes);

            setHijoComponente(node0, planGlobalBo.getLstSubComponente(pgComponentes.getComponente()));
            node0.setExpanded(true);
        }
        
        if(!existeSubComponentes){
            componenteValido = true;
        }

        if (lstComponente.isEmpty()) {
            lstMetas.clear();
        }

        for (PgConvenio pgConvenio : planGlobalBo.getLstConvenios(idOrigen)) {
            if (pgConvenio.getConvenio() == idConvenio) {
                idEstadoEjeConvenio = pgConvenio.getIdEstadoEjecProyecto();
                break;
            }
        }
        cargarPlantillaComportamiento();
    }

    public Integer getIdEstadoEjeConvenio() {
        return idEstadoEjeConvenio;
    }

    public void setIdEstadoEjeConvenio(Integer idEstadoEjeConvenio) {
        this.idEstadoEjeConvenio = idEstadoEjeConvenio;
    }

    private TreeNode setHijoComponente(TreeNode nodoPadre, List<PgComponentes> subLstComponentes) {
        for (PgComponentes pgComponentes : subLstComponentes) {
            if (!existeSubComponentes) {
                existeSubComponentes = true;
            }
            TreeNode node0 = new DefaultTreeNode(pgComponentes, nodoPadre);
            setHijoComponente(node0, planGlobalBo.getLstSubComponente(pgComponentes.getComponente()));
        }

        return nodoPadre;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public TreeNode getNodoComponentes() {
        return nodoComponentes;
    }

    public void setNodoComponentes(TreeNode nodoComponentes) {
        this.nodoComponentes = nodoComponentes;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        PgComponentes pgComp = (PgComponentes) event.getTreeNode().getData();
        idComponente = pgComp.getComponente();
        nombreComponente = pgComp.getComponenteNombre();
        if (existeSubComponentes || componenteValido) {
            componenteValido = (pgComp.getPg__Componente() != null && pgComp.getPg__Componente() > 0);
            if (componenteValido) {
                cargaDeDatos();
            }
        }
    }

    private void cargaDeDatos() {
        lstMetas = planGlobalBo.getLstMetas(idComponente);
        for (PgMetas pgMetas : lstMetas) {
            pgMetas.setLstPgActividades(planGlobalBo.getLstActividades(pgMetas.getMeta()));

            for (PgActividades pgAct : pgMetas.getLstPgActividades()) {
                if (pgAct.getLstPoaRecursos().isEmpty()) {
                    pgAct.setLstPoaRecursos(planGlobalBo.getLstPoaRecursos(pgAct.getActividad()));
                }
            }
        }

        if (visibleMetas) {
            this.disabledBtnNuevo = false;
            this.disableBtnGuardar = false;
            RequestContext.getCurrentInstance().update("frmPrincipal:menuBtns");
        }
    }

    public void onSelectElemento(SelectEvent event) {
        disabledBtnBorrar = false;
        selectRecurso();
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public void selectRecurso() {
        disabledBtnBorrar = false;
        recursoFtePpal = currentRecurso.getRecursosSaldos(true);
        if (contrapartida) {
            recursoFteCont = currentRecurso.getRecursosSaldos(false);
        }
        cargarClasificador();
        porcentajeRecurso = planGlobalBo.getPorcentajeByCategoria(currentRecurso.getIdCategoria());
        deshabilitarRecurso = currentRecurso.getEliminado();
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgRecurso");
    }

    public BigDecimal getTotalMontoGoesMeta() {
        BigDecimal totalMontoGoes = BigDecimal.ZERO;
        for (PgMetas pgMetas : lstMetas) {
            totalMontoGoes = totalMontoGoes.add(pgMetas.getMontoGoes());
        }
        return totalMontoGoes;
    }

    public BigDecimal getTotalMontoBancoMeta() {
        BigDecimal totalMontoBanco = BigDecimal.ZERO;
        for (PgMetas pgMetas : lstMetas) {
            totalMontoBanco = totalMontoBanco.add(pgMetas.getMontoBanco());
        }
        return totalMontoBanco;
    }

    public BigDecimal getTotalMeta() {
        if (contrapartida) {
            return getTotalMontoBancoMeta().add(getTotalMontoGoesMeta());
        } else {
            return getTotalMontoBancoMeta();
        }
    }

    public BigDecimal getTotalMontoGoesActividad() {
        BigDecimal totalMontoGoes = BigDecimal.ZERO;
        if (pgMeta != null) {
            for (PgActividades pgAct : pgMeta.getLstPgActividades()) {
                totalMontoGoes = totalMontoGoes.add(pgAct.getFinanciamientoGoes());
            }
        }
        return totalMontoGoes;
    }

    public BigDecimal getTotalMontoBancoActividad() {
        BigDecimal totalMontoBanco = BigDecimal.ZERO;
        if (pgMeta != null) {
            for (PgActividades pgAct : pgMeta.getLstPgActividades()) {
                totalMontoBanco = totalMontoBanco.add(pgAct.getFinanciamientoBanco());
            }
        }
        return totalMontoBanco;
    }

    public BigDecimal getTotalActividad() {
        if (contrapartida) {
            return getTotalMontoBancoActividad().add(getTotalMontoGoesActividad());
        } else {
            return getTotalMontoBancoActividad();
        }
    }

    public BigDecimal getTotalRecurso() {
        BigDecimal totalRecurso = BigDecimal.ZERO;
        if (pgActividad != null) {
            for (PoaRecursos poaRecurso : pgActividad.getLstPoaRecursos()) {
                totalRecurso = totalRecurso.add(poaRecurso.getValor());
            }
        }

        return totalRecurso;
    }

    public List<TipoDeMetas> getLstTipoMeta() {
        return lstTipoMeta;
    }

    public void setLstTipoMeta(List<TipoDeMetas> lstTipoMeta) {
        this.lstTipoMeta = lstTipoMeta;
    }

    public PgMetas getPgMeta() {
        if (pgMeta == null) {
            pgMeta = new PgMetas();
        }
        return pgMeta;
    }

    public void setPgMeta(PgMetas pgMeta) {
        this.pgMeta = pgMeta;
    }

    public String getClasificadorPresupuestario() {
        return clasificadorPresupuestario;
    }

    public void setClasificadorPresupuestario(String clasificadorPresupuestario) {
        this.clasificadorPresupuestario = clasificadorPresupuestario;
    }

    @FacesConverter(forClass = PgAnosPlanGlobal.class)
    public static class PgAnosPlanGlobalConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent uic, String value) {
            PlanGlobalBean pgB = (PlanGlobalBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "planGlobalBean");

            if (value == null || value.length() == 0) {
                return null;
            }

            return pgB.getAnhosPgByKey(getKey(value));
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
            if (object instanceof PgAnosPlanGlobal) {
                return getStringKey(((PgAnosPlanGlobal) object).getIdentificadorAnoPap());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PgAnosPlanGlobal.class.getName());
            }
        }
    }

    @FacesConverter(forClass = TipoDeMetas.class)
    public static class TipoDeMetasConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent uic, String value) {
            PlanGlobalBean pgB = (PlanGlobalBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "planGlobalBean");

            if (value == null || value.length() == 0) {
                return null;
            }

            return pgB.getTipoMetaByKey(getKey(value));
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
            if (object instanceof TipoDeMetas) {
                return getStringKey(((TipoDeMetas) object).getIdTipoMeta());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoDeMetas.class.getName());
            }
        }
    }

    public Boolean getVisibleComponentes() {
        return visibleComponentes;
    }

    public Boolean getVisibleAnhoPap() {
        return visibleAnhoPap;
    }

    public String getNombreFtePpal() {
        return nombreFtePpal;
    }

    public String getNombreFteCont() {
        return nombreFteCont;
    }

    public Boolean getDeshabilitarRecurso() {
        return deshabilitarRecurso;
    }

    public PoaRecursosSaldos getRecursoFtePpal() {
        if (recursoFtePpal == null) {
            recursoFtePpal = new PoaRecursosSaldos();
        }
        return recursoFtePpal;
    }

    public void setRecursoFtePpal(PoaRecursosSaldos recursoFtePpal) {
        this.recursoFtePpal = recursoFtePpal;
    }

    public PoaRecursosSaldos getRecursoFteCont() {
        if (recursoFteCont == null) {
            recursoFteCont = new PoaRecursosSaldos();
        }
        return recursoFteCont;
    }

    public void setRecursoFteCont(PoaRecursosSaldos recursoFteCont) {
        this.recursoFteCont = recursoFteCont;
    }

    public List<CategoriasDto> getLstCategorias() {
        return planGlobalBo.getCategoriasDto(idConvenio);
    }

    public List<ComponentesDto> getLstComponentes() {
        return planGlobalBo.getComponentesDto(idConvenio);
    }

    public List<MontosPapDto> getLstMontoPap() {
        return planGlobalBo.getMontoPapByConvenio(idConvenio);
    }

    public Integer getIdPoa() {
        return idPoa;
    }

    public void setIdPoa(Integer idPoa) {
        this.idPoa = idPoa;
    }

    private Date getFechaComponente(SelectEvent event, boolean fechaInicio) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String parametro = facesContext.getExternalContext().getRequestParameterMap().get("parametro");
        RequestContext.getCurrentInstance().update("tblActividades:" + parametro + ":calendario");
        if (fechaInicio) {
            return getLstActividades().get(Integer.parseInt(parametro)).getActividadInicioPrevisto();
        } else {
            return getLstActividades().get(Integer.parseInt(parametro)).getActividadFinPrevisto();
        }
    }

    public void handleDateSelectInicio(SelectEvent event) {
        Date fechaFin = getFechaComponente(event, false);
        Date fechaInicio = (Date) ((Calendar) event.getSource()).getValue();

        if (fechaInicio != null && fechaFin != null) {
            if (fechaInicio.after(fechaFin)) {
                JsfUtil.addErrorMessage("La fecha de Inicio debe de ser menor a la fecha de Fin.");
                ((Calendar) event.getSource()).setValue(null);
            }
        }
    }

    public void handleDateSelectFin(SelectEvent event) {
        Date fechaInicio = getFechaComponente(event, true);
        Date fechaFin = (Date) ((Calendar) event.getSource()).getValue();

        if (fechaInicio != null && fechaFin != null) {
            if (fechaFin.before(fechaInicio)) {
                JsfUtil.addErrorMessage("La fecha de fin debe de ser mayor a la fecha de inicio.");
                ((Calendar) event.getSource()).setValue(null);
            }
        }
    }

    public void calcularMontos() {
        if (currentRecurso != null) {
            BigDecimal newMontoFtePpal = currentRecurso.getValor().multiply(porcentajeRecurso.divide(new BigDecimal(100)));
            DecimalFormat df = new DecimalFormat("###0.00");
            newMontoFtePpal = new BigDecimal(df.format(newMontoFtePpal));

            recursoFtePpal.setMontoPreliminar(newMontoFtePpal);
            recursoFteCont.setMontoPreliminar(currentRecurso.getValor().add(newMontoFtePpal.negate()));
        }
    }

    public Boolean getDisabledBtnNuevo() {
        return disabledBtnNuevo;
    }

    public void setDisabledBtnNuevo(Boolean disabledBtnNuevo) {
        this.disabledBtnNuevo = disabledBtnNuevo;
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

    public Boolean getDiabledBtnRecuperar() {
        return diabledBtnRecuperar;
    }

    public void setDiabledBtnRecuperar(Boolean diabledBtnRecuperar) {
        this.diabledBtnRecuperar = diabledBtnRecuperar;
    }

    public Boolean getDisabledBtnImprimir() {
        return disabledBtnImprimir;
    }

    public void setDisabledBtnImprimir(Boolean disabledBtnImprimir) {
        this.disabledBtnImprimir = disabledBtnImprimir;
    }

    public Boolean getShowVerificacionPap() {
        return showVerificacionPap;
    }

    /*public Boolean mostrarDlgVerificacionPap() {
     if (ifPopupOpen == false) {
     if (modoEdit == 2) {
     this.showVerificacionPap = true;
     RequestContext.getCurrentInstance().update("frmPrincipal:dlgVerificacionPap");
     RequestContext.getCurrentInstance().execute("dlgVerificacionPap.show();");
     } else {
     JsfUtil.addSuccessMessage("Completar paso" + this.modoEdit);
     }
     this.ifPopupOpen = true;
     }
     return showVerificacionPap;
     }

     public Boolean mostrarDlgMontoPap() {
     if (ifPopupOpen == false) {
     if (modoEdit == 3) {
     this.showDlgMontoPap = true;
     RequestContext.getCurrentInstance().update("frmPrincipal:dlgMontoPap");
     RequestContext.getCurrentInstance().execute("dlgMontoPap.show();");
     } else {
     JsfUtil.addSuccessMessage("Completar paso" + this.modoEdit);
     }
     ifPopupOpen = true;
     }
     return showDlgMontoPap;
     }*/
    public void setShowVerificacionPap(Boolean showVerificacionPap) {
        this.showVerificacionPap = showVerificacionPap;
    }

    public Boolean getShowDlgMontoPap() {
        return showDlgMontoPap;
    }

    public void setShowDlgMontoPap(Boolean showDlgMontoPap) {
        this.showDlgMontoPap = showDlgMontoPap;
    }

    /*private void activarBonotes(Boolean valor) {
     disableBtnGuardar = valor;
     disabledBtnBorrar = valor;
     disabledBtnImprimir = valor;
     disabledBtnNuevo = valor;

     JsfUtil.updateComponent("frmPrincipal:menuBtns");
     }*/
}