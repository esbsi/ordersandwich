package be.abis.ordersandwich.exception;

public class ShopAlreadyExistsException extends Exception{

    public ShopAlreadyExistsException() {
        super("Shop already exists.");
    }

    public ShopAlreadyExistsException(String message) {
        super(message);
    }

}
