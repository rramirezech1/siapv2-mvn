/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityRole;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityRoleDao;

/**
 *
 * @author Infosoft
 */
@Repository
public class SecurityRoleDaoImpl extends XJdbcTemplate implements SecurityRoleDao {

    public SecurityRoleDaoImpl() {
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
    public List<SecurityRole> findAll() {
        String sql = "SELECT * FROM SecurityGroup";
        List<SecurityRole> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityRole.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityRole.class));
        }
    }
    
    @Override
    public SecurityRole findById(Integer idRole) {
        String sql = "SELECT * FROM SecurityRole WHERE idRole = " + idRole;
        List<SecurityRole> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityRole.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
     @Override
    public void setSecurityRole(SecurityRole role) {
        super.setObjeto(role);
    }
    
        
}