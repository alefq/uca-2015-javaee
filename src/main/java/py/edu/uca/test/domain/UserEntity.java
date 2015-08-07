package py.edu.uca.test.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import py.edu.uca.test.domain.enums.RoleTypeEnum;

import javax.persistence.*;

/**
 * Entidad que representa a un usuario del sistema.
 */
@Entity
@Table(name = "users", schema = "public")
@SequenceGenerator(name = "users_id_seq", sequenceName = "public.users_id_seq")
public class UserEntity extends SodepEntity {

    private Long id;

    private String mail;
    private String name;
    private String lastname;
    private String password;
    private String state;
    private RoleTypeEnum role;

    public UserEntity() {
    }

    public UserEntity(Long sellerId) {
        setId(sellerId);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    @NotNull
    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public RoleTypeEnum getRole() {
        return role;
    }

    public void setRole(RoleTypeEnum role) {
        this.role = role;
    }

    @Transient
    public String getFullname() {
        return lastname + ", " + name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        UserEntity rhs = (UserEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.mail, rhs.mail)
                .append(this.name, rhs.name)
                .append(this.lastname, rhs.lastname)
                .append(this.password, rhs.password)
                .append(this.state, rhs.state)
                .append(this.role, rhs.role)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(mail)
                .append(name)
                .append(lastname)
                .append(password)
                .append(state)
                .append(role)
                .toHashCode();
    }
}
