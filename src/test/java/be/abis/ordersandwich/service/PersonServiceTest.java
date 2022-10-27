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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    PersonService ps;
    @Autowired
    ShopRepository shopRepository;
    @Mock
    Person person;
    @Mock
    Person person2;
    OrderToday orderToday;

    Shop shop;

    @BeforeEach
    void setUp() throws ShopNotFoundException {
        shop=shopRepository.findShop("Vleugels");
        orderToday=new OrderToday();
        orderToday.setShop(shop);
        orderToday.setClosingTime(LocalTime.MAX);
    }

    @Test
    void order() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        ps.orderSandwich(1,true,true,"",person,orderToday);

        assertEquals(1,orderToday.getOrder().size());
    }

    @Test
    void noorder() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        ps.noOrderToday(person,orderToday);

        assertEquals(1,orderToday.getOrder().size());
    }



}
