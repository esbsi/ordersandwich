package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.PersonRepository;
import be.abis.ordersandwich.repository.SandwichTypeRepository;
import be.abis.ordersandwich.repository.SessionRepository;
import be.abis.ordersandwich.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OrderTodayServiceTest {

    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    SandwichTypeRepository sandwichTypeRepository;
    @Mock
    Person person2;


    OrderToday orderToday;
    Shop shop ;
    List<Person> personList =new ArrayList<>();
    List<Shop> shops=new ArrayList<>();
    List<Session> sessionList=new ArrayList<>();
    @Mock
    Person person;
    @BeforeEach
    void setUp() throws ShopNotFoundException {
        shop=shopRepository.findShop("Vleugels");
        orderToday=new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));

    }


    @Test
    void order() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.orderSandwich(1,true,true,"", person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"", person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"", person2,orderToday);
        orderTodayService.orderSandwich(1,true,true,"", person2,orderToday);

        assertEquals(4,orderToday.getOrder().size());
    }

    @Test
    void orderTooMany() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.orderSandwich(1,true,true,"", person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"", person,orderToday);

        assertThrows(TooManySandwichesException.class,()->orderTodayService.orderSandwich(1,true,true,"", person,orderToday));
    }

    @Test
    void orderToolate() throws TooLateException, TooManySandwichesException {
        orderToday.setClosingTime(LocalTime.MIN);


        assertThrows(TooLateException.class,()->orderTodayService.orderSandwich(1,true,true,"", person,orderToday));
    }

    @Test
    void nullPerson() throws TooLateException, TooManySandwichesException {

        assertThrows(Exception.class,()->orderTodayService.orderSandwich(1,true,true,"", null,orderToday));
    }

    @Test
    void indexTooHigh(){
        assertThrows(SandwichTypeNotFoundException.class,()->orderTodayService.orderSandwich(10000,true,true,"", person,orderToday));
    }
    @Test
    void noOrder() throws TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person,orderToday);
        assertEquals(1,orderToday.getOrder().size());
    }

    @Test
    void noOrderWithexistingorder() throws TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person,orderToday);

        assertThrows(TooManySandwichesException.class,() ->orderTodayService.noOrder(person,orderToday));
    }

    @Test
    void OrderAfterNoOrder() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.noOrder(person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        assertEquals(1,orderToday.getOrder().size());
    }
    // weird cases van noorder kunnen nog gecheckt worden

    @Test
    void removeOrder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderTodayService.orderSandwich(2,true,true,"",person,orderToday);
        orderTodayService.removeOrder(1,orderToday);

        assertEquals(1,orderToday.getOrder().size());
    }

    @Test
    void removeOrderCorrect() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderTodayService.orderSandwich(2,true,true,"",person,orderToday);
        orderTodayService.removeOrder(0,orderToday);

        assertEquals(sandwichTypeRepository.getSandwichTypes().get(2),orderToday.getOrder().get(0).getSandwichType());
    }
    @Test
    void totalPrice() throws NullInputException {
        assertEquals(0,orderTodayService.totalPrice(orderToday));
    }

    @Test
    void totalPricefrom2() throws NullInputException, SandwichTypeNotFoundException, TooLateException, TooManySandwichesException {
        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderTodayService.orderSandwich(2,true,true,"",person,orderToday);


        assertEquals(9.12,orderTodayService.totalPrice(orderToday));
    }



}
