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

    // ToDo clean up (remove sessions)
    public PersonFactory() {
        Session c =new Session("java");
        Session c1=new Session("python");

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
/*
        try {
            c.addPerson(p1);
            c.addPerson(p2);
            c.addPerson(p3);
            c.addPerson(p4);
            c1.addPerson(p5);
            c1.addPerson(p6);
            c1.addPerson(p7);
            c1.addPerson(p8);
        } catch (PersonAlreadyInSessionException e) {
            throw new RuntimeException(e);
        }

 */


    }


    // business

    @Override
    public void addPerson(String personName){
        persons.add(new Person(personName));
    }

    @Override
    public void removePerson(String personName) throws PersonNotFoundException {
        persons.remove(findPerson(personName));
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