/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGrupos;

/**
 *
 * @author Infosoft
 */
public interface GruposDao {

    public List<VwGrupos> findGruposByUser(String usuarioSession);
    
    public VwGrupos findById(Integer idGrupo);
}
