/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwUsuarioGrupos;

/**
 *
 * @author Infosoft
 */
public interface SecurityUserGroupDao {

    public int create();

    public int update();
    
    public List<SecurityUserGroup> findAll();
    
    public SecurityUserGroup findById(Integer idUserGroup);
    
    public List<VwUsuarioGrupos> findAllView();
    
    public void setSecurityUserGroup(SecurityUserGroup idUserGroup);

  

    
}
