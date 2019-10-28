/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;

/**
 *
 * @author Infosoft
 */
public interface SecurityUserGroupDao {

    public int create();

    public int update();
    
    public List<SecurityUserGroup> findAll();
    
    public SecurityUserGroup findById(Integer idUserGroup);
    
    public void setSecurityUserGroup(SecurityUserGroup idUserGroup);
    
}
