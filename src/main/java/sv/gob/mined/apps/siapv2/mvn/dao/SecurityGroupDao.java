/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;

/**
 *
 * @author Infosoft
 */
public interface SecurityGroupDao {

    public int create();

    public int update();
    
    public List<SecurityGroup> findAll();
    
    public SecurityGroup findById(Integer id);
    
    public void setSecurityGroup(SecurityGroup grupo);
    
  
}
