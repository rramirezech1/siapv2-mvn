/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.SecurityInfo;

/**
 *
 * @author
 */
public interface SecurityInfoDao {

    public List<SecurityInfo> findAll();

    public int create();

    public int update();

    public void setSecurityInfo(SecurityInfo recurso);

    public SecurityInfo getSecurityInfo();

    public List<SecurityInfo> getSecurityInfoByIdPlantilla(Integer id);

    public void deleteDetalleEdit(Integer id);
}
