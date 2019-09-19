/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.TecnicoUaciDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwTecnicoUaci;

/**
 *
 * @author Infosoft
 */
@Repository
public class TecnicoUaciDaoImpl extends XJdbcTemplate2 implements TecnicoUaciDao {

    public TecnicoUaciDaoImpl() {
    }

    @Override
    public List<VwTecnicoUaci> findAll() {
        
        String sql = " SELECT     g_personal.personal_id, g_personal.primer_nombre + ' ' + ISNULL(g_personal.segundo_nombre, '') + ' ' + ISNULL(g_personal.primer_apellido, '') + ' ' + ISNULL(g_personal.segundo_apellido, '') AS nombre\n" +
                     " FROM         security_users INNER JOIN  g_personal ON security_users.personal_id = g_personal.personal_id\n" +
                     " WHERE   (security_users.activo = 1) AND   (g_personal.estructura_organizativa = '15') \n" +
                     " ORDER BY g_personal.primer_nombre + ' ' + ISNULL(g_personal.segundo_nombre, '') + ' ' + ISNULL(g_personal.primer_apellido, '') + ' ' + ISNULL(g_personal.segundo_apellido,'')";
        
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwTecnicoUaci.class));
    }
    
 }
