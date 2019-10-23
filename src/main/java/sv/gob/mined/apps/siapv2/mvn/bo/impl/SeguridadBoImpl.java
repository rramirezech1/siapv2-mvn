/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.SeguridadBo;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityUsersDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityGroupDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityInfo;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityInfoDao;

/**
 *
 * @author
 */
@Service
public class SeguridadBoImpl implements SeguridadBo {

    public SeguridadBoImpl() {
    }
    
    @Autowired
    private SecurityUsersDao securityusersdao;
    @Autowired
    private SecurityGroupDao securitygroupdao;
    @Autowired
    private SecurityInfoDao securityinfodao;
    
    
    @Override
    public List<SecurityUsers> getLstSecurityUser() {
        return securityusersdao.findAll();
    }
    
     @Override
    public List<SecurityGroup> getLstSecurityGroup() {
        return securitygroupdao.findAll();
    }

    @Override
    public List<SecurityUsers> getLstSecurityUserByCriteria(String criteria) {
        return securityusersdao.findByCriteria(criteria);
    }

    @Override
    public SecurityUsers buscarSecurityUserById(Integer id) {
        return securityusersdao.findById(id);
    }

    @Override
    public SecurityGroup buscarSecurityGroupById(Integer id) {
        return securitygroupdao.findById(id);
    }
    
    @Override
    public Integer saveSecurityGroup(SecurityGroup grupo) {
        securitygroupdao.setSecurityGroup(grupo);
        if (grupo.getIdGrupo() == null) {
            Integer id = securitygroupdao.create();
            grupo.setIdGrupo(id);
            return id;
        } else {
            return securitygroupdao.update();
        }
    }
    
    @Override
    public Integer saveSecurityInfo(SecurityInfo securityinfo) {
        securityinfodao.setSecurityInfo(securityinfo);
        return securityinfodao.create();
    }
    
}
