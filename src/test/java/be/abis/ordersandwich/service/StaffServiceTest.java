package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.OrderHistory;
import be.abis.ordersandwich.repository.OrderRepository;
import be.abis.ordersandwich.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StaffServiceTest {

    @Autowired
    StaffService staffService;
    @Autowired
    OrderTodayService orderTodayService;

    @Autowired
    ShopRepository shopRepository;
    @Autowired
    OrderRepository history;
    Person person=new Person("sim");
    Person person2=new Person("claus");
    Person person3=new Person("jana");
    Person person4=new Person("esben");

    OrderToday orderToday;
    Shop shop;
    Shop shop2;


    @BeforeEach
    void setUp() throws ShopNotFoundException, PersonAlreadyInSessionException, NullInputException {

        shop=shopRepository.findShop("Vleugels");
        shop2=shopRepository.findShop("Pinkys");
        orderToday=new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));


    }

    @Test
    void sendOrder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        //orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderToday = staffService.sendOrder(orderToday, history, shop2);
        assertEquals(shop2,orderToday.getShop());
    }

    @Test
    void changeShop() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        assertEquals(shop2,orderToday.getShop());
    }

    @Test
    void sendOrderWithnull() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderToday = staffService.sendOrder(orderToday, history, shop2);
        assertThrows(NullInputException.class,()->staffService.sendOrder(orderToday, history, null));
    }


}
