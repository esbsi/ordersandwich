package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;

import java.util.List;

public interface PersonService {

    //public void removeMyOrder(Person person, OrderToday orderToday) throws TooLateException, NullInputException;

    List<Person> getPersonList();

    void addPerson(Person person) throws PersonAlreadyInExistException;

    void removePerson(Person person) throws PersonNotFoundException;
    Person findPerson(int id ) throws PersonNotFoundException;
    Person update(Person person) throws PersonNotFoundException;

    Person findPerson(String firstname,String lastname) throws PersonNotFoundException;

    }
