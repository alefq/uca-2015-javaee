package py.edu.uca.test.service;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.web.dto.EmpleadoDTO;

public interface EmpleadoService {

    EmpleadoDTO findById(Long number);

    Empleado saveEmpleado(EmpleadoDTO empleadoDTO);

}
