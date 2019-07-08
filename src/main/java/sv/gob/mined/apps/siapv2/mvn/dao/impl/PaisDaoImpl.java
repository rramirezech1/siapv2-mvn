/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PaisDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.Pais;

/**
 *
 * @author Infosoft
 */
@Repository
public class PaisDaoImpl extends XJdbcTemplate implements PaisDao{

    public PaisDaoImpl() {
    }

    @Override
    public List<Pais> findAll() {
        String sql = "SELECT * FROM PAIS";
        List<Pais> pais = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Pais.class));
        return pais;
    }
}