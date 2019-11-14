/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.OpcionMenuDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu1;

/**
 *
 * @author Infosoft
 */
@Repository
public class OpcionMenuDaoImpl extends XJdbcTemplate implements OpcionMenuDao {

    public OpcionMenuDaoImpl() {
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
    public void setOpcionMenu(OpcionMenu1 opcionmenu) {
        super.setObjeto(opcionmenu);
    }
  
    @Override
    public List<OpcionMenu1> findAllByUser(Integer user) {
        String sql = "SELECT * "
                + "  FROM vw_garantias_grupo "
                + " WHERE grupoSiap = " + user
                + "   AND estadodeeliminacion = 0 "
                + "   ORDER BY identificadorGarantia desc";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu1.class));
    }
    
    @Override
    public List<OpcionMenu1> findAllByGroup(Integer group) {
        String sql = "SELECT * "
                + "  FROM vw_garantias_grupo "
                + " WHERE grupoSiap = " + group
                + "   AND estadodeeliminacion = 0 "
                + "   ORDER BY identificadorGarantia desc";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu1.class));
    }

    @Override
    public OpcionMenu1 findById(Integer idOpcionMenu) {
        String sql = "SELECT * FROM GarantiasOferente WHERE identificadorGarantia = " + idOpcionMenu;
        List<OpcionMenu1> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu1.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    @Override
    public List<OpcionMenu1> findByMenuPadre() {
        String sql = "SELECT * FROM opcion_menu WHERE(idOpcionPadre IS NULL)";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu1.class));
    }
    
     @Override
    public List<OpcionMenu1> findByMenuHijo() {
        String sql = "SELECT * FROM opcion_menu WHERE(idOpcionPadre IS NOT NULL)";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu1.class));
    }
    
}
