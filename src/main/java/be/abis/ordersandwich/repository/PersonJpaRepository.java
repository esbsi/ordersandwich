package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.PersonNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonJpaRepository extends JpaRepository<Person, Integer> {





    Person findPersonById(int id);
    Person findPersonByFirstNameAndLastName(String firstname,String lastname);


}
