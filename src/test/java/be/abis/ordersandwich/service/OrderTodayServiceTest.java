package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.PersonJpaRepository;
import be.abis.ordersandwich.repository.SandwichTypeJpaRepository;
import be.abis.ordersandwich.repository.SessionJpaRepository;
import be.abis.ordersandwich.repository.ShopJpaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTodayServiceTest {

    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    ShopJpaRepository shopRepository;
    @Autowired
    PersonJpaRepository personRepository;
    @Autowired
    SessionJpaRepository sessionRepository;
    @Autowired
    SandwichTypeJpaRepository sandwichTypeRepository;
    @Autowired
    SessionService sessionService;

    Person person;
    Person person2;
    Person person3;
    Person person4;
    OrderToday orderToday;
    Shop shop ;
    List<Person> personList =new ArrayList<>();
    List<Shop> shops=new ArrayList<>();
    List<Session> sessionList=new ArrayList<>();
    Session session;
    Session session2;





    @BeforeEach
    void setUp() throws ShopNotFoundException, PersonAlreadyInSessionException, NullInputException {
        shop=shopRepository.findShop("Vleugels");
        orderToday=new OrderToday(shop);
        orderTodayService.setOrderToday(orderToday);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));

        person=new Person("sim");
        person2=new Person("claus");
        person3=new Person("jana");
        person4=new Person("esben");
        session=sessionService.getSessions().get(0);
        session2=sessionService.getSessions().get(1);

        session.addPerson(person);
        session.addPerson(person2);
        session.addPerson(person3);
        session2.addPerson(person4);



    }

    @AfterEach
    void after(){
        List<Person> list=new ArrayList<>();
        session.setPersonList(list);
        session2.setPersonList(list);
    }

    @Test
    void personrepo(){
        personRepository.findAll();
    }

    @Test
    void order() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.orderSandwich(1,true, false, true,"", person);
        orderTodayService.orderSandwich(1,true,false,true,"", person);
        orderTodayService.orderSandwich(1,true,false,true,"", person2);
        orderTodayService.orderSandwich(1,true,false,true,"", person2);

        assertEquals(4,orderToday.getOrder().size());
    }

    @Test
    void orderTooMany() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.orderSandwich(1,true,false,true,"", person);
        orderTodayService.orderSandwich(1,true,false,true,"", person);

        assertThrows(TooManySandwichesException.class,()->orderTodayService.orderSandwich(1,true,false,true,"", person));
    }

    @Test
    void orderToolate() throws TooLateException, TooManySandwichesException {
        orderToday.setClosingTime(LocalTime.MIN);


        assertThrows(TooLateException.class,()->orderTodayService.orderSandwich(1,true,false,true,"", person));
    }

    @Test
    void nullPerson() throws TooLateException, TooManySandwichesException {

        assertThrows(Exception.class,()->orderTodayService.orderSandwich(1,true,false,true,"", null));
    }

    @Test
    void indexTooHigh(){
        assertThrows(SandwichTypeNotFoundException.class,()->orderTodayService.orderSandwich(10000,true,false,true,"", person));
    }
    @Test
    @Order(2)
    void noOrder() throws TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);
        assertEquals(1,orderToday.getOrder().size());
    }

    @Test
    @Order(4)
    void personAlreadyIn() throws TooLateException, TooManySandwichesException, NullInputException, PersonAlreadyInSessionException {

        Person pers= new Person("simson");
        session.addPerson(pers);
        assertThrows(PersonAlreadyInSessionException.class,()->session.addPerson(pers) );


    }

    @Test
    void noOrderWithexistingorder() throws TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);

        assertThrows(TooManySandwichesException.class,() ->orderTodayService.noOrder(person));
    }

    @Test
    void OrderAfterNoOrder() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.noOrder(person);
        orderTodayService.orderSandwich(1,true,false,true,"",person);
        assertEquals(1,orderToday.getOrder().size());
    }
    // weird cases van noorder kunnen nog gecheckt worden

    @Test
    void removeOrder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);
        orderTodayService.orderSandwich(1,true,false,true,"",person);
        orderTodayService.orderSandwich(2,true,false,true,"",person);
        orderTodayService.removeOrder(orderTodayService.getOrderToday().getOrder().get(1));

        assertEquals(1,orderToday.getOrder().size());
    }

    @Test
    void removeOrderCorrect() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);
        orderTodayService.orderSandwich(1,true,false,true,"",person);
        orderTodayService.orderSandwich(2,true,false,true,"",person);
        orderTodayService.removeOrder(orderTodayService.getOrderToday().getOrder().get(0));

        assertEquals(sandwichTypeRepository.getSandwichTypeByShop(orderToday.getShop()).get(2),orderToday.getOrder().get(0).getSandwichType());
    }
    @Test
    void totalPrice() throws NullInputException {
        assertEquals(0,orderTodayService.totalPrice());
    }

    @Test
    void totalPricefrom2() throws NullInputException, SandwichTypeNotFoundException, TooLateException, TooManySandwichesException {
        orderTodayService.orderSandwich(1,true,false,true,"",person);
        orderTodayService.orderSandwich(2,true,false,true,"",person);
        System.out.println(        session.getPersonList());


        assertEquals(9.12,orderTodayService.totalPrice());
    }

    @Test
    void checkOrdersNobody() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.orderSandwich(1,true,false,true,"",person);
        orderTodayService.orderSandwich(1,true,false,true,"",person2);
        orderTodayService.orderSandwich(1,true,false,true,"",person2);


        assertTrue(orderTodayService.checkAllOrderedString(session).startsWith(session.getName()));
    }

    @Test
    @Order(1)
    void checkOrders() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {

        orderTodayService.orderSandwich(1,true,false,true,"",person);
        orderTodayService.orderSandwich(1,true,false,true,"",person2);
        orderTodayService.orderSandwich(1,true,false,true,"",person3);
        System.out.println(orderTodayService.checkAllOrderedString(session));

        assertTrue(orderTodayService.checkAllOrderedString(session).startsWith("All"));
    }

    @Test
    void checkOrdersOtherSession() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {

        orderTodayService.orderSandwich(1,true,false,true,"",person);
        orderTodayService.orderSandwich(1,true,false,true,"",person2);
        orderTodayService.orderSandwich(1,true,false,true,"",person3);


        assertTrue(orderTodayService.checkAllOrderedString(session2).startsWith(session2.getName()));
    }



}
