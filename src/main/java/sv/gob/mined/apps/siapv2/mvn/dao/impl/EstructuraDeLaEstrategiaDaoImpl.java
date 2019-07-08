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
import sv.gob.mined.apps.siapv2.mvn.dao.EstructuraDeLaEstrategiaDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.EstructuraDeLaEstrategia;

/**
 *
 * @author Infosoft
 */
@Repository
public class EstructuraDeLaEstrategiaDaoImpl implements EstructuraDeLaEstrategiaDao {

    public EstructuraDeLaEstrategiaDaoImpl() {
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
    public List<EstructuraDeLaEstrategia> findAll() {
        String sql = "SELECT * FROM EstructuraDeLaEstrategia";
        List<EstructuraDeLaEstrategia> estructuradelaestrategia = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(EstructuraDeLaEstrategia.class));
        return estructuradelaestrategia;
    }

    @Override
    public EstructuraDeLaEstrategia findEstructuraById(Integer id) {
        EstructuraDeLaEstrategia est= new EstructuraDeLaEstrategia();
        String sql = "select * from dbo.EstructuraDeLaEstrategia where idEstructuraEstrategia = "+id;
        List<EstructuraDeLaEstrategia> estructuradelaestrategia = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(EstructuraDeLaEstrategia.class));
        if(!estructuradelaestrategia.isEmpty()){
            est=estructuradelaestrategia.get(0);
        }
        return est;
    }
}
