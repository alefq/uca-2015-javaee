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

    /**
     * Filtra automáticamente mediante spring data, por "nombre" de la tabla
     * empleado. El tipo de retorno, indica que esta es una consulta que puede
     * retornar más de un resultado.
     * 
     * @param nombreCargo
     * @return
     */
    List<Empleado> findByNombre(String nombreCargo);

    /**
     * Filtra utilizando spring data por nombre y codigo.
     * @param nombreCargo
     * @param codigo
     * @return
     */
    List<Empleado> findByNombreAndCodigo(String nombreCargo, Long codigo);

}
