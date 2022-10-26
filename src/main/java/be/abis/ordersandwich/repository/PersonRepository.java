package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getPersonList();
}
