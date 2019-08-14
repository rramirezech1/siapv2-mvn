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
    
    private Integer id_forma_garantia;
    private String codigo_forma_garantia;
    private String descripcion_forma_garantia;
    private Integer estado_forma_garantia;
    
    public FormaGarantia(){
    }

    public Integer getId_forma_garantia() {
        return id_forma_garantia;
    }

    public void setId_forma_garantia(Integer id_forma_garantia) {
        this.id_forma_garantia = id_forma_garantia;
    }

    public String getCodigo_forma_garantia() {
        return codigo_forma_garantia;
    }

    public void setCodigo_forma_garantia(String codigo_forma_garantia) {
        this.codigo_forma_garantia = codigo_forma_garantia;
    }

    public String getDescripcion_forma_garantia() {
        return descripcion_forma_garantia;
    }

    public void setDescripcion_forma_garantia(String descripcion_forma_garantia) {
        this.descripcion_forma_garantia = descripcion_forma_garantia;
    }

    public Integer getEstado_forma_garantia() {
        return estado_forma_garantia;
    }

    public void setEstado_forma_garantia(Integer estado_forma_garantia) {
        this.estado_forma_garantia = estado_forma_garantia;
    }    
}
