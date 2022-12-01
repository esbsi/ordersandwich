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
import org.springframework.transaction.annotation.Transactional;

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
    PersonService personService;

    @Test
    void getSessionsSizeShouldBe2(){
        assertEquals(3, sessionService.getSessions().size());
    }

    @Test
    void findSessionsDuringSizeShouldBe1() throws SessionNotFoundException {
        assertEquals(2, sessionService.findSessionsDuring(LocalDate.parse("2022-12-14")).size());
    }

    @Test
    void findSessionsDuringSizeShouldBe2() throws SessionNotFoundException {
        assertEquals(3, sessionService.findSessionsDuring(LocalDate.parse("2022-11-01"), LocalDate.parse("2022-12-14")).size());
    }

    @Test
    void findSessionsDuringShouldThrowNotFound() throws SessionNotFoundException {
        assertThrows(SessionNotFoundException.class, () -> sessionService.findSessionsDuring(LocalDate.parse("2022-11-01"), LocalDate.parse("2022-11-14")));
    }

    @Test
    void findSessionShouldBeIntroSQL() throws SessionNotFoundException {
        assertEquals("Intro SQL", sessionService.findSession(1).getName());
    }

    @Test
    void findSessionsByNameShouldBe1() throws SessionNotFoundException {
        assertEquals(1, sessionService.findSessionsByName("Intro SQL")
                .stream().findFirst().orElseThrow(SessionNotFoundException::new).getId());
    }

    @Transactional
    @Test
    void addSessionSizeShouldBePlus1() throws SessionAlreadyExistsException {
        int size = sessionService.getSessions().size();
        Session session = new Session("Carbon Aware SDK", LocalDate.parse("2023-11-01"), LocalDate.parse("2023-11-01"));
        sessionService.addSession(session);
        assertEquals(size+1, sessionService.getSessions().size());
    }

    @Transactional
    @Test
    void addSessionShouldThrowExists() throws SessionAlreadyExistsException {
        Session session = new Session("Carbon Aware SDK", LocalDate.parse("2023-11-01"), LocalDate.parse("2023-11-01"));
        sessionService.addSession(session);
        assertThrows(SessionAlreadyExistsException.class, () -> sessionService.addSession(session));
    }

    @Transactional
    @Test
    void removeSessionById() throws SessionNotFoundException {
        sessionService.removeSession(3);
        assertEquals(2, sessionService.getSessions().size());
    }

    @Transactional
    @Test
    void removeSession() throws SessionNotFoundException {
        sessionService.removeSession(sessionService.findSession(3));
        assertEquals(2, sessionService.getSessions().size());
    }

    @Transactional
    @Test
    void removeSessionByIdShouldThrowNotFound() throws SessionNotFoundException {
        assertThrows(SessionNotFoundException.class, () -> sessionService.removeSession(99999999));
    }


    //Participant list tests

    @Test
    void getAllPersonsFromSessionSizeShouldBe() throws SessionNotFoundException {
        assertEquals(2, sessionService.getAllPersonsFromSession(sessionService.findSession(1)).size());
    }

    @Transactional
    @Test
    void addPerson() throws SessionNotFoundException, PersonNotFoundException, PersonAlreadyInSessionException, NullInputException {
        Session session = sessionService.findSession(2);
        int size = sessionService.getAllPersonsFromSession(session).size();
        Person person = personService.findPerson(2);
        sessionService.addPersonToSession(session, person);
        assertEquals(size+1, sessionService.getAllPersonsFromSession(session).size());
    }

    @Transactional
    @Test
    void addPersonThatAlreadyExists() throws PersonAlreadyInSessionException, NullInputException, SessionNotFoundException, PersonNotFoundException {
        Session session = sessionService.findSession(2);
        Person person = personService.findPerson(2);
        sessionService.addPersonToSession(session, person);
        assertThrows(PersonAlreadyInSessionException.class, ()-> sessionService.addPersonToSession(session, person));
    }

    @Transactional
    @Test
    void addNullPerson() throws SessionNotFoundException, PersonAlreadyInSessionException, NullInputException {
        Session session = sessionService.findSession(2);
        Person person = null;
        assertThrows(NullInputException.class, ()-> sessionService.addPersonToSession(session, person));
    }

    @Transactional
    @Test
    void removePerson() throws NullInputException, PersonNotInSessionException, SessionNotFoundException, PersonNotFoundException {
        Session session = sessionService.findSession(2);
        int size = sessionService.getAllPersonsFromSession(session).size();
        Person person = personService.findPerson(1);
        sessionService.removePersonFromSession(session, person);
        assertEquals(size-1, sessionService.getAllPersonsFromSession(session).size());
    }

    @Transactional
    @Test
    void removePersonNotInList() throws SessionNotFoundException, PersonNotFoundException {
        Session session = sessionService.findSession(2);
        Person person = personService.findPerson(2);
        assertThrows(PersonNotInSessionException.class, ()-> sessionService.removePersonFromSession(session, person));
    }

    @Transactional
    @Test
    void removeNullPerson() throws SessionNotFoundException {
        Session session = sessionService.findSession(2);
        Person person = null;
        assertThrows(NullInputException.class, ()-> sessionService.removePersonFromSession(session, person));
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
