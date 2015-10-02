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

    /**
     * Si se utiliza JPA puro, es necesario un EntityManagerFactory
     */
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Los repositories de spring-data son casos especiales de Injección de
     * dependencias. Se utiliza el annotation Resource
     */
    @Resource
    private EmpleadoRepository empleadoRepository;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }

    /**
     * Cada vez que se necesita utilizar una instancia del EntityManager, se
     * invoca el método de creación del EntityManagerFactory
     * 
     * @return
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * py.edu.uca.test.service.EmpleadoService#updateEmpleado(py.edu.uca.test.
     * web.dto.EmpleadoDTO) Este método, utilizando el annotation Transactional
     * ya le indica al spring framework que necesitamos una transacción. Se
     * realiza automáticamente si todo fue bien, un commit. Caso contrario se
     * realiza el rollback. En los casos de actualización, todo lo que se
     * modifique en un entity traído de la base de datos automáticamente al
     * terminar la llamada se realiza el update a la base de datos.
     */
    @Transactional
    @Override
    public void updateEmpleado(EmpleadoDTO dto) {
        if (dto != null && dto.getId() != null) {
            // Traemos el empleado que queremos modificar.
            Empleado empleadoEntity = empleadoRepository.findOne(dto.getId());
            // Actualizamos los datos que provienen en este ejemplo, de la capa
            // Web.
            // ****************************************************************
            // OBSERVACION IMPORTANTE: El copyProperties es una facilidad, NO
            // garantiza que se van a copiar todas las propiedades
            // Copiará correctamente los campos que coincidan en nombre y tipo
            // de dato. El resto, deberá copiarse manualmente.
            // ****************************************************************
            BeanUtils.copyProperties(dto, empleadoEntity);
        } else {
            throw new RuntimeException("Se necesita un dto válido con ID");
        }

    }

    @Override
    public long count() {
        return empleadoRepository.count();
    }
}
