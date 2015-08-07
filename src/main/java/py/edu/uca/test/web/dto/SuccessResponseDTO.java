package py.edu.uca.test.web.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * DTO generico que representa el resultado de una operacion. Permite representar exito o fracaso.
 * Se puede adjuntar un mensaje y atributos
 */
@Api(value = "successResponseDTO", description = "DTO generico que representa el resultado de una operacion. Permite representar exito o fracaso. Se puede adjuntar un mensaje y atributos")
public class SuccessResponseDTO {
    private boolean success;
    @JsonInclude(NON_EMPTY)
    private String message;
    @JsonInclude(NON_EMPTY)
    private Map<String, Object> attributes;

    public SuccessResponseDTO(boolean success, String message, Map<String, Object> attributes) {
        this.success = success;
        this.message = message;
        this.attributes = attributes;
    }

    public SuccessResponseDTO() {
        this.success = true;
        this.attributes = new HashMap<String, Object>();
    }

    public SuccessResponseDTO(boolean p_success, String p_message) {
        setMessage(p_message);
        setSuccess(p_success);
    }

    @ApiModelProperty(value = "Representa un exito o fracaso.")
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @ApiModelProperty(value = "El mensaje de respuesta")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ApiModelProperty(value = "Atributos para enriqueser la respuesta")
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
