package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.PersonNotInSessionException;
import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;

import java.util.List;

public interface SessionService {


    // repository methods
    void addPersonToSession(Session session, Person person) throws PersonAlreadyInSessionException, NullInputException;
    List<Session> getSessions();
    void addSession(Session session);
    List<Person> getAllPersonsFromSession(Session session) throws SessionNotFoundException;
    Person findPersonInSession(Session session, int id) throws PersonNotInSessionException, SessionNotFoundException;
    Session findSession(int id) throws SessionNotFoundException;


    List<Session> findSessionsByName(String name) throws SessionNotFoundException;
}
