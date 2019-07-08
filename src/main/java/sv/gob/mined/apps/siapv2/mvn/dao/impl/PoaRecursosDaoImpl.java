/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PoaRecursosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.ClasificadoresPresupuestarios;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgCategorias;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgConvenio;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgTramosCategorias;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursosSaldos;

/**
 *
 * @author Infosoft
 */
@Repository
public class PoaRecursosDaoImpl extends XJdbcTemplate implements PoaRecursosDao {

    private PoaRecursos recurso;

    public PoaRecursosDaoImpl() {
    }

    @Override
    public int create() {
        super.setObjeto(recurso);
        int idRecurso = super.create();
        this.recurso.setRecurso(idRecurso);
        return idRecurso;
    }

    @Override
    public int update() {
        super.setObjeto(recurso);
        return super.update();
    }

    @Override
    public List<PoaRecursos> findAll() {
        String sql = "SELECT * FROM poa_recursos where estadoDeEliminacion = 0";
        List<PoaRecursos> poarecursos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaRecursos.class));
        return poarecursos;
    }

    @Override
    public void setPoaRecursos(PoaRecursos recurso) {
        this.recurso = recurso;
    }

    @Override
    public PoaRecursos getPoaRecursos() {
        return recurso;
    }

    @Override
    public List<PoaRecursos> findAllByActividad(Integer actividad) {
        String sql = "SELECT * FROM poa_recursos where estadoDeEliminacion = 0 and actividad = " + actividad;
        List<PoaRecursos> poarecursos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaRecursos.class));
        for (PoaRecursos poaRecursos : poarecursos) {
            sql = "SELECT * FROM poa_recursos_saldos where estadoDeEliminacion = 0 and recurso = " + poaRecursos.getRecurso();
            poaRecursos.setLstPoaRecursosSaldos(getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaRecursosSaldos.class)));
        }

        return poarecursos;
    }

    @Override
    public String categoriaByRecurso(Integer recurso) {
        String sql = "select pg_categorias.* from poa_recursos inner join pg_categorias on poa_recursos.id_categoria = pg_categorias.id_categoria where poa_recursos.estadoDeEliminacion = 0 and recurso = " + recurso;
        List<PgCategorias> lstPgCategoria = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgCategorias.class));
        if (lstPgCategoria.isEmpty()) {
            return "";
        } else {
            return lstPgCategoria.get(0).getDescripcion();
        }
    }

    @Override
    public Integer idCategoriaByRecurso(Integer recurso) {
        String sql = "select pg_categorias.* from poa_recursos inner join pg_categorias on poa_recursos.id_categoria = pg_categorias.id_categoria where poa_recursos.estadoDeEliminacion and recurso = " + recurso;
        List<PgCategorias> lstPgCategoria = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgCategorias.class));
        if (lstPgCategoria.isEmpty()) {
            return -1;
        } else {
            return lstPgCategoria.get(0).getIdCategoria();
        }
    }

    @Override
    public String oegByRecurso(Integer recurso) {
        String sql = "select cp.* from clasificadores_presupuestarios cp "
                + "    inner join g_catalogo_productos gcp on cp.clasificador_presupuestario = gcp.clasificador_presupuestario "
                + "    inner join poa_recursos on gcp.producto = poa_recursos.producto "
                + "where poa_recursos.estadoDeEliminacion=0 and recurso = " + recurso;
        List<ClasificadoresPresupuestarios> lstClasificadores = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ClasificadoresPresupuestarios.class));
        if (lstClasificadores.isEmpty()) {
            return "";
        } else {
            return lstClasificadores.get(0).getClasificadorPresupuestario();
        }
    }

    @Override
    public BigDecimal getPorcentajeFtePpal(Integer recurso) {
        String sql = "select pg_tramos_categorias.* from poa_recursos "
                + "    inner join pg_categorias on poa_recursos.id_categoria = pg_categorias.id_categoria "
                + "    inner join pg_tramos_categorias on pg_categorias.id_categoria = pg_tramos_categorias.id_categoria "
                + "where poa_recursos.estadoDeEliminacion = 0 and recurso = " + recurso;
        List<PgTramosCategorias> lstTramos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgTramosCategorias.class));
        if (lstTramos.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return lstTramos.get(0).getPorcentajeBanco();
        }
    }

    @Override
    public BigDecimal getPorcentajeByCategoria(Integer idCategoria) {
        String sql = "select * from pg_tramos_categorias "
                + "where id_categoria  = " + idCategoria;
        List<PgTramosCategorias> lstTramos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgTramosCategorias.class));
        if (lstTramos.isEmpty()) {
            return BigDecimal.ZERO;
        } else {
            return lstTramos.get(0).getPorcentajeBanco();
        }
    }

    @Override
    public PoaRecursos getRecursoById(Integer recurso) {
        String sql = "SELECT * FROM poa_recursos where estadoDeEliminacion = 0 and recurso = " + recurso;
        List<PoaRecursos> poarecursos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaRecursos.class));
        for (PoaRecursos poaRecursos : poarecursos) {
            sql = "SELECT * FROM poa_recursos_saldos where estadoDeEliminacion = 0 and recurso = " + poaRecursos.getRecurso();
            poaRecursos.setLstPoaRecursosSaldos(getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PoaRecursosSaldos.class)));
        }

        return poarecursos.get(0);
    }

    @Override
    public Integer estadoConvenio(Integer convenio) {
        String sql = "SELECT * FROM pg_convenio where convenio = " + convenio;
        List<PgConvenio> lstPgConvenio = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PgConvenio.class));

        return lstPgConvenio.get(0).getIdEstadoEjecProyecto();
    }
}
