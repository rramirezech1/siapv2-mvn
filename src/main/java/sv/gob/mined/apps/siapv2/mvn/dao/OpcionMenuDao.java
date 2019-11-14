/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu1;

/**
 *
 * @author Infosoft
 */
public interface OpcionMenuDao {

    public int create();

    public int update();

    public void setOpcionMenu(OpcionMenu1 opcionmenu);
    
    public List<OpcionMenu1> findAllByUser(Integer user);
    
    public List<OpcionMenu1> findAllByGroup(Integer group);

    public OpcionMenu1 findById(Integer idGarantia);
    
    public List<OpcionMenu1> findByMenuPadre();
    
    public List<OpcionMenu1> findByMenuHijo();
    
}
