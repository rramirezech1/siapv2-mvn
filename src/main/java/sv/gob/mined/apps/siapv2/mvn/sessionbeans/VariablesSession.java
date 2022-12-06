package sv.gob.mined.apps.siapv2.mvn.sessionbeans;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
        if (null == JsfUtil.getVarSesion(JsfUtil.sUsuario)) {
            logout();
        } else {
            usuario = JsfUtil.getVarSesion(JsfUtil.sUsuario).toString();
        }
    }

    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        //context.getExternalContext().redirect("/index.jsp");
        return "/index.jsp?faces-redirect=true";
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
