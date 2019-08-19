/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Infosoft
 */
public class XJdbcTemplate2 {

    private PersistenciaDao Objeto;
    @Autowired
    //@Qualifier(value = "dataSource2")
    JdbcTemplate jdbcTemplate2;

    public XJdbcTemplate2() {   
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate2;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate2 = jdbcTemplate;
    }

   
    public PersistenciaDao getObjeto() {
        return Objeto;
    }

    public void setObjeto(PersistenciaDao Objeto) {
        this.Objeto = Objeto;
    }

}