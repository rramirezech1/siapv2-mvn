/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.PermisoOpcionMenu;

/**
 *
 * @author Infosoft
 */
public interface PermisoOpcionMenuDao {

    public int create();

    public int update();

    public void setPermisoOpcionMenu(PermisoOpcionMenu permisoopcionmenu);
    
    public List<PermisoOpcionMenu> findAll();
    
    public List<PermisoOpcionMenu> findAllByUser(Integer user);
    
    public List<PermisoOpcionMenu> findAllByGroup(Integer group);

    public PermisoOpcionMenu findById(Integer idGarantia);
    
    public PermisoOpcionMenu findOpcionMenuById(Integer idOpcion);
    
    public List<PermisoOpcionMenu> findByMenuPadre();
    
    public List<PermisoOpcionMenu> findByMenuHijo();
    
}
