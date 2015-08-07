package py.edu.uca.test.web.exceptions;

/**
 * Created by afeltes on 3/8/14.
 */
public class ModelException extends Exception {
    public ModelException() {
    }

    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelException(Throwable cause) {
        super(cause);
    }

}
