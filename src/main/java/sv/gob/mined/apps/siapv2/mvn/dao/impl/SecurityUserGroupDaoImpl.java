/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityUserGroupDao;

/**
 *
 * @author Infosoft
 */
@Repository
public class SecurityUserGroupDaoImpl extends XJdbcTemplate implements SecurityUserGroupDao {

    public SecurityUserGroupDaoImpl() {
    }

  @Override
    public int create() {
        return super.create();
    }

    @Override
    public int update() {
        return super.update();
    }
    
    @Override
    public List<SecurityUserGroup> findAll() {
        String sql = "SELECT * FROM Security_Group";
        List<SecurityUserGroup> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUserGroup.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUserGroup.class));
        }
    }
    
    @Override
    public SecurityUserGroup findById(Integer idUserGroup) {
        String sql = "SELECT * FROM security_user_group WHERE idUserGroup = "+ idUserGroup;
        List<SecurityUserGroup> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUserGroup.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
     @Override
    public void setSecurityUserGroup(SecurityUserGroup idUserGroup) {
        super.setObjeto(idUserGroup);
    }
        
}