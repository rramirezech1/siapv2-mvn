/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;


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
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwContratosGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwOfertasGrupo;
import sv.gob.mined.apps.siapv2.mvn.sessionbeans.VariablesSession;


@Component(value = "consultasSiapBean")
@Scope(value = "view")
public class ConsultasSiapBean {
    
    private List<VwGrupos> lstGrupos;
    private List<VwContratosGrupo> lstContratosGrupo;
    private List<VwOfertasGrupo> lstOfertasGrupo;
    private VwGrupos currentGrupo;
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
         
    public void trasladoAtribBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        BancoProveedoresBean bp = context.getApplication().evaluateExpressionGet(context, "#{bancoProveedoresBean}", BancoProveedoresBean.class);
        bp.getCurrentGarantiaOferente().setIdTipoGarantia(this.tipoGarantia);
        bp.getCurrentGarantiaOferente().setGrupoSiap(currentGrupo.getGrupo()); 
        bp.setDeshabilitado(false);
        bp.setDeshabilitadoEliminar(true);
        
        bp.getLstGarantias(currentGrupo.getGrupo());
        
        bp.getCurrentGarantiaOferente().setNoLicitacion(currentGrupo.getNo_licitacion());
        
        if(tipoGarantia==1){
            if (currentOferta != null){
                bp.getCurrentGarantiaOferente().setIdDocumento(currentOferta.getIdOferta());
                bp.getCurrentGarantiaOferente().setIdentificadorPrimarioOferente(currentOferta.getIdentificadorPrimarioOferente());
                bp.getCurrentGarantiaOferente().setRazonSocial(currentOferta.getRazonSocial());
            }
        }else{
            if (currentContrato != null){
                bp.getCurrentGarantiaOferente().setIdDocumento(currentContrato.getIdContrato());
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