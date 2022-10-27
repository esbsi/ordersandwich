package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.PersonNotFoundException;
import be.abis.ordersandwich.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getPersonList();

    void addPerson(Person person);

    void removePerson(Person person) throws PersonNotFoundException;

    Person findPerson(String personName) throws PersonNotFoundException;
}
