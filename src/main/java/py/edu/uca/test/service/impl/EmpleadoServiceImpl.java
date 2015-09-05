package py.edu.uca.test.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import py.edu.uca.test.domain.Empleado;
import py.edu.uca.test.service.EmpleadoService;
import py.edu.uca.test.web.dto.EmpleadoDTO;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }

    private EntityManager getEntityManager() {
        return  entityManagerFactory.createEntityManager();
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
    public Empleado saveEmpleado(EmpleadoDTO empleadoDTO) throws PersistenceException{
        Empleado empleadoEntity = new Empleado();
        BeanUtils.copyProperties(empleadoDTO, empleadoEntity);
        EntityManager entityManager = getEntityManager();
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        try {
           entityManager.persist(empleadoEntity);
        }
        catch (PersistenceException up) {
            t.rollback();
            throw up;
        }
        finally{
            try {
               if(t.isActive())
               {
                    t.commit();
               }
            } catch (Exception ex) {
               t.rollback();
            }
        }
        return empleadoEntity;
    }
    
    

}
