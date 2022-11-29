package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.SandwichOrderJpaRepository;
import be.abis.ordersandwich.repository.ShopJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
public class SandwichOrderTest {

    @Autowired
    SessionService sessionService;
    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    ShopJpaRepository shopRepository;

    @Autowired
    SandwichOrderJpaRepository sandwichOrderJpaRepository;

    Person person=new Person("sim");

    Person person2=new Person("claus");

    Person person3=new Person("jana");

    Person person4=new Person("esben");

    OrderToday orderToday;
    Shop shop;
    Session session;
    Session session2;

    @BeforeEach
    void setUp() throws ShopNotFoundException, PersonAlreadyInSessionException, NullInputException {

        shop=shopRepository.findShopByName("Vleugels");
        orderToday=new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));
        session=sessionService.getSessions().get(0);
        session2=sessionService.getSessions().get(1);
        orderTodayService.setOrderToday(orderToday);

        session.addPerson(person);
        session.addPerson(person2);
        session.addPerson(person3);
        session2.addPerson(person4);

    }

    @Test
    public void findAll(){
        sandwichOrderJpaRepository.findAll();
    }










}
