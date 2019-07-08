/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityInfoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityInfo;

/**
 *
 * @author reynaldo
 */
@Repository
public class SecurityInfoDaoImpl extends XJdbcTemplate implements SecurityInfoDao {

    private SecurityInfo securityinfo;

    @Override
    public List<SecurityInfo> findAll() {
        String sql = "SELECT * FROM security_info";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityInfo.class));

    }

    @Override
    public void setSecurityInfo(SecurityInfo securityinfo) {
        this.securityinfo = securityinfo;
    }

    @Override
    public SecurityInfo getSecurityInfo() {
        return this.securityinfo;
    }

    @Override
    public int create() {
        int rows = getJdbcTemplate().update(securityinfo.generarInsertSQL(), securityinfo.getDatosInsert());
        return rows;
    }

    @Override
    public int update() {
        int rows = getJdbcTemplate().update(securityinfo.generarUpdateSQL(), securityinfo.getDatosUpdate());
        return rows;
    }

    @Override
    public List<SecurityInfo> getSecurityInfoByIdPlantilla(Integer id) {
        String sql = "SELECT * FROM security_info where idPlantillaSeguridad=" + id;
        List<SecurityInfo> lista = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityInfo.class));
        if (!lista.isEmpty()) {
            SecurityInfo security = lista.get(0);
            String user = security.getUser_name();
            sql = "SELECT * FROM security_info where idPlantillaSeguridad= " + id + "and user_name='" + user + "'";
            lista = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityInfo.class));
        }
        return lista;
    }

    @Override
    public void deleteDetalleEdit(Integer id) {
        String sql = "delete FROM security_info where idPlantillaSeguridad=" + id;
        getJdbcTemplate().update(sql);
    }
}
