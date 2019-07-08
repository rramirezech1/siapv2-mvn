/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.managedbeans;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;


@Component(value = "consultasSiapBean")
@Scope(value = "view")
public class ConsultasSiapBean {
    
    private List<VwGrupos> lstGrupos;
    private List<VwContratosGrupo> lstContratosGrupo;
    private List<VwOfertasGrupo> lstOfertasGrupo;
    private VwGrupos currentGrupo;
    private VwOfertasGrupo currentOferta;
    private VwContratosGrupo currentContrato;
    
    private Boolean deshabilitado = true;
    private Boolean deshabilitadoEliminar = true;
    private Boolean deshabilitadoEfectiva = true;
    private int tipoGrupo;
    private int tipoGarantia;
    
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
        lstGrupos = consultasSiap.getLstGrupos(variablesSession.getUsuario());
        return lstGrupos;
    }

    public void setLstGrupos(List<VwGrupos> lstGrupos) {
        this.lstGrupos = lstGrupos;
    }
    
    public void onRowSelectGrupos(SelectEvent event) {
        VwGrupos grupo = consultasSiap.getGrupoById(((VwGrupos) event.getObject()).getGrupo());
        if (grupo != null) {
            currentGrupo = grupo;
            if(tipoGarantia==1){
                this.lstOfertasGrupo = consultasSiap.getLstOfertasByGrupo(currentGrupo.getGrupo());
            }
            else{
                this.lstContratosGrupo = consultasSiap.getLstContratosByGrupo(currentGrupo.getGrupo());
            }
        }
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
    
    
}