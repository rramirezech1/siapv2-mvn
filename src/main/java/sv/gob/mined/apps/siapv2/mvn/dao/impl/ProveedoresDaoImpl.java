
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.ProveedoresDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwProveedoresSiap;

/**
 *
 * @author Infosoft
 */
@Repository
public class ProveedoresDaoImpl extends XJdbcTemplate2 implements ProveedoresDao {

    public ProveedoresDaoImpl() {
    }

    @Override
    public List<VwProveedoresSiap> findProveedoresSiap(String condicion) {
//        String sql = " SELECT     contratos_ordenes_compras.id_contrato, contratos_ordenes_compras.numero_contrato, proveedores.razon_social, contratos_ordenes_compras.fecha_emision as fechaEmision, \n" +
//                     " contratos_ordenes_compras.inicio_vigencia as fechaInicioVigencia, contratos_ordenes_compras.fin_vigencia as fechaFinVigencia, contratos_ordenes_compras.valor as montoContrato, \n" +
//                     " proveedores.identificadorPrimarioOferente\n" +
//                     " FROM         contratos_ordenes_compras INNER JOIN\n" +
//                     " proveedores ON contratos_ordenes_compras.id_proveedor = proveedores.id_proveedor\n" +
//                     " WHERE     (contratos_ordenes_compras.grupo =" + condicion +")";
        
        String sql = " SELECT proveedores.id_proveedor, siapv2.dbo.Empresa.fechaDeInsercion, proveedores.nit_proveedor,"+
                     " CASE dbo.proveedores.persona_juridica WHEN '0' THEN 'NATURAL' ELSE 'JURIDICA' END AS persona_juridica, proveedores.razon_social, proveedores.giro, "+
                     " proveedores.nombre_comercial, proveedores.nrc AS numero_iva, pais.descripcion, proveedores.nit_representante, proveedores.dui, "+
                     " ISNULL(proveedores.nombre_representante, '') + ' ' + ISNULL(proveedores.apellidos_representante, '') AS nombre_representante, "+
                     " detalle_proveedores.correo_electronico, detalle_proveedores.direccion, departamentos.descripcion AS departamento, municipios.descripcion AS municipio, "+
                     " detalle_proveedores.telefono1, detalle_proveedores.telefono2, detalle_proveedores.fax, siapv2.dbo.ClasificacionEmpresaTamano.descripcionEmpresaTamano"+
                     " FROM departamentos LEFT OUTER JOIN municipios ON departamentos.departamento = municipios.departamento RIGHT OUTER JOIN"+
                     " proveedores INNER JOIN detalle_proveedores ON proveedores.id_proveedor = detalle_proveedores.id_proveedor INNER JOIN"+
                     " pais ON proveedores.pais = pais.pais INNER JOIN security_users ON proveedores.name = security_users.name INNER JOIN"+
                     " siapv2.dbo.Empresa ON proveedores.identificadorPrimarioOferente = siapv2.dbo.Empresa.identificadorPrimarioOferente INNER JOIN"+
                     " g_personal ON security_users.personal_id = g_personal.personal_id ON municipios.municipio = detalle_proveedores.municipio AND "+
                     " departamentos.departamento = detalle_proveedores.departamento LEFT OUTER JOIN siapv2.dbo.ClasificacionEmpresaTamano ON "+
                     " siapv2.dbo.ClasificacionEmpresaTamano.identificadorEmpresaTamano = siapv2.dbo.Empresa.clasificacionEmpresaTamano"+
                     " WHERE (proveedores.activo = 1) AND (proveedores.resumido = 0)"+
                     " ORDER BY proveedores.fecha_actualizacion DESC";
        
        
        
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwProveedoresSiap.class));
    }
    
//    @Override
//    public VwContratosGrupo findById(Integer idContrato) {
//        String sql = " SELECT contratos_ordenes_compras.id_contrato AS idContrato, contratos_ordenes_compras.numero_contrato AS numeroContrato, proveedores.razon_social AS razonSocial, \n" +
//                     " contratos_ordenes_compras.fecha_emision AS fechaEmision, contratos_ordenes_compras.inicio_vigencia AS fechaInicioVigencia, contratos_ordenes_compras.fin_vigencia AS fechaFinVigencia, \n" +
//                     " contratos_ordenes_compras.valor AS montoContrato, proveedores.identificadorPrimarioOferente\n" +
//                     " FROM contratos_ordenes_compras INNER JOIN\n" +
//                     " proveedores ON contratos_ordenes_compras.id_proveedor = proveedores.id_proveedor\n" +
//                     " WHERE contratos_ordenes_compras.id_contrato = " + idContrato;
//        
//        List<VwContratosGrupo> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwContratosGrupo.class));
//        if (lst.isEmpty()) {
//            return null;
//        } else {
//            return lst.get(0);
//        }
//    }
    
 }
