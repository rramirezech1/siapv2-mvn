/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.Empresa;

/**
 *
 * @author Infosoft
 */
public interface EmpresaDao {

    public List<Empresa> findEmpresaByRazonSocial(String razonSocial);
    
    public List<Empresa> findEmpresaByNombreComercial(String nombreComercial);
    
    public List<Empresa> findEmpresaByGiro(String giroEmpresa);

    public List<Empresa> findEmpresaByNit(String numeroNit);
    
    public List<Empresa> findEmpresa();
    
    public List<Empresa> findEmpresaByQueOfrece(Integer idSubSector);

    public int trasladoOferente(Integer identificadorPrimarioDeLaEmpresa, String usuarioSession);

    public List<Empresa> findbyAll();
    
    public void setEmpresa(Empresa empresa);

    public Empresa getEmpresa();

    public int create();

    public int update();

    public Empresa findByEmpresaId(Integer custId);

    public Empresa findByEmpresaIdOferente(Integer custId);
    
    public String findRepresentanteByEmpresa(Integer idEmpresa);
    
}
