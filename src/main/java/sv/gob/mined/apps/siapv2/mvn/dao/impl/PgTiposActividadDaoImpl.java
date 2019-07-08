/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PgTiposActividadDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgTiposActividad;

/**
 *
 * @author Infosoft
 */
@Repository
public class PgTiposActividadDaoImpl implements PgTiposActividadDao {

    public PgTiposActividadDaoImpl() {
    }
    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PgTiposActividad> findAll() {
        String sql = "SELECT * FROM pg_tipos_actividad";
        List<PgTiposActividad> tiposactividad = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgTiposActividad.class));
        return tiposactividad;
    }
}
