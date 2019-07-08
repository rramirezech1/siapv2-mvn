/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.NivelesOrganigramaInstitucionalDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.NivelesOrganigramaInstitucional;

/**
 *
 * @author
 */
@Repository
public class NivelesOrganigramaInstitucionalDaoImpl extends XJdbcTemplate implements NivelesOrganigramaInstitucionalDao {

    @Override
    public List<NivelesOrganigramaInstitucional> findAll() {
        String sql = "SELECT * FROM NivelesOrganigramaInstitucional";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(NivelesOrganigramaInstitucional.class));
    }
}
