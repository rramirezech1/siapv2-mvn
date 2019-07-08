/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.ProcesosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.Procesos;

/**
 *
 * @author
 */
@Repository
public class ProcesosDaoImpl extends XJdbcTemplate implements ProcesosDao {

    @Override
    public List<Procesos> findAll() {
        String sql = "SELECT * FROM procesos";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Procesos.class));
    }
}
