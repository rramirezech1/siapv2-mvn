/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo.view;

/**
 *
 * @author Infosoft
 */
public class VwGrupos {

    private Integer grupo;
    private String no_licitacion;
    private String convenio;
    private String concepto;
    private Boolean seleccionado; 
    
    public VwGrupos() {
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public String getNo_licitacion() {
        return no_licitacion;
    }

    public void setNo_licitacion(String no_licitacion) {
        this.no_licitacion = no_licitacion;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

}