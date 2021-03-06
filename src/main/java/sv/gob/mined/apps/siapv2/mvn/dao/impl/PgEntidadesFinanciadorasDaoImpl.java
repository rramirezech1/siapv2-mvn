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
import sv.gob.mined.apps.siapv2.mvn.dao.PgEntidadesFinanciadorasDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgEntidadesFinanciadoras;

/**
 *
 * @author Infosoft
 */
@Repository
public class PgEntidadesFinanciadorasDaoImpl implements PgEntidadesFinanciadorasDao {

    public PgEntidadesFinanciadorasDaoImpl() {
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
    public List<PgEntidadesFinanciadoras> findAll() {
        String sql = "SELECT * FROM pg_entidades_financiadoras";
        List<PgEntidadesFinanciadoras> pgentidadesfinanciadoras = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgEntidadesFinanciadoras.class));
        return pgentidadesfinanciadoras;
    }
}
