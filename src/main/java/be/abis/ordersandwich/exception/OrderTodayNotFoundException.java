package be.abis.ordersandwich.exception;

public class OrderTodayNotFoundException extends Exception{

    public OrderTodayNotFoundException() {
        super("Order not found.");
    }

    public OrderTodayNotFoundException(String message) {
        super(message);
    }


}
