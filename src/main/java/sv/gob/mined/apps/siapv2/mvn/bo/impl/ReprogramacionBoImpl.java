/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.ReprogramacionBo;
import sv.gob.mined.apps.siapv2.mvn.dao.DetalleCompPorPlantillaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.EstadoPoaReprogramacionDao;
import sv.gob.mined.apps.siapv2.mvn.dao.FuentesFinanciamientosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.OrigenDeLosRecursosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PgActividadesDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PgComponentesDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PgConvenioDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PgMetasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PlantillaDeComportamientosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PoaAnosPlanAccionDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PoaRecursosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PoaReprogramacionesDao;
import sv.gob.mined.apps.siapv2.mvn.dao.TipoDeMetasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.TiposPoaReprogramacionesDao;
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
@Service
public class ReprogramacionBoImpl implements ReprogramacionBo {

    public ReprogramacionBoImpl() {
    }
    @Autowired
    private EstadoPoaReprogramacionDao estadoPoaReprogramacion;
    @Autowired
    private TiposPoaReprogramacionesDao tiposPoaReprogramaciones;
    @Autowired
    private OrigenDeLosRecursosDao origenDeRecursos;
    @Autowired
    private PgConvenioDao pgConvenio;
    @Autowired
    private PlantillaDeComportamientosDao plantillaComportamiento;
    @Autowired
    private DetalleCompPorPlantillaDao detCompPlantilla;
    @Autowired
    private PoaAnosPlanAccionDao poaAnosPlanAccion;
    @Autowired
    private PoaReprogramacionesDao poaReprogramacion;
    @Autowired
    private PoaRecursosDao poaRecursos;
    @Autowired
    private TipoDeMetasDao tipoDeMetas;
    @Autowired
    private FuentesFinanciamientosDao fuentesFinanciamientos;
    @Autowired
    private PgComponentesDao pgComponentes;
    @Autowired
    private PgMetasDao pgMetas;
    @Autowired
    private PgActividadesDao pgActividades;

    @Override
    public List<EstadoPoaReprogramaciones> findAllEstadoPoaReprogramacion() {
        return estadoPoaReprogramacion.findAll();
    }

    @Override
    public List<TiposPoaReprogramaciones> findAllTiposPoaReprogramaciones() {
        return tiposPoaReprogramaciones.findAll();
    }

    @Override
    public List<OrigenDeLosRecursos> getLstOrigenRecursos() {
        return origenDeRecursos.findAll();
    }

    @Override
    public List<PgConvenio> getLstConvenio(Integer idOrigen) {
        return pgConvenio.findAll(idOrigen);
    }

    @Override
    public List<PlantillaDeComportamientos> getLstPlantillaDeComportamiento(Integer origen) {
        return plantillaComportamiento.findAllByOrigen(origen);
    }

    @Override
    public DetalleCompPorPlantilla getDetCompPorPlantilla(Integer convenio) {
        return detCompPlantilla.findByConvenio(convenio);
    }

    @Override
    public List<PoaReprogramaciones> getLstReprogramaciones(Integer poa) {
        return poaReprogramacion.findAllByPoa(poa);
    }

    @Override
    public List<PoaAnosPlanAccion> getLstPoaAnhosPlanAccion(Integer convenio) {
        return poaAnosPlanAccion.getAnhoByConvenio(convenio);
    }

    @Override
    public int saveFichaReprogramacion(PoaReprogramaciones poaReprogramacion) {
        this.poaReprogramacion.setPoaReprogramacion(poaReprogramacion);
        if (poaReprogramacion.getReprogramacion() == null) {
            return this.poaReprogramacion.createFichaReprogramacion();
        } else {
            return this.poaReprogramacion.updateFichaReprogramacion();
        }
    }

    @Override
    public List<ElementoReproDto> getLstElementoRepro(Integer convenio) {
        return pgConvenio.findByConvenio(convenio);
    }

    @Override
    public List<PoaRecursos> getLstRecursosByActividad(Integer actividad) {
        return poaRecursos.findAllByActividad(actividad);
    }

    @Override
    public String categoriaByRecurso(Integer recurso) {
        return poaRecursos.categoriaByRecurso(recurso);
    }

    @Override
    public String oegByRecurso(Integer recurso) {
        return poaRecursos.oegByRecurso(recurso);
    }

    @Override
    public BigDecimal getPorcentajeFtePpal(Integer recurso) {
        return poaRecursos.getPorcentajeFtePpal(recurso);
    }

    @Override
    public int getOrganismoByConvenio(Integer convenio, Boolean ftePrincipal) {
        return fuentesFinanciamientos.findOrganismoByConvenio(convenio, ftePrincipal);
    }

    @Override
    public Boolean isUnicaFuente(Integer convenio) {
        return pgConvenio.isUnicaFuente(convenio);
    }

    @Override
    public PgEntidadesFinanciadoras getEntidadesFinanciadoras(Integer convenio, Boolean ftePpal) {
        return fuentesFinanciamientos.findEntidadesFinanByConvenio(convenio, ftePpal);
    }

    @Override
    public PoaRecursos getRecursoById(Integer recurso) {
        return poaRecursos.getRecursoById(recurso);
    }

    @Override
    public Integer idCategoriaByRecurso(Integer recurso) {
        return poaRecursos.idCategoriaByRecurso(recurso);
    }

    @Override
    public int saveDetalleReprogramacion(PoaDetalleReprogramaciones detRepro) {
        this.poaReprogramacion.setDetPoaReprogramacion(detRepro);
        return this.poaReprogramacion.saveDetalleReprogramacion();
    }

    @Override
    public List<PoaDetalleReprogramaciones> getLstDetalleReprogramaciones(Integer poaReprogramacion) {
        return this.poaReprogramacion.findDetalleReprogramacionByPoaReprogramacion(poaReprogramacion);
    }

    @Override
    public Integer estadoConvenio(Integer convenio) {
        return poaRecursos.estadoConvenio(convenio);
    }

    @Override
    public List<PgComponentes> getLstComponente(Integer convenio) {
        return pgComponentes.findAll(convenio);
    }

    @Override
    public List<PgComponentes> getLstSubComponente(Integer componente) {
        return pgComponentes.findAllSub(componente);
    }

    @Override
    public List<PgMetas> getLstMetas(Integer componente) {
        return pgMetas.findAll(componente);
    }

    @Override
    public List<PgActividades> getLstActividades(Integer meta) {
        return pgActividades.findAllByMeta(meta);
    }

    @Override
    public List<PoaRecursos> getLstPoaRecursos(Integer actividad) {
        return poaRecursos.findAllByActividad(actividad);
    }
    
    @Override
    public List<TipoDeMetas> getLstTipoMetas() {
        return tipoDeMetas.findAll();
    }

}