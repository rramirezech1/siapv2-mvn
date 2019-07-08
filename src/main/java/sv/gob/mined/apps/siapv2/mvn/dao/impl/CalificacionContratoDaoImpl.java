/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.CalificacionContratoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.CalificacionContrato;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwCalificacionContratos;

@Repository
public class CalificacionContratoDaoImpl extends XJdbcTemplate implements CalificacionContratoDao {

    private CalificacionContrato calificacion;

    @Override
    public List<VwCalificacionContratos> findAllByOferente(Integer identificadorPrimarioOferente) {
        String sql = "SELECT * FROM vw_calificacion_contratos WHERE identificadorPrimarioOferente=" + identificadorPrimarioOferente;
        List<VwCalificacionContratos> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(VwCalificacionContratos.class));
        return lst;
    }

    @Override
    public CalificacionContrato findById(Integer idCalificacion) {
        String sql = "SELECT * FROM CalificacionContrato WHERE estadoDeEliminacion = 0 and identificadorCalificacion = " + idCalificacion;
        List<CalificacionContrato> pganosplanglobal = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(CalificacionContrato.class));
        if (pganosplanglobal.isEmpty()) {
            return null;
        } else {
            return pganosplanglobal.get(0);
        }
    }

    @Override
    public int create() {
        super.setObjeto(calificacion);
        int id = super.create();
        this.calificacion.setIdentificadorCalificacion(id);
        return id;
    }

    @Override
    public int update() {
        super.setObjeto(calificacion);
        return super.update();
    }

    @Override
    public void setCalificacionContrato(CalificacionContrato calificacion) {
        this.calificacion = calificacion;
    }
}
