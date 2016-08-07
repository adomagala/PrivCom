package pl.privcom.infrastructure.exceptions;

/**
 * Created by Aleksander Domagała on 07/07/2016.
 */
public abstract class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(final String message) {
        super(message);
    }

    public DAOException(final String message, Throwable cause) {
        super(message, cause);
    }
}
