package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Shop;
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
public class OrderTodayServiceTest2 {

    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    OrderRepository history;
    Person person=new Person("sim");

    OrderToday orderToday;
    Shop shop;
    Shop shop2;


    @BeforeEach
    void setUp() throws ShopNotFoundException, PersonAlreadyInSessionException, NullInputException {

        shop=shopRepository.findShop("Vleugels");
        shop2=shopRepository.findShop("Pinkys");
        orderToday=new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.parse("18:00:00"));
        orderTodayService.setOrderToday(orderToday);

    }

    @Test
    void sendOrder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        //orderTodayService.orderSandwich(1,true,true,"",person);
        orderTodayService.sendOrder();
    }

    @Test
    void changeShop() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        assertEquals(shop,orderToday.getShop());
    }

}
