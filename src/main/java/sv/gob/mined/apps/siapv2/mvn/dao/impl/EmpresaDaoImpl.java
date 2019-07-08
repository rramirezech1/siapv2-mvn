/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.EmpresaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.Empresa;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;

/**
 *
 * @author Infosoft
 */
@Repository
public class EmpresaDaoImpl extends XJdbcTemplate implements EmpresaDao {

    private Empresa empresa;

    public EmpresaDaoImpl() {
    }

    @Override
    public List<Empresa> findEmpresaByRazonSocial(String razonSocial) {
        String sql = "SELECT * FROM Empresa WHERE razonSocial like '%" + razonSocial + "%'";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
    }
    
    @Override
    public List<Empresa> findEmpresaByNombreComercial(String nombreComercial) {
        String sql = "SELECT * FROM Empresa WHERE nombreComercial like '%" + nombreComercial + "%'";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
    }
    
    @Override
    public List<Empresa> findEmpresaByGiro(String giroEmpresa) {
        String sql = "SELECT * FROM Empresa WHERE giroEmpresa like '%" + giroEmpresa + "%'";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
    }

    @Override
    public List<Empresa> findEmpresaByNit(String numeroNit) {
        String sql = "SELECT * FROM Empresa WHERE numeroDeNit like '%" + numeroNit + "%'";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
    }
    
    @Override
    public List<Empresa> findEmpresa() {
        String sql = "SELECT * FROM Empresa";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
    }
    
    @Override
    public List<Empresa> findEmpresaByQueOfrece(Integer idSubSector) {
        String sql = "SELECT dbo.Empresa.* FROM dbo.Empresa INNER JOIN dbo.ClasificacionEmpresaEconomico ON dbo.Empresa.identificadorPrimarioDeLaEmpresa = dbo.ClasificacionEmpresaEconomico.identificadorPrimarioDeLaEmpresa WHERE dbo.ClasificacionEmpresaEconomico.identificadorDelSectorEconomico = " + idSubSector;
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
    }

    @Override
    public int trasladoOferente(Integer identificadorPrimarioDeLaEmpresa, String usuarioSession) {
        try {
            SimpleJdbcCall jdbcCall;
            jdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withProcedureName("sp_traslado_oferentes_usuario");
            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("idOferente", identificadorPrimarioDeLaEmpresa);
            in.addValue("name", usuarioSession);
            
            jdbcCall.execute(in);
            return 1;
        }
        catch(Exception e) {
            System.out.println("Error en sp_traslado_oferentes_usuario: " + e);
            return 0;
        }        
    }

    @Override
    public List<Empresa> findbyAll() {
        String sql = "SELECT * FROM Empresa";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
    }

    @Override
    public int create() {
        super.setObjeto(empresa);
        Integer idEmpresa = super.create();
        if (idEmpresa != JsfUtil.COD_ERROR) {
            getEmpresa().setIdentificadorPrimarioDeLaEmpresa(idEmpresa);
        }
        return idEmpresa;
    }

    @Override
    public int update() {
        super.setObjeto(empresa);
        return super.update();
    }

    @Override
    public Empresa findByEmpresaId(Integer custId) {
        String sql = "SELECT * FROM EMPRESA WHERE identificadorPrimarioDeLaEmpresa = " + custId;
        List<Empresa> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    @Override
    public String findRepresentanteByEmpresa(Integer idEmpresa) {
        String sql = "SELECT RTRIM(dbo.Persona.primerNombre) + ' ' + ISNULL(RTRIM(dbo.Persona.segundoNombre) + ' ', '') + ISNULL(RTRIM(dbo.Persona.primerApellido) + ' ', '') + ISNULL(RTRIM(dbo.Persona.segundoApellido), '')\n" +
                     "FROM dbo.OferenteDeBienesYServicios INNER JOIN\n" +
                     " dbo.Empresa ON dbo.OferenteDeBienesYServicios.identificadorPrimarioOferente = dbo.Empresa.identificadorPrimarioOferente INNER JOIN\n" +
                     " dbo.Persona ON dbo.OferenteDeBienesYServicios.identificadorPrimarioOferente = dbo.Persona.identificadorPrimarioOferente\n" +
                     " WHERE dbo.Empresa.identificadorPrimarioDeLaEmpresa = " + idEmpresa;
        String rep = (String) getJdbcTemplate().queryForObject(sql, String.class); 
        if (rep.isEmpty()) {
            return null;
        } else {
            return rep;
        }
    }

    @Override
    public Empresa findByEmpresaIdOferente(Integer custId) {
        String sql = "SELECT * FROM EMPRESA WHERE identificadorPrimarioOferente = " + custId;
        List<Empresa> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }

    @Override
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public Empresa getEmpresa() {
        return empresa;
    }
}
