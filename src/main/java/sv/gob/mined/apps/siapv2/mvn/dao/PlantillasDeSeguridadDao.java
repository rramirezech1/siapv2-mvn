/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillasDeSeguridad;

/**
 *
 * @author
 */
public interface PlantillasDeSeguridadDao {

    public List<PlantillasDeSeguridad> findAll();

    public int create();

    public int update();

    public void setPlantillasDeSeguridad(PlantillasDeSeguridad recurso);

    public PlantillasDeSeguridad getPlantillasDeSeguridad();

    public PlantillasDeSeguridad findPlantillaSeguridadById(Integer id);
}
