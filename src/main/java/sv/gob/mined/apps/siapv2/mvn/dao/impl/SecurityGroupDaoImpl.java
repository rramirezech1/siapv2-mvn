/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityGroupDao;

/**
 *
 * @author Infosoft
 */
@Repository
public class SecurityGroupDaoImpl extends XJdbcTemplate implements SecurityGroupDao {

    public SecurityGroupDaoImpl() {
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
    public List<SecurityGroup> findAll() {
        String sql = "SELECT * FROM Security_Group";
        List<SecurityGroup> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityGroup.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityGroup.class));
        }
    }
    
    @Override
    public SecurityGroup findById(Integer idGrupo) {
        String sql = "SELECT * FROM SecurityGroup WHERE idGrupo = " + idGrupo;
        List<SecurityGroup> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityGroup.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
     @Override
    public void setSecurityGroup(SecurityGroup grupo) {
        super.setObjeto(grupo);
    }
    
        
}