/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.BancoProveedoresBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.CalificacionContrato;
import sv.gob.mined.apps.siapv2.mvn.modelo.Empresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.Departamento;
import sv.gob.mined.apps.siapv2.mvn.modelo.Municipio;
import sv.gob.mined.apps.siapv2.mvn.modelo.GarantiasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.MultasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.Persona;
import sv.gob.mined.apps.siapv2.mvn.modelo.RescisionesOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.SectorEconomico;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoCalificacionContratista;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoGarantias;
import sv.gob.mined.apps.siapv2.mvn.modelo.FormaGarantia;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoMultas;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRescision;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;
import sv.gob.mined.apps.siapv2.mvn.modelo.EntidadEmisora;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwCalificacionContratos;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGarantiasGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwMultasEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwRescisionesEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwTrasladoEmpresa;
import sv.gob.mined.apps.siapv2.mvn.sessionbeans.VariablesSession;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;

/**
 *
 * @author Infosoft
 */
@Component(value = "bancoProveedoresBean")
@Scope(value = "view")
public class BancoProveedoresBean implements Serializable {

    private Integer idEmpresa;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String nombreCompleto;
    private String razonSocial;
    private String numeroDeNit;
    private String descripcion;
    private String usuario;
    private String claveDeAcceso;
    private String nombreComercial;
    private String giroEmpresa;
    private String representante;
    private String verificar;
    private Integer idSector;
    private Integer idSubSector;
    private Empresa currentEmpresa = new Empresa();
    private CalificacionContrato currentCalificacionContrato;
    private MultasOferente currentMultaOferente;
    private GarantiasOferente currentGarantiaOferente;
    private RescisionesOferente currentRescisionesOferente;
    private Usuario currentUsuario;
    private Persona currentPersona;
    private List<Empresa> lstEmpresa;
    private List<Usuario> lstUsuario;
    private List<VwCalificacionContratos> lstCalificaciones;
    private List<VwMultasEmpresa> lstMultas;
    private List<VwGarantiasGrupo> lstGarantias;
    private List<VwRescisionesEmpresa> lstRescision;
    private List<VwTrasladoEmpresa> lstTrasladoEmpresa;
    private List<SectorEconomico> lstSectorEconomico;
    private List<SectorEconomico> lstSubSector;
    private List<FormaGarantia> lstFormaGarantia;
    private List<EntidadEmisora> lstEntidadEmisora;
    private Boolean deshabilitado = true;
    private Boolean deshabilitadoEliminar = true;
    private Boolean deshabilitadoEstado = false;
    private Boolean deshabilitadoEstadoDatos = true;
    //Control de reportes a imprimir, se hace porque es necesario contar con la información para que no de error
    private Boolean deshabilitadoImpresionFichaGarantia = true;
    private Boolean deshabilitadoImpresionDevolucionGarantia = true;
    private Boolean deshabilitadoImpresionGarantiaEfectiva = true;
    private Boolean deshabilitadoImpresionGarCumplimientoOferta = true;
    private Boolean deshabilitadoImpresionGarCumplimientoContrato = true;
    private Integer reporteImprimir;
    private Integer tecnicoResponsable;
    private Integer jefeArea;
    private Integer tecnicoRecibe;
    /*
    Control de correlativos y documentos
        1- Garantia  2- Devolucion de Garantia 3- Garantia efectiva
    */
    
    public BancoProveedoresBean() {
    }
    @Autowired
    private BancoProveedoresBo bancoProv;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Inject
    private VariablesSession variablesSession;

    /*public List<TipoCalificacionContratista> getLstTipoFaltas() {
     return bancoProv.getLstTipoFaltas();
     }*/
   
    public void buscarEmpresas() {
        if (razonSocial != null && !razonSocial.isEmpty()) {
            lstEmpresa = bancoProv.getLstEmpresaByRazonSocial(razonSocial);
        } else if (numeroDeNit != null && !numeroDeNit.isEmpty()) {
            lstEmpresa = bancoProv.getLstEmpresaByNit(numeroDeNit);
        }
    }

    public void buscarEmpresa() {
        deshabilitado = false;
        if (razonSocial != null && !razonSocial.isEmpty()) {
            lstEmpresa = bancoProv.getLstEmpresaByRazonSocial(razonSocial);
        } else if (nombreComercial != null && !nombreComercial.isEmpty()) {
            lstEmpresa = bancoProv.getLstEmpresaByNombreComercial(nombreComercial);
        } else if (numeroDeNit != null && !numeroDeNit.isEmpty()) {
            lstEmpresa = bancoProv.getLstEmpresaByNit(numeroDeNit);
        } else if (giroEmpresa != null && !giroEmpresa.isEmpty()) {
            lstEmpresa = bancoProv.getLstEmpresaByGiro(giroEmpresa);
        } else if (idSubSector != null && idSubSector != 0) {
            lstEmpresa = bancoProv.getLstEmpresaByQueOfrece(idSubSector);
        } else {
            lstEmpresa = bancoProv.getLstEmpresa();
        }
    }

    public void nuevaCalificacion() {
        nuevo();
        currentCalificacionContrato = new CalificacionContrato();
    }

    public void nuevaGarantia() {
        nuevo();
        currentGarantiaOferente = new GarantiasOferente();
        currentGarantiaOferente.setEstadoGarantia(1); // estado 1 = Registrada
    }

    public void nuevaRescision() {
        nuevo();
        currentRescisionesOferente = new RescisionesOferente();
    }

    public void nuevaMulta() {
        nuevo();
        currentMultaOferente = new MultasOferente();
    }

    public void nuevoUsuario() {
        nuevo();
        currentUsuario = new Usuario();
    }

    private void nuevo() {
        deshabilitado = false;
        deshabilitadoImpresionFichaGarantia = true;
        deshabilitadoImpresionGarantiaEfectiva = true;
        deshabilitadoImpresionDevolucionGarantia = true;
        deshabilitadoEliminar = true;
        deshabilitadoEstado = true;
        deshabilitadoEstadoDatos = true;
        currentEmpresa = null;
        numeroDeNit = null;
        razonSocial = null;
        descripcion = null;
        lstMultas = null;
        lstCalificaciones = null;
        //lstGarantias = null;
        lstRescision = null;
    }

    public void guardarFalta() {
        Boolean valido;

        if (currentEmpresa != null) {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtContratista", InputText.class, currentEmpresa.getRazonSocial());
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtNumContrato", InputText.class, currentCalificacionContrato.getNumeroContrato()) && valido;

            valido = JsfUtil.addErrorStyle("frmPrincipal", "cbTipoCalificacion", SelectOneMenu.class, currentCalificacionContrato.getIdTipoDeCalificacion()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txaDescripcion", InputTextarea.class, currentCalificacionContrato.getObservacion()) && valido;
        } else {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtContratista", InputText.class, null);
        }

        if (currentEmpresa != null && valido == true) {
            currentCalificacionContrato.setIdentificadorPrimarioOferente(currentEmpresa.getIdentificadorPrimarioOferente());
            currentCalificacionContrato.setFechaDeInsercion(new Date());
            currentCalificacionContrato.setEstadoDeEliminacion(0);
            bancoProv.saveCalificacionContrato(currentCalificacionContrato);
            lstCalificaciones = bancoProv.getLstFaltasOferente(currentEmpresa.getIdentificadorPrimarioOferente());

            if (currentCalificacionContrato.getIdentificadorCalificacion() == null) {
                currentCalificacionContrato = new CalificacionContrato();
                deshabilitadoEliminar = true;
            } else {
                deshabilitadoEliminar = false;
            }

            JsfUtil.addSuccessMessage("El registro ha sido guardado");
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }

    public void guardarUsuario() {
        Boolean valido;

        if (currentUsuario != null) {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtPrimerNombre", InputText.class, currentUsuario.getPrimerNombre());
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtPrimerApellido", InputText.class, currentUsuario.getPrimerApellido()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtUsuarioDeAcceso", InputText.class, currentUsuario.getUserName()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtPass", InputText.class, currentUsuario.getPassword());
        } else {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtUsuarioDeAcceso", InputText.class, null);
        }

        if (currentUsuario != null && valido == true) {
            currentUsuario.setPrimerNombre(currentUsuario.getPrimerNombre());
            currentUsuario.setSegundoNombre(currentUsuario.getSegundoNombre());
            currentUsuario.setPrimerApellido(currentUsuario.getPrimerApellido());
            currentUsuario.setSegundoApellido(currentUsuario.getSegundoApellido());
            currentUsuario.setEMail(currentUsuario.getEMail());
            currentUsuario.setTelefono(currentUsuario.getTelefono());
            currentUsuario.setUserName(currentUsuario.getUserName());
            currentUsuario.setPassword(currentUsuario.getPassword());
            currentUsuario.setFechaDeInsercion(new Date());
            bancoProv.saveUsuario(currentUsuario);

            if (currentUsuario.getUserName() == null) {
                JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
            } else {
                JsfUtil.addSuccessMessage("El registro ha sido guardado");
                currentUsuario = new Usuario();
                deshabilitadoEliminar = true;
            }
        }
    }

    public void guardarEstadoEmpresa() {
        currentEmpresa.setEstadoDeRegistro(currentEmpresa.getEstadoDeRegistro());
        bancoProv.saveEmpresa(currentEmpresa);
        this.habilitaOferente();
        //deshabilitado = true;
        //JsfUtil.addSuccessMessage("El registro ha sido guardado");
    }

    public void guardarMulta() {
        Boolean valido;

        if (currentEmpresa != null) {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtContratista", InputText.class, currentEmpresa.getRazonSocial());
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtNumContrato", InputText.class, currentMultaOferente.getNumeroContrato()) && valido;

            valido = JsfUtil.addErrorStyle("frmPrincipal", "cbTipoMulta", SelectOneMenu.class, currentMultaOferente.getIdTipoDeMulta()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtMonto", InputText.class, currentMultaOferente.getMontoMulta()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txaDescripcion", InputTextarea.class, currentMultaOferente.getDescripcionDeMulta()) && valido;
        } else {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtContratista", InputText.class, null);
        }

        if (currentEmpresa != null && valido == true) {
            currentMultaOferente.setIdentificadorPrimarioOferente(currentEmpresa.getIdentificadorPrimarioOferente());
            currentMultaOferente.setFechaDeInsercion(new Date());
            currentMultaOferente.setEstadoDeEliminacion(0);
            bancoProv.saveMultaOferente(currentMultaOferente);
            lstMultas = bancoProv.getLstMultasOferente(currentEmpresa.getIdentificadorPrimarioOferente());

            if (currentMultaOferente.getIdentificadorMulta() == null) {
                currentMultaOferente = new MultasOferente();
                deshabilitadoEliminar = true;
            } else {
                deshabilitadoEliminar = false;
            }

            JsfUtil.addSuccessMessage("El registro ha sido guardado");
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }

    public void guardarGarantia() {
        Boolean valido;
        java.util.Date fechaHoy = new Date();

        if (currentGarantiaOferente != null) {
            valido = JsfUtil.addErrorStyle("frmDialog", "txtIdDocumento", InputText.class, currentGarantiaOferente.getIdDocumento());
            valido = JsfUtil.addErrorStyle("frmDialog", "cbFormaGarantia", SelectOneMenu.class, currentGarantiaOferente.getIdFormaGarantia()) && valido;
            valido = JsfUtil.addErrorStyle("frmDialog", "txtMonto", InputText.class, currentGarantiaOferente.getMontoGarantia()) && valido;
            valido = JsfUtil.addErrorStyle("frmDialog", "cbEntidadEmisora", SelectOneMenu.class, currentGarantiaOferente.getIdEntidadEmisora()) && valido;
            valido = JsfUtil.addErrorStyle("frmDialog", "cdFechaInicio", Calendar.class, currentGarantiaOferente.getFechaEmision()) && valido;
            valido = JsfUtil.addErrorStyle("frmDialog", "cdFechaFin", Calendar.class, currentGarantiaOferente.getFechaVencimiento()) && valido;
            valido = JsfUtil.addErrorStyle("frmDialog", "cdFechaRecepcion", Calendar.class, currentGarantiaOferente.getFechaPresentacion()) && valido;
            
            if (currentGarantiaOferente.getEstadoGarantia() == null){
                    if (currentGarantiaOferente.getFechaVencimiento().before(fechaHoy)){
                        currentGarantiaOferente.setEstadoGarantia(3);
                    }else{
                        currentGarantiaOferente.setEstadoGarantia(1);
                    }
            }
                        
            if ((currentGarantiaOferente.getEstadoGarantia() == 2)|(currentGarantiaOferente.getEstadoGarantia() == 4)) {
                valido = JsfUtil.addErrorStyle("frmDialog", "cdFechaAccion", Calendar.class, currentGarantiaOferente.getFechaAccionEstado()) && valido;
                valido = JsfUtil.addErrorStyle("frmDialog", "cbAutorizaAccion", SelectOneMenu.class, currentGarantiaOferente.getAutorizaAccionEstado()) && valido;
            }
            
            //valido = JsfUtil.addErrorStyle("frmDialog", "txaDescripcion", InputTextarea.class, currentGarantiaOferente.getDescripcionGarantia()) && valido;
        } else {
            valido = JsfUtil.addErrorStyle("frmDialog", "txtContratista", InputText.class, null);
        }

        if (valido == true) {
            // currentGarantiaOferente.setFechaDeInsercion(new Date());
            currentGarantiaOferente.setEstadoDeEliminacion(0);
            currentGarantiaOferente.setName(variablesSession.getUsuario());
            
            /*if (currentGarantiaOferente.getEstadoEfectiva()){
                currentGarantiaOferente.setEstadoGarantia(4);
            }
            */
            
            generaCorrelativo(1); //El tipo de documento 1 corresponde a Garantia
            
            bancoProv.saveGarantiaOferente(currentGarantiaOferente);
            lstGarantias = bancoProv.getLstGarantiasGrupo(currentGarantiaOferente.getGrupoSiap());

            if (currentGarantiaOferente.getIdentificadorGarantia() == null) {
                currentGarantiaOferente = new GarantiasOferente();
                deshabilitadoEliminar = true;
            } else {
                deshabilitadoEliminar = false;
            }

            JsfUtil.addSuccessMessage("El registro ha sido guardado");
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }
   
    public void estadoChange() {
        
        if ((this.currentGarantiaOferente.getEstadoGarantia() == 2)|(this.currentGarantiaOferente.getEstadoGarantia() == 4)){
            this.deshabilitadoEstadoDatos = false;
            if (this.currentGarantiaOferente.getEstadoGarantia() == 2){
                this.deshabilitadoImpresionGarantiaEfectiva = true;
                this.deshabilitadoImpresionDevolucionGarantia = false;
            }else{
                this.deshabilitadoImpresionGarantiaEfectiva = false;
                this.deshabilitadoImpresionDevolucionGarantia = true;
            }
        } else {
            this.deshabilitadoEstadoDatos = true;
            this.deshabilitadoImpresionGarantiaEfectiva = true;
            this.deshabilitadoImpresionDevolucionGarantia = true;
        }
    }
    
    
    public void guardarRescision() {
        Boolean valido;

        if (currentEmpresa != null) {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtContratista", InputText.class, currentEmpresa.getRazonSocial());
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtNumContrato", InputText.class, currentRescisionesOferente.getNumeroContrato()) && valido;

            valido = JsfUtil.addErrorStyle("frmPrincipal", "cdlVencimiento", Calendar.class, currentRescisionesOferente.getFechaRecesion()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtMontoPagado", InputText.class, currentRescisionesOferente.getMontoPagado()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtMontoRemanente", InputText.class, currentRescisionesOferente.getMontoRemanente()) && valido;
            valido = JsfUtil.addErrorStyle("frmPrincipal", "cbTipoRescision", SelectOneMenu.class, currentRescisionesOferente.getTipoRecesion()) && valido;

            valido = JsfUtil.addErrorStyle("frmPrincipal", "txaObservaciones", InputTextarea.class, currentRescisionesOferente.getDescripcionRecesion()) && valido;
        } else {
            valido = JsfUtil.addErrorStyle("frmPrincipal", "txtContratista", InputText.class, null);
        }

        if (currentEmpresa != null && valido == true) {
            currentRescisionesOferente.setIdentificadorPrimarioOferente(currentEmpresa.getIdentificadorPrimarioOferente());
            currentRescisionesOferente.setFechaDeInsercion(new Date());
            currentRescisionesOferente.setEstadoDeEliminacion(0);
            bancoProv.saveRescisionOferente(currentRescisionesOferente);
            lstRescision = bancoProv.getLstRescisionesOferente(currentEmpresa.getIdentificadorPrimarioOferente());

            if (currentRescisionesOferente.getIdentificadorRecesion() == null) {
                currentRescisionesOferente = new RescisionesOferente();
                deshabilitadoEliminar = true;
            } else {
                deshabilitadoEliminar = false;
            }

            JsfUtil.addSuccessMessage("El registro ha sido guardado");
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }

    public void onRowSelectEmpresa(SelectEvent event) {
        deshabilitadoEliminar = true;
        if (currentEmpresa != null) {
            if (currentCalificacionContrato != null) {
                currentCalificacionContrato = new CalificacionContrato();
            } else if (currentMultaOferente != null) {
                currentMultaOferente = new MultasOferente();
            } else if (currentGarantiaOferente != null) {
                currentGarantiaOferente = new GarantiasOferente();
            } else if (currentRescisionesOferente != null) {
                currentRescisionesOferente = new RescisionesOferente();
            }
        }
    }

    public void onRowSelectUsuario(SelectEvent event) {
        Usuario user = (Usuario) event.getObject();
        if (user != null) {
            deshabilitadoEliminar = false;
            currentUsuario = user;

        }
    }

    public void onRowConsultaEmpresa(SelectEvent event) {
        Empresa empresa = (Empresa) event.getObject();
        representante = bancoProv.findRepresentanteByEmpresa(empresa.getIdentificadorPrimarioDeLaEmpresa());

        if (empresa != null) {
            deshabilitadoEliminar = false;
            currentEmpresa = empresa;
        }
    }

    public void onRowSelectCalificacion(SelectEvent event) {
        CalificacionContrato falta = bancoProv.getCalificacionById(((VwCalificacionContratos) event.getObject()).getIdentificadorCalificacion());
        if (falta != null) {
            currentCalificacionContrato = falta;
            deshabilitadoEliminar = false;
        }
    }

    public void onRowSelectGarantias(SelectEvent event) {
        
        GarantiasOferente garantia = bancoProv.getGarantiaById(((VwGarantiasGrupo) event.getObject()).getIdentificadorGarantia());
                
        if (garantia != null) {
            currentGarantiaOferente = garantia;
            currentGarantiaOferente.setRazonSocial(((VwGarantiasGrupo) event.getObject()).getRazonSocial());
            deshabilitadoEliminar = false;
            deshabilitadoEstado = false;
            deshabilitadoEstadoDatos = true;
            deshabilitadoImpresionFichaGarantia=false;
            deshabilitado = false;
            this.estadoChange();
        }
    }

    public void onRowSelectMultas(SelectEvent event) {
        MultasOferente multa = bancoProv.getMultaById(((VwMultasEmpresa) event.getObject()).getIdentificadorMulta());
        if (multa != null) {
            deshabilitadoEliminar = false;
            currentMultaOferente = multa;
        }
    }

    public void onRowSelectRescisiones(SelectEvent event) {
        RescisionesOferente rescision = bancoProv.getRescisionById(((VwRescisionesEmpresa) event.getObject()).getIdentificadorRecesion());
        if (rescision != null) {
            deshabilitadoEliminar = false;
            currentRescisionesOferente = rescision;
        }
    }

    public void eliminarFalta() {
        if (currentCalificacionContrato != null && currentCalificacionContrato.getIdentificadorCalificacion() != null) {
            currentCalificacionContrato.setEstadoDeEliminacion(1);
            currentCalificacionContrato.setFechaDeEliminacion(new Date());
            bancoProv.saveCalificacionContrato(currentCalificacionContrato);
            lstCalificaciones = bancoProv.getLstFaltasOferente(currentEmpresa.getIdentificadorPrimarioOferente());
            currentCalificacionContrato = new CalificacionContrato();
            deshabilitadoEliminar = true;

            JsfUtil.addSuccessMessage("El registro ha sido eliminado");
        }
    }

    public void eliminarGarantia() {
        if (currentGarantiaOferente != null && currentGarantiaOferente.getIdentificadorGarantia() != null) {
            currentGarantiaOferente.setEstadoDeEliminacion(1);
            currentGarantiaOferente.setFechaDeEliminacion(new Date());
            bancoProv.saveGarantiaOferente(currentGarantiaOferente);
            lstGarantias = bancoProv.getLstGarantiasGrupo(currentGarantiaOferente.getGrupoSiap());

            currentGarantiaOferente = new GarantiasOferente();
            deshabilitadoEliminar = true;
            JsfUtil.addSuccessMessage("El registro ha sido eliminado");
        }
    }

    public void eliminarMulta() {
        if (currentMultaOferente != null && currentMultaOferente.getIdentificadorMulta() != null) {
            currentMultaOferente.setEstadoDeEliminacion(1);
            currentMultaOferente.setFechaDeEliminacion(new Date());
            bancoProv.saveMultaOferente(currentMultaOferente);
            lstMultas = bancoProv.getLstMultasOferente(currentEmpresa.getIdentificadorPrimarioOferente());

            currentMultaOferente = new MultasOferente();
            deshabilitadoEliminar = true;
            JsfUtil.addSuccessMessage("El registro ha sido eliminado");
        }
    }

    public void eliminarUsuario() {
        if (currentMultaOferente != null && currentMultaOferente.getIdentificadorMulta() != null) {
            currentMultaOferente.setEstadoDeEliminacion(1);
            currentMultaOferente.setFechaDeEliminacion(new Date());
            bancoProv.saveMultaOferente(currentMultaOferente);
            lstMultas = bancoProv.getLstMultasOferente(currentEmpresa.getIdentificadorPrimarioOferente());

            currentMultaOferente = new MultasOferente();
            deshabilitadoEliminar = true;
            JsfUtil.addSuccessMessage("El registro ha sido eliminado");
        }
    }

    public void eliminarRescision() {
        if (currentRescisionesOferente != null && currentRescisionesOferente.getIdentificadorRecesion() != null) {
            currentRescisionesOferente.setEstadoDeEliminacion(1);
            currentRescisionesOferente.setFechaDeEliminacion(new Date());
            bancoProv.saveRescisionOferente(currentRescisionesOferente);
            lstRescision = bancoProv.getLstRescisionesOferente(currentEmpresa.getIdentificadorPrimarioOferente());

            currentRescisionesOferente = new RescisionesOferente();
            deshabilitadoEliminar = true;

            JsfUtil.addSuccessMessage("El registro ha sido eliminado");
        }
    }

    public void trasladoOferente() {
        boolean entro = false;
        for (VwTrasladoEmpresa empresa : lstTrasladoEmpresa) {
            if (empresa.getSeleccionado()) {
                bancoProv.trasladoOferente(empresa.getIdentificadorPrimarioOferente(), variablesSession.getUsuario());
                entro = true;
            }
        }

        if (entro) {
            JsfUtil.addSuccessMessage("Proceso completado con éxito!");
        } else {
            JsfUtil.addWarningMessage("No ha seleccionado ningun registro");
        }
    }
    
    public void generaCorrelativo( int tipoDocumento) {
            
            if (currentGarantiaOferente.getNoGarantia()==null){
                int ejercicioFiscal = JsfUtil.getNumAnyo(currentGarantiaOferente.getFechaEmision());
                int corr = bancoProv.generaCorrelativo(tipoDocumento, ejercicioFiscal);
                currentGarantiaOferente.setNoGarantia(String.valueOf(corr)+'/'+String.valueOf(ejercicioFiscal));
            }
    }

    public void habilitaOferente() {
        int traslado = 1;
        bancoProv.trasladoOferente(currentEmpresa.getIdentificadorPrimarioOferente(), variablesSession.getUsuario());
        if (traslado == 1) {
            JsfUtil.addSuccessMessage("Proceso completado con éxito!");
        } else {
            JsfUtil.addWarningMessage("No ha seleccionado ningun registro");
        }

    }

    public void linkRegistro() {

        Persona per = bancoProv.findPersonaByUsuarioClave(usuario, claveDeAcceso);

        if (per != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put(JsfUtil.sUsuario, usuario);
            context.getExternalContext().getSessionMap().put(JsfUtil.sIdPersona, per.getIdentificadorDeLaPersona());

            //return "/app/vistas/registro.jsf?faces-redirect=true";
        }

    }
      
    public byte[] imprimirFicha() throws SQLException, JRException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportPath = ctx.getRealPath(JsfUtil.PATH_REPORTES);
        HashMap param = new HashMap();
        param.put("SUBREPORT_DIR", reportPath + File.separator);
        param.put("ubicacionImagenes", ctx.getRealPath(JsfUtil.PATH_IMAGENES));
        if (idEmpresa != null) {
            param.put("identificadorPrimarioDeLaEmpresa", idEmpresa);
        } else {
            param.put("identificadorPrimarioDeLaEmpresa", currentEmpresa.getIdentificadorPrimarioDeLaEmpresa());
        }

        return JasperRunManager.runReportToPdf(reportPath + File.separator + "fichaOferente.jasper", param, jdbcTemplate.getDataSource().getConnection());
    }
    
        public void validaImprimirRecepcion() {
        //Reporte Numero 2
        
        Boolean valido;
        
        valido = JsfUtil.addErrorStyle("frmImpDialog", "cbReporteImp", SelectOneMenu.class, this.getReporteImprimir());
        valido = JsfUtil.addErrorStyle("frmImpDialog", "cbTecRespImp", SelectOneMenu.class, this.getTecnicoResponsable()) && valido;
        valido = JsfUtil.addErrorStyle("frmImpDialog", "cbAutorizaImp", SelectOneMenu.class, this.getJefeArea()) && valido;
        valido = JsfUtil.addErrorStyle("frmImpDialog", "cbTecRecibe", SelectOneMenu.class, this.getTecnicoRecibe()) && valido;
        
        
        if (valido == true) {
            imprimir(2);
            
        } else {
            JsfUtil.addErrorMessage("Los campos marcados con rojo son REQUERIDOS");
        }
    }

    
    public byte[] imprimirRecepcion() throws SQLException, JRException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportPath = ctx.getRealPath(JsfUtil.PATH_REPORTES);
        HashMap param = new HashMap();
        param.put("ubicacionImagenes", ctx.getRealPath(JsfUtil.PATH_IMAGENES));
        param.put("reporteImprimir", this.getReporteImprimir());
        param.put("tecnicoResponsable", this.getTecnicoResponsable());
        param.put("jefeArea", this.getJefeArea());
        param.put("tecnicoRecibe", this.getTecnicoRecibe());
        param.put("grupoSIAP", currentGarantiaOferente.getGrupoSiap());

        return JasperRunManager.runReportToPdf(reportPath + File.separator + "formularioRecepcionGarantia.jasper", param, jdbcTemplate.getDataSource().getConnection());
    }

    public void imprimir(int contRep) {
        try {
            byte[] content;
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            switch (contRep){
                case 1:
                    content = imprimirFicha();
                    break;
                case 2:
                    content = imprimirRecepcion();
                    break;
                default:
                    content = imprimirFicha();
            }
            
            response.setContentType("application/pdf");
            response.setContentLength(content == null ? 0 : content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException ex) {
            Logger.getLogger(BancoProveedoresBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(BancoProveedoresBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BancoProveedoresBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CalificacionContrato getCurrentCalificacionContrato() {
        if (currentCalificacionContrato == null) {
            currentCalificacionContrato = new CalificacionContrato();
        }
        return currentCalificacionContrato;
    }

    public GarantiasOferente getCurrentGarantiaOferente() {
        if (currentGarantiaOferente == null) {
            currentGarantiaOferente = new GarantiasOferente();
        }
        return currentGarantiaOferente;
    }

    public void setCurrentGarantiaOferente(GarantiasOferente currentGarantiaOferente) {
        this.currentGarantiaOferente = currentGarantiaOferente;
    }

    public void setCurrentFaltasOferente(CalificacionContrato curretFaltasOferente) {
        this.currentCalificacionContrato = curretFaltasOferente;
    }

    public List<VwCalificacionContratos> getLstCalificaciones() {
        if (lstCalificaciones == null) {
            lstCalificaciones = new ArrayList<VwCalificacionContratos>();
        }
        return lstCalificaciones;
    }

    public void setLstCalificaciones(List<VwCalificacionContratos> lstCalificaciones) {
        this.lstCalificaciones = lstCalificaciones;
    }

    public List<VwRescisionesEmpresa> getLstRescision() {
        if (lstRescision == null) {
            lstRescision = new ArrayList<VwRescisionesEmpresa>();
        }
        return lstRescision;
    }

    public void setLstRescision(List<VwRescisionesEmpresa> lstRescision) {
        this.lstRescision = lstRescision;
    }

    public List<VwMultasEmpresa> getLstMultas() {
        if (lstMultas == null) {
            lstMultas = new ArrayList<VwMultasEmpresa>();
        }
        return lstMultas;
    }

    public void setLstMultas(List<VwMultasEmpresa> lstMultas) {
        this.lstMultas = lstMultas;
    }

    public List<VwGarantiasGrupo> getLstGarantias() {
        if (lstGarantias == null) {
            lstGarantias = new ArrayList<VwGarantiasGrupo>();
        }
        return lstGarantias;
    }
    
    public List<VwGarantiasGrupo> getLstGarantias(int grupo){
        lstGarantias = bancoProv.getLstGarantiasGrupo(grupo);
        return lstGarantias;    
    }

    public void setLstGarantias(List<VwGarantiasGrupo> lstGarantias) {
        this.lstGarantias = lstGarantias;
    }

    public List<VwTrasladoEmpresa> getLstTrasladoEmpresa() {
        lstTrasladoEmpresa = bancoProv.getLstTrasladoEmpresa();
        return lstTrasladoEmpresa;
    }

    public List<TipoCalificacionContratista> getLstTipoCalificacionContratista() {
        return bancoProv.getLstTipoCalificacionContratista();
    }
   
    public Usuario getCurrentUsuario() {
        if (currentUsuario == null) {
            currentUsuario = new Usuario();
            //currentUsuario.setIdPregunta1(getLstPreguntas().get(0).getIdPregunta());
        }
        return currentUsuario;
    }

    public void setCurrentUsuario(Usuario currentUsuario) {
        this.currentUsuario = currentUsuario;
    }

    public Empresa getCurrentEmpresa() {
        return currentEmpresa;
    }

    public void setCurrentEmpresa(Empresa currentEmpresa) {
        if (currentEmpresa != null) {
            this.currentEmpresa = currentEmpresa;
            if (currentCalificacionContrato != null) {
                lstCalificaciones = bancoProv.getLstFaltasOferente(currentEmpresa.getIdentificadorPrimarioOferente());
            } else if (currentMultaOferente != null) {
                lstMultas = bancoProv.getLstMultasOferente(currentEmpresa.getIdentificadorPrimarioOferente());
            } 
            else if (currentGarantiaOferente != null) {
                lstGarantias = bancoProv.getLstGarantiasGrupo(currentGarantiaOferente.getGrupoSiap());
            }
            else if (currentRescisionesOferente != null) {
                lstRescision = bancoProv.getLstRescisionesOferente(currentEmpresa.getIdentificadorPrimarioOferente());
            }
        }
    }

    public Persona getCurrentPersona() {
        return currentPersona;
    }

    public void setCurrentPersona(Persona currentPersona) {
        this.currentPersona = currentPersona;
    }

    public MultasOferente getCurrentMultaOferente() {
        if (currentMultaOferente == null) {
            currentMultaOferente = new MultasOferente();
        }
        return currentMultaOferente;
    }

    public void setCurrentMultaOferente(MultasOferente currentMultaOferente) {
        this.currentMultaOferente = currentMultaOferente;
    }

    public RescisionesOferente getCurrentRescisionesOferente() {
        if (currentRescisionesOferente == null) {
            currentRescisionesOferente = new RescisionesOferente();
        }
        return currentRescisionesOferente;
    }

    public void setCurrentRescisionesOferente(RescisionesOferente currentRescisionesOferente) {
        this.currentRescisionesOferente = currentRescisionesOferente;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClaveDeAcceso() {
        return claveDeAcceso;
    }

    public void setClaveDeAcceso(String claveDeAcceso) {
        this.claveDeAcceso = claveDeAcceso;
    }

     public List<TipoMultas> getLstTipoMultas() {
        return bancoProv.getLstTipoMultas();
    }

    public List<TipoGarantias> getLstTipoGarantias() {
        return bancoProv.getLstTipoGarantias();
    }
    
    public List<FormaGarantia> getLstFormaGarantia() {
        return bancoProv.getLstFormaGarantia();
    }

    public List<TipoRescision> getLstTipoRescisiones() {
        return bancoProv.getLstTipoRescisiones();
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroDeNit() {
        return numeroDeNit;
    }

    public void setNumeroDeNit(String numeroDeNit) {
        this.numeroDeNit = numeroDeNit;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getGiroEmpresa() {
        return giroEmpresa;
    }

    public void setGiroEmpresa(String giroEmpresa) {
        this.giroEmpresa = giroEmpresa;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public Integer getIdSubSector() {
        return idSubSector;
    }

    public void setIdSubSector(Integer idSubSector) {
        this.idSubSector = idSubSector;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getVerificar() {
        return verificar;
    }

    public void setVerificar(String verificar) {
        this.verificar = verificar;
    }

    public void buscarUsuarios() {
        deshabilitado = false;
        lstUsuario = bancoProv.getLstUsuarioByNombreCompleto(nombreCompleto);
    }

    public List<Departamento> getLstDepaEmp() {
        return bancoProv.findAllDepartamentos();
    }

    public List<Municipio> getLstMuniEmp() {
        return bancoProv.findAllMunicipios();
    }

    public List<SectorEconomico> getLstSectorEconomico() {
        return bancoProv.findAllSectorEconomico();
    }

    public List<SectorEconomico> getLstSubSector() {
        List<SectorEconomico> tmpLstSubSector = bancoProv.findAllSubSectorEconomico(idSector);
        lstSubSector = tmpLstSubSector;
        return lstSubSector;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public List<Usuario> getLstUsuario() {
        if (lstUsuario == null) {
            lstUsuario = new ArrayList<Usuario>();
        }
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public List<Empresa> getLstEmpresa() {
        if (lstEmpresa == null) {
            lstEmpresa = new ArrayList<>();
        }
        return lstEmpresa;
    }

    public void setLstEmpresa(List<Empresa> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }

    public void setLstSubSectorEconomico() {
        if (idSector != null) {
            lstSubSector = bancoProv.findAllSubSectorEconomico(idSector);
        }
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public Boolean getDeshabilitadoEliminar() {
        return deshabilitadoEliminar;
    }

    public void setDeshabilitadoEliminar(Boolean deshabilitadoEliminar) {
        this.deshabilitadoEliminar = deshabilitadoEliminar;
    }

    public Boolean getDeshabilitadoEstado() {
        return deshabilitadoEstado;
    }

    public void setDeshabilitadoEstado(Boolean deshabilitadoEstado) {
        this.deshabilitadoEstado = deshabilitadoEstado;
    }
    
    
    /*public TipoFaltas getCurrentFaltas() {
     return currentFaltas;
     }

     public void setCurrentFaltas(TipoFaltas currentFaltas) {
     this.currentFaltas = currentFaltas;
     }*/

    public Boolean getDeshabilitadoEstadoDatos() {
        return deshabilitadoEstadoDatos;
    }

    public void setDeshabilitadoEstadoDatos(Boolean deshabilitadoEstadoDatos) {
        this.deshabilitadoEstadoDatos = deshabilitadoEstadoDatos;
    }

    public Boolean getDeshabilitadoImpresionFichaGarantia() {
        return deshabilitadoImpresionFichaGarantia;
    }

    public void setDeshabilitadoImpresionFichaGarantia(Boolean deshabilitadoImpresionFichaGarantia) {
        this.deshabilitadoImpresionFichaGarantia = deshabilitadoImpresionFichaGarantia;
    }

    public Boolean getDeshabilitadoImpresionDevolucionGarantia() {
        return deshabilitadoImpresionDevolucionGarantia;
    }

    public void setDeshabilitadoImpresionDevolucionGarantia(Boolean deshabilitadoImpresionDevolucionGarantia) {
        this.deshabilitadoImpresionDevolucionGarantia = deshabilitadoImpresionDevolucionGarantia;
    }

    public Boolean getDeshabilitadoImpresionGarantiaEfectiva() {
        return deshabilitadoImpresionGarantiaEfectiva;
    }

    public void setDeshabilitadoImpresionGarantiaEfectiva(Boolean deshabilitadoImpresionGarantiaEfectiva) {
        this.deshabilitadoImpresionGarantiaEfectiva = deshabilitadoImpresionGarantiaEfectiva;
    }

    public Boolean getDeshabilitadoImpresionGarCumplimientoOferta() {
        return deshabilitadoImpresionGarCumplimientoOferta;
    }

    public void setDeshabilitadoImpresionGarCumplimientoOferta(Boolean deshabilitadoImpresionGarCumplimientoOferta) {
        this.deshabilitadoImpresionGarCumplimientoOferta = deshabilitadoImpresionGarCumplimientoOferta;
    }

    public Boolean getDeshabilitadoImpresionGarCumplimientoContrato() {
        return deshabilitadoImpresionGarCumplimientoContrato;
    }

    public void setDeshabilitadoImpresionGarCumplimientoContrato(Boolean deshabilitadoImpresionGarCumplimientoContrato) {
        this.deshabilitadoImpresionGarCumplimientoContrato = deshabilitadoImpresionGarCumplimientoContrato;
    }

    public List<EntidadEmisora> getLstEntidadEmisora() {
        return bancoProv.findAllEntidadEmisora();
    }

    public Integer getReporteImprimir() {
        return reporteImprimir;
    }

    public void setReporteImprimir(Integer reporteImprimir) {
        this.reporteImprimir = reporteImprimir;
    }

    public Integer getTecnicoResponsable() {
        return tecnicoResponsable;
    }

    public void setTecnicoResponsable(Integer tecnicoResponsable) {
        this.tecnicoResponsable = tecnicoResponsable;
    }

    public Integer getJefeArea() {
        return jefeArea;
    }

    public void setJefeArea(Integer jefeArea) {
        this.jefeArea = jefeArea;
    }

    public Integer getTecnicoRecibe() {
        return tecnicoRecibe;
    }

    public void setTecnicoRecibe(Integer tecnicoRecibe) {
        this.tecnicoRecibe = tecnicoRecibe;
    }
    
    
}
