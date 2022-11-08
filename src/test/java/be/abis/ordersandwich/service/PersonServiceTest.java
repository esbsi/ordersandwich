package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Shop;
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

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    PersonService ps;
    @Autowired
    ShopRepository shopRepository;

    Person person=new Person("sim");
    Person person2=new Person("esben");
    @Autowired
    OrderTodayService orderTodayService;

    OrderToday orderToday;

    Shop shop;

    @BeforeEach
    void setUp() throws ShopNotFoundException {
        shop=shopRepository.findShop("Vleugels");
        orderToday=new OrderToday();
        orderToday.setShop(shop);
        orderTodayService.setOrderToday(orderToday);
        orderToday.setClosingTime(LocalTime.MAX);
    }

    @Test
    void checkOrder() throws TooLateException, TooManySandwichesException, NullInputException, SandwichTypeNotFoundException {

        orderTodayService.orderSandwich(1,true,true,"",person);
        orderTodayService.orderSandwich(1,true,true,"",person2);
        orderTodayService.orderSandwich(1,true,true,"",person);
        List<Integer> integerList=new ArrayList<>();
        integerList.add(0);
        integerList.add(2);

        assertEquals(integerList,ps.checkMyOrderToday(person,orderToday));

    }

    @Test
    void addPerson(){


    }

    @Test
    void removePerson(){

    }
    @Test
    void findPerson(){

    }







}
