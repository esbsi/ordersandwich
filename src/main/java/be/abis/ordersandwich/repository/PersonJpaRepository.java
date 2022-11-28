package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.PersonNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonJpaRepository extends JpaRepository<Person, Integer> {
    List<Person> getPersonList();

    void addPerson(Person person);

    void removePerson(Person person) throws PersonNotFoundException;

    Person findPersonById(int id) throws PersonNotFoundException;

    Person findPerson(String personName) throws PersonNotFoundException;
}
