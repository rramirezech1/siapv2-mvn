/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.AutorizaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwAutoriza;

/**
 *
 * @author Infosoft
 */
@Repository
public class AutorizaDaoImpl extends XJdbcTemplate2 implements AutorizaDao {

    public AutorizaDaoImpl() {
    }

    @Override
    public List<VwAutoriza> findAll() {
        
        String sql = " SELECT g_personal.personal_id, g_personal.primer_nombre + ' ' + ISNULL(g_personal.segundo_nombre, '') + ' ' + ISNULL(g_personal.primer_apellido, '') + ' ' + ISNULL(g_personal.segundo_apellido, '') AS nombre\n" +
                     " FROM   security_users INNER JOIN\n" +
                     " g_personal ON security_users.personal_id = g_personal.personal_id INNER JOIN\n" +
                     " security_groupings ON security_users.name = security_groupings.user_name\n" +
                     " WHERE (security_groupings.group_name LIKE '%JEFATURA_UACI%')";
        
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwAutoriza.class));
    }
    
 }
