package py.edu.uca.test.web.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import py.edu.uca.test.web.dto.DefaultResponseDTO;
import py.edu.uca.test.web.dto.MessageDTO;
import py.edu.uca.test.web.enums.ErrorCode;
import py.edu.uca.test.web.response.MessageResponse;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ServiceException extends BusinessException {
    public static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages");
    private static final long serialVersionUID = -1666251053168904093L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceException.class);
    private final ErrorCode errorCode;


    public ServiceException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        LOGGER.error(String.valueOf(errorCode));
    }

    public ServiceException(ErrorCode errorCode, String key) {
        super(getMessageBundle(key, null));
        this.errorCode = errorCode;
    }

    public ServiceException(String msg) {
        super(msg);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }

    public ServiceException(ErrorCode errorCode, String key, Object... param) {
        super(getMessageBundle(key, param));
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }


    private static ResponseEntity getResponseEntity(ServiceException exception, String pErrorMessage, HttpStatus pHttpStatus) {
        DefaultResponseDTO dto = new DefaultResponseDTO();
        dto.setStatus(pErrorMessage);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setDescription(exception.getMessage());
        messageDTO.setKey(MessageResponse.getKey(exception.getErrorCode()));
        dto.getMessages().add(messageDTO);
        return new ResponseEntity<DefaultResponseDTO>((DefaultResponseDTO) dto, pHttpStatus);
    }

    private static String getMessageBundle(String key, Object... params) {
        String messageTemplate = key;
        try {
            String bundleMessage = MESSAGES.getString(key);
            if (bundleMessage != null) {
                messageTemplate = MessageFormat.format(bundleMessage, params);
            }
        } catch (MissingResourceException e) {
            messageTemplate = key;
            LOGGER.warn("ResourceBundle no encontrado: ", e);
        }
        return messageTemplate;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
