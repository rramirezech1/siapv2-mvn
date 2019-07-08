/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PlantillasDeSeguridadDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillasDeSeguridad;

/**
 *
 * @author
 */
@Repository
public class PlantillasDeSeguridadDaoImpl extends XJdbcTemplate implements PlantillasDeSeguridadDao {

    private PlantillasDeSeguridad plantillasDeSeguridad;

    @Override
    public List<PlantillasDeSeguridad> findAll() {
        String sql = "SELECT * FROM PlantillasDeSeguridad";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PlantillasDeSeguridad.class));
    }

    @Override
    public void setPlantillasDeSeguridad(PlantillasDeSeguridad recurso) {
        this.plantillasDeSeguridad = recurso;
    }

    @Override
    public PlantillasDeSeguridad getPlantillasDeSeguridad() {
        return plantillasDeSeguridad;
    }

    @Override
    public int create() {
        super.setObjeto(plantillasDeSeguridad);
        Integer id = super.create();
        plantillasDeSeguridad.setIdPlantillaSeguridad(id);
        return id;
    }

    @Override
    public int update() {
        super.setObjeto(plantillasDeSeguridad);
        return super.update();
    }

    @Override
    public PlantillasDeSeguridad findPlantillaSeguridadById(Integer id) {
        String sql = "SELECT * FROM PlantillasDeSeguridad where idPlantillaSeguridad=" + id;
        List<PlantillasDeSeguridad> lista = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PlantillasDeSeguridad.class));
        PlantillasDeSeguridad plantilla = new PlantillasDeSeguridad();
        if (!lista.isEmpty()) {
            plantilla = lista.get(0);
        }
        return plantilla;
    }
}
