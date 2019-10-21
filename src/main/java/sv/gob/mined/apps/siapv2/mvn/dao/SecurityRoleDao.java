/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityRole;

/**
 *
 * @author Infosoft
 */
public interface SecurityRoleDao {

    public int create();

    public int update();
    
    public List<SecurityRole> findAll();
    
    public SecurityRole findById(Integer id);
    
    public void setSecurityRole(SecurityRole role);
    
  
}
