package py.edu.uca.test.web.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


public class DefaultResponseDTO {


    @JsonInclude(NON_EMPTY)
    private Map<String, Object> attributes = new HashMap<String, Object>();
    private String status;
    private List<MessageDTO> messages = new ArrayList<MessageDTO>();

    public DefaultResponseDTO(Map<String, Object> attributes) {
        this();


        setAttributes(attributes);
    }

    public DefaultResponseDTO() {
    }


    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String pStatus) {
        status = pStatus;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> pMessages) {
        messages = pMessages;
    }
}
