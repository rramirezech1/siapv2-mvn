/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;

/**
 *
 * @author Infosoft
 */
public interface OpcionMenuDao {

    public int create();

    public int update();

    public void setOpcionMenu(OpcionMenu opcionmenu);
    
    public List<OpcionMenu> findAll();
    
    public List<OpcionMenu> findAllByUser(Integer user);
    
    public List<OpcionMenu> findAllByGroup(Integer group);

    public OpcionMenu findById(Integer idGarantia);
    
    public OpcionMenu findOpcionMenuById(Integer idOpcion);
    
    public List<OpcionMenu> findByMenuPadre();
    
    public List<OpcionMenu> findByMenuHijo();
    
}
