package py.edu.uca.test.web.exceptions;

/**
 * Created by sodep on 17/01/14.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Entity not created")
public class EntityNotCreatedException extends RuntimeException {

    public EntityNotCreatedException(Exception e) {
        super(e);
    }
}
