package py.edu.uca.test.service;

import java.util.List;

import py.edu.uca.test.web.dto.ParameterDTO;

/**
 * Servicio para manipular y obtener parametros
 */
public interface ParameterService {

    /**
     * Encontrar todos los parametros
     * @return
     */
	public List<ParameterDTO> findAll();


    /**
     * Encontrar por estado
     * @param p_goal
     * @return
     */
    public ParameterDTO findByLabel(String p_goal);

    /**
     * Actualizar por label
     * @param label
     * @param value
     * @return
     */
    public ParameterDTO updateValueByLabel(String label, String value);

    /**
     * Obtener string por label
     * @param label
     * @return
     */
    public String getStringByLabel(String label);
    
}
