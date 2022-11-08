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
        orderTodayService.setOrderToday(orderToday);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));

    }


    @Test
    void order() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.orderSandwich(1,true,true,"", person);
        orderTodayService.orderSandwich(1,true,true,"", person);
        orderTodayService.orderSandwich(1,true,true,"", person2);
        orderTodayService.orderSandwich(1,true,true,"", person2);

        assertEquals(4,orderToday.getOrder().size());
    }

    @Test
    void orderTooMany() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.orderSandwich(1,true,true,"", person);
        orderTodayService.orderSandwich(1,true,true,"", person);

        assertThrows(TooManySandwichesException.class,()->orderTodayService.orderSandwich(1,true,true,"", person));
    }

    @Test
    void orderToolate() throws TooLateException, TooManySandwichesException {
        orderToday.setClosingTime(LocalTime.MIN);


        assertThrows(TooLateException.class,()->orderTodayService.orderSandwich(1,true,true,"", person));
    }

    @Test
    void nullPerson() throws TooLateException, TooManySandwichesException {

        assertThrows(Exception.class,()->orderTodayService.orderSandwich(1,true,true,"", null));
    }

    @Test
    void indexTooHigh(){
        assertThrows(SandwichTypeNotFoundException.class,()->orderTodayService.orderSandwich(10000,true,true,"", person));
    }
    @Test
    void noOrder() throws TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);
        assertEquals(1,orderToday.getOrder().size());
    }

    @Test
    void noOrderWithexistingorder() throws TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);

        assertThrows(TooManySandwichesException.class,() ->orderTodayService.noOrder(person));
    }

    @Test
    void OrderAfterNoOrder() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.noOrder(person);
        orderTodayService.orderSandwich(1,true,true,"",person);
        assertEquals(1,orderToday.getOrder().size());
    }
    // weird cases van noorder kunnen nog gecheckt worden

    @Test
    void removeOrder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);
        orderTodayService.orderSandwich(1,true,true,"",person);
        orderTodayService.orderSandwich(2,true,true,"",person);
        orderTodayService.removeOrder(1);

        assertEquals(1,orderToday.getOrder().size());
    }

    @Test
    void removeOrderCorrect() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.noOrder(person);
        orderTodayService.orderSandwich(1,true,true,"",person);
        orderTodayService.orderSandwich(2,true,true,"",person);
        orderTodayService.removeOrder(0);

        assertEquals(sandwichTypeRepository.getSandwichTypes().get(2),orderToday.getOrder().get(0).getSandwichType());
    }
    @Test
    void totalPrice() throws NullInputException {
        assertEquals(0,orderTodayService.totalPrice());
    }

    @Test
    void totalPricefrom2() throws NullInputException, SandwichTypeNotFoundException, TooLateException, TooManySandwichesException {
        orderTodayService.orderSandwich(1,true,true,"",person);
        orderTodayService.orderSandwich(2,true,true,"",person);


        assertEquals(9.12,orderTodayService.totalPrice());
    }



}
