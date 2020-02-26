/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityUserGroupDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwUsuarioGrupos;

/**
 *
 * @author Infosoft
 */
@Repository
public class SecurityUserGroupDaoImpl extends XJdbcTemplate implements SecurityUserGroupDao {

    public SecurityUserGroupDaoImpl() {
    }

  @Override
    public int create() {
        return super.create();
    }

    @Override
    public int update() {
        return super.update();
    }
    
    
     @Override
    public List<SecurityUserGroup> findAll() {
        String sql = "SELECT * FROM Security_user_group";
        List<VwUsuarioGrupos> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwUsuarioGrupos.class));
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwUsuarioGrupos.class));
    }
    
    @Override
    public List<VwUsuarioGrupos> findAllView() {
        String sql = "SELECT security_user_group.id_user_group, security_user_group.userName, security_user_group.idGrupo, security_user_group.name, security_group.grupo, ISNULL(siap_2010.dbo.g_personal.primer_nombre, '') \n" +
                     " + ' ' + ISNULL(siap_2010.dbo.g_personal.segundo_nombre, '') + ' ' + ISNULL(siap_2010.dbo.g_personal.primer_apellido, '') + ' ' + ISNULL(siap_2010.dbo.g_personal.segundo_apellido, '') AS nombreUsuario\n" +
                     " FROM security_user_group INNER JOIN\n" +
                     " security_group ON security_user_group.idGrupo = security_group.idGrupo INNER JOIN\n" +
                     " siap_2010.dbo.security_users ON security_user_group.userName = siap_2010.dbo.security_users.name INNER JOIN\n" +
                     " siap_2010.dbo.g_personal ON siap_2010.dbo.security_users.personal_id = siap_2010.dbo.g_personal.personal_id\n" +
                     " WHERE (security_user_group.estadoDeEliminacion = 0)";
        
        List<SecurityUserGroup> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUserGroup.class));
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUserGroup.class));
    }
        
    @Override
    public SecurityUserGroup findById(Integer idUserGroup) {
        String sql = "SELECT * FROM security_user_group WHERE idUserGroup = "+ idUserGroup;
        List<SecurityUserGroup> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUserGroup.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
     @Override
    public void setSecurityUserGroup(SecurityUserGroup idUserGroup) {
        super.setObjeto(idUserGroup);
    }
        
}