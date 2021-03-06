/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.GruposDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGrupos;

/**
 *
 * @author Infosoft
 */
@Repository
public class GruposDaoImpl extends XJdbcTemplate2 implements GruposDao {

    public GruposDaoImpl() {
    }

    @Override
    public List<VwGrupos> findGruposByUser(String usuarioSession, Integer tipoGrupo) {
        
        String condision;
        condision = "(grupos.estado IN ('I'))";
        
        if(tipoGrupo == 1){
            condision= "(grupos.estado IN ('I'))";
        }
        else{
           condision= "(grupos.estado IN ('F', 'C'))";
        }
        
        String sql = " SELECT grupos.grupo, grupos.no_licitacion, pg_convenio.numero_convenio as convenio, grupos.concepto, metodos_adquisiciones.descripcion\n" +
                     " FROM grupos INNER JOIN\n" +
                     " pg_convenio ON grupos.convenio = pg_convenio.convenio INNER JOIN\n" +
                     " g_personal ON grupos.personal_id = g_personal.personal_id INNER JOIN\n" +
                     " metodos_adquisiciones ON grupos.metodo= metodos_adquisiciones.metodo INNER JOIN\n"+     
                     " security_users ON g_personal.personal_id = security_users.personal_id\n" +
                     " WHERE (grupos.estado NOT IN ('A', 'S', 'E', 'P')) AND "+condision+ " AND (security_users.name = '" + usuarioSession +"')";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwGrupos.class));
    }
    
    @Override
    public VwGrupos findById(Integer idGrupo) {
        String sql = "SELECT grupos.grupo, grupos.no_licitacion, pg_convenio.numero_convenio as convenio, grupos.concepto, metodos_adquisiciones.descripcion as metodoAdquisicion FROM Grupos  INNER JOIN\n" +
                     " metodos_adquisiciones ON grupos.metodo= metodos_adquisiciones.metodo INNER JOIN\n"+     
                     " pg_convenio ON grupos.convenio = pg_convenio.convenio WHERE grupo = " + idGrupo;
        List<VwGrupos> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwGrupos.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
 }
