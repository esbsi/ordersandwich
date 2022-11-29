package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.ShopJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    SessionService sessionService;
    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    ShopJpaRepository shopRepository;

    @Test
    void getSessionsSizeShouldBe2(){
        assertEquals(2, sessionService.getSessions().size());
    }

    @Test
    void findSessionsDuringSizeShouldBe1() throws SessionNotFoundException {
        assertEquals(1, sessionService.findSessionsDuring(LocalDate.parse("2022-12-14")).size());
    }

    @Test
    void findSessionsDuringSizeShouldBe2() throws SessionNotFoundException {
        assertEquals(2, sessionService.findSessionsDuring(LocalDate.parse("2022-11-01"), LocalDate.parse("2022-12-14")).size());
    }

    @Test
    void findSessionsDuringShouldThrowNotFound() throws SessionNotFoundException {
        assertThrows(SessionNotFoundException.class, () -> sessionService.findSessionsDuring(LocalDate.parse("2022-11-01"), LocalDate.parse("2022-11-14")).size());
    }

/*
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
*/









}
