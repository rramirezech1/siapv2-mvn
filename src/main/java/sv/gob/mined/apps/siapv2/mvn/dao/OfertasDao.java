/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwOfertasGrupo;

/**
 *
 * @author Infosoft
 */
public interface OfertasDao {

    public List<VwOfertasGrupo> findOfertasByGrupo(Integer grupo);
    
    public VwOfertasGrupo findById(Integer idOferta);
}
