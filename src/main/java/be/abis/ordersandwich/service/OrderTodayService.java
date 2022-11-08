package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;

import java.time.LocalTime;
import java.util.List;

public interface OrderTodayService {

    void orderSandwich(int i, boolean club, boolean grilledVegs, boolean white, String comment, Person person) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException;

    void noOrder(Person person) throws TooManySandwichesException, TooLateException, NullInputException;
    void removeOrder(int index) throws TooLateException, NullInputException;
    double totalPrice() throws NullInputException;

    void sendOrder() throws NullInputException;

    List<Integer> checkMyOrderToday(Person person) throws NullInputException;

    void toFile(String writing, boolean bool);

    void setClosingTime(LocalTime closingTime);
    OrderToday getOrderToday();
    void setOrderToday(OrderToday orderToday);

}
