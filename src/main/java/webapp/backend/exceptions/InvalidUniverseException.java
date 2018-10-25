package webapp.backend.exceptions;
/**
 *
 * @author lbennette
 */
public class InvalidUniverseException extends Exception {
    public InvalidUniverseException(String message) {
        super(message);
    }
    public InvalidUniverseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
