/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.ConsultasSiapBo;
import sv.gob.mined.apps.siapv2.mvn.dao.GruposDao;
import sv.gob.mined.apps.siapv2.mvn.dao.ContratosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.OfertasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.AutorizaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.TecnicoUaciDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGrupos;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwContratosGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwOfertasGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwAutoriza;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwTecnicoUaci;
/**
 *
 * @author Infosoft
 */
@Service

public class ConsultasSiapBoImpl implements ConsultasSiapBo {

    public ConsultasSiapBoImpl() {
    }
    
    @Autowired
    private GruposDao grupos;
    @Autowired
    private AutorizaDao autoriza;
    @Autowired
    private TecnicoUaciDao tecnicoUaci;
    @Autowired
    private ContratosDao contratos;
    @Autowired
    private OfertasDao ofertas;
    
    @Override
    public List<VwGrupos> getLstGrupos(String usuarioSession, Integer tipoGrupo) {
        return grupos.findGruposByUser(usuarioSession, tipoGrupo);
    }
    
    @Override
    public List<VwAutoriza> getLstAutoriza() {
        return autoriza.findAll();
    }
    
    @Override
    public List<VwTecnicoUaci> getLstTecnicoUaci() {
        return tecnicoUaci.findAll();
    }
    
    @Override
    public VwGrupos getGrupoById(Integer id) {
        return grupos.findById(id);
    }
    
    @Override
    public VwOfertasGrupo getOfertaByGrupoOferente(Integer grupo, Integer idProveedor) {
        return ofertas.findByGrupoIdProveedor(grupo, idProveedor);
    }
    
    @Override
    public VwContratosGrupo getContratoByIdContrato(Integer id) {
        return contratos.findById(id);
    }
    
     @Override
    public List<VwOfertasGrupo> getLstOfertasByGrupo(Integer grupo) {
        return ofertas.findOfertasByGrupo(grupo);
    }
    
    @Override
    public List<VwContratosGrupo> getLstContratosByGrupo(Integer grupo) {
        return contratos.findContratosByGrupo(grupo);
    }
  
}