/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.CalificacionContrato;
import sv.gob.mined.apps.siapv2.mvn.modelo.Empresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.GarantiasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.MultasOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.Persona;
import sv.gob.mined.apps.siapv2.mvn.modelo.RescisionesOferente;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoCalificacionContratista;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoGarantias;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoMultas;
import sv.gob.mined.apps.siapv2.mvn.modelo.TipoRescision;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;
import sv.gob.mined.apps.siapv2.mvn.modelo.Departamento;
import sv.gob.mined.apps.siapv2.mvn.modelo.Municipio;
import sv.gob.mined.apps.siapv2.mvn.modelo.SectorEconomico;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwCalificacionContratos;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwGarantiasEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwMultasEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwRescisionesEmpresa;
import sv.gob.mined.apps.siapv2.mvn.modelo.view.VwTrasladoEmpresa;

/**
 *
 * @author Infosoft
 */
public interface BancoProveedoresBo {

    public List<Usuario> getLstUsuarioByNombreCompleto(String nombreCompleto);
    
    public List<Empresa> getLstEmpresaByRazonSocial(String razonSocial);
    
    public List<Empresa> getLstEmpresaByNombreComercial(String nombreComercial);
    
    public List<Empresa> getLstEmpresaByGiro(String giroEmpresa);

    public List<Empresa> getLstEmpresaByNit(String numeroDeNit);
    
    public List<Empresa> getLstEmpresa();
    
    public List<Empresa> getLstEmpresaByQueOfrece(Integer idSubSector);
    
    public List<TipoMultas> getLstTipoMultas();
    
    public List<TipoGarantias> getLstTipoGarantias();

    public List<TipoRescision> getLstTipoRescisiones();
    
    public List<TipoCalificacionContratista> getLstTipoCalificacionContratista();

    public List<VwCalificacionContratos> getLstFaltasOferente(Integer idOferente);

    public List<VwMultasEmpresa> getLstMultasOferente(Integer idOferente);

    public List<VwGarantiasEmpresa> getLstGarantiasOferente(Integer idOferente);

    public List<VwRescisionesEmpresa> getLstRescisionesOferente(Integer idOferente);

    public List<VwTrasladoEmpresa> getLstTrasladoEmpresa();
    
    public CalificacionContrato getCalificacionById(Integer id);

    public GarantiasOferente getGarantiaById(Integer id);

    public MultasOferente getMultaById(Integer id);
        
    public Usuario getUsuarioByUserName(String userName);

    public RescisionesOferente getRescisionById(Integer id);

    public int saveMultaOferente(MultasOferente multa);

    public int saveGarantiaOferente(GarantiasOferente garantia);

    public int saveRescisionOferente(RescisionesOferente rescision);
    
    public int saveCalificacionContrato(CalificacionContrato falta);

    public int trasladoOferente(Integer identificadorPrimerioDeLaEmpresa, String usuarioSession);
    
    public int saveUsuario(Usuario usr);
    
    public int saveEmpresa(Empresa emp);
    
    public Usuario isExistPersonaByUsuario(String usuario);
    
    public Persona findPersonaById(Integer idPersona);
    
    public Persona findPersonaByUsuarioClave(String usuario, String clave);
    
    public String findRepresentanteByEmpresa(Integer idEmpresa);
    
    public List<SectorEconomico> findAllSubSectorEconomico(Integer idSector);
    
    public List<Departamento> findAllDepartamentos();
    
    public List<Municipio> findAllMunicipios();
    
    public List<SectorEconomico> findAllSectorEconomico();
    
    public List<SectorEconomico> findAllSubSector();
   
}
