/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGrupos;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwContratosGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwOfertasGrupo;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwAutoriza;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwTecnicoUaci;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwProveedoresSiap;

public interface ConsultasSiapBo {
          
   public List<VwGrupos> getLstGrupos(String usuarioSession, Integer tipoGrupo);
   
   public List<VwAutoriza> getLstAutoriza();
   
   public List<VwTecnicoUaci> getLstTecnicoUaci();
    
   public VwGrupos getGrupoById(Integer id);
   
   public VwOfertasGrupo getOfertaByGrupoOferente (Integer grupo, Integer idProveedor);
   
   public VwContratosGrupo getContratoByIdContrato (Integer idContrato);
   
   public List<VwOfertasGrupo> getLstOfertasByGrupo (Integer grupo);
      
   public List<VwContratosGrupo> getLstContratosByGrupo (Integer grupo);
   
   public List<VwProveedoresSiap> getLstProveedoresSiap (String condicion);
   
}



