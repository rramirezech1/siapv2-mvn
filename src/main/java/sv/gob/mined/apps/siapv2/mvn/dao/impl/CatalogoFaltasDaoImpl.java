/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.CatalogoFaltasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoCalificacionContratista;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoGarantias;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoMultas;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRescision;

@Repository
public class CatalogoFaltasDaoImpl extends XJdbcTemplate implements CatalogoFaltasDao {

    @Override
    public List<TipoMultas> findTipoMultas() {
        String sql = "SELECT * FROM TipoMultas";
        List<TipoMultas> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TipoMultas.class));
        return lst;
    }

    @Override
    public List<TipoGarantias> findTipoGarantias() {
        String sql = "SELECT * FROM TipoGarantias";
        List<TipoGarantias> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TipoGarantias.class));
        return lst;
    }

    @Override
    public List<TipoRescision> findTipoRescision() {
        String sql = "SELECT * FROM TipoRescision";
        List<TipoRescision> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TipoRescision.class));
        return lst;
    }

    @Override
    public List<TipoCalificacionContratista> findTipoClasificacion() {
        String sql = "SELECT * FROM TipoCalificacionContratista";
        List<TipoCalificacionContratista> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TipoCalificacionContratista.class));
        return lst;
    }
}
