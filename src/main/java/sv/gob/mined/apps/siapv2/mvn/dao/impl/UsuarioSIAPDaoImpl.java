/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.UsuarioSIAPDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;

/**
 *
 * @author Infosoft
 */
@Repository
public class UsuarioSIAPDaoImpl extends XJdbcTemplate2 implements UsuarioSIAPDao {

    public UsuarioSIAPDaoImpl() {
    }
    
    @Override
    public Usuario findUsuarioByNameClave(String userName, String clave){
        
        String passw = encriptarPassw(clave);
        String sql = "SELECT * FROM security_users WHERE name = '" + userName + "' AND password ='"+passw+"'";
        List<Usuario> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
        if(lst !=null && lst.size()>0){
            return lst.get(0);
        }else{ 
            return null;
        }
    }
    
    @Override
    public List<Usuario> findAll() {
        
        String sql = " SELECT security_users.name as userName, g_personal.primer_nombre as primerNombre, g_personal.segundo_nombre as segundoNombre, g_personal.primer_apellido as primerApellido, g_personal.segundo_apellido AS segundoApellido" +
                     " FROM   security_users INNER JOIN\n" +
                     " g_personal ON security_users.personal_id = g_personal.personal_id \n" +
                     " WHERE (security_users.activo=1)";
        
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
    }
    
    @Override
    public void setUsuario(Usuario userName) {
        super.setObjeto(userName);
    }
    
    public static String desencriptarPassw(String paramString)
    {
      String str1 = "CGI";
      String str2 = "";

      int m = 0;
      int n = 0;

      int j = 0;
      int k = str1.length() - 1;

      for (int i = 0; i < paramString.length(); i++)
      {
        m = paramString.charAt(i);
        n = str1.charAt(j);

        m -= n;

        while (m < 0) {
          m += 255;
        }
        char c = (char)m;

        str2 = str2 + c;

        j++;
        if (j > k)
          j = 0;
      }
      return str2;
    }
    
    public static String encriptarPassw(String paramString){
            String str1 = "CGI";
        String str2 = "";
    
        int m = 0;
        int n = 0;

        int j = 0;
        int k = str1.length() - 1;

        for (int i = 0; i < paramString.length(); i++)
        {
          m = paramString.charAt(i);
          n = str1.charAt(j);

          m += n;


          while (m > 255) {
            m -= 255;
          }
          char c = (char)m;

          str2 = str2 + c;

          j++;
          if (j > k)
            j = 0;
        }
        return str2;
    }    
}