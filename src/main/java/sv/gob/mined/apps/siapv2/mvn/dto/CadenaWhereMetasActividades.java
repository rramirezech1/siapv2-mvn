/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dto;

/**
 *
 * @author reynaldo
 */
public class CadenaWhereMetasActividades {
    private Integer estructura;
    private Integer convenio;
    private Integer meta;
    private Integer actividad;

    public CadenaWhereMetasActividades() {
    }

    public Integer getConvenio() {
        return convenio;
    }

    public void setConvenio(Integer convenio) {
        this.convenio = convenio;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public Integer getActividad() {
        return actividad;
    }

    public void setActividad(Integer actividad) {
        this.actividad = actividad;
    }

    public Integer getEstructura() {
        return estructura;
    }

    public void setEstructura(Integer estructura) {
        this.estructura = estructura;
    }
    
    
}
