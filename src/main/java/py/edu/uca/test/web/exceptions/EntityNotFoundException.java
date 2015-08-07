package py.edu.uca.test.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sodep on 17/01/14.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such entity")
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {

    }
}
