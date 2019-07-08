/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.dto.CadenaWhereMetasActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.DetalleAccesoPlantilla;

/**
 *
 * @author
 */
public interface DetalleAccesoPlantillaDao {

    public List<DetalleAccesoPlantilla> findAll();
    
    public int create();

    public int update();

    public void setDetPlantillaSeg(DetalleAccesoPlantilla recurso);

    public DetalleAccesoPlantilla getDetPlantillaSeg();

    public List<DetalleAccesoPlantilla> getDetPlantillaSegByIdPlantilla(Integer id);

    public void deleteDetPlantillaSeg(Integer id);
    
    public List<CadenaWhereMetasActividades> getListCadenaWhereMetasActividades(Integer id);
    
    public DetalleAccesoPlantilla getDepPlantillaWhere(Integer id);   
     
    
}
