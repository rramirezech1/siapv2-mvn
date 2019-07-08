/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityUsersDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;

/**
 *
 * @author
 */
@Repository
public class SecurityUsersDaoImpl extends XJdbcTemplate implements SecurityUsersDao {

    @Override
    public List<SecurityUsers> findAll() {
        String sql = "SELECT * FROM security_users";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUsers.class));
    }

    @Override
    public List<SecurityUsers> findByCriteria(String criteria) {
        String sql = "select * from security_users where description like'%" + criteria + "%'";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUsers.class));
    }

    @Override
    public SecurityUsers findById(Integer id) {
        String sql = "select * from security_users where idUserSeguridad=?";
        List<SecurityUsers> lista = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUsers.class), new Object[]{id});
        return lista.get(0);
    }

    @Override
    public List<SecurityUsers> findAllUserModifi(Integer id) {
        String sql = "select  * from security_users where name in(select distinct user_name from security_info where idPlantillaSeguridad=" + id + ")";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUsers.class));
    }
}
