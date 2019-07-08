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
import sv.gob.mined.apps.siapv2.mvn.dao.PgTiposProyectosDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgTiposProyectos;

/**
 *
 * @author RCeron
 */
@Repository
public class PgTiposProyectosDaoImpl implements PgTiposProyectosDao {

    public PgTiposProyectosDaoImpl() {
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
    public List<PgTiposProyectos> findAll() {
        String sql = "SELECT * FROM pg_tipos_proyectos";
        List<PgTiposProyectos> pgtiposproyectos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgTiposProyectos.class));
        return pgtiposproyectos;
    }
}
