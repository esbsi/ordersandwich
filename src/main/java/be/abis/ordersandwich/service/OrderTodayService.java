package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;

public interface OrderTodayService {
    void orderSandwich(int i, boolean club, boolean white, String comment, Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException;
    void noOrder(Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException, NullInputException;
    void removeOrder(int index,OrderToday orderToday) throws TooLateException, NullInputException;
    double totalPrice(OrderToday orderToday) throws NullInputException;
}
