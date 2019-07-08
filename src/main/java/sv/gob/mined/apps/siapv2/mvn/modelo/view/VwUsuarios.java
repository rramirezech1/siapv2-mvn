/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo.view;

/**
 *
 * @author Infosoft
 */
public class VwUsuarios {
    private String userName;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String eMail;
    private String password;
   /* private Integer estadoDeEliminacion;
    private String respuesta1;
    private String respuesta2;
    private String name;
    private Integer idPregunta1;
    private Integer idPregunta2;*/

    public VwUsuarios() {
    }
    
    public String getUserName() {
        return userName;
    }
    public String getPrimerNombre() {
        return primerNombre;
    }
    public String getSegundoNombre() {
        return segundoNombre;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public String getSegundoApellido() {
        return segundoApellido;
    }
    public String getTelefono() {
        return telefono;
    }
    
    public String geteMail() {
        return eMail;
    }
    
    public String getPassword() {
        return password;
    }
/*
    public String getRespuesta1() {
        return respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }
    
    public Integer getEstadoDeEliminacion() {
        return estadoDeEliminacion;
    }
    
    public String getName() {
        return name;
    }

    public Integer getIdPregunta1() {
        return idPregunta1;
    }

    public Integer getIdPregunta2() {
        return idPregunta2;
    }
*/
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
  
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
/*
    public void setEstadoDeEliminacion(Integer estadoDeEliminacion) {
        this.estadoDeEliminacion = estadoDeEliminacion;
    }
    
     public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setIdPregunta1(Integer idPregunta1) {
        this.idPregunta1 = idPregunta1;
    }

    public void setIdPregunta2(Integer idPregunta2) {
        this.idPregunta2 = idPregunta2;
    }
    */
}
