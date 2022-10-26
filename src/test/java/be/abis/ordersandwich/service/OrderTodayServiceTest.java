package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.PersonRepository;
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


    OrderToday orderToday;
    Shop shop ;
    List<Person> personList =new ArrayList<>();
    List<Shop> shops=new ArrayList<>();
    List<Session> sessionList=new ArrayList<>();
    @Mock
    Person person;
    @BeforeEach
    void setUp(){
        shop=shopRepository.findShop("Vleugels");
        orderToday=new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));

    }


    @Test
    void order() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {
        orderTodayService.orderSandwich(1,true,true,"", person,orderToday);

        assertEquals(1,orderToday.getOrder().size());
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
    void indexToohigh(){
        assertThrows(SandwichTypeNotFoundException.class,()->orderTodayService.orderSandwich(10000,true,true,"", person,orderToday));
    }


}
