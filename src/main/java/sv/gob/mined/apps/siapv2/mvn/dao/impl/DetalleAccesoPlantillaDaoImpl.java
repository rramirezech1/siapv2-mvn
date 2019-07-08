/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.DetalleAccesoPlantillaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.dto.CadenaWhereMetasActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.DetalleAccesoPlantilla;

/**
 *
 * @author
 */
@Repository
public class DetalleAccesoPlantillaDaoImpl extends XJdbcTemplate implements DetalleAccesoPlantillaDao {

    private DetalleAccesoPlantilla detplantilla;

    @Override
    public List<DetalleAccesoPlantilla> findAll() {
        String sql = "select * from DetalleAccesoPlantilla";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(DetalleAccesoPlantilla.class));
    }

    @Override
    public int create() {
        return getJdbcTemplate().update(detplantilla.generarInsertSQL(), detplantilla.getDatosInsert());
    }

    @Override
    public int update() {
        int rows = getJdbcTemplate().update(detplantilla.generarUpdateSQL(), detplantilla.getDatosUpdate());
        return rows;
    }

    @Override
    public void setDetPlantillaSeg(DetalleAccesoPlantilla plantilla) {
        this.detplantilla = plantilla;
    }

    @Override
    public DetalleAccesoPlantilla getDetPlantillaSeg() {
        return detplantilla;
    }

    @Override
    public List<DetalleAccesoPlantilla> getDetPlantillaSegByIdPlantilla(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDetPlantillaSeg(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CadenaWhereMetasActividades> getListCadenaWhereMetasActividades(Integer id) {
        DetalleAccesoPlantilla det = getDepPlantillaWhere(id);
        String sql = "SELECT pg_convenio.convenio,pg_actividades.meta,pg_actividades.actividad FROM pg_convenio INNER JOIN pg_componentes ON pg_convenio.convenio = pg_componentes.convenio INNER JOIN pg_metas ON pg_componentes.componente = pg_metas.componente INNER JOIN pg_actividades ON pg_metas.meta = pg_actividades.meta where "+ det.getCadenaFiltradoNivel();
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(CadenaWhereMetasActividades.class));
    }

    @Override
    public DetalleAccesoPlantilla getDepPlantillaWhere(Integer id) {
         DetalleAccesoPlantilla det = new DetalleAccesoPlantilla();
         String sql = "select * from dbo.DetalleAccesoPlantilla where idPlantillaAccesoDatos="+id;
         List<DetalleAccesoPlantilla> lista = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(DetalleAccesoPlantilla.class));
         if(!lista.isEmpty()){
            det=lista.get(0);
         }
         return det;
    }
}
