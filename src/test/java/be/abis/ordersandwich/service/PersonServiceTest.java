package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.ShopJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    PersonService ps;
    @Autowired
    ShopJpaRepository shopRepository;

    Person person=new Person("sim","sdfsdf");
    Person person2=new Person("esben","sdqfqs");
    @Autowired
    OrderTodayService orderTodayService;


    OrderToday orderToday;

    Shop shop;



    @BeforeEach
    void setUp() throws ShopNotFoundException {
        ps.getPersonList();
        shop=shopRepository.findShopByName("Vleugels");
        orderToday=new OrderToday();
        orderToday.setShop(shop);
        orderTodayService.setOrderToday(orderToday);
        orderToday.setClosingTime(LocalTime.MAX);
    }


    @Test
    @Transactional
    void addPerson() throws PersonAlreadyInExistException, PersonNotFoundException {

        ps.addPerson(person);

        assertEquals(person,ps.findPerson(person.getFirstName() , person.getLastName()));


    }
    @Test
    @Transactional
    void addPersonSameId() throws PersonAlreadyInExistException, PersonNotFoundException {
        Person p = new Person(2,"jkdfjsl","sdfsf");
        ps.addPerson(person);

        assertThrows(PersonAlreadyInExistException.class,()-> ps.addPerson(p));


    }
    @Test
    @Transactional
    void addPersonSameName() throws PersonAlreadyInExistException, PersonNotFoundException {

        Person p = new Person("Jietse","Molenaers");
        ps.addPerson(person);

        assertThrows(PersonAlreadyInExistException.class,()-> ps.addPerson(p));


    }

    @Test
    @Transactional
    void removePerson() throws PersonAlreadyInExistException, PersonNotFoundException {
       Person p= ps.addPerson(person);
        ps.removePerson(p);
    }

    @Test
    @Transactional
    void removePersonWrongName() throws PersonAlreadyInExistException, PersonNotFoundException {
        Person p= ps.addPerson(person);
        Person per=new Person(p.getId(),p.getFirstName(),p.getLastName());
        per.setFirstName("kjfsqdlk");
        assertThrows(PersonNotFoundException.class,()-> ps.removePerson(per));

    }

    @Test
    @Transactional

    void removePersonWrongId() throws PersonAlreadyInExistException, PersonNotFoundException {
        Person p= new Person(8987,"kjsdlkfjds","fkqsdjkfqsdj");


        assertThrows(PersonNotFoundException.class,()-> ps.removePerson(p));
    }

    @Test
    @Transactional

    void removePersonDontMatch() throws PersonAlreadyInExistException, PersonNotFoundException {

        Person per= ps.findPerson(1);
        Person p= new Person(3, per.getFirstName(),per.getLastName());
        assertThrows(PersonNotFoundException.class,()-> ps.removePerson(p));
    }

    @Test
    @Transactional

    void removeByID() throws PersonAlreadyInExistException, PersonNotFoundException {
        int a=ps.getPersonList().size();
         ps.removePerson(4);
         assertEquals(a-1,ps.getPersonList().size());
    }

    @Test
    @Transactional

    void removeBYIDWrong() throws PersonAlreadyInExistException, PersonNotFoundException {


        assertThrows(PersonNotFoundException.class,()-> ps.removePerson(98908));
    }

    @Test
    @Transactional

    void update() throws PersonAlreadyInExistException, PersonNotFoundException {
        Person p =new Person(3,"kjsddf","sqdf");
        ps.update(p);
        assertEquals(ps.findPerson(3).getFirstName(),"kjsddf");
    }

    @Test
    @Transactional

    void updateWrongId() throws PersonAlreadyInExistException, PersonNotFoundException {
        Person p =new Person(38908,"kjsddf","sqdf");

        assertThrows(PersonNotFoundException.class,()-> ps.update(p));
    }
    @Test
    void findPersonID() throws PersonNotFoundException {
        Person p= ps.findPerson(2);
        assertEquals("Jietse",p.getFirstName());
    }


    @Test
    void findPersonIDPersonNoFound() throws PersonNotFoundException {

        assertThrows(PersonNotFoundException.class,()->ps.findPerson(897897));
    }
    @Test
    void findPersonName() throws PersonNotFoundException {
        Person p= ps.findPerson("Jietse","Molenaers");
        assertEquals("Jietse",p.getFirstName());
    }


    @Test
    void findPersonNamePersonNoFound() throws PersonNotFoundException {

        assertThrows(PersonNotFoundException.class,()->ps.findPerson("Jietse","Molenasdfers"));
    }







}
