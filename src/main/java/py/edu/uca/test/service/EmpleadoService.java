package py.edu.uca.test.service;

import java.util.List;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.web.dto.EmpleadoDTO;

public interface EmpleadoService {

    EmpleadoDTO findById(Long numScheduledber);

    /**
     * Versión con JPA puro, utilizando el EntityManager, abriendo y cerrando
     * transacciones manualmente
     * 
     * @param empleadoDTO
     * @return
     */
    Empleado saveEmpleado(EmpleadoDTO empleadoDTO);

    /**
     * Persiste un empleado utilizando spring data
     * 
     * @param empleadoDTO
     * @return
     */
    Empleado saveEmpleado2(EmpleadoDTO empleadoDTO);

    /**
     * Retorna una lista de empleados, por nombre del cargo. Utiliza Spring data
     * 
     * @param name
     * @return
     */
    public List<EmpleadoDTO> findByNombre(String name);

    void updateEmpleado(EmpleadoDTO dto);

    long count();

    List<Empleado> findAll();

}
