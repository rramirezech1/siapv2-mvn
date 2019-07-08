/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sv.gob.mined.apps.siapv2.mvn.dao.UsuarioDao;
import sv.gob.mined.apps.siapv2.mvn.dao.XJdbcTemplate2;
import sv.gob.mined.apps.siapv2.mvn.modelo.Usuario;

/**
 *
 * @author Infosoft
 */
@Repository
public class UsuarioDaoImpl extends XJdbcTemplate2 implements UsuarioDao {

    public UsuarioDaoImpl() {
    }

    @Override
    public int guardar(Usuario usr) {
        return getJdbcTemplate().update(usr.generarInsertSQL(), usr.getDatosInsert());
    }

   @Override
    public int actualizar(Usuario usr) {
        return getJdbcTemplate().update(usr.generarUpdateSQL(), usr.getDatosUpdate());
    }
    
    @Override
    public Usuario findUsuario(String userName) {
        String sql = "SELECT * FROM USUARIO WHERE userName = '" + userName + "'";
        List<Usuario> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
        //return !lst.isEmpty();
        if(lst !=null && lst.size()>0){
            return lst.get(0);
        }else{ 
            return null;
        }
    }
    
    @Override
    public Usuario findUsuarioByUserName(String userName) {
        String sql = "SELECT * FROM USUARIO WHERE userName = '" + userName + "'";
        List<Usuario> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
        if(lst !=null && lst.size()>0){
            return lst.get(0);
        }else{ 
            return null;
        }
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
    public List<Usuario> findUsuarioByNombreCompleto(String nombreCompleto) {
        nombreCompleto = nombreCompleto.replace(" ","%");
        String sql = "SELECT * FROM dbo.usuario WHERE REPLACE(primerNombre + segundoNombre + primerApellido + segundoApellido, ' ', '') LIKE  '%"+nombreCompleto+"%'";
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
    }
    
        
    @Override
    public Boolean comprobarRespuestas(String userName, String respuesta1, String respuesta2) {
        String sql = "SELECT * FROM USUARIO WHERE userName = '" + userName + "' and respuesta1 = '"+respuesta1+"' and respuesta2 = '"+respuesta2+"'";
        List<Usuario> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
        return !lst.isEmpty();
    }

    @Override
    public Usuario findUsuarioByDui(String numeroDeDui) {
        String sql = "SELECT     dbo.usuario.* "
                + "FROM         dbo.usuario INNER JOIN  dbo.Persona ON dbo.usuario.userName = dbo.Persona.userName "
                + "WHERE     (dbo.Persona.numeroDocumentoLegal = '" + numeroDeDui + "')";
        List<Usuario> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
        if(lst !=null && lst.size()>0){
            return lst.get(0);
        }else{ 
            return null;
        }
    } 
    
     @Override
    public Usuario findUsuarioByEmail(String mail) {
        String sql = "SELECT     dbo.usuario.* "
                + "FROM         dbo.usuario INNER JOIN  dbo.Persona ON dbo.usuario.userName = dbo.Persona.userName "
                + "WHERE     (dbo.Persona.numeroDocumentoLegal = '" + mail + "')";
        List<Usuario> lst = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
        if(lst !=null && lst.size()>0){
            return lst.get(0);
        }else{ 
            return null;
        }
    } 
    
    
    @Override
    public void setUsuario(Usuario userName) {
        super.setObjeto(userName);
    }
    
    @Override
    public Usuario isExistPersonaByUsuario(String usuario) {
        return this.findUsuario(usuario);
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