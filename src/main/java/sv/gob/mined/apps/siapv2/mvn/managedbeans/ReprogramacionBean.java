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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.ReprogramacionBo;
import sv.gob.mined.apps.siapv2.mvn.dto.DetReprogramacionDto;
import sv.gob.mined.apps.siapv2.mvn.dto.ElementoReproDto;
import sv.gob.mined.apps.siapv2.mvn.modelo.DetalleCompPorPlantilla;
import sv.gob.mined.apps.siapv2.mvn.modelo.EstadoPoaReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.OrigenDeLosRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgConvenio;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaAnosPlanAccion;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaDetalleReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursosSaldos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.TiposPoaReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;

/**
 *
 * @author Infosoft
 */
@Component(value = "reprogramacionBean")
@Scope(value = "view")
public class ReprogramacionBean {

    private Integer tipoReprogramacion = 1;
    private Integer idOrigen = 0;
    private Integer idConvenio = 0;
    private Integer estadoPoa = 0;
    private Integer nivelEdicion = 0;
    /*
     * 1 - Creación/Selección de Ficha de reprogramación
     * 2 - Detalle de Reprogramación
     * 3 - Afectaciones presupestarias y administrativas
     */
    private Integer numPaso = 1;
    private Integer tipoOperacion = 0; // 1 disminucion y 2 aumento
    private final Integer DISMINUCION = 1;
    private final Integer AUMENTO = 2;
    private Boolean showFichaRepro = false;
    private Boolean showAumento = true;
    private Boolean showDisminucion = true;
    private Boolean showEditReprogramacion = false;
    private Boolean showMeta = false;
    private Boolean showActividad = false;
    private Boolean showRecurso = false;
    private Boolean contrapartida = true;
    private Boolean deshabilitar = true;
    private Boolean disabledBoton = true;
    private Boolean disabledBotonGuardar = true;
    private Boolean disBtnAddDism = false;
    private Boolean disBtnAddAum = false;
    private String tituloDialog;
    private String totalAumentos = "$ 0.00";
    private String totalDisminuciones = "$ 0.00";
    private String total = "$ 0.00";
    private String pgCategoria;
    private String oeg;
    private String nombreFtePpal;
    private String nombreFteCont;
    private BigDecimal montoReprogramar = BigDecimal.ZERO;
    private BigDecimal newMontoFtePpal = BigDecimal.ZERO;
    private BigDecimal newMontoFteCont = BigDecimal.ZERO;
    private BigDecimal newMontoTotal = BigDecimal.ZERO;
    private BigDecimal totalDismFtePpal = BigDecimal.ZERO;
    private BigDecimal totalDismFteCont = BigDecimal.ZERO;
    private BigDecimal totalAumFtePpal = BigDecimal.ZERO;
    private BigDecimal totalAumFteCont = BigDecimal.ZERO;
    private PoaReprogramaciones poaReprogramacion = new PoaReprogramaciones();
    private DetReprogramacionDto detProgramacion;
    private PoaRecursos poaRecurso;
    private PoaRecursosSaldos ftePpal;
    private PoaRecursosSaldos fteCont;
    private List<DetReprogramacionDto> lstDetalleReproDisminucion = new ArrayList<DetReprogramacionDto>();
    private List<DetReprogramacionDto> lstDetalleReproAumento = new ArrayList<DetReprogramacionDto>();
    private List<PoaReprogramaciones> lstReprogramaciones;
    private List<OrigenDeLosRecursos> lstOrigenRecursos;
    private List<PoaRecursos> lstRecursos;
    private DecimalFormat df = new DecimalFormat("$ #,##0.00");
    @Autowired
    private ReprogramacionBo reprogramacionBo;

    public ReprogramacionBean() {
    }

    @PostConstruct
    public void init() {
        lstOrigenRecursos = reprogramacionBo.getLstOrigenRecursos();
    }

    public Boolean getDisBtnAddDism() {
        return disBtnAddDism;
    }

    public Boolean getDisBtnAddAum() {
        return disBtnAddAum;
    }

    public String getTotalDismFtePpal() {
        return df.format(totalDismFtePpal);
    }

    public String getTotalDismFteCont() {
        return df.format(totalDismFteCont);
    }

    public String getTotalDismSaldo() {
        return df.format(totalDismFtePpal.add(totalDismFteCont));
    }

    public String getTotalAumFtePpal() {
        return df.format(totalAumFtePpal);
    }

    public String getTotalAumFteCont() {
        return df.format(totalAumFteCont);
    }

    public String getTotalAumSaldo() {
        return df.format(totalAumFtePpal.add(totalAumFteCont));
    }

    public Boolean getDisabledBotonGuardar() {
        return disabledBotonGuardar;
    }

    public void setDisabledBotonGuardar(Boolean disabledBotonGuardar) {
        this.disabledBotonGuardar = disabledBotonGuardar;
    }

    public Integer getNumPaso() {
        return numPaso;
    }

    public void setNumPaso(Integer numPaso) {
        this.numPaso = numPaso;
    }

    public Boolean getDisabledBoton() {
        return disabledBoton;
    }

    public void setDisabledBoton(Boolean disabledBoton) {
        this.disabledBoton = disabledBoton;
    }

    public ReprogramacionBo getReprogramacionBo() {
        return reprogramacionBo;
    }

    public Boolean getDeshabilitar() {
        return deshabilitar;
    }

    public Integer getNivelEdicion() {
        return nivelEdicion;
    }

    public Boolean getContrapartida() {
        return contrapartida;
    }

    public Boolean getShowFichaRepro() {
        return showFichaRepro;
    }

    public void setShowFichaRepro(Boolean showFichaRepro) {
        this.showFichaRepro = showFichaRepro;
    }

    public Boolean getShowEditReprogramacion() {
        return showEditReprogramacion;
    }

    public void setShowEditReprogramacion(Boolean showEditReprogramacion) {
        this.showEditReprogramacion = showEditReprogramacion;
    }

    public void primerPaso() {
        numPaso = 1;
        nivelEdicion = 1;
        showFichaRepro = true;
        disabledBoton = false;
        JsfUtil.updateComponent("frmPrincipal:dlgFicha", "frmPrincipal:menuBtns");
    }

    public void segundoPaso() {
        if (validarSelectRepro()) {
            numPaso = 2;
            showEditReprogramacion = true;
            disabledBoton = false;

            //Cargar detalle de reprogramación
            poaReprogramacion.setLstDetalleReprogramacion(reprogramacionBo.getLstDetalleReprogramaciones(poaReprogramacion.getReprogramacion()));
            for (PoaDetalleReprogramaciones detReprogramacion : poaReprogramacion.getLstDetalleReprogramacion()) {
                Boolean agregar = true;
                if (contrapartida) {
                    DetReprogramacionDto operacion = new DetReprogramacionDto();
                    if (!lstDetalleReproDisminucion.isEmpty()) {
                        for (DetReprogramacionDto detReprogramacionDto : lstDetalleReproDisminucion) {
                            if (detReprogramacionDto.getRecurso() == detReprogramacion.getRecurso()) {
                                operacion = detReprogramacionDto;
                                agregar = false;
                                break;
                            }
                        }
                    }
                    if (!lstDetalleReproDisminucion.isEmpty()) {
                        if (agregar) {
                            for (DetReprogramacionDto detReprogramacionDto : lstDetalleReproAumento) {
                                if (detReprogramacionDto.getRecurso() == detReprogramacion.getRecurso()) {
                                    operacion = detReprogramacionDto;
                                    agregar = false;
                                    break;
                                }
                            }
                        }
                    }

                    pgCategoria = reprogramacionBo.categoriaByRecurso(detReprogramacion.getRecurso());
                    oeg = reprogramacionBo.oegByRecurso(detReprogramacion.getRecurso());

                    operacion.setActividad(detReprogramacion.getPoaRecurso().getActividad());
                    operacion.setCategoria(pgCategoria);
                    operacion.setClasificadorPresupuestario(oeg);
                    operacion.setDescripcion(detReprogramacion.getPoaRecurso().getObservacion());
                    operacion.setId(detReprogramacion.getPoaDetalleReprogramacion());
                    operacion.setIdCategoria(reprogramacionBo.idCategoriaByRecurso(detReprogramacion.getRecurso()));
                    operacion.setMontoPrograFtePpal(detReprogramacion.getPoaRecurso().getRecursosSaldos(true).getMontoPreliminar());
                    operacion.setMontoPrograFteCont(detReprogramacion.getPoaRecurso().getRecursosSaldos(false).getMontoPreliminar());
                    if (detReprogramacion.getOrganismo() != 5) {
                        operacion.setMontoReproFtePpal(detReprogramacion.getPoaRecurso().getRecursosSaldos(true).getMontoReprogramado().add(detReprogramacion.getMonto()));
                    } else {
                        operacion.setMontoReproFteCont(detReprogramacion.getPoaRecurso().getRecursosSaldos(false).getMontoReprogramado().add(detReprogramacion.getMonto()));
                    }
                    operacion.setOperacion(detReprogramacion.getOperacion());
                    operacion.setPoaReprogramacion(poaReprogramacion.getReprogramacion());
                    operacion.setRecurso(detReprogramacion.getRecurso());
                    setPoaRecurso(detReprogramacion.getPoaRecurso());
                    calcularMontos();
                    if (agregar) {
                        if (detReprogramacion.getOperacion().equals("-")) {
                            lstDetalleReproDisminucion.add(operacion);
                            totalDismFtePpal = totalDismFtePpal.add(operacion.getMontoPrograFtePpal());
                            totalDismFteCont = totalDismFteCont.add(operacion.getMontoPrograFteCont());
                        } else {
                            lstDetalleReproAumento.add(operacion);
                            totalAumFtePpal = totalAumFtePpal.add(operacion.getMontoPrograFtePpal());
                            totalAumFteCont = totalAumFteCont.add(operacion.getMontoPrograFteCont());
                        }
                    }
                }
            }
            TabView tv = (TabView) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrincipal").findComponent("acordion").findComponent("pnlDetalleReprogramacion");
            tv.setActiveIndex(0);
            if (poaReprogramacion.getTipoPoaReprog() == 3) {
                disBtnAddAum = false;
            } else {
                disBtnAddDism = false;
                disBtnAddAum = true;
            }
            JsfUtil.updateComponent("frmPrincipal:dlgDetalle", "frmPrincipal:menuBtns");
        }
    }
    
    public void tercerPaso(){
        if (validarSelectRepro()) {
            numPaso = 3;
            showEditReprogramacion = true;
            disabledBoton = false;
        }
    }
    
    private Boolean validarSelectRepro(){
        if (poaReprogramacion != null && poaReprogramacion.getPoa() != null) {
            return true;
        } else {
            JsfUtil.addWarningMessage("Debe de seleccionar una reprogramación.");
            return false;
        }
    }

    public void setContrapartida(Boolean contrapartida) {
        this.contrapartida = contrapartida;
    }

    public String getTituloDialog() {
        return tituloDialog;
    }

    public void setTituloDialog(String tituloDialog) {
        this.tituloDialog = tituloDialog;
    }

    public String getTotalAumentos() {
        return totalAumentos;
    }

    public void setTotalAumentos(String totalAumentos) {
        this.totalAumentos = totalAumentos;
    }

    public String getTotalDisminuciones() {
        return totalDisminuciones;
    }

    public void setTotalDisminuciones(String totalDisminuciones) {
        this.totalDisminuciones = totalDisminuciones;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getNumColSpanFuentes() {
        if (contrapartida) {
            return 3;
        } else {
            return 1;
        }
    }

    public Boolean getShowAumento() {
        return showAumento;
    }

    public Boolean getShowDisminucion() {
        return showDisminucion;
    }

    public void showDisminucionAumento() {
        showAumento = true;
        showDisminucion = true;

        switch (tipoReprogramacion) {
            case 1://entre recursos existentes
                break;
            case 2://recursos nuevos
                break;
            case 3://aumento
                showDisminucion = false;
                break;
            case 4://disminucion
                showAumento = false;
                break;
            default:
                break;
        }
    }

    public List<EstadoPoaReprogramaciones> getEstadoPoaReprogramacion() {
        return reprogramacionBo.findAllEstadoPoaReprogramacion();
    }

    public List<TiposPoaReprogramaciones> getTiposPoaReprogramaciones() {
        return reprogramacionBo.findAllTiposPoaReprogramaciones();
    }

    public Integer getTipoReprogramacion() {
        return tipoReprogramacion;
    }

    public void setTipoReprogramacion(Integer tipoReprogramacion) {
        this.tipoReprogramacion = tipoReprogramacion;
    }

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public List<OrigenDeLosRecursos> getLstOrigenRecursos() {
        return lstOrigenRecursos;
    }

    public List<PgConvenio> getLstConvenios() {
        return reprogramacionBo.getLstConvenio(idOrigen);
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        if (idConvenio != null && idConvenio != 0) {
            this.idConvenio = idConvenio;
        }
    }

    public DetalleCompPorPlantilla getDetCompPlantilla() {
        if (idConvenio == 0) {
            return new DetalleCompPorPlantilla();
        } else {
            return reprogramacionBo.getDetCompPorPlantilla(idConvenio);
        }
    }

    public PoaReprogramaciones getPoaReprogramacion() {
        if (poaReprogramacion == null) {
            poaReprogramacion = new PoaReprogramaciones();
        }
        return poaReprogramacion;
    }

    public void setPoaReprogramacion(PoaReprogramaciones poaReprogramacion) {
        this.poaReprogramacion = poaReprogramacion;
    }

    public void cerrarPopupCabecera() {
        RequestContext.getCurrentInstance().execute("frmPrincipal:dlgDetalle");
    }

    public List<DetReprogramacionDto> getLstDetalleReproDisminucion() {
        return lstDetalleReproDisminucion;
    }

    public void setLstDetalleReproDisminucion(List<DetReprogramacionDto> lstDetalleReproDisminucion) {
        this.lstDetalleReproDisminucion = lstDetalleReproDisminucion;
    }

    public List<DetReprogramacionDto> getLstDetalleReproAumento() {
        return lstDetalleReproAumento;
    }

    public void setLstDetalleReproAumento(List<DetReprogramacionDto> lstDetalleReproAumento) {
        this.lstDetalleReproAumento = lstDetalleReproAumento;
    }

    public void addDetalleDiminucion() {
        agregarDetalleReprogramacion(true);
        JsfUtil.updateComponent("frmPrincipal:acordion:pnlDetalleReprogramacion:tblDisminuacion");
    }

    public void addDetalleAumento() {
        agregarDetalleReprogramacion(false);
        JsfUtil.updateComponent("frmPrincipal:acordion:pnlDetalleReprogramacion:tblAumento");
    }

    private void agregarDetalleReprogramacion(Boolean disminuacion) {
        DetReprogramacionDto det = new DetReprogramacionDto();
        det.setActividad(0);
        det.setPoaReprogramacion(poaReprogramacion.getReprogramacion());

        if (disminuacion) {
            tipoOperacion = DISMINUCION;
            det.setOperacion("-");
            lstDetalleReproDisminucion.add(det);
        } else {
            tipoOperacion = AUMENTO;
            det.setOperacion("+");
            lstDetalleReproAumento.add(det);
        }
    }

    public List<PoaReprogramaciones> getLstReprogramaciones() {
        if (lstReprogramaciones == null) {
            lstReprogramaciones = new ArrayList<PoaReprogramaciones>();
        }
        return lstReprogramaciones;
    }

    public void cargarReprogramaciones() {
        if (getPoaReprogramacion().getPoa() == null) {
            lstReprogramaciones.clear();
        } else {
            lstReprogramaciones = reprogramacionBo.getLstReprogramaciones(poaReprogramacion.getPoa());
        }
    }

    public List<PoaAnosPlanAccion> getLstPoaAnhosPlanAccion() {
        return reprogramacionBo.getLstPoaAnhosPlanAccion(idConvenio);
    }

    public void nuevo() {
        switch (numPaso) {
            case 1:
                nivelEdicion = 1;
                disabledBotonGuardar = false;
                if (showMeta) {//Plan global
                } else {//Reprogramacion
                    deshabilitar = false;
                    switch (nivelEdicion) {
                        case 1:
                            JsfUtil.updateComponent("frmPrincipal:dlgFicha");
                            break;
                        case 2:
                            break;
                    }
                }
                break;
            case 2:
                TabView tv = (TabView) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrincipal").findComponent("acordion").findComponent("pnlDetalleReprogramacion");
                if (tv.getActiveIndex() == 0 && poaReprogramacion.getTipoPoaReprog() == 3) {
                    addDetalleAumento();
                } else if (tv.getActiveIndex() == 0) {
                    addDetalleDiminucion();
                } else {
                    addDetalleAumento();
                }
                break;
        }
    }

    public void modificar() {
        nivelEdicion = 2;
        switch (numPaso) {
            case 1:
                deshabilitar = false;
                JsfUtil.updateComponent("frmPrincipal:dlgFicha");
                break;
            case 2:
                break;
        }
    }

    public void guardar() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        if (sessionMap.containsKey("reprogramacion")) {
        } else {
            switch (nivelEdicion) {
                case 1:
                    if (poaReprogramacion.getPoa() != null && poaReprogramacion.getFechaDeInsercion() != null
                            && poaReprogramacion.getJustificacion() != null && poaReprogramacion.getTipoPoaReprog() > 0) {
                        if (poaReprogramacion.getReprogramacion() == null) {
                            poaReprogramacion.setEstadoPoaReprogramaciones(1);
                        }
                        reprogramacionBo.saveFichaReprogramacion(poaReprogramacion);
                        cargarReprogramaciones();
                        JsfUtil.mensajePersistencia();
                        JsfUtil.updateComponent("frmPrincipal:dlgFicha", "frmPrincipal:tblReprogramaciones");
                    } else {
                        JsfUtil.addWarningMessage("Debe de completar todos los capos requeridos");
                    }
                    break;
                case 2:
                    for (PoaDetalleReprogramaciones detReprogramacion : poaReprogramacion.getLstDetalleReprogramacion()) {
                        reprogramacionBo.saveDetalleReprogramacion(detReprogramacion);
                    }
                    JsfUtil.updateComponent("frmPrincipal:dlgDetalle");
                    break;
            }
        }
    }

    public void showFichaReprogramacion() {
        showFichaRepro = true;
    }

    public void cerrarFichaReprogramacion() {
        showFichaRepro = false;
        disabledBoton = true;
        deshabilitar = true;
        JsfUtil.updateComponent("frmPrincipal:dlgFicha", "frmPrincipal:menuBtns");
    }

    /**
     * Carga los datos de la reprogramación seleccionada
     */
    public void editReprogramacion() {
        tipoReprogramacion = poaReprogramacion.getTipoPoaReprog();
        disabledBotonGuardar = false;
        showDisminucionAumento();
        switch (nivelEdicion) {
            case 1:
                nivelEdicion = 2;
                break;
        }
    }

    public void cerrarEditReprogramacion() {
        showFichaRepro = true;
        showEditReprogramacion = false;
        disabledBoton = true;
    }

    public void cerrarPopupMetas() {
        showMeta = true;
    }

    public List<ElementoReproDto> getLstElementoReprog() {
        return reprogramacionBo.getLstElementoRepro(idConvenio);
    }

    public List<PoaRecursos> getLstRecursos() {
        return lstRecursos;
    }

    public void findRecursosByActividad() {
        if (detProgramacion.getActividad() != null && detProgramacion.getActividad() > 0) {
            lstRecursos = reprogramacionBo.getLstRecursosByActividad(detProgramacion.getActividad());
        }
        poaRecurso = null;
        pgCategoria = null;
    }

    public void findDetRecurso() {
        poaRecurso = reprogramacionBo.getRecursoById(detProgramacion.getRecurso());
        ftePpal = poaRecurso.getRecursosSaldos(true);
        fteCont = poaRecurso.getRecursosSaldos(false);
        pgCategoria = reprogramacionBo.categoriaByRecurso(poaRecurso.getRecurso());
        oeg = reprogramacionBo.oegByRecurso(poaRecurso.getRecurso());
        montoReprogramar = detProgramacion.getMontoReproFtePpal().add(detProgramacion.getMontoReproFteCont());

        detProgramacion.setIdCategoria(reprogramacionBo.idCategoriaByRecurso(poaRecurso.getRecurso()));
        detProgramacion.setDescripcion(poaRecurso.getObservacion());
        detProgramacion.setCategoria(pgCategoria);
        detProgramacion.setClasificadorPresupuestario(oeg);
        detProgramacion.setMontoPrograFtePpal(ftePpal.getMontoPreliminar());
        detProgramacion.setMontoPrograFteCont(fteCont.getMontoPreliminar());
    }

    public DetReprogramacionDto getDetProgramacion() {
        if (detProgramacion == null) {
            detProgramacion = new DetReprogramacionDto();
        }
        return detProgramacion;
    }

    public void setDetProgramacion(DetReprogramacionDto detProgramacion) {
        if (detProgramacion != null) {
            this.detProgramacion = detProgramacion;
        }
    }

    public void editDetReprogramacion() {
        findRecursosByActividad();
        if (detProgramacion.getRecurso() != null && detProgramacion.getRecurso() > 0) {
            findDetRecurso();
            calcularMontos();
        }
        showEditReprogramacion = true;
        JsfUtil.updateComponent("frmPrincipal:dlgEditDetRepro");
        //RequestContext.getCurrentInstance().execute("dlgEditDetRepro.show();");
        PrimeFaces.current().executeScript("PF('dlgEditDetRepro').show();");
    }

    public PoaRecursos getPoaRecurso() {
        if (poaRecurso == null) {
            poaRecurso = new PoaRecursos();
        }
        return poaRecurso;
    }

    public void setPoaRecurso(PoaRecursos poaRecurso) {
        this.poaRecurso = poaRecurso;
    }

    public String getPgCategoria() {
        return pgCategoria;
    }

    public void setPgCategoria(String pgCategoria) {
        this.pgCategoria = pgCategoria;
    }

    public String getOeg() {
        return oeg;
    }

    public void setOeg(String oeg) {
        this.oeg = oeg;
    }

    public PoaRecursosSaldos getFtePpal() {
        if (ftePpal == null) {
            ftePpal = new PoaRecursosSaldos();
            ftePpal.setMontoPreliminar(BigDecimal.ZERO);
        }
        return ftePpal;
    }

    public void setFtePpal(PoaRecursosSaldos ftePpal) {
        this.ftePpal = ftePpal;
    }

    public PoaRecursosSaldos getFteCont() {
        if (fteCont == null) {
            fteCont = new PoaRecursosSaldos();
            fteCont.setMontoPreliminar(BigDecimal.ZERO);
        }
        return fteCont;
    }

    public void setFteCont(PoaRecursosSaldos fteCont) {
        this.fteCont = fteCont;
    }

    public BigDecimal getTotalRecursoPreliminar() {
        return getFtePpal().getMontoPreliminar().add(getFteCont().getMontoPreliminar());
    }

    public void setTotalRecursoPreliminar(BigDecimal total) {
    }

    public BigDecimal getMontoReprogramar() {
        return montoReprogramar;
    }

    public void setMontoReprogramar(BigDecimal montoReprogramar) {
        this.montoReprogramar = montoReprogramar;
    }

    public void calcularMontos() {
        DecimalFormat dff = new DecimalFormat("###0.00");
        BigDecimal porcentaje = reprogramacionBo.getPorcentajeFtePpal(poaRecurso.getRecurso());
        newMontoFtePpal = montoReprogramar.multiply(porcentaje.divide(new BigDecimal(100)));
        newMontoFtePpal = new BigDecimal(dff.format(newMontoFtePpal));
        newMontoFteCont = montoReprogramar.add(newMontoFtePpal.negate());
        newMontoFtePpal = getPoaRecurso().getRecursosSaldos(true).getMontoPreliminar().add(newMontoFtePpal.negate());
        newMontoFteCont = getPoaRecurso().getRecursosSaldos(false).getMontoPreliminar().add(newMontoFteCont.negate());
        newMontoTotal = newMontoFtePpal.add(newMontoFteCont);
    }

    public Integer getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Integer tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public BigDecimal getNewMontoFtePpal() {
        return newMontoFtePpal;
    }

    public void setNewMontoFtePpal(BigDecimal newMontoFtePpal) {
        this.newMontoFtePpal = newMontoFtePpal;
    }

    public BigDecimal getNewMontoFteCont() {
        return newMontoFteCont;
    }

    public void setNewMontoFteCont(BigDecimal newMontoFteCont) {
        this.newMontoFteCont = newMontoFteCont;
    }

    public BigDecimal getNewMontoTotal() {
        return newMontoTotal;
    }

    public void setNewMontoTotal(BigDecimal newMontoTotal) {
        this.newMontoTotal = newMontoTotal;
    }

    /**
     *
     */
    public void aceptarEditDetRepro() {
        PoaDetalleReprogramaciones detReproFtePpal = new PoaDetalleReprogramaciones();
        detReproFtePpal.setActividad(poaRecurso.getActividad());
        detReproFtePpal.setEstadoDeEliminacion(0);
        detReproFtePpal.setFechaDeInsercion(new Date());
        detReproFtePpal.setMonto(poaRecurso.getMontoPreliminarFtePpal().add(newMontoFtePpal.negate()).add(poaRecurso.getMontoReprogramadoFtePpal()));
        detReproFtePpal.setOperacion(detProgramacion.getOperacion());
        detReproFtePpal.setOrganismo(reprogramacionBo.getOrganismoByConvenio(idConvenio, true));
        detReproFtePpal.setPoaReprogramacion(poaReprogramacion.getReprogramacion());
        detReproFtePpal.setRecurso(poaRecurso.getRecurso());
        poaReprogramacion.getLstDetalleReprogramacion().add(detReproFtePpal);
        detProgramacion.setMontoReproFtePpal(detReproFtePpal.getMonto());

        if (contrapartida) {
            PoaDetalleReprogramaciones detReproFteCont = new PoaDetalleReprogramaciones();
            detReproFteCont.setActividad(poaRecurso.getActividad());
            detReproFteCont.setEstadoDeEliminacion(0);
            detReproFteCont.setFechaDeInsercion(new Date());
            detReproFteCont.setMonto(poaRecurso.getMontoPreliminarFteCont().add(newMontoFteCont.negate()).add(poaRecurso.getMontoReprogramadoFteCont()));
            detReproFteCont.setOperacion(detProgramacion.getOperacion());
            detReproFteCont.setOrganismo(reprogramacionBo.getOrganismoByConvenio(idConvenio, false));
            detReproFteCont.setPoaReprogramacion(poaReprogramacion.getReprogramacion());
            detReproFteCont.setRecurso(poaRecurso.getRecurso());
            poaReprogramacion.getLstDetalleReprogramacion().add(detReproFteCont);
            detProgramacion.setMontoReproFteCont(detReproFteCont.getMonto());
        }

        limpiarEditDetRepro();
    }

    public void cancelEditDetRepro() {
        limpiarEditDetRepro();
    }

    private void limpiarEditDetRepro() {
        showEditReprogramacion = false;
        newMontoFtePpal = BigDecimal.ZERO;
        newMontoFteCont = BigDecimal.ZERO;
        newMontoTotal = BigDecimal.ZERO;
        montoReprogramar = BigDecimal.ZERO;
        oeg = "";
        pgCategoria = "";
        poaRecurso = null;
    }
    
    public void proyectoConMasFuetes() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("idConvenio", idConvenio);
        sessionMap.put("idOrigen", idOrigen);

        contrapartida = reprogramacionBo.isUnicaFuente(idConvenio);
        nombreFtePpal = reprogramacionBo.getEntidadesFinanciadoras(idConvenio, true).getNombreCorto();
        nombreFteCont = reprogramacionBo.getEntidadesFinanciadoras(idConvenio, false).getNombreCorto();
    }

    public String getNombreFtePpal() {
        return nombreFtePpal;
    }

    public void setNombreFtePpal(String nombreFtePpal) {
        this.nombreFtePpal = nombreFtePpal;
    }

    public String getNombreFteCont() {
        return nombreFteCont;
    }

    public void setNombreFteCont(String nombreFteCont) {
        this.nombreFteCont = nombreFteCont;
    }

    public Boolean getShowMeta() {
        return showMeta;
    }

    public void setShowMeta(Boolean showMeta) {
        this.showMeta = showMeta;
    }

    public Boolean getShowActividad() {
        return showActividad;
    }

    public Boolean getShowRecurso() {
        return showRecurso;
    }

    public void openDialogMetas() {
        showMeta = true;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("reprogramacion", true);

        JsfUtil.updateComponent("frmPrincipal:dlgMeta");
        //RequestContext.getCurrentInstance().execute("dlgMeta.show();");
        PrimeFaces.current().executeScript("PF('dlgMeta').show();");
        
    }

    public Integer getEstadoPoa() {
        return estadoPoa;
    }

    public void setEstadoPoa(Integer estadoPoa) {
        this.estadoPoa = estadoPoa;
    }

    public String getDescripcionPlantilla() {
        if (idConvenio != 0) {
            return reprogramacionBo.getDetCompPorPlantilla(idConvenio).getDescripcionDeLaPlantilla();
        } else {
            return "";
        }
    }

    public void onTabChange(TabChangeEvent event) {
        if (poaReprogramacion.getTipoPoaReprog() != 3) {
            TabView tv = (TabView) event.getComponent();
            if (tv.getActiveIndex() == 0) {
                disBtnAddAum = true;
                disBtnAddDism = false;
            } else {
                disBtnAddAum = false;
                disBtnAddDism = true;
            }
        }
    }
}