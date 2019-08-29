package sv.gob.mined.apps.siapv2.mvn.bo;

import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;


public interface UsuarioBo {
    
    public Usuario findUsuarioByNameClave(String usuario, String clave);
    
}