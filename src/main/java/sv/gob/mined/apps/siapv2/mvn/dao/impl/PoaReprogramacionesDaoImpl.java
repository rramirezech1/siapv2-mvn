/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PoaReprogramacionesDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaDetalleReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursosSaldos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaReprogramaciones;

/**
 *
 * @author Infosoft
 */
@Repository
public class PoaReprogramacionesDaoImpl extends XJdbcTemplate implements PoaReprogramacionesDao {

    private PoaReprogramaciones poaReprogramacion;
    private PoaDetalleReprogramaciones detPoaReprogramacion;

    public PoaReprogramacionesDaoImpl() {
    }

    @Override
    public List<PoaReprogramaciones> findAll() {
        String sql = "SELECT * FROM poa_reprogramaciones";
        List<PoaReprogramaciones> poareprogramaciones = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaReprogramaciones.class));
        return poareprogramaciones;
    }

    @Override
    public int createFichaReprogramacion() {
        super.setObjeto(poaReprogramacion);
        int repro = super.create();
        this.poaReprogramacion.setReprogramacion(repro);
        return repro;
    }

    @Override
    public int updateFichaReprogramacion() {
        super.setObjeto(poaReprogramacion);
        return super.update();
    }

    @Override
    public PoaReprogramaciones getPoaReprogramacion() {
        return poaReprogramacion;
    }

    @Override
    public void setPoaReprogramacion(PoaReprogramaciones poaReprogramacion) {
        this.poaReprogramacion = poaReprogramacion;
    }

    @Override
    public List<PoaReprogramaciones> findAllByPoa(Integer poa) {
        String sql = "SELECT p.*, t.descripcion_tipo FROM poa_reprogramaciones p inner join tipos_poa_reprogramaciones t on p.tipo_poa_reprog = t.tipo_poa_reprog where p.poa =" + poa;
        List<PoaReprogramaciones> lstReprogramaciones = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaReprogramaciones.class));
        return lstReprogramaciones;
    }

    @Override
    public int saveDetalleReprogramacion() {
        super.setObjeto(detPoaReprogramacion);
        int repro;
        if (detPoaReprogramacion.getPoaDetalleReprogramacion() == null) {
            repro = super.create();
            this.detPoaReprogramacion.setPoaDetalleReprogramacion(repro);
        } else {
            repro = super.update();
        }
        return repro;
    }

    @Override
    public void setDetPoaReprogramacion(PoaDetalleReprogramaciones detRepro) {
        this.detPoaReprogramacion = detRepro;
    }

    @Override
    public List<PoaDetalleReprogramaciones> findDetalleReprogramacionByPoaReprogramacion(Integer poaReprogramacion) {
        String sql = "SELECT * FROM poa_detalle_reprogramaciones where poa_reprogramacion = " + poaReprogramacion + " order by poa_detalle_reprogramacion, organismo";
        List<PoaDetalleReprogramaciones> lstDetReprogramaciones = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaDetalleReprogramaciones.class));
        for (PoaDetalleReprogramaciones poaDetalleReprogramaciones : lstDetReprogramaciones) {
            sql = "SELECT * FROM poa_recursos where estadoDeEliminacion = 0 and recurso = " + poaDetalleReprogramaciones.getRecurso();
            poaDetalleReprogramaciones.setPoaRecurso((PoaRecursos) getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaRecursos.class)).get(0));


            sql = "SELECT * FROM poa_recursos_saldos where estadoDeEliminacion = 0 and recurso = " + poaDetalleReprogramaciones.getRecurso();
            poaDetalleReprogramaciones.getPoaRecurso().setLstPoaRecursosSaldos(getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaRecursosSaldos.class)));
        }
        return lstDetReprogramaciones;
    }
}