package py.edu.uca.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.domain.Usuario;
import py.edu.uca.test.service.EmpleadoService;
import py.edu.uca.test.web.dto.EmpleadoDTO;
import py.edu.uca.test.web.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private EmpleadoRepository empleadoRepository;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public EmpleadoDTO findById(Long id) {
        EmpleadoDTO dto = new EmpleadoDTO();
        Empleado empleado = getEntityManager().find(Empleado.class, id);
        if (empleado != null) {
            System.out.println("empleado: " + empleado.getNombre());
            BeanUtils.copyProperties(empleado, dto);
        }
        Usuario usuario = getEntityManager().find(Usuario.class, 1l);
        System.out.println(usuario);
        return dto;
    }

    @Override
    public Empleado saveEmpleado(EmpleadoDTO empleadoDTO) throws PersistenceException {
        Empleado empleadoEntity = new Empleado();
        BeanUtils.copyProperties(empleadoDTO, empleadoEntity);
        EntityManager entityManager = getEntityManager();
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        try {
            entityManager.persist(empleadoEntity);
        } catch (PersistenceException up) {
            t.rollback();
            throw up;
        } finally {
            try {
                if (t.isActive()) {
                    t.commit();
                }
            } catch (Exception ex) {
                t.rollback();
            }
        }
        return empleadoEntity;
    }

    public List<EmpleadoDTO> findByNombre(String name) {
        List<EmpleadoDTO> dtos = new ArrayList<EmpleadoDTO>();
        List<Empleado> empleados = empleadoRepository.findByNombre(name);
        if (empleados != null && !empleados.isEmpty()) {
            for (Empleado emp : empleados) {
                EmpleadoDTO dto = new EmpleadoDTO();
                BeanUtils.copyProperties(emp, dto);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Transactional
    @Override
    public Empleado saveEmpleado2(EmpleadoDTO empleadoDTO) {
        Empleado empleadoEntity = new Empleado();
        BeanUtils.copyProperties(empleadoDTO, empleadoEntity);
        empleadoRepository.save(empleadoEntity);
        return empleadoEntity;
    }

    @Transactional
    @Override
    public void updateEmpleado(EmpleadoDTO dto) {
        if(dto != null && dto.getId() != null) {
        Empleado empleadoEntity = empleadoRepository.findOne(dto.getId());
        BeanUtils.copyProperties(dto, empleadoEntity);
        } else {
            throw new RuntimeException("Se necesita un dto válido con ID");
        }
     
    }
}
