/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityInfo;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;

/**
 *
 * @author Infosoft
 */
public interface SeguridadBo {

    public List<SecurityUsers> getLstSecurityUser();
    
    public List<SecurityGroup> getLstSecurityGroup();

    public List<SecurityUsers> getLstSecurityUserByCriteria(String criteria);

    public SecurityUsers buscarSecurityUserById(Integer id);
    
    public SecurityGroup buscarSecurityGroupById(Integer id);
    
    public Integer saveSecurityGroup(SecurityGroup grupo);
    
    public Integer saveSecurityInfo(SecurityInfo securityinfo);
    
}
