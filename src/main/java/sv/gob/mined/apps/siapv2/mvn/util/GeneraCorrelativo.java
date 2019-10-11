/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.util;

import java.util.Map;
import javax.faces.validator.FacesValidator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;

/**
 *
 * @author Roberto Antonio Ramírez
 */
@FacesValidator("validatorFechaActividad")
public class GeneraCorrelativo extends XJdbcTemplate {

    public synchronized int CorrelativoDocEjercicion(Integer tipoDocumento, Integer ejercicioFiscal) {
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
            System.out.println("Error en la generación del correlativo, enviar captura de este mensaje a informatico: " + e);
            return 0;
        }        
    }
}
