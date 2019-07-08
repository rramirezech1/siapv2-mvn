/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.CalificacionContrato;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwCalificacionContratos;

/**
 *
 * @author misanchez
 */
public interface CalificacionContratoDao {

    public List<VwCalificacionContratos> findAllByOferente(Integer identificadorPrimarioOferente);

    public CalificacionContrato findById(Integer idCalificacion);

    public int create();

    public int update();

    public void setCalificacionContrato(CalificacionContrato calificacion);
}
