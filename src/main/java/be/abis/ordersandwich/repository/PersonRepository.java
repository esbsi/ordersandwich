package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.PersonNotFoundException;
import be.abis.ordersandwich.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getPersonList();

    void addPerson(String personName);

    void removePerson(String personName) throws PersonNotFoundException;

    Person findPerson(String personName) throws PersonNotFoundException;
}
