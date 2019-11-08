
package sv.gob.mined.apps.siapv2.mvn.managedbeans;

import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.gob.mined.apps.siapv2.mvn.bo.UsuarioBo;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;
import sv.gob.mined.apps.siapv2.mvn.util.EMailEJB;

/**
 *
 * @author Infosoft
 */
@Component(value = "validacionUsuarioBean")
@Scope(value = "view")
public class ValidacionUsuarioBean {

    private String usuario;
    private String perfil;
    private String claveDeAcceso;
    private String dui;
    private String email;
    private Usuario currentUsuario;
    private Boolean mostrarSignUp= Boolean.FALSE;
    private Boolean mostrarRecuperarCont= Boolean.FALSE;
    private List<Usuario> lstUsuario;

    
    @Autowired
    private UsuarioBo usuBo;
    
    
    public ValidacionUsuarioBean() {
    }

    
    public String validarCredencialesDeAcceso() {
        if (usuario != null && claveDeAcceso != null) {
            Usuario usu = usuBo.findUsuarioByNameClave(usuario, claveDeAcceso);

            if (usu != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put(JsfUtil.sUsuario, usuario);
                context.getExternalContext().getSessionMap().put(JsfUtil.sPerfil, perfil);
//                userName= JsfUtil.getVarSesion(JsfUtil.sUsuario).toString();
                return "/app/inicio.jsf?faces-redirect=true";
                
            } else {
                JsfUtil.addErrorMessage("Usuario y/o Clave de acceso erroneas.");
                return null;
            }
        } else {
            JsfUtil.addErrorMessage("Usuario y/o Clave de acceso erroneas.");
            return "";
        }
    }

    public UsuarioBo getUsuBo() {
        return usuBo;
    }

    public void setUsuBo(UsuarioBo usuBo) {
        this.usuBo = usuBo;
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        return "/index.jsp?faces-redirect=true";
    }

    public Usuario getCurrentUsuario() {
        if (currentUsuario == null) {
            currentUsuario = new Usuario();
        }
        return currentUsuario;
    }

    public void setCurrentUsuario(Usuario currentUsuario) {
        this.currentUsuario = currentUsuario;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String encriptaPassword(String pass){
        String str1 = "CGI";
        String str2 = "";
    
        int m = 0;
        int n = 0;

        int j = 0;
        int k = str1.length() - 1;

        for (int i = 0; i < pass.length(); i++)
        {
          m = pass.charAt(i);
          n = str1.charAt(j);

          m += n;


          while (m > 255) {
            m -= 255;
          }
          char c = (char)m;

          str2 = str2 + c;

          j++;
          if (j > k)
            j = 0;
        }
        return str2;
    }
    
    /*public void buscarUsuarioPreguntas() {
        mostrarRecuperarCont = Boolean.TRUE;
        if (dui.replace("_", "").length() >= 9) {
            lstPreguntasUsuario = provBo.findAllByDui(dui);
            if(lstPreguntasUsuario == null || lstPreguntasUsuario.isEmpty()){
                JsfUtil.addWarningMessage("El usuario con # Documento Legal " + dui + " no esta registrado");
            }
        }
        RequestContext.getCurrentInstance().update("frmPrincipal:dlgRecuperarContra");
    }*/   
    
   /* public void envioUsrPassMail(String usr) {

    String para = "carlosp@codehero.co";
    String de = "cpicca@codehero.co";

    // El servidor (host). En este caso usamos localhost
    String host = "localhost";

    // Obtenemos las propiedades del sistema
    Properties propiedades = System.getProperties();

    // Configuramos el servidor de correo
    propiedades.setProperty("mail.smtp.host", host);

    // Obtenemos la sesi�n por defecto
    Session sesion = Session.getDefaultInstance(propiedades);

    try{
      // Creamos un objeto mensaje tipo MimeMessage por defecto.
      MimeMessage mensaje = new MimeMessage(sesion);

      // Asignamos el ?de o from? al header del correo.
      mensaje.setFrom(new InternetAddress(de));

      // Asignamos el ?para o to? al header del correo.
      mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

      // Asignamos el asunto
      mensaje.setSubject("Primer correo HTML");

      // Asignamos el contenido HTML, tan grande como nosotros queramos
      mensaje.setContent("<h1>El mensaje de nuestro primer correo HTML</h1>","text/html" );

      // Enviamos el correo
      Transport.send(mensaje);
      System.out.println("Mensaje enviado");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }*/
    
    public Boolean getMostrarSignUp() {
        return mostrarSignUp;
    }

    public void setMostrarSignUp(Boolean mostrarSignUp) {
        this.mostrarSignUp = mostrarSignUp;
    }

    public Boolean getMostrarRecuperarCont() {
        return mostrarRecuperarCont;
    }

    public void setMostrarRecuperarCont(Boolean mostrarRecuperarCont) {
        this.mostrarRecuperarCont = mostrarRecuperarCont;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        //this.claveDeAcceso = encriptaPassword(claveDeAcceso);
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }  

    public List<Usuario> getLstUsuario() {
        lstUsuario = usuBo.getLstUsuario();
        return lstUsuario;
        
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }   
}