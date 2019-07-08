/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import sv.gob.mined.apps.siapv2.mvn.modelo.Persona;

/**
 *
 * @author Infosoft
 */
public interface PersonaDao {

    public void setPersona(Persona per);

    public Persona getPersona();

    public int create();

    public int update();
    
    public int updateUsr(String usuario);

    public Persona findByPersonaId(Integer custId);

    public Persona findPersonaByUsuarioClave(String usuario, String clave);
    
    public Persona findPersonaByUsuario(String usuario);
        
}
