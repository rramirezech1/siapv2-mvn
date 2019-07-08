/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillaAccesoDatos;

/**
 *
 * @author
 */
public interface PlantillaAccesoDatosDao {

    public List<PlantillaAccesoDatos> findAll();

    public int create();

    public int update();

    public void setPlantillasDeAccesoDatos(PlantillaAccesoDatos recurso);

    public PlantillaAccesoDatos getPlantillasDeAccesoDatos();

    public PlantillaAccesoDatos findPlantillasDeAccesoDatosById(Integer id);
    
}
