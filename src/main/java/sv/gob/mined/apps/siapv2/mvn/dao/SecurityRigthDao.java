/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityRigth;

/**
 *
 * @author Infosoft
 */
public interface SecurityRigthDao {

    public int create();

    public int update();
    
    public List<SecurityRigth> findAll();
    
    public SecurityRigth findById(Integer id);
    
    public void setSecurityRigth(SecurityRigth rigth);
}
