package py.edu.uca.test.web.response;

import py.edu.uca.test.web.dto.DefaultResponseDTO;
import py.edu.uca.test.web.dto.MessageDTO;
import py.edu.uca.test.web.enums.ErrorCode;
import py.edu.uca.test.web.exceptions.ServiceException;

public class MessageResponse {
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_INFO = "info";
    public static final String LEVEL_INFO = STATUS_INFO;
    public static final String KEY_INFO = STATUS_INFO;
    public static final String KEY_ERROR = STATUS_ERROR;


    public static final String LEVEL_ERROR = "error";

    public static DefaultResponseDTO getSuccessDTO() {
        DefaultResponseDTO defaultResponseDTO = new DefaultResponseDTO();
        defaultResponseDTO.setStatus(STATUS_SUCCESS);
        defaultResponseDTO.setMessages(null);
        return defaultResponseDTO;
    }

    public static DefaultResponseDTO getErrorDTO(ErrorCode errorCode, String message) {
        DefaultResponseDTO defaultResponseDTO = new DefaultResponseDTO();
        defaultResponseDTO.setStatus(STATUS_ERROR);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setKey(getKey(errorCode));
        messageDTO.setLevel(STATUS_ERROR);
        messageDTO.setDescription(message);
        defaultResponseDTO.getMessages().add(messageDTO);
        return defaultResponseDTO;
    }

    public static String getKey(ErrorCode errorCode) {
        String keyResult = null;
        switch (errorCode) {
            case PARAMETER_ERROR:
                keyResult = "InvalidParameters";
                break;
            case INTERNAL_ERROR:
                keyResult = "InternalError";
                break;
            case NO_DATA_FOUND:
                keyResult = "InvalidParameters";
                break;
            case JSON_ENCODING_ERROR:
                keyResult = "InvalidJson";
                break;
            case PERSISTENCE_EXCEPTION:
                keyResult = "DataBaseError";
                break;
            case DATABASE_ERROR:
                keyResult = "DataBaseError";
                break;
            default:
                keyResult = "UnknownError";
                break;
        }
        return keyResult;
    }

    public DefaultResponseDTO getErrorDTO(ErrorCode errorCode, Exception e) {
        return getErrorDTO(errorCode, e.getMessage());
    }

    public DefaultResponseDTO getErrorDTO(ServiceException e) {
        return getErrorDTO(e.getErrorCode(), e.getMessage());
    }
}
