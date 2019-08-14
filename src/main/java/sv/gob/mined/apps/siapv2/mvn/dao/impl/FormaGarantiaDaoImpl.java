/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.FormaGarantiaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.FormaGarantia;

/**
 *
 * @author Infosoft
 */
@Repository
public class FormaGarantiaDaoImpl extends XJdbcTemplate implements FormaGarantiaDao {

    public FormaGarantiaDaoImpl() {
    }

    @Override
    public List<FormaGarantia> findFormaGarantia() {
        String sql = "SELECT * FROM forma_garantia WHERE estado_forma_garantia = 1";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(FormaGarantia.class));
    }
}
