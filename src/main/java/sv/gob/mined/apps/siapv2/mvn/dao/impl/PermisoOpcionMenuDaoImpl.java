/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.PermisoOpcionMenuDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.PermisoOpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwPermisoOpcionMenu;

/**
 *
 * @author Infosoft
 */
@Repository
public class PermisoOpcionMenuDaoImpl extends XJdbcTemplate implements PermisoOpcionMenuDao {

    public PermisoOpcionMenuDaoImpl() {
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
    public void setPermisoOpcionMenu(PermisoOpcionMenu permisoopcionmenu) {
        super.setObjeto(permisoopcionmenu);
    }
    
    @Override
    public List<VwPermisoOpcionMenu> findAllByUser(Integer user) {
        String sql = "SELECT * "
                + "  FROM vw_garantias_grupo "
                + " WHERE grupoSiap = " + user
                + "   AND estadodeeliminacion = 0 "
                + "   ORDER BY identificadorGarantia desc";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PermisoOpcionMenu.class));
    }
    
    @Override
    public List<VwPermisoOpcionMenu> findAllByGroup(Integer group) {
        String sql = "SELECT * "
                + "  FROM vw_garantias_grupo "
                + " WHERE grupoSiap = " + group
                + "   AND estadodeeliminacion = 0 "
                + "   ORDER BY identificadorGarantia desc";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PermisoOpcionMenu.class));
    }

    @Override
    public PermisoOpcionMenu findById(Integer idPermisoOpcionMenu) {
        String sql = "SELECT * FROM GarantiasOferente WHERE identificadorGarantia = " + idPermisoOpcionMenu;
        List<PermisoOpcionMenu> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PermisoOpcionMenu.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    @Override
    public PermisoOpcionMenu findPermisoOpcionMenuById(Integer idPermisoOpcionMenu) {
        String sql = "SELECT * FROM PermisoOpcionMenu WHERE idPermisoOpcionMenu = " + idPermisoOpcionMenu;
        List<PermisoOpcionMenu> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PermisoOpcionMenu.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
        
    @Override
    public List<VwPermisoOpcionMenu> findByMenuPadre() {
        String sql = "SELECT * FROM opcionMenu WHERE(idOpcionPadre IS NULL)";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PermisoOpcionMenu.class));
    }
    
    @Override
    public List<VwPermisoOpcionMenu> findByMenuHijo() {
        String sql = "SELECT * FROM opcionMenu WHERE(idOpcionPadre IS NOT NULL)";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PermisoOpcionMenu.class));
    }
    
    @Override
    public List<VwPermisoOpcionMenu> findAll() {
        String sql = "SELECT idPermisoOpcionMenu, idOpcionMenu, idGrupo, dbo.f_obtener_nombre_grupo_seguridad(idGrupo) AS grupo, estadoDeEliminacion, dbo.f_obtener_opcion_menu(idOpcionMenu) AS opcionMenu FROM PermisoOpcionMenu WHERE (estadoDeEliminacion = 0)";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(PermisoOpcionMenu.class));
    }
}
