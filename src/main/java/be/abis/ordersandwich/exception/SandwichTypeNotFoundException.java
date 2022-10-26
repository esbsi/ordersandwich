package be.abis.ordersandwich.exception;

public class SandwichTypeNotFoundException extends Exception{

    public SandwichTypeNotFoundException() {
        super("Sandwich type not found.");
    }

    public SandwichTypeNotFoundException(String message) {
        super(message);
    }

}
