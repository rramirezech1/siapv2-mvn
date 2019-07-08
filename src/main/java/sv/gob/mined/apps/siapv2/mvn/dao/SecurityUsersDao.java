/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityUsers;

/**
 *
 * @author
 */
public interface SecurityUsersDao {

    public List<SecurityUsers> findAll();

    public List<SecurityUsers> findByCriteria(String criteria);

    public SecurityUsers findById(Integer id);

    public List<SecurityUsers> findAllUserModifi(Integer id);
}
