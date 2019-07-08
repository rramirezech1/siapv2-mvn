/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.NivelesRealizacGeografDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.NivelesRealizacGeograf;

/**
 *
 * @author
 */
@Repository
public class NivelesRealizacGeografDaoImpl extends XJdbcTemplate implements NivelesRealizacGeografDao {

    @Override
    public List<NivelesRealizacGeograf> findAll() {
        String sql = "SELECT * FROM NivelesRealizacGeograf";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(NivelesRealizacGeograf.class));
    }
}
