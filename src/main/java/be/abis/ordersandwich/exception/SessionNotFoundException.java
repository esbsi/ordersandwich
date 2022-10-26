package be.abis.ordersandwich.exception;

public class SessionNotFoundException extends Exception{

    public SessionNotFoundException() {
        super("Course not found.");
    }

    public SessionNotFoundException(String message) {
        super(message);
    }


}
