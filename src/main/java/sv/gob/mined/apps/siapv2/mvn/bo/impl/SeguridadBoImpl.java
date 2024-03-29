/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.SeguridadBo;
import sv.gob.mined.apps.siapv2.mvn.dao.OpcionMenuDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityInfo;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityInfoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityUsersDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityGroupDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityUserGroupDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwUsuarioGrupos;

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
    private SecurityUserGroupDao securityusergroupdao;
    @Autowired
    private SecurityInfoDao securityinfodao;
    @Autowired
    private OpcionMenuDao opcionmenudao;

    @Override
    public List<SecurityUsers> getLstSecurityUser() {
        return securityusersdao.findAll();
    }

    @Override
    public List<SecurityGroup> getLstSecurityGroup() {
        return securitygroupdao.findAll();
    }

//    @Override
//    public List<OpcionMenu> getLstSecurityGroupOpciones(Integer rol) {
//        //System.out.println("El usuario en seguidadBolmpl es: "+rol);
//        return opcionmenudao.getLstSecurityGroupOpciones(rol);
//    }

    @Override
    public List<OpcionMenu> getLstSecurityGroupOpciones(List<SecurityUserGroup> list) {
        //System.out.println("El usuario en seguidadBolmpl es: "+rol);
        return opcionmenudao.getLstSecurityGroupOpciones(list);
    }

    @Override
    public List<SecurityUserGroup> getLstSecurityUserGroupByUser(String username) {
        return securityusergroupdao.findByUser(username);
    }

    @Override
    public List<SecurityUserGroup> getLstSecurityUserGroup() {
        return securityusergroupdao.findAll();
    }

    @Override
    public List<VwUsuarioGrupos> getLstUsuarioGrupos() {
        return securityusergroupdao.findAllView();
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
    public SecurityUserGroup buscarSecurityUserGroupById(Integer idUserGroup) {
        return securityusergroupdao.findById(idUserGroup);
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
    public Integer saveSecurityUserGroup(SecurityUserGroup userGroup) {
        securityusergroupdao.setSecurityUserGroup(userGroup);
        if (userGroup.getIdUserGroup() == null) {
            Integer id = securityusergroupdao.create();
            userGroup.setIdUserGroup(id);
            return id;
        } else {
            return securityusergroupdao.update();
        }
    }

    @Override
    public Integer saveSecurityInfo(SecurityInfo securityinfo) {
        securityinfodao.setSecurityInfo(securityinfo);
        return securityinfodao.create();
    }

    @Override
    public SecurityUsers usuarioOpciones(String us) {

        return opcionmenudao.usuarioOpciones(us);
    }

}
