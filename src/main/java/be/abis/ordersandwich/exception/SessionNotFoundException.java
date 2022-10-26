package be.abis.ordersandwich.exception;

public class SessionNotFoundException extends Exception{

    public SessionNotFoundException() {
        super("Session not found.");
    }

    public SessionNotFoundException(String message) {
        super(message);
    }


}
