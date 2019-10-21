/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityRigth;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityRigthDao;

/**
 *
 * @author Infosoft
 */
@Repository
public class SecurityRigthImpl extends XJdbcTemplate implements SecurityRigthDao {

    public SecurityRigthImpl() {
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
    public List<SecurityRigth> findAll() {
        String sql = "SELECT * FROM SecurityRigth";
        List<SecurityRigth> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityRigth.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityRigth.class));
        }
    }
    
    @Override
    public SecurityRigth findById(Integer idRigth) {
        String sql = "SELECT * FROM SecurityRigth WHERE idRigth = " + idRigth;
        List<SecurityRigth> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityRigth.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
     @Override
    public void setSecurityRigth(SecurityRigth rigth) {
        super.setObjeto(rigth);
    }
}