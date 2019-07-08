/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.PreguntaValidaUsuario;

/**
 *
 * @author Infosoft
 */
public interface PreguntaValidaUsuarioDao {
    public List<PreguntaValidaUsuario> findAll();
    public List<PreguntaValidaUsuario> findAllExcepto(Integer id);
    public List<PreguntaValidaUsuario> findAllByDui(String dui);
}
