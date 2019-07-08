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
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGrupos;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwContratosGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwOfertasGrupo;

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
    private ContratosDao contratos;
    @Autowired
    private OfertasDao ofertas;
    
    @Override
    public List<VwGrupos> getLstGrupos(String usuarioSession, Integer tipoGrupo) {
        return grupos.findGruposByUser(usuarioSession, tipoGrupo);
    }
    
    @Override
    public VwGrupos getGrupoById(Integer id) {
        return grupos.findById(id);
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