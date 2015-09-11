package py.edu.uca.test.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.uca.test.domain.Empleado;

/**
 * <p>
 * Repositorio JPA de Spring Data para la entidad Empleado, que representa un
 * parámetro
 * </p>
 *
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findByNombre(String nombreCargo);

    List<Empleado> findByNombreAndCodigo(String nombreCargo, Long codigo);

}
