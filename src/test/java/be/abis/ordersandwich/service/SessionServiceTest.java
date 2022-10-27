package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    SessionService sessionService;
    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    ShopRepository shopRepository;

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

        shop=shopRepository.findShop("Vleugels");
        orderToday=new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));
        session=sessionService.getSessions().get(0);
        session2=sessionService.getSessions().get(1);

        session.addPerson(person);
        session.addPerson(person2);
        session.addPerson(person3);
        session2.addPerson(person4);


    }

    @Test
    void checkOrdersNobody() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person2,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person2,orderToday);


        assertTrue(sessionService.checkAllOrdered(orderToday,session).startsWith(session.getName()));
    }

    @Test
    void checkOrders() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {

        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person2,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person3,orderToday);


        assertTrue(sessionService.checkAllOrdered(orderToday,session).startsWith("All"));
    }

    @Test
    void checkOrdersotherSession() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {

        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person2,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person3,orderToday);


        assertTrue(sessionService.checkAllOrdered(orderToday,session2).startsWith(session2.getName()));
    }








}
