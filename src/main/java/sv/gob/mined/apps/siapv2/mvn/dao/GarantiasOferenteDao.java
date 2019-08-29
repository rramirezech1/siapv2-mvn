/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.GarantiasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGarantiasGrupo;

/**
 *
 * @author Infosoft
 */
public interface GarantiasOferenteDao {

    public List<VwGarantiasGrupo> findAllByGrupo(Integer grupo);

    public GarantiasOferente findById(Integer idGarantia);

    public int create();

    public int update();

    public void setGarantiaOferente(GarantiasOferente garantia);
}
