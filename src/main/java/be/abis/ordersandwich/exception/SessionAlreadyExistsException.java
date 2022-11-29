package be.abis.ordersandwich.exception;

public class SessionAlreadyExistsException extends Exception{

    public SessionAlreadyExistsException() {
        super("Session already exists.");
    }

    public SessionAlreadyExistsException(String message) {
        super(message);
    }

}
