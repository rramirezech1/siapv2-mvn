/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.SectorEconomicoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SectorEconomico;

/**
 *
 * @author Infosoft
 */
@Repository
public class SectorEconomicoDaoImpl extends XJdbcTemplate implements SectorEconomicoDao {

    public SectorEconomicoDaoImpl() {
    }

    @Override
    public List<SectorEconomico> findAll() {
        String sql = "SELECT * FROM SECTORECONOMICO WHERE SEC_IDENTIFICADORDELSECTORECONOMICO is null ORDER BY IDENTIFICADORDELSECTORECONOMICO, SEC_IDENTIFICADORDELSECTORECONOMICO";
        List<SectorEconomico> sector = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SectorEconomico.class));
        return sector;
    }

    @Override
    public List<SectorEconomico> findAllSub(Integer idSector) {
        String sql = "SELECT * FROM SECTORECONOMICO WHERE SEC_IDENTIFICADORDELSECTORECONOMICO=" + idSector + " ORDER BY IDENTIFICADORDELSECTORECONOMICO ";
        List<SectorEconomico> sector = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(SectorEconomico.class));
        return sector;
    }
}