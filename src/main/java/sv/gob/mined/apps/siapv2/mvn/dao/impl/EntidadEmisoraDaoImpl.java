/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.EntidadEmisoraDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.EntidadEmisora;

/**
 *
 * @author Infosoft
 */
@Repository
public class EntidadEmisoraDaoImpl extends XJdbcTemplate implements EntidadEmisoraDao {

    public EntidadEmisoraDaoImpl() {
    }    

    @Override
    public List<EntidadEmisora> findAll() {
        String sql = "SELECT * FROM EntidadEmisora";
        List<EntidadEmisora> entidadEmisora = getJdbcTemplate().query(sql,new BeanPropertyRowMapper(EntidadEmisora.class));
        return entidadEmisora;
    }
}
