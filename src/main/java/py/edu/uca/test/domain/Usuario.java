package py.edu.uca.test.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@SequenceGenerator(name = "usuarios_id_seq", sequenceName = "public.usuarios_id_seq")
public class Usuario implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7398248526814716709L;
    private Long id;
    private String username;
    private String passwordkey;
    private Persona persona;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarios_id_seq")
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordkey() {
        return passwordkey;
    }

    public void setPasswordkey(String passwordkey) {
        this.passwordkey = passwordkey;
    }

    /**
     * Ejemplo de una relación (foreign key) a la tabla/entity Persona
     * 
     * @return
     */
    @OneToOne
    @JoinColumn(name = "id")
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setId(Long id) {
        this.id = id;
    }

}