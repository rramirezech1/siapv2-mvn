/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

/**
 *
 * @author Infosoft
 */
public class OpcionMenu1 {

    private Integer idOpcionMenu;
    private String nombreOpcionMenu;
    private String labelOpcionMenu;
    private String direccionAccesoOpcion;
    private Integer idOpcionPadre;
    private Boolean estado;
    
    
    public OpcionMenu1() {
    }

    public Integer getIdOpcionMenu() {
        return idOpcionMenu;
    }

    public void setIdOpcionMenu(Integer idOpcionMenu) {
        this.idOpcionMenu = idOpcionMenu;
    }

    public String getNombreOpcionMenu() {
        return nombreOpcionMenu;
    }

    public void setNombreOpcionMenu(String nombreOpcionMenu) {
        this.nombreOpcionMenu = nombreOpcionMenu;
    }

    public String getLabelOpcionMenu() {
        return labelOpcionMenu;
    }

    public void setLabelOpcionMenu(String labelOpcionMenu) {
        this.labelOpcionMenu = labelOpcionMenu;
    }

    public String getDireccionAccesoOpcion() {
        return direccionAccesoOpcion;
    }

    public void setDireccionAccesoOpcion(String direccionAccesoOpcion) {
        this.direccionAccesoOpcion = direccionAccesoOpcion;
    }

    public Integer getIdOpcionPadre() {
        return idOpcionPadre;
    }

    public void setIdOpcionPadre(Integer idOpcionPadre) {
        this.idOpcionPadre = idOpcionPadre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }    

    
    @Override
    public String toString() {
        return "entidades.OpcionMenu[ id_opcion_Menu=" + idOpcionMenu + " ]";
    }
}