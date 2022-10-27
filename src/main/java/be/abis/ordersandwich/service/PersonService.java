package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;

import java.util.List;

public interface PersonService {
    public void removeMyOrder(Person person, OrderToday orderToday) throws TooLateException, NullInputException;
    public List<Integer> checkMyOrderToday(Person person, OrderToday orderToday);
    

    }
