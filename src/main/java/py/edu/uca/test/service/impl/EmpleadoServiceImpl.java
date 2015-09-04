package py.edu.uca.test.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.service.EmpleadoService;
import py.edu.uca.test.web.dto.EmpleadoDTO;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public EmpleadoDTO findById(Long id) {
        EmpleadoDTO dto = new EmpleadoDTO();
        Empleado empleado = entityManager.find(Empleado.class, id);
        if (empleado != null) {
            System.out.println("empleado: " + empleado.getNombre());
            BeanUtils.copyProperties(empleado, dto);
        }
        return dto;
    }

}
