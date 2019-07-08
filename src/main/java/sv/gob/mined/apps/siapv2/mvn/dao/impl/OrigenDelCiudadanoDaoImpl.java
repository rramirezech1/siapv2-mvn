/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.OrigenDelCiudadanoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.OrigenDelCiudadano;

/**
 *
 * @author Infosoft
 */
@Repository
public class OrigenDelCiudadanoDaoImpl extends XJdbcTemplate implements OrigenDelCiudadanoDao {

    public OrigenDelCiudadanoDaoImpl() {
    }

    @Override
    public List<OrigenDelCiudadano> findAll() {
        String sql = "SELECT * FROM ORIGENDELCIUDADANO";
        List<OrigenDelCiudadano> origenes = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OrigenDelCiudadano.class));
        return origenes;
    }
}