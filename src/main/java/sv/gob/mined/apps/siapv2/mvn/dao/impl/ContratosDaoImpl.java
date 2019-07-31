/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.ContratosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwContratosGrupo;

/**
 *
 * @author Infosoft
 */
@Repository
public class ContratosDaoImpl extends XJdbcTemplate2 implements ContratosDao {

    public ContratosDaoImpl() {
    }

    @Override
    public List<VwContratosGrupo> findContratosByGrupo(Integer grupo) {
        String sql = " SELECT     contratos_ordenes_compras.id_contrato, contratos_ordenes_compras.numero_contrato, proveedores.razon_social, contratos_ordenes_compras.fecha_emision as fechaEmision, \n" +
                     " contratos_ordenes_compras.inicio_vigencia as fechaInicioVigencia, contratos_ordenes_compras.fin_vigencia as fechaFinVigencia, contratos_ordenes_compras.valor as montoContrato, \n" +
                     " proveedores.identificadorPrimarioOferente\n" +
                     " FROM         contratos_ordenes_compras INNER JOIN\n" +
                     " proveedores ON contratos_ordenes_compras.id_proveedor = proveedores.id_proveedor\n" +
                     " WHERE     (contratos_ordenes_compras.grupo =" + grupo +")";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwContratosGrupo.class));
    }
    
    @Override
    public VwContratosGrupo findById(Integer idContrato) {
        String sql = " SELECT contratos_ordenes_compras.id_contrato AS idContrato, contratos_ordenes_compras.numero_contrato AS numeroContrato, proveedores.razon_social AS razonSocial, \n" +
                     " contratos_ordenes_compras.fecha_emision AS fechaEmision, contratos_ordenes_compras.inicio_vigencia AS fechaInicioVigencia, contratos_ordenes_compras.fin_vigencia AS fechaFinVigencia, \n" +
                     " contratos_ordenes_compras.valor AS montoContrato, proveedores.identificadorPrimarioOferente\n" +
                     " FROM contratos_ordenes_compras INNER JOIN\n" +
                     " proveedores ON contratos_ordenes_compras.id_proveedor = proveedores.id_proveedor\n" +
                     " WHERE contratos_ordenes_compras.id_contrato = " + idContrato;
        
        List<VwContratosGrupo> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwContratosGrupo.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
 }
