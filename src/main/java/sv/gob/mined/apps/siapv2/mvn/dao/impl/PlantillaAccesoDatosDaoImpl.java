/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PlantillaAccesoDatosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillaAccesoDatos;

/**
 *
 * @author
 */
@Repository
public class PlantillaAccesoDatosDaoImpl extends XJdbcTemplate implements PlantillaAccesoDatosDao {

    private PlantillaAccesoDatos plantillasDeAccesoDatos;

    @Override
    public List<PlantillaAccesoDatos> findAll() {
        String sql = "SELECT * FROM PlantillaAccesoDatos";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PlantillaAccesoDatos.class));
    }

    @Override
    public PlantillaAccesoDatos findPlantillasDeAccesoDatosById(Integer id) {
        String sql = "SELECT * FROM PlantillaAccesoDatos where idPlantillaAccesoDatos=" + id;
        PlantillaAccesoDatos plantilla = new PlantillaAccesoDatos();
        List<PlantillaAccesoDatos> lista = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PlantillaAccesoDatos.class));
        if (!lista.isEmpty()) {
            plantilla = lista.get(0);
        }
        return plantilla;
    }

    @Override
    public int create() {
        super.setObjeto(plantillasDeAccesoDatos);
        Integer id = super.create();
        plantillasDeAccesoDatos.setIdPlantillaSeguridad(id);
        return id;
    }

    @Override
    public int update() {
        super.setObjeto(plantillasDeAccesoDatos);
        return super.update();
    }

    @Override
    public void setPlantillasDeAccesoDatos(PlantillaAccesoDatos recurso) {
        this.plantillasDeAccesoDatos = recurso;
    }

    @Override
    public PlantillaAccesoDatos getPlantillasDeAccesoDatos() {
        return plantillasDeAccesoDatos;
    }

   
   

   
}
