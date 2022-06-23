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
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.ConsultasSiapBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGrupos;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwAutoriza;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwProveedoresSiap;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwTecnicoUaci;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwContratosGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGarantiasGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwOfertasGrupo;
import sv.gob.mined.apps.siapv2.mvn.sessionbeans.VariablesSession;


@Component(value = "consultasSiapBean")
@Scope(value = "view")
public class ConsultasSiapBean implements Serializable{
    
    private List<VwGrupos> lstGrupos;
    private List<VwAutoriza> lstAutoriza;
    private List<VwTecnicoUaci> lstTecnicoUaci;
    private List<VwContratosGrupo> lstContratosGrupo;
    private List<VwOfertasGrupo> lstOfertasGrupo;
    private List<VwProveedoresSiap> lstProveedores;
    private VwGrupos currentGrupo;
    private VwAutoriza currentAutoriza;
    private VwOfertasGrupo currentOferta;
    private VwContratosGrupo currentContrato;
    private int tipoGrupo;
    private int tipoGarantia;
    private Boolean habilitadoContratos = false;
    private Boolean habilitadoOfertas = false;
        
    public ConsultasSiapBean() {
    }
    
    @PostConstruct
    public void init() {
           tipoGrupo = 1;
    }
    
    @Autowired
    private ConsultasSiapBo consultasSiap;
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Inject
    private VariablesSession variablesSession;
          
    public List<VwGrupos> getLstGrupos() {
        lstGrupos = consultasSiap.getLstGrupos(variablesSession.getUsuario(), tipoGrupo);
        return lstGrupos;
    }

    public List<VwAutoriza> getLstAutoriza() {
        lstAutoriza = consultasSiap.getLstAutoriza();
        return lstAutoriza;
    }
    
    public List<VwTecnicoUaci> getLstTecnicoUaci() {
        lstTecnicoUaci = consultasSiap.getLstTecnicoUaci();
        return lstTecnicoUaci;
    }
    
    public void setLstGrupos(List<VwGrupos> lstGrupos) {
        this.lstGrupos = lstGrupos;
    }
    
    public void onRowSelectGrupos(SelectEvent event) {
        VwGrupos grupo = consultasSiap.getGrupoById(((VwGrupos) event.getObject()).getGrupo());
        if (grupo != null) {
            currentGrupo = grupo;
                        
            this.lstOfertasGrupo = consultasSiap.getLstOfertasByGrupo(currentGrupo.getGrupo());
            this.lstContratosGrupo = consultasSiap.getLstContratosByGrupo(currentGrupo.getGrupo());
            trasladoAtribBean();
        }
    }
    
    public void onRowSelectOferta(SelectEvent event) {
        VwOfertasGrupo oferta = consultasSiap.getOfertaByGrupoOferente(((VwOfertasGrupo) event.getObject()).getGrupo(), ((VwOfertasGrupo) event.getObject()).getIdProveedor());
        if (oferta != null) {
            currentOferta = oferta;
            trasladoAtribBean();
        }
    }
    
     public void onRowSelectContrato(SelectEvent event) {
        VwContratosGrupo contrato = consultasSiap.getContratoByIdContrato(((VwContratosGrupo) event.getObject()).getIdContrato());
        if (contrato != null) {
            currentContrato = contrato;
            trasladoAtribBean();
        }
    }
     
     public void buscarProveedorSiap() {
        String condicion ="";
        
//        if (idDepto != null) {
//            condicion = "idDepartamento ="+ idDepto;
//        } else if (idMunicipio != null) {
//            condicion = "idMunicipio ="+ idMunicipio;
//        } else if (tamanoEmpresa != null) {
//            condicion = "tamanoEmpresa="+ tamanoEmpresa ;
//        } else if (idSubSector != null && idSubSector != 0) {
//            condicion = "idSubSector= "+ idSubSector;
//        } 
//        
//        lstProveedores = consultasSiap.getLstProveedores(condicion);
    }    
    
    public void trasladoAtribBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        BancoProveedoresBean bp = context.getApplication().evaluateExpressionGet(context, "#{bancoProveedoresBean}", BancoProveedoresBean.class);
        bp.getCurrentGarantiaOferente().setIdTipoGarantia(this.tipoGarantia);
        bp.getCurrentGarantiaOferente().setGrupoSiap(currentGrupo.getGrupo()); 
        bp.setDeshabilitado(false);
        bp.setDeshabilitadoEliminar(true);
        
        bp.getLstGarantias(currentGrupo.getGrupo());
        
        for (VwGarantiasGrupo garantia : bp.getLstGarantias()) {
            if(garantia.getIdTipoGarantia()== 1){
                bp.setDeshabilitadoImpresionGarCumplimientoOferta(false);
            }else if (garantia.getIdTipoGarantia()>=2){
                     bp.setDeshabilitadoImpresionGarCumplimientoContrato(false);
                  } else 
                  {
                    bp.setDeshabilitadoImpresionGarCumplimientoOferta(true);
                    bp.setDeshabilitadoImpresionGarCumplimientoContrato(true);
                  }
        }
        
        
        bp.getCurrentGarantiaOferente().setNoLicitacion(currentGrupo.getNo_licitacion());
        bp.setMetodoAdquisicion(currentGrupo.getMetodoAdquisicion());
        
        if(tipoGarantia==1){
            if (currentOferta != null){
                bp.getCurrentGarantiaOferente().setDocumento(currentOferta.getIdOferta().toString());
                bp.getCurrentGarantiaOferente().setIdentificadorPrimarioOferente(currentOferta.getIdentificadorPrimarioOferente());
                bp.getCurrentGarantiaOferente().setRazonSocial(currentOferta.getRazonSocial());
            }
        }else{
            if (currentContrato != null){
                bp.getCurrentGarantiaOferente().setDocumento(currentContrato.getNumeroContrato());
                bp.getCurrentGarantiaOferente().setIdentificadorPrimarioOferente(currentContrato.getIdentificadorPrimarioOferente());
                bp.getCurrentGarantiaOferente().setRazonSocial(currentContrato.getRazonSocial());
            }
            
        }
         
    }
    
    public List<VwGrupos> getLstGruposByTipo() {
        lstGrupos = consultasSiap.getLstGrupos(variablesSession.getUsuario(), tipoGrupo);
        return lstGrupos;
    }
    
    public List<VwOfertasGrupo> getLstOfertasByGrupo(Integer grupo) {
        lstOfertasGrupo = consultasSiap.getLstOfertasByGrupo(grupo);
        return lstOfertasGrupo;              
    }
    
    public List<VwContratosGrupo> getLstContratosByGrupo(Integer grupo) {
        lstContratosGrupo = consultasSiap.getLstContratosByGrupo(grupo);
        return lstContratosGrupo;              
    }

    public int getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(int tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public List<VwContratosGrupo> getLstContratosGrupo() {
        if (lstContratosGrupo == null) {
            lstContratosGrupo = new ArrayList<VwContratosGrupo>();
        }
        return lstContratosGrupo;
    }

    public void setLstContratosGrupo(List<VwContratosGrupo> lstContratosGrupo) {
        this.lstContratosGrupo = lstContratosGrupo;
    } 

    public List<VwOfertasGrupo> getLstOfertasGrupo() {
        return lstOfertasGrupo;
    }

    public void setLstOfertasGrupo(List<VwOfertasGrupo> lstOfertasGrupo) {
        this.lstOfertasGrupo = lstOfertasGrupo;
    }

    public List<VwProveedoresSiap> getLstProveedores() {
        return lstProveedores;
    }

    public void setLstProveedores(List<VwProveedoresSiap> lstProveedores) {
        this.lstProveedores = lstProveedores;
    }

    public int getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(int tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }
    
    public void tipoGarantiaChange() {
        if (this.tipoGarantia == 1) {
            this.habilitadoContratos = false;
            this.habilitadoOfertas = true;
            
        } else {
            this.habilitadoContratos = true;
            this.habilitadoOfertas = false;
        }
    }

     public Boolean getHabilitadoContratos() {
        return habilitadoContratos;
    }

    public void setHabilitadoContratos(Boolean habilitadoContratos) {
        this.habilitadoContratos = habilitadoContratos;
    }

    public Boolean getHabilitadoOfertas() {
        return habilitadoOfertas;
    }

    public void setHabilitadoOfertas(Boolean habilitadoOfertas) {
        this.habilitadoOfertas = habilitadoOfertas;
    }
    
    
}