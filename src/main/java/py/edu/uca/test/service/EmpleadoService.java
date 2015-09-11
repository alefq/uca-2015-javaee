package py.edu.uca.test.service;

import java.util.List;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.web.dto.EmpleadoDTO;

public interface EmpleadoService {

    EmpleadoDTO findById(Long number);

    Empleado saveEmpleado(EmpleadoDTO empleadoDTO);

    public List<EmpleadoDTO> findByNombre(String name);

}
