/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.OfertasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwOfertasGrupo;

/**
 *
 * @author Infosoft
 */
@Repository
public class OfertasDaoImpl extends XJdbcTemplate2 implements OfertasDao {

    public OfertasDaoImpl() {
    }

    @Override
    public List<VwOfertasGrupo> findOfertasByGrupo(Integer grupo) {
        String sql = " SELECT recepcion_ofertas.grupo, recepcion_ofertas.id_proveedor as idProveedor, proveedores.razon_social as razonSocial, proveedores.identificadorPrimarioOferente, recepcion_ofertas.fecha_recepcion as fechaRecepcion, \n" +
                     " recepcion_ofertas.plazo_entrega as plazoEntrega, recepcion_ofertas.vence_oferta as fechaVencimiento \n" +
                     " FROM recepcion_ofertas INNER JOIN\n" +
                     " proveedores ON recepcion_ofertas.id_proveedor = proveedores.id_proveedor WHERE(recepcion_ofertas.grupo = " + grupo +")";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwOfertasGrupo.class));
    }
    
    @Override
    public VwOfertasGrupo findByGrupoIdProveedor(Integer grupo, Integer idProveedor){
        String sql = " SELECT recepcion_ofertas.grupo, recepcion_ofertas.id_proveedor AS idProveedor, proveedores.razon_social AS razonSocial, recepcion_ofertas.fecha_recepcion AS fechaRecepcion, recepcion_ofertas.plazo_entrega AS plazoEntrega, \n" +
                     " recepcion_ofertas.vence_oferta AS fechaVencimiento, proveedores.identificadorPrimarioOferente\n" +
                     " FROM recepcion_ofertas INNER JOIN\n" +
                     " proveedores ON recepcion_ofertas.id_proveedor = proveedores.id_proveedor\n" +
                     " WHERE (recepcion_ofertas.grupo = "+grupo+") AND (recepcion_ofertas.id_proveedor = " +idProveedor+")";
        
        List<VwOfertasGrupo> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwOfertasGrupo.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
 }
