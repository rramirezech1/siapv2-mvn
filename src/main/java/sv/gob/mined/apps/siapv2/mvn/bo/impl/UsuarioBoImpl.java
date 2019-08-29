/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mined.apps.siapv2.mvn.bo.UsuarioBo;
import sv.gob.mined.apps.siapv2.mvn.dao.UsuarioSIAPDao;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;

/**
 *
 * @author Infosoft
 */
@Service
public class UsuarioBoImpl implements UsuarioBo {

    @Autowired
    UsuarioSIAPDao usuSIAPDao;

    @Override
    public Usuario findUsuarioByNameClave(String userName, String clave) {
        return usuSIAPDao.findUsuarioByNameClave(userName, clave);
    }
}