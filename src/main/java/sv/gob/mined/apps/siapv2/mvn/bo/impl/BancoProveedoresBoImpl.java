/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.BancoProveedoresBo;
import sv.gob.mined.apps.siapv2.mvn.dao.CalificacionContratoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.EmpresaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.CatalogoFaltasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.GarantiasOferenteDao;
import sv.gob.mined.apps.siapv2.mvn.dao.TipoGarantiasDao;
import sv.gob.mined.apps.siapv2.mvn.dao.FormaGarantiaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.MultasOferenteDao;
import sv.gob.mined.apps.siapv2.mvn.dao.PersonaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.RescisionesOferenteDao;
import sv.gob.mined.apps.siapv2.mvn.dao.TrasladoEmpresaDao;
import sv.gob.mined.apps.siapv2.mvn.dao.UsuarioDao;
import sv.gob.mined.apps.siapv2.mvn.dao.DepartamentoDao;
import sv.gob.mined.apps.siapv2.mvn.dao.MunicipioDao;
import sv.gob.mined.apps.siapv2.mvn.dao.SectorEconomicoDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.CalificacionContrato;
import sv.gob.mined.apps.siapv2.mvn.modelo.Empresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.Departamento;
import sv.gob.mined.apps.siapv2.mvn.modelo.Municipio;
import sv.gob.mined.apps.siapv2.mvn.modelo.GarantiasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.MultasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.Persona;
import sv.gob.mined.apps.siapv2.mvn.modelo.RescisionesOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.SectorEconomico;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoCalificacionContratista;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoGarantias;
import sv.gob.mined.apps.siapv2.mvn.modelo.FormaGarantia;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoMultas;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRescision;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwCalificacionContratos;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGarantiasEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwMultasEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwRescisionesEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwTrasladoEmpresa;

/**
 *
 * @author Infosoft
 */
@Service
public class BancoProveedoresBoImpl implements BancoProveedoresBo {

    public BancoProveedoresBoImpl() {
    }
    @Autowired
    private EmpresaDao empresa;
    @Autowired
    private CatalogoFaltasDao tipoFaltas;
    @Autowired
    private TipoGarantiasDao tipoGarantia;
    @Autowired
    private FormaGarantiaDao formaGarantia;    
    @Autowired
    private CalificacionContratoDao calificacionContrato;
    @Autowired
    SectorEconomicoDao sector;
    @Autowired
    private MultasOferenteDao multasOferente;
    @Autowired
    private GarantiasOferenteDao garantiasOferente;
    @Autowired
    private RescisionesOferenteDao rescisionesOferente;
    @Autowired
    private TrasladoEmpresaDao trasladoOferente;
    @Autowired
    private UsuarioDao usuario;
    @Autowired
    PersonaDao persona;
    @Autowired
    DepartamentoDao departamento;
    @Autowired
    MunicipioDao municipio;
    @Autowired
    SectorEconomicoDao sectorEconomico;
    

    @Override
    public List<Empresa> getLstEmpresaByRazonSocial(String razonSocial) {
        return empresa.findEmpresaByRazonSocial(razonSocial);
    }
    
    @Override
    public List<Empresa> getLstEmpresaByNombreComercial(String nombreComercial) {
        return empresa.findEmpresaByNombreComercial(nombreComercial);
    }
    
    @Override
    public List<Empresa> getLstEmpresaByGiro(String giroEmpresa) {
        return empresa.findEmpresaByGiro(giroEmpresa);
    }

    @Override
    public List<Empresa> getLstEmpresaByNit(String numeroDeNit) {
        return empresa.findEmpresaByNit(numeroDeNit);
    }
    
    @Override
    public List<Empresa> getLstEmpresa() {
        return empresa.findEmpresa();
    }
    
    @Override
    public List<Empresa> getLstEmpresaByQueOfrece(Integer idSubSector) {
        return empresa.findEmpresaByQueOfrece(idSubSector);
    }
    
   @Override
    public List<VwCalificacionContratos> getLstFaltasOferente(Integer idOferente) {
        return calificacionContrato.findAllByOferente(idOferente);
    }

    @Override
    public int saveCalificacionContrato(CalificacionContrato falta) {
        calificacionContrato.setCalificacionContrato(falta);
        if (falta.getIdentificadorCalificacion() == null) {
            Integer id = calificacionContrato.create();
            falta.setIdentificadorCalificacion(id);
            return id;
        } else {
            return calificacionContrato.update();
        }
    }

    @Override
    public int saveMultaOferente(MultasOferente multa) {
        multasOferente.setMultaOferente(multa);
        if (multa.getIdentificadorMulta() == null) {
            Integer id = multasOferente.create();
            multa.setIdentificadorMulta(id);
            return id;
        } else {
            return multasOferente.update();
        }
    }
    
   
    @Override
    public int saveGarantiaOferente(GarantiasOferente garantia) {
        garantiasOferente.setGarantiaOferente(garantia);
        if (garantia.getIdentificadorGarantia() == null) {
            Integer id = garantiasOferente.create();
            garantia.setIdentificadorGarantia(id);
            return id;
        } else {
            return garantiasOferente.update();
        }
    }

    @Override
    public int saveRescisionOferente(RescisionesOferente rescision) {
        rescisionesOferente.setRescisionOferente(rescision);
        if (rescision.getIdentificadorRecesion() == null) {
            Integer id = rescisionesOferente.create();
            rescision.setIdentificadorRecesion(id);
            return id;
        } else {
            return rescisionesOferente.update();
        }
    }
    
    @Override
    public int saveUsuario(Usuario usr) {
       usuario.setUsuario(usr);
       
       if (usuario.isExistPersonaByUsuario(usr.getUserName())==null) {
            int i= usuario.guardar(usr);
            
            Persona per = new Persona();        
            per.setPrimerApellido(usr.getPrimerApellido());
            per.setSegundoApellido(usr.getSegundoApellido());
            per.setPrimerNombre(usr.getPrimerNombre());
            per.setSegundoNombre(usr.getSegundoNombre());
            per.setEMail(usr.getEMail());
            per.setUserName(usr.getUserName());
            per.setNumeroTelefono(usr.getTelefono());
            per.setFechaDeInsercion(new Date());
            per.setName("ADMIN");
            per.setEstadoDeEliminacion(0);
            persona.setPersona(per);
            return persona.create();
            
       } else {
           Persona per= persona.findPersonaByUsuario(usr.getUserName());
           persona.setPersona(per);
           persona.updateUsr(usr.getUserName());
            return usuario.actualizar(usr);
            
       }      
    }
    
    @Override
    public int saveEmpresa(Empresa emp) {
        empresa.setEmpresa(emp);
        if (emp.getIdentificadorPrimarioDeLaEmpresa() != null) {
            return empresa.update();
        } else {
            return empresa.create();
        }
    }
      
    @Override
    public List<TipoMultas> getLstTipoMultas() {
        return tipoFaltas.findTipoMultas();
    }

    @Override
    public List<VwMultasEmpresa> getLstMultasOferente(Integer idOferente) {
        return multasOferente.findAllByOferente(idOferente);
    }

    @Override
    public List<VwGarantiasEmpresa> getLstGarantiasOferente(Integer idOferente) {
        return garantiasOferente.findAllByOferente(idOferente);
    }

    @Override
    public List<VwRescisionesEmpresa> getLstRescisionesOferente(Integer idOferente) {
        return rescisionesOferente.findAllByOferente(idOferente);
    }

    @Override
    public List<TipoGarantias> getLstTipoGarantias() {
        return tipoGarantia.findTipoGarantias();
    }

    @Override
    public List<FormaGarantia> getLstFormaGarantia() {
        return formaGarantia.findFormaGarantia();
    }
    
    @Override
    public List<TipoRescision> getLstTipoRescisiones() {
        return tipoFaltas.findTipoRescision();
    }

    
    @Override
    public CalificacionContrato getCalificacionById(Integer id) {
        return calificacionContrato.findById(id);
    }

    @Override
    public GarantiasOferente getGarantiaById(Integer id) {
        return garantiasOferente.findById(id);
    }

    @Override
    public MultasOferente getMultaById(Integer id) {
        return multasOferente.findById(id);
    }
    
    @Override
    public Usuario getUsuarioByUserName(String userName) {
        return usuario.findUsuarioByUserName(userName);
    }

    @Override
    public RescisionesOferente getRescisionById(Integer id) {
        return rescisionesOferente.findById(id);
    }

    @Override
    public int trasladoOferente(Integer identificadorPrimarioDeLaEmpresa, String usuarioSession) {
        return empresa.trasladoOferente(identificadorPrimarioDeLaEmpresa, usuarioSession);
    }

    @Override
    public List<TipoCalificacionContratista> getLstTipoCalificacionContratista() {
        return tipoFaltas.findTipoClasificacion();
    }
    
    @Override
    public List<Usuario> getLstUsuarioByNombreCompleto(String nombreCompleto) {
        return usuario.findUsuarioByNombreCompleto(nombreCompleto);
    }
       
    @Override
    public Usuario isExistPersonaByUsuario(String usuario) {
        return this.usuario.findUsuario(usuario);
    }
    
     @Override
    public Persona findPersonaById(Integer idPersona) {
        return persona.findByPersonaId(idPersona);
    }
     
     @Override
    public Persona findPersonaByUsuarioClave(String usuario, String clave) {
        return persona.findPersonaByUsuarioClave(usuario, clave);
    }
    
    @Override
    public String findRepresentanteByEmpresa(Integer idEmpresa) {
        return empresa.findRepresentanteByEmpresa(idEmpresa);
    }
     
     
    @Override
    public List<SectorEconomico> findAllSubSectorEconomico(Integer idSector) {
        return sector.findAllSub(idSector);
    }
     
     @Override
    public List<Departamento> findAllDepartamentos() {
        return departamento.findAll();
    }
    
    @Override
    public List<Municipio> findAllMunicipios() {
        return municipio.findAll();
    }
    
     @Override
    public List<SectorEconomico> findAllSectorEconomico() {
        return sectorEconomico.findAll();
    }
    
    @Override
    public List<SectorEconomico> findAllSubSector() {
        return sectorEconomico.findAll();
    }

    @Override
    public List<VwTrasladoEmpresa> getLstTrasladoEmpresa() {
        return trasladoOferente.findAllEmpresa();
    }

}