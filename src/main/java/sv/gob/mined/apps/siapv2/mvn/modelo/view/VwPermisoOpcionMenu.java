/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo.view;

/**
 *
 * @author Infosoft
 */
public class VwPermisoOpcionMenu {

    private Integer idPermisoOpcionMenu;
    private Integer idOpcionMenu;
    private Integer idOpcionPadre;
    private Integer idGrupo;
    private Integer estadoEliminacion;
    private String Grupo;
    private String labelOpcionMenu;
    private String direccionAccesoOpcion;
    private String opcionPadre;    
    
    
    public VwPermisoOpcionMenu() {
    }

    public Integer getIdPermisoOpcionMenu() {
        return idPermisoOpcionMenu;
    }

    public void setIdPermisoOpcionMenu(Integer idPermisoOpcionMenu) {
        this.idPermisoOpcionMenu = idPermisoOpcionMenu;
    }

    public Integer getIdOpcionMenu() {
        return idOpcionMenu;
    }

    public void setIdOpcionMenu(Integer idOpcionMenu) {
        this.idOpcionMenu = idOpcionMenu;
    }

    public Integer getIdOpcionPadre() {
        return idOpcionPadre;
    }

    public void setIdOpcionPadre(Integer idOpcionPadre) {
        this.idOpcionPadre = idOpcionPadre;
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

    public String getOpcionPadre() {
        return opcionPadre;
    }

    public void setOpcionPadre(String opcionPadre) {
        this.opcionPadre = opcionPadre;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getEstadoEliminacion() {
        return estadoEliminacion;
    }

    public void setEstadoEliminacion(Integer estadoEliminacion) {
        this.estadoEliminacion = estadoEliminacion;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

}