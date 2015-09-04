package py.edu.uca.test.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empleado {

    @Id
    private Long id;
    private String nombre;
    
    public Empleado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
