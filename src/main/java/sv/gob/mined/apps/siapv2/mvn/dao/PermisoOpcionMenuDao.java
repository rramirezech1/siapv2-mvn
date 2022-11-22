/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.PermisoOpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwPermisoOpcionMenu;

/**
 *
 * @author Infosoft
 */
public interface PermisoOpcionMenuDao {

    public int create();

    public int update();

    public void setPermisoOpcionMenu(PermisoOpcionMenu permisoopcionmenu);
    
    public List<VwPermisoOpcionMenu> findAll();
    
    public List<VwPermisoOpcionMenu> findAllByUser(Integer user);
    
    public List<VwPermisoOpcionMenu> findAllByGroup(Integer group);

    public PermisoOpcionMenu findById(Integer idGarantia);
    
    public PermisoOpcionMenu findPermisoOpcionMenuById(Integer idPermisoOpcion);
    
    public List<VwPermisoOpcionMenu> findByMenuPadre();
    
    public List<VwPermisoOpcionMenu> findByMenuHijo();
    
}
