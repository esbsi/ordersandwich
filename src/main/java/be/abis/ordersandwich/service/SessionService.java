package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {


    // repository methods
    void addPersonToSession(Session session, Person person) throws PersonAlreadyInSessionException, NullInputException;
    List<Session> getSessions();
    Session addSession(Session session) throws SessionAlreadyExistsException;
    List<Person> getAllPersonsFromSession(Session session) throws SessionNotFoundException;
    Person findPersonInSession(Session session, int id) throws PersonNotInSessionException, SessionNotFoundException;
    Session findSession(int id) throws SessionNotFoundException;


    List<Session> findSessionsByName(String name) throws SessionNotFoundException;

    List<Session> findSessionsDuring(LocalDate fromDate, LocalDate untilDate) throws SessionNotFoundException;

    List<Session> findSessionsDuring(LocalDate fromDate) throws SessionNotFoundException;

    void removeSession(int id) throws SessionNotFoundException;

    void removeSession(Session session) throws SessionNotFoundException;
}
