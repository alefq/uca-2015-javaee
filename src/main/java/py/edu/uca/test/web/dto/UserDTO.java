package py.edu.uca.test.web.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import py.edu.uca.test.domain.enums.RoleTypeEnum;

/**
 * Created by sodep on 10/06/14.
 */
@ApiModel(value="Datos del usuario", description="Objeto que retorna los datos del usuario")
public class UserDTO {
    private Long id;

    private String mail;
    private String name;
    private String lastname;
    private String password;
    private String state;
    private RoleTypeEnum role;
    private String userCellphone;

    @ApiModelProperty(value="Identificador")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(value="Dirección de correo electronico")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @ApiModelProperty(value="Nombre")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value="Apellido")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @ApiModelProperty(value="Contraseña")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value="Estado del usuario. Los posibles valores son: ACTIVE/INACTIVE (ACTIVO/INACTIVO)")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @ApiModelProperty(value="Rol del usario. Los posibles valores son: ...")
    public RoleTypeEnum getRole() {
        return role;
    }

    public void setRole(RoleTypeEnum role) {
        this.role = role;
    }

    @ApiModelProperty(value="Número de telefono movil")
	public String getUserCellphone() {
		return userCellphone;
	}

	public void setUserCellphone(String userCellphone) {
		this.userCellphone = userCellphone;
	}
}
