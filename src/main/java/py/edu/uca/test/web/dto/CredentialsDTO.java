package py.edu.uca.test.web.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author rodrigo
 */
@ApiModel(value = "Credenciales del usario.", description = "Objeto que retorna las credenciales del usuario")
public class CredentialsDTO {

    private String password;
    private String newPassword;
    private String newPasswordConfirm;

    @ApiModelProperty(value = "Contraseña")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value = "Nueva contraseña")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @ApiModelProperty(value = "Confirmación de la nueva contraseña")
    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

}
