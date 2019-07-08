/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityTemplateDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityTemplate;

/**
 *
 * @author
 */
@Repository
public class SecurityTemplateDaoImpl extends XJdbcTemplate implements SecurityTemplateDao {

    @Override
    public List<SecurityTemplate> findAll() {
        String sql = "SELECT * FROM security_template";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityTemplate.class));
    }
}
