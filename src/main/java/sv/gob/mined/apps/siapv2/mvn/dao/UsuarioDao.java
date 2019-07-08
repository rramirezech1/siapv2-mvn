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
public interface UsuarioDao {

    public int guardar(Usuario usr);
    
    public int actualizar(Usuario usr);
    
    public Usuario findUsuario(String userName);
    
    public Usuario findUsuarioByDui(String numeroDeDui);
    
    public Usuario findUsuarioByUserName(String userName);
    
    public Usuario findUsuarioByNameClave(String usuario, String clave);
    
    public Usuario findUsuarioByEmail(String mail);
    
    public void setUsuario(Usuario userName);
    
    public Usuario isExistPersonaByUsuario(String usuario);
       
    public List<Usuario> findUsuarioByNombreCompleto (String nombreCompleto);
         
    public Boolean comprobarRespuestas(String userName, String respuesta1, String respuesta2);
}
