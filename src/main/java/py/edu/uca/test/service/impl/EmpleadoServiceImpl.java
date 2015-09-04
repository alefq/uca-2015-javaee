package py.edu.uca.test.service.impl;

import org.springframework.stereotype.Service;

import py.edu.uca.test.service.EmpleadoService;
import py.edu.uca.test.web.dto.EmpleadoDTO;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Override
    public EmpleadoDTO findById(Long number) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setNombre("Guardia");
        // TODO Auto-generated method stub
        return dto;
    }

}
