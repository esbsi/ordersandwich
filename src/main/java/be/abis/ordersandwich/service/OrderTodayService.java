package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;

public interface OrderTodayService {
    void orderSandwich(int i, boolean club, boolean white, String comment, Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException;
    void noOrder(Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException;
    void removeOrder(int index,OrderToday orderToday) throws TooLateException;
    double totalPrice(OrderToday orderToday);
}
