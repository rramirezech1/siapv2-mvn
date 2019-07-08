/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.UsuarioBo;
import sv.gob.mined.apps.siapv2.mvn.dao.UsuarioDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;

/**
 *
 * @author Infosoft
 */
@Service
public class UsuarioBoImpl implements UsuarioBo {

    @Autowired
    UsuarioDao usuDao;

    @Override
    public Usuario findUsuarioByNameClave(String userName, String clave) {
        return usuDao.findUsuarioByNameClave(userName, clave);
    }

    @Override
    public Usuario findUsuarioByDui(String dui) {
        return usuDao.findUsuarioByDui(dui);
    }
    
    @Override
    public Usuario findUsuarioByEmail(String mail) {
        return usuDao.findUsuarioByEmail(mail);
    }
}