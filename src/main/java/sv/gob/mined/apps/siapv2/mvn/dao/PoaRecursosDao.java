/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.apps.siapv2.mvn.dao;

import java.math.BigDecimal;
import java.util.List;
import sv.gob.mined.apps.siapv2.mvn.modelo.PoaRecursos;

/**
 *
 *
 */
public interface PoaRecursosDao {

    public List<PoaRecursos> findAll();

    public List<PoaRecursos> findAllByActividad(Integer actividad);

    public int create();

    public int update();

    public void setPoaRecursos(PoaRecursos recurso);

    public PoaRecursos getPoaRecursos();

    public String categoriaByRecurso(Integer recurso);

    public String oegByRecurso(Integer recurso);

    public BigDecimal getPorcentajeFtePpal(Integer recurso);
    
    public BigDecimal getPorcentajeByCategoria(Integer idCategoria);
    
    public PoaRecursos getRecursoById(Integer recurso);
    
    public Integer idCategoriaByRecurso(Integer recurso);
    
    public Integer estadoConvenio(Integer convenio);
}