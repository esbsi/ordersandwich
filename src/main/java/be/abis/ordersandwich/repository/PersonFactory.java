package be.abis.ordersandwich.repository;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;

import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonFactory implements PersonRepository{

    private static PersonFactory personFactory=new PersonFactory();
    private List<Person> personList=new ArrayList<>();

    private PersonFactory() {
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
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);
        personList.add(p7);
        personList.add(p8);

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


    }

    public static PersonFactory getInstance(){
        return personFactory;
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
