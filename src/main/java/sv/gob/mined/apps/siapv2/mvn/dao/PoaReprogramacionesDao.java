/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaDetalleReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaReprogramaciones;

/**
 *
 *
 */
public interface PoaReprogramacionesDao {

    public List<PoaReprogramaciones> findAll();

    public List<PoaReprogramaciones> findAllByPoa(Integer poa);
    
    public List<PoaDetalleReprogramaciones> findDetalleReprogramacionByPoaReprogramacion(Integer poaReprogramacion);

    public int createFichaReprogramacion();

    public int updateFichaReprogramacion();

    public int saveDetalleReprogramacion();

    public PoaReprogramaciones getPoaReprogramacion();

    public void setPoaReprogramacion(PoaReprogramaciones reprogramacion);

    public void setDetPoaReprogramacion(PoaDetalleReprogramaciones detRepro);
}
