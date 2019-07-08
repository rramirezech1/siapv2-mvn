/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PgMetasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgMetas;

/**
 *
 * @author Infosoft
 */
@Repository
public class PgMetasDaoImpl extends XJdbcTemplate implements PgMetasDao {

    private PgMetas pgMeta;

    public PgMetasDaoImpl() {
    }
    @Autowired
    private TipoDeMetasDaoImpl tipoMeta;

    public TipoDeMetasDaoImpl getTipoMeta() {
        return tipoMeta;
    }

    public void setTipoMeta(TipoDeMetasDaoImpl tipoMeta) {
        this.tipoMeta = tipoMeta;
    }

    @Override
    public List<PgMetas> findAll(Integer componente) {
        String sql = "SELECT * FROM pg_metas where estadoDeEliminacion = 0 and componente = " + componente + " order by meta";
        List<PgMetas> pgmetas = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgMetas.class));
        for (PgMetas pgMetas : pgmetas) {
            pgMetas.setTipoDeMetas(tipoMeta.findById(pgMetas.getIdTipoMeta()));
        }
        return pgmetas;
    }

    @Override
    public int create() {
        super.setObjeto(pgMeta);
        int idMeta = super.create();
        this.pgMeta.setMeta(idMeta);
        return idMeta;
    }

    @Override
    public int update() {
        super.setObjeto(pgMeta);
        return super.update();
    }

    @Override
    public PgMetas getPgMeta() {
        return pgMeta;
    }

    @Override
    public void setPgMeta(PgMetas pgMeta) {
        this.pgMeta = pgMeta;
    }

    @Override
    public List<PgMetas> findAll() {
        String sql = "SELECT * FROM pg_metas WHERE estadoDeEliminacion = 0";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgMetas.class));
    }

    @Override
    public List<PgMetas> findMetasByConvenio(Integer convenio) {
        String sql = "select * from pg_metas inner join pg_componentes on pg_metas.componente=pg_componentes.componente where pg_metas.estadoDeEliminacion = 0 and convenio="+convenio;
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgMetas.class));
    }

    @Override
    public PgMetas getPgMetasById(Integer id) {
        PgMetas meta = new PgMetas();
       String sql = "select * from dbo.pg_metas where estadoDeEliminacion = 0 and meta="+id;
        List<PgMetas> lista= getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgMetas.class));
        if(!lista.isEmpty()){
            meta = lista.get(0);
        }
        return meta;
    }
}
