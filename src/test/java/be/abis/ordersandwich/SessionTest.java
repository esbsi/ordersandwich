package be.abis.ordersandwich;

import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.PersonRepository;
import be.abis.ordersandwich.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SessionTest {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    PersonRepository personRepository;
    List<Person> personList=new ArrayList<>();

    List<Session> sessionList=new ArrayList<>();

    @BeforeEach
    void setUp(){
        personList=personRepository.getPersonList();
        sessionList=sessionRepository.getSessions();

    }

    @Test
    void length(){
        System.out.println(personList.size());
        System.out.println(sessionList.size());
    }

}
