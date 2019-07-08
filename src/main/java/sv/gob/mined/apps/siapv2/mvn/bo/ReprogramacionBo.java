/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo;

import java.math.BigDecimal;
import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.dto.ElementoReproDto;
import sv.gob.mined.apps.siapv2.mvn.modelo.DetalleCompPorPlantilla;
import sv.gob.mined.apps.siapv2.mvn.modelo.EstadoPoaReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.OrigenDeLosRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgComponentes;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgConvenio;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgEntidadesFinanciadoras;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgMetas;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillaDeComportamientos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaAnosPlanAccion;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaDetalleReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaReprogramaciones;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoDeMetas;
import sv.gob.mined.apps.siapv2.mvn.modelo.TiposPoaReprogramaciones;

/**
 *
 * @author Infosoft
 */
public interface ReprogramacionBo {

    public int saveFichaReprogramacion(PoaReprogramaciones poaReprogramacion);
    
    public int saveDetalleReprogramacion(PoaDetalleReprogramaciones detRepro);

    public List<EstadoPoaReprogramaciones> findAllEstadoPoaReprogramacion();

    public List<TiposPoaReprogramaciones> findAllTiposPoaReprogramaciones();

    public List<OrigenDeLosRecursos> getLstOrigenRecursos();
    
    public List<TipoDeMetas> getLstTipoMetas();

    public List<PgConvenio> getLstConvenio(Integer idOrigen);

    public List<PlantillaDeComportamientos> getLstPlantillaDeComportamiento(Integer convenio);

    public DetalleCompPorPlantilla getDetCompPorPlantilla(Integer convenio);

    public PoaRecursos getRecursoById(Integer recurso);
    
    public List<PoaRecursos> getLstRecursosByActividad(Integer actividad);

    public List<PoaReprogramaciones> getLstReprogramaciones(Integer poa);
    
    public List<PoaDetalleReprogramaciones> getLstDetalleReprogramaciones(Integer poaReprogramacion);

    public List<PoaAnosPlanAccion> getLstPoaAnhosPlanAccion(Integer convenio);

    public List<ElementoReproDto> getLstElementoRepro(Integer convenio);

    public String categoriaByRecurso(Integer recurso);
    
    public Integer idCategoriaByRecurso(Integer recurso);
    
    public Integer estadoConvenio(Integer convenio);

    public String oegByRecurso(Integer recurso);

    public BigDecimal getPorcentajeFtePpal(Integer recurso);

    public int getOrganismoByConvenio(Integer convenio, Boolean ftePrincipal);

    public Boolean isUnicaFuente(Integer convenio);

    public PgEntidadesFinanciadoras getEntidadesFinanciadoras(Integer convenio, Boolean ftePpal);
    
    public List<PgComponentes> getLstComponente(Integer convenio);
    
    public List<PgComponentes> getLstSubComponente(Integer componente);
    
    public List<PgMetas> getLstMetas(Integer componente);

    public List<PgActividades> getLstActividades(Integer meta);

    public List<PoaRecursos> getLstPoaRecursos(Integer actividad);

}
