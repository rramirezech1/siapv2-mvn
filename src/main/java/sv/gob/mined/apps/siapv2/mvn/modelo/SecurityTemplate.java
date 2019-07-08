/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

/**
 *
 * @author
 */
public class SecurityTemplate {

    private String application;
    private String window;
    private String control;
    private String description;
    private String object_type;
    private String pagina_web;
    private String nivelSeleccionado;
    private Integer target;
    private String pagina_web_ayuda;
    private Integer identificacionDelProceso;
    private Boolean status;

    public SecurityTemplate() {
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    public void setPagina_web(String pagina_web) {
        this.pagina_web = pagina_web;
    }

    public String getNivelSeleccionado() {
        return nivelSeleccionado;
    }

    public void setNivelSeleccionado(String nivelSeleccionado) {
        this.nivelSeleccionado = nivelSeleccionado;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getPagina_web_ayuda() {
        return pagina_web_ayuda;
    }

    public void setPagina_web_ayuda(String pagina_web_ayuda) {
        this.pagina_web_ayuda = pagina_web_ayuda;
    }

    public Integer getIdentificacionDelProceso() {
        return identificacionDelProceso;
    }

    public void setIdentificacionDelProceso(Integer identificacionDelProceso) {
        this.identificacionDelProceso = identificacionDelProceso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SecurityTemplate{" + "application=" + application + ", window=" + window + ", control=" + control + ", description=" + description + ", status=" + status + '}';
    }
}
