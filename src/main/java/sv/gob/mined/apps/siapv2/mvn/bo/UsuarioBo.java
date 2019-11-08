package sv.gob.mined.apps.siapv2.mvn.bo;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;


public interface UsuarioBo {
    
    public Usuario findUsuarioByNameClave(String usuario, String clave);
    
    public List<Usuario> getLstUsuario();
    
}