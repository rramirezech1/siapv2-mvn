/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.OpcionMenuDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUserGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;

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
    public void setOpcionMenu(OpcionMenu opcionmenu) {
        super.setObjeto(opcionmenu);
    }

    @Override
    public List<OpcionMenu> findAllByUser(Integer user) {
        String sql = "SELECT * "
                + "  FROM vw_garantias_grupo "
                + " WHERE grupoSiap = " + user
                + "   AND estadodeeliminacion = 0 "
                + "   ORDER BY identificadorGarantia desc";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
    }

    @Override
    public List<OpcionMenu> findAllByGroup(Integer group) {
        String sql = "SELECT * "
                + "  FROM vw_garantias_grupo "
                + " WHERE grupoSiap = " + group
                + "   AND estadodeeliminacion = 0 "
                + "   ORDER BY identificadorGarantia desc";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
    }

    @Override
    public OpcionMenu findById(Integer idOpcionMenu) {
        String sql = "SELECT * FROM GarantiasOferente WHERE identificadorGarantia = " + idOpcionMenu;
        List<OpcionMenu> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }

    @Override
    public OpcionMenu findOpcionMenuById(Integer idOpcionMenu) {
        String sql = "SELECT * FROM opcionMenu WHERE idOpcionMenu = " + idOpcionMenu;
        List<OpcionMenu> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }

    @Override
    public List<OpcionMenu> getLstSecurityGroupOpciones(List<SecurityUserGroup> list) {
        List<OpcionMenu> opciones = new ArrayList<>();

        for (SecurityUserGroup a : list) {
            Integer id = a.getIdGrupo();
            String sql = "SELECT * FROM PermisoOpcionMenu WHERE idGrupo in (" + id + ")";

            List<OpcionMenu> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));

            for (OpcionMenu b : lst) {

                String sql2 = "SELECT * FROM OpcionMenu WHERE (idOpcionPadre IS NULL) AND (idOpcionMenu =" + b.getIdOpcionMenu() + ")"
                        + " UNION ALL SELECT * FROM OpcionMenu WHERE (idOpcionPadre = " + b.getIdOpcionMenu() +")";

                List<OpcionMenu> lst2 = getJdbcTemplate().query(sql2, new BeanPropertyRowMapper(OpcionMenu.class));

                for (OpcionMenu c : lst2) {
                    opciones.add(c);
                }
            }

        }
        return opciones;
    }

    @Override
    public List<OpcionMenu> findByMenuPadre() {
        String sql = "SELECT * FROM opcionMenu WHERE(idOpcionPadre IS NULL)";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
    }

    @Override
    public List<OpcionMenu> findByMenuHijo() {
        String sql = "SELECT * FROM opcionMenu WHERE(idOpcionPadre IS NOT NULL)";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
    }

    @Override
    public List<OpcionMenu> findAll() {
        String sql = "SELECT idOpcionMenu, nombreOpcionMenu, labelOpcionMenu, iconOpcionMenu, direccionAccesoOpcion, idOpcionPadre, dbo.f_obtener_opcion_menu(idOpcionPadre) as opcionPadre FROM opcionMenu WHERE estadoDeEliminacion=0";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
    }

    @Override
    public SecurityUsers usuarioOpciones(String us
    ) {
        SecurityUsers usuario = null;
        //System.out.println("Estoy en el usuario01: " + us);
        String sql = "SELECT * FROM security_users WHERE name =" + us;
        List<SecurityUsers> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SecurityUsers.class));

        //System.out.println("Estoy en el usuario02: " + lst.get(0).getName());
        return usuario;
    }

}
