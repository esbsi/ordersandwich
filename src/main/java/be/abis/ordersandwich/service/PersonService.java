package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;

import java.util.List;

public interface PersonService {
    //public void removeMyOrder(Person person, OrderToday orderToday) throws TooLateException, NullInputException;
    public List<Integer> checkMyOrderToday(Person person, OrderToday orderToday) throws NullInputException;
    List<Person> getPersonList();

    void addPerson(Person person);

    void removePerson(Person person) throws PersonNotFoundException;

    Person findPerson(String personName) throws PersonNotFoundException;

    }
