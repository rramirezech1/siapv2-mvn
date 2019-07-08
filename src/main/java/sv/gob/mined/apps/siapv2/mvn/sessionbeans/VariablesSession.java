
package sv.gob.mined.apps.siapv2.mvn.sessionbeans;


import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;

/**
 *
 * @author Infosoft
 */

@Component(value = "variablesSession")
@Scope("session")

public class VariablesSession {

    private String usuario;
    private String claveDeAcceso;
    private String perfil;

  
    public VariablesSession() {
    }
    
    @PostConstruct
    public void ObtenerUsuarioSesion() {
        usuario= JsfUtil.getVarSesion(JsfUtil.sUsuario).toString();
    }

    public String getUsuario() {
       return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
       
    }

    public String getClaveDeAcceso() {
        return claveDeAcceso;
    }

    public void setClaveDeAcceso(String claveDeAcceso) {
        this.claveDeAcceso = claveDeAcceso;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    
}