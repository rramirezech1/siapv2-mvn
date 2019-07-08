/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.ProcedimientoTareaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.ProcedimientoTarea;

/**
 *
 * @author
 */
@Repository
public class ProcedimientoTareaDaoImpl extends XJdbcTemplate implements ProcedimientoTareaDao {

    @Override
    public List<ProcedimientoTarea> findAll() {
        String sql = "SELECT * FROM procedimiento_tarea";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProcedimientoTarea.class));
    }
}
