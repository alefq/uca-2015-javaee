package py.edu.uca.test.web.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by sodep on 26/03/14.
 */
@ApiModel(value = "Perfil del usario.", description = "Objeto que retorna el perfil del usuario")
public class UserProfileDTO {

    /**
     * Información del usuario.
     */
    private String userFullname;
    private Long userId;
    private String name;
    private String lastname;
    private String userCellphone;
    private String mail;


    @ApiModelProperty(value = "Dirección de correo electrónico")
    public String getMail() {
        return mail;
    }

    public void setMail(String userMail) {
        this.mail = userMail;
    }

    @ApiModelProperty(value = "Nombre completo")
    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    @ApiModelProperty(value = "Identificador")
    public Long getUserId() {
        return this.userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "Nombre")
    public String getName() {
        return this.name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @ApiModelProperty(value = "Apellido")
    public String getLastname() {
        return this.lastname;
    }

    public void setUserCellphone(String userCellphone) {
        this.userCellphone = userCellphone;
    }

    @ApiModelProperty(value = "Número de telefono movil personal")
    public String getUserCellphone() {
        return this.userCellphone;
    }


}
