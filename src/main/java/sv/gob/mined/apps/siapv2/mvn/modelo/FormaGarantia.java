/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.modelo;

/**
 *
 * @author RAREcheverria
 */
public class FormaGarantia {
    
    private Integer idFormaGarantia;
    private String codigoFormaGarantia;
    private String descripcionFormaGarantia;
    private Integer estadoFormaGarantia;
    
    public FormaGarantia(){
    }

    public Integer getIdFormaGarantia() {
        return idFormaGarantia;
    }

    public void setIdFormaGarantia(Integer idFormaGarantia) {
        this.idFormaGarantia = idFormaGarantia;
    }

    public String getCodigoFormaGarantia() {
        return codigoFormaGarantia;
    }

    public void setCodigoFormaGarantia(String codigoFormaGarantia) {
        this.codigoFormaGarantia = codigoFormaGarantia;
    }

    public String getDescripcionFormaGarantia() {
        return descripcionFormaGarantia;
    }

    public void setDescripcionFormaGarantia(String descripcionFormaGarantia) {
        this.descripcionFormaGarantia = descripcionFormaGarantia;
    }

   
    public Integer getEstadoFormaGarantia() {
        return estadoFormaGarantia;
    }

    public void setEstadoFormaGarantia(Integer estadoFormaGarantia) {
        this.estadoFormaGarantia = estadoFormaGarantia;
    }

    
}
