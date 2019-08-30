/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.GarantiasOferenteDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.GarantiasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGarantiasGrupo;

/**
 *
 * @author Infosoft
 */
@Repository
public class GarantiasOferenteDaoImpl extends XJdbcTemplate implements GarantiasOferenteDao {

    public GarantiasOferenteDaoImpl() {
    }

    @Override
    public int create() {
        return super.create();
    }

    @Override
    public int update() {
        return super.update();
    }

    @Override
    public void setGarantiaOferente(GarantiasOferente garantia) {
        super.setObjeto(garantia);
    }
  
    @Override
    public List<VwGarantiasGrupo> findAllByGrupo(Integer grupo) {
        String sql = "SELECT * "
                + "  FROM vw_garantias_grupo "
                + " WHERE grupoSiap = " + grupo
                + "   AND estadodeeliminacion = 0 "
                + "   ORDER BY identificadorGarantia desc";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwGarantiasGrupo.class));
    }

    @Override
    public GarantiasOferente findById(Integer idGarantia) {
        String sql = "SELECT * FROM GarantiasOferente WHERE identificadorGarantia = " + idGarantia;
        List<GarantiasOferente> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(GarantiasOferente.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    @Override
    public synchronized int generaCorrelativo(Integer tipoDocumento, Integer ejercicioFiscal) {
        try {
            SimpleJdbcCall jdbcCall;
            jdbcCall = new SimpleJdbcCall(getJdbcTemplate()).withProcedureName("sp_control_correlativos_documentos");
            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("tipoDocumento", tipoDocumento);
            in.addValue("ejercicioFiscal", ejercicioFiscal);
            
            Map<String, Object> out = jdbcCall.execute(in);
            return  (Integer)out.get("correlativo");
        }
        catch(Exception e) {
            System.out.println("Error en la generación del correlativo, envie lo siguiente a Roberto: " + e);
            return 0;
        }        
    }
}
