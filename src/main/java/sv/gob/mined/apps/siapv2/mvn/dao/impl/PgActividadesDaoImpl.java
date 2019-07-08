/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PgActividadesDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgAnosPlanGlobal;

/**
 *
 * @author Infosoft
 */
@Repository
public class PgActividadesDaoImpl extends XJdbcTemplate implements PgActividadesDao {

    private PgActividades pgActividad;

    public PgActividadesDaoImpl() {
    }

    @Override
    public List<PgActividades> findAll() {
        String sql = "SELECT * FROM pg_actividades WHERE estadoDeEliminacion = 0";
        List<PgActividades> pgactividades = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgActividades.class));

        return pgactividades;
    }

    private PgAnosPlanGlobal findByKey(Integer id) {
        String sql = "SELECT * FROM pg_anos_plan_global WHERE estadoDeEliminacion = 0 and identificadorAnoPap = " + id;
        List<PgAnosPlanGlobal> pganosplanglobal = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgAnosPlanGlobal.class));
        if (pganosplanglobal.isEmpty()) {
            return null;
        } else {
            return pganosplanglobal.get(0);
        }
    }

    @Override
    public int create() {
        super.setObjeto(pgActividad);
        int idActividad = super.create();
        this.pgActividad.setActividad(idActividad);
        return idActividad;
    }

    @Override
    public int update() {
        super.setObjeto(pgActividad);
        return super.update();
    }

    @Override
    public void setPgActividad(PgActividades pgActividad) {
        this.pgActividad = pgActividad;
    }

    @Override
    public PgActividades getPgActividad() {
        return pgActividad;
    }

    @Override
    public List<PgActividades> findAllByMeta(Integer meta) {
        String sql = "SELECT * FROM pg_actividades WHERE estadoDeEliminacion = 0 and meta = " + meta;
        List<PgActividades> pgactividades = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgActividades.class));
        for (PgActividades pgActividades : pgactividades) {
            pgActividades.setPgAnosPlanGlobal(findByKey(pgActividades.getIdentificadorAnoPap()));
        }
        return pgactividades;
    }

    @Override
    public List<PgActividades> getPgActividadesbyMeta(Integer meta) {
        String sql = "SELECT * FROM pg_actividades WHERE estadoDeEliminacion = 0 and meta = " + meta;
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgActividades.class));
    }

    @Override
    public PgActividades getPgActividadById(Integer id) {
        PgActividades act = new PgActividades();
        String sql = "select * from dbo.pg_actividades where estadoDeEliminacion = 0 and actividad=" + id;
        List<PgActividades> lista = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgActividades.class));
        if(!lista.isEmpty()){
          act=lista.get(0);
        }
        return act;
    }
}
