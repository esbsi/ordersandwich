package be.abis.ordersandwich;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.PersonNotInSessionException;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.PersonJpaRepository;
import be.abis.ordersandwich.repository.SessionJpaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SessionTest {
/*    @Autowired
    SessionJpaRepository sessionRepository;
    @Autowired
    PersonJpaRepository personRepository;
    List<Person> personList=new ArrayList<>();

    List<Session> sessionList=new ArrayList<>();
    Session session1;

    @BeforeEach
    void setUp(){
        personList=personRepository.findAll();
        sessionList=sessionRepository.findAll();
        session1=sessionList.get(0);

    }

    @Test
    @Order(1)
    void addPersons() throws PersonAlreadyInSessionException, NullInputException {

        session1.addPerson(personList.get(0));
        session1.addPerson(personList.get(1));
        assertEquals(session1.getPersonList().size(),2);
    }

    @Test
    @Order(2)
    void addPersonThatAlreadyExists() throws PersonAlreadyInSessionException {

        assertThrows(PersonAlreadyInSessionException.class,()->session1.addPerson(personList.get(1)));
    }

    @Test
    @Order(3)
    void remove() throws PersonNotInSessionException, NullInputException {
        session1.removePerson(personList.get(0));
        assertEquals(1,session1.getPersonList().size());
    }

    @Test
    @Order(4)
    void removePersonNotInList() throws PersonNotInSessionException {

        assertThrows(PersonNotInSessionException.class  ,()->session1.removePerson(personList.get(0)));
    }

    @Test
    void nullAdd(){
      assertThrows(NullInputException.class,()->session1.addPerson(null));
    }
    @Test
    void nullRemove(){
        assertThrows(NullInputException.class,()->session1.removePerson(null));
    }
*/
}
