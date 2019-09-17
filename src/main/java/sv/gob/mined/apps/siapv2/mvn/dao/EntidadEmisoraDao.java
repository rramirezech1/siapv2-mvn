/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.EntidadEmisora;

/**
 *
 * @author Roberto Antonio Ramirez
 * @date  13/09/2019
 * 
 */

public interface EntidadEmisoraDao {
    public List<EntidadEmisora> findAll();    
}
