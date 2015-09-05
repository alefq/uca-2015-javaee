package py.edu.uca.test.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="public",  name="empleado")
@SequenceGenerator(name = "empleado_id_seq", sequenceName = "public.empleado_id_seq")
public class Empleado {


    private Long id;
    private String nombre;
    private Long codigo;
    private Date fechaIngreso;
    private Long nroDeHijos;
    
    public Empleado() {
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "empleado_id_seq")
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

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    

    @Column(name="nro_de_hijos")
    public Long getNroDeHijos() {
        return nroDeHijos;
    }

    public void setNroDeHijos(Long nroDeHijos) {
        this.nroDeHijos = nroDeHijos;
    }
    
    
    
    
}
