package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.PersonNotFoundException;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonFactory implements PersonRepository{
    
    private List<Person> persons=new ArrayList<>();

    public PersonFactory() {
        Person p1=new Person("sim");
        Person p2=new Person("jana");
        Person p3=new Person("esben");
        Person p4=new Person("marcel");
        Person p5=new Person("jens");
        Person p6=new Person("kim");
        Person p7=new Person("quintin");
        Person p8=new Person("claudia");
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);
        persons.add(p6);
        persons.add(p7);
        persons.add(p8);
    }


    // business

    @Override
    public void addPerson(Person person){
        persons.add(person);
    }

    @Override
    public void removePerson(Person person) throws PersonNotFoundException {
        if(!persons.remove(person)){
            throw new PersonNotFoundException();
        }
    }

    @Override
    public Person findPersonById(int id) throws PersonNotFoundException {
        return persons.stream()
                .filter(person -> id == person.getId())
                .findFirst().orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person findPerson(String personName) throws PersonNotFoundException {
        return persons.stream()
                .filter(person -> personName.equals(person.getName()))
                .findFirst().orElseThrow(PersonNotFoundException::new);
    }


    // getset

    @Override
    public List<Person> getPersonList() {
        return persons;
    }
    
}