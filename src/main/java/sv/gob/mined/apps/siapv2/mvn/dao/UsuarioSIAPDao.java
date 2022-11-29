/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;

/**
 *
 * @author Infosoft
 */
public interface UsuarioSIAPDao {

    public Usuario findUsuarioByNameClave(String usuario, String clave);

    public Usuario findUsuarioOpciones(String us);
     
    public void setUsuario(Usuario userName);
    
    public List<Usuario> findAll(); 
    
         
}
