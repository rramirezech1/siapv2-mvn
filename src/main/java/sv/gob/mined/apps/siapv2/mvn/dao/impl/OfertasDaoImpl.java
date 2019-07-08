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
    public VwOfertasGrupo findById(Integer idOferta) {
        // No funciona
        String sql = "SELECT grupos.grupo, grupos.no_licitacion, pg_convenio.numero_convenio, grupos.concepto FROM Grupos WHERE grupo = " + idOferta;
        List<VwOfertasGrupo> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwOfertasGrupo.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
 }
