package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;

import java.util.List;

public interface PersonService {
    public void removeMyOrder(Person person, OrderToday orderToday) throws TooLateException;
    public List<Integer> checkMyOrderToday(Person person, OrderToday orderToday);
    public void noOrderToday(Person person,OrderToday o) throws TooManySandwichesException, TooLateException;
    public void orderSandwich(int pos, boolean club, boolean white, String comment, Person person ,OrderToday orderToday) throws TooManySandwichesException, TooLateException ;


    }
