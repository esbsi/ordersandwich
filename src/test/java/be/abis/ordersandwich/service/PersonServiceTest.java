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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void removePerson(){

    }
    @Test
    void findPerson() throws PersonNotFoundException {
        Person p= ps.findPerson(2);
        assertEquals("Jietse",p.getFirstName());
    }







}
