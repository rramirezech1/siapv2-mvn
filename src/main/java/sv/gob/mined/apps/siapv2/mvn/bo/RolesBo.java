/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.dto.CadenaWhereMetasActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.ActorDelProceso;
import sv.gob.mined.apps.siapv2.mvn.modelo.DetalleAccesoPlantilla;
import sv.gob.mined.apps.siapv2.mvn.modelo.EstructuraDeLaEstrategia;
import sv.gob.mined.apps.siapv2.mvn.modelo.GEstructuraOrganizativa;
import sv.gob.mined.apps.siapv2.mvn.modelo.MacroProcesos;
import sv.gob.mined.apps.siapv2.mvn.modelo.NivelesOrganigramaInstitucional;
import sv.gob.mined.apps.siapv2.mvn.modelo.NivelesRealizacGeograf;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgActividades;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgConvenio;
import sv.gob.mined.apps.siapv2.mvn.modelo.PgMetas;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillaAccesoDatos;
import sv.gob.mined.apps.siapv2.mvn.modelo.PlantillasDeSeguridad;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaAnosPlanAccion;
import sv.gob.mined.apps.siapv2.mvn.modelo.ProcedimientoTarea;
import sv.gob.mined.apps.siapv2.mvn.modelo.Procesos;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityInfo;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityTemplate;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRolControl;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityGroup;
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.modelo.PermisoOpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwPermisoOpcionMenu;

/**
 *
 * @author Infosoft
 */
public interface RolesBo {

    public List<MacroProcesos> getLstMacroProcesos();

    public List<GEstructuraOrganizativa> getLstGEstructuraOrganizativa();

    public List<GEstructuraOrganizativa> getLstGEstructuraOrganizativaChilds(String id);

    public List<ActorDelProceso> getLstActorDelProceso();

    public List<TipoRolControl> getLstTipoRolControl();

    public List<PlantillasDeSeguridad> getLstPlantillasDeSeguridad();

    public List<NivelesRealizacGeograf> getLstNivelesRealizacGeograf();

    public List<NivelesOrganigramaInstitucional> getLstNivelesOrganigramaInstitucional();

    public List<PlantillaAccesoDatos> getLstPlantillaAccesoDatos();

    public List<Procesos> getLstProcesos();

    public List<ProcedimientoTarea> getLstProcedimientoTarea();

    public List<SecurityTemplate> getLstSecurityTemplate();

    public List<SecurityUsers> getLstSecurityUser();
    
    public List<SecurityGroup> getLstSecurityGroup();
    
    public List<OpcionMenu> getLstOpcionMenu();
    
    public List<VwPermisoOpcionMenu> getLstPermisoOpcionMenu();
    
    public List<SecurityUsers> getLstSecurityUserByCriteria(String criteria);

    public Integer createPlantillaSeguridad(PlantillasDeSeguridad plantilla, Boolean edit);

    public Integer createPlantillaAccesoDatos(PlantillaAccesoDatos plantilla, Boolean edit);

    public Integer createDetalleAccesoPlantilla(DetalleAccesoPlantilla detplan, Boolean edit);

    public SecurityUsers buscarSecurityUserById(Integer id);
    
    public SecurityGroup buscarSecurityGroupById(Integer id);
    
    public OpcionMenu buscarOpcionMenuById(Integer id);
    
    public PermisoOpcionMenu buscarPermisoOpcionMenuById(Integer id);
    
    public List<OpcionMenu> getLstOpcionMenuPadre();
    
    public List<OpcionMenu> getLstOpcionMenuHijo();
    
    public Integer saveSecurityGroup(SecurityGroup grupo);
    
    public Integer saveOpcionMenu(OpcionMenu opcion);
    
    public Integer savePermisoOpcionMenu(PermisoOpcionMenu permisoopcion);

    public Integer saveSecurityInfo(SecurityInfo securityinfo);

    public List<SecurityTemplate> getListadosSecurityTemplateModificacion(Integer id);

    public PlantillasDeSeguridad getPlantillaModificacion(Integer id);

    public PlantillaAccesoDatos getPlantillaDatosModificacion(Integer id);

    public List<SecurityUsers> getListadoUsuariosPlantillaModificacion(Integer id);

    public void deleteDetallesPlantillaPorModificacion(Integer id);

    public List<EstructuraDeLaEstrategia> getListadoEstructuraDeLaEstrategia();

    public List<PgConvenio> getListadoDeConvenio();

    public List<PoaAnosPlanAccion> getListadoPoaAnosPlanAccionByConvenio(Integer idConvenio);

    public List<PgMetas> getListadodeMetas();

    public List<PgActividades> getLitadoActividades();

    public List<PgActividades> getListadoActividadesPorMeta(Integer meta);

    public List<PgMetas> getListadodeMetasByConvenio(Integer convenio);

    public PgMetas getMetaById(Integer Meta);

    public PgActividades getPgActividadById(Integer id);
    
    public List<CadenaWhereMetasActividades> getListaCadenaWhereMetasActividades(Integer id);
    
    public DetalleAccesoPlantilla getDepPlantillaById(Integer id);   
    
    public EstructuraDeLaEstrategia getEstructuraById(Integer id);   
}
