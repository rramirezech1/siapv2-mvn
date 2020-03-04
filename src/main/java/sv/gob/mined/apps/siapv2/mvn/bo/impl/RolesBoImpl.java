/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.RolesBo;
import sv.gob.mined.apps.siapv2.mvn.dao.ActorDelProcesoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.DetalleAccesoPlantillaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.EstructuraDeLaEstrategiaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.GEstructuraOrganizativaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.MacroProcesosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.NivelesOrganigramaInstitucionalDao;
import sv.gob.mined.apps.siapv2.mvn.dao.NivelesRealizacGeografDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PgActividadesDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PgConvenioDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PgMetasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PlantillaAccesoDatosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PlantillasDeSeguridadDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PoaAnosPlanAccionDao;
import sv.gob.mined.apps.siapv2.mvn.dao.ProcedimientoTareaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.ProcesosDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityInfoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityTemplateDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityUsersDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SecurityGroupDao;
import sv.gob.mined.apps.siapv2.mvn.dao.TipoRolControlDao;
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
import sv.gob.mined.apps.siapv2.mvn.modelo.OpcionMenu;
import sv.gob.mined.apps.siapv2.mvn.dao.OpcionMenuDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRolControl;
import sv.gob.mined.apps.siapv2.mvn.util.JsfUtil;

/**
 *
 * @author
 */
@Service
public class RolesBoImpl implements RolesBo {

    public RolesBoImpl() {
    }
    
    @Autowired
    private MacroProcesosDao macroprocesodao;
    @Autowired
    private GEstructuraOrganizativaDao gestruturaOrganizativadao;
    @Autowired
    private ActorDelProcesoDao actordelprocesodao;
    @Autowired
    private TipoRolControlDao tiporolcontroldao;
    @Autowired
    private PlantillasDeSeguridadDao plantillasdeseguridaddao;
    @Autowired
    private NivelesRealizacGeografDao nivelesrealizacgeografdao;
    @Autowired
    private NivelesOrganigramaInstitucionalDao nivelesorganigramainstitucional;
    @Autowired
    private PlantillaAccesoDatosDao plantillaaccesodatosdao;
    @Autowired
    private ProcesosDao procesosdao;
    @Autowired
    private ProcedimientoTareaDao prodedimientotareadao;
    @Autowired
    private SecurityTemplateDao securitytemplatedao;
    @Autowired
    private SecurityUsersDao securityusersdao;
    @Autowired
    private SecurityGroupDao securitygroupdao;
    @Autowired
    private OpcionMenuDao opcionmenudao;
    @Autowired
    private SecurityInfoDao securityinfodao;
    @Autowired
    private EstructuraDeLaEstrategiaDao estruturadelaestrategiadao;
    @Autowired
    private PgConvenioDao pgconveniodao;
    @Autowired
    private PoaAnosPlanAccionDao poaanosplanacciondao;
    @Autowired
    private PgMetasDao pgmetasdao;
    @Autowired
    private PgActividadesDao pgactividadesdao;
    @Autowired
    private DetalleAccesoPlantillaDao detalleaccesoplantillaDao;

    @Override
    public List<MacroProcesos> getLstMacroProcesos() {
        return macroprocesodao.findAll();
    }

    @Override
    public List<GEstructuraOrganizativa> getLstGEstructuraOrganizativa() {
        return gestruturaOrganizativadao.findAll();
    }

    @Override
    public List<ActorDelProceso> getLstActorDelProceso() {
        return actordelprocesodao.findAll();
    }

    @Override
    public List<TipoRolControl> getLstTipoRolControl() {
        return tiporolcontroldao.findAll();
    }

    @Override
    public List<PlantillasDeSeguridad> getLstPlantillasDeSeguridad() {
        return plantillasdeseguridaddao.findAll();
    }

    @Override
    public List<NivelesRealizacGeograf> getLstNivelesRealizacGeograf() {
        return nivelesrealizacgeografdao.findAll();
    }

    @Override
    public List<NivelesOrganigramaInstitucional> getLstNivelesOrganigramaInstitucional() {
        return nivelesorganigramainstitucional.findAll();
    }

    @Override
    public List<PlantillaAccesoDatos> getLstPlantillaAccesoDatos() {
        return plantillaaccesodatosdao.findAll();
    }

    @Override
    public List<Procesos> getLstProcesos() {
        return procesosdao.findAll();
    }

    @Override
    public List<ProcedimientoTarea> getLstProcedimientoTarea() {
        return prodedimientotareadao.findAll();
    }

    @Override
    public List<SecurityTemplate> getLstSecurityTemplate() {
        return securitytemplatedao.findAll();
    }

    @Override
    public List<SecurityUsers> getLstSecurityUser() {
        return securityusersdao.findAll();
    }
    
     @Override
    public List<SecurityGroup> getLstSecurityGroup() {
        return securitygroupdao.findAll();
    }
    
    @Override
    public List<OpcionMenu> getLstOpcionMenu() {
        return opcionmenudao.findAll();
    }
    
    @Override
    public List<OpcionMenu> getLstOpcionMenuPadre() {
        return opcionmenudao.findByMenuPadre();
    }
    
    @Override
    public List<OpcionMenu>getLstOpcionMenuHijo() {
        return opcionmenudao.findByMenuHijo();
    }

    @Override
    public Integer createPlantillaSeguridad(PlantillasDeSeguridad plantilla, Boolean edit) {
        Integer id = plantilla.getIdPlantillaSeguridad();
        plantillasdeseguridaddao.setPlantillasDeSeguridad(plantilla);
        if (edit) {
            plantillasdeseguridaddao.update();
        } else {
            id = plantillasdeseguridaddao.create();
        }

        return id;
    }

    @Override
    public Integer createPlantillaAccesoDatos(PlantillaAccesoDatos plantilla, Boolean edit) {
        Integer id = plantilla.getIdPlantillaAccesoDatos();
        plantillaaccesodatosdao.setPlantillasDeAccesoDatos(plantilla);
        if (edit) {
            plantillaaccesodatosdao.update();
        } else {
            id = plantillaaccesodatosdao.create();            
        }
        return id;
     }

    @Override
    public List<SecurityUsers> getLstSecurityUserByCriteria(String criteria) {
        return securityusersdao.findByCriteria(criteria);
    }

    @Override
    public SecurityUsers buscarSecurityUserById(Integer id) {
        return securityusersdao.findById(id);
    }

    @Override
    public SecurityGroup buscarSecurityGroupById(Integer id) {
        return securitygroupdao.findById(id);
    }
    
    @Override
    public OpcionMenu buscarOpcionMenuById(Integer id) {
        return opcionmenudao.findOpcionMenuById(id);
    }
    
    @Override
    public Integer saveSecurityGroup(SecurityGroup grupo) {
        securitygroupdao.setSecurityGroup(grupo);
        if (grupo.getIdGrupo() == null) {
            Integer id = securitygroupdao.create();
            grupo.setIdGrupo(id);
            return id;
        } else {
            return securitygroupdao.update();
        }
    }
    
    @Override
    public Integer saveOpcionMenu(OpcionMenu opcion) {
        opcionmenudao.setOpcionMenu(opcion);
        if (opcion.getIdOpcionMenu() == null) {
            Integer id = opcionmenudao.create();
            opcion.setIdOpcionMenu(id);
            return id;
        } else {
            return opcionmenudao.update();
        }
    }
        
    @Override
    public Integer saveSecurityInfo(SecurityInfo securityinfo) {
        securityinfodao.setSecurityInfo(securityinfo);
        return securityinfodao.create();
    }
    
    @Override
    public List<SecurityTemplate> getListadosSecurityTemplateModificacion(Integer id) {
        List<SecurityTemplate> listaretorno = new ArrayList<>();
        List<SecurityTemplate> lista = securitytemplatedao.findAll();
        List<SecurityInfo> listainfo = securityinfodao.getSecurityInfoByIdPlantilla(id);
        for (SecurityInfo securityInfo : listainfo) {

            for (SecurityTemplate securityTemplate : lista) {
                if (securityTemplate.getWindow().equals(securityInfo.getWindow()) && securityTemplate.getApplication().equals(securityInfo.getApplication()) && securityTemplate.getControl().equals(securityInfo.getControl())) {
                    Boolean status = false;
                    if ("1".equals(securityInfo.getStatus())) {
                        status = true;
                    }
                    securityTemplate.setStatus(status);
                    listaretorno.add(securityTemplate);
                    status = false;
                }
            }
        }

        return listaretorno;
    }

    @Override
    public PlantillasDeSeguridad getPlantillaModificacion(Integer id) {
        return this.plantillasdeseguridaddao.findPlantillaSeguridadById(id);
    }

    @Override
    public List<SecurityUsers> getListadoUsuariosPlantillaModificacion(Integer id) {
        return this.securityusersdao.findAllUserModifi(id);
    }

    @Override
    public void deleteDetallesPlantillaPorModificacion(Integer id) {
        this.securityinfodao.deleteDetalleEdit(id);
    }

    @Override
    public List<EstructuraDeLaEstrategia> getListadoEstructuraDeLaEstrategia() {
        return estruturadelaestrategiadao.findAll();
    }

    @Override
    public List<GEstructuraOrganizativa> getLstGEstructuraOrganizativaChilds(String id) {
        return gestruturaOrganizativadao.findAllChilds(id);
    }

    @Override
    public List<PgConvenio> getListadoDeConvenio() {
        return pgconveniodao.findAll();
    }

    @Override
    public List<PoaAnosPlanAccion> getListadoPoaAnosPlanAccionByConvenio(Integer idConvenio) {
        return poaanosplanacciondao.getAnhoByConvenio(idConvenio);
    }

    @Override
    public List<PgMetas> getListadodeMetas() {
        return pgmetasdao.findAll();
    }

    @Override
    public List<PgActividades> getLitadoActividades() {
        return pgactividadesdao.findAll();

    }

    @Override
    public List<PgMetas> getListadodeMetasByConvenio(Integer convenio) {
        return pgmetasdao.findMetasByConvenio(convenio);
    }

    @Override
    public List<PgActividades> getListadoActividadesPorMeta(Integer meta) {
        return pgactividadesdao.getPgActividadesbyMeta(meta);
    }

    @Override
    public PgMetas getMetaById(Integer meta) {
        return pgmetasdao.getPgMetasById(meta);
    }

    @Override
    public PgActividades getPgActividadById(Integer id) {
        return pgactividadesdao.getPgActividadById(id);
    }

   
    @Override
    public Integer createDetalleAccesoPlantilla(DetalleAccesoPlantilla detplan, Boolean edit) {
        Integer id = detplan.getIdPlantillaAccesoDatos();        
        if (edit) {
            detplan.setFechaDeModificacion(new Date());
            DetalleAccesoPlantilla det= detalleaccesoplantillaDao.getDepPlantillaWhere(detplan.getIdPlantillaAccesoDatos());
            detplan.setIdDetalleAccesoDatos(det.getIdDetalleAccesoDatos());
            detalleaccesoplantillaDao.setDetPlantillaSeg(detplan);
            detalleaccesoplantillaDao.update();
        } else {
            detplan.setEstadoDeEliminacion(0);
            detplan.setFechaDeInsercion(new Date());
            detplan.setName(JsfUtil.sUsuario);
            detalleaccesoplantillaDao.setDetPlantillaSeg(detplan);
            id = detalleaccesoplantillaDao.create();            
        }
        return id;
    }

    @Override
    public PlantillaAccesoDatos getPlantillaDatosModificacion(Integer id) {
       return plantillaaccesodatosdao.findPlantillasDeAccesoDatosById(id);
    }

    @Override
    public List<CadenaWhereMetasActividades> getListaCadenaWhereMetasActividades(Integer id) {
        return detalleaccesoplantillaDao.getListCadenaWhereMetasActividades(id);
    }

    @Override
    public DetalleAccesoPlantilla getDepPlantillaById(Integer id) {
        return detalleaccesoplantillaDao.getDepPlantillaWhere(id);
    }

    @Override
    public EstructuraDeLaEstrategia getEstructuraById(Integer id) {
        return estruturadelaestrategiadao.findEstructuraById(id);
    }
}
