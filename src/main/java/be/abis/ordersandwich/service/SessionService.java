package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {

    Session findSession(int id) throws SessionNotFoundException;
    List<Session> getSessions();
    List<Session> findSessionsByName(String name) throws SessionNotFoundException;
    List<Session> findSessionsDuring(LocalDate fromDate, LocalDate untilDate) throws SessionNotFoundException;
    List<Session> findSessionsDuring(LocalDate fromDate) throws SessionNotFoundException;

    Session addSession(Session session) throws SessionAlreadyExistsException;
    void removeSession(int id) throws SessionNotFoundException;
    void removeSession(Session session) throws SessionNotFoundException;

    List<Person> getAllPersonsFromSession(Session session) throws SessionNotFoundException;
    Person findPersonInSession(Session session, int id) throws PersonNotInSessionException, SessionNotFoundException;
    Session addPersonToSession(Session session, Person person) throws PersonAlreadyInSessionException, NullInputException, SessionNotFoundException;
    Session removePersonFromSession(Session session, Person person) throws NullInputException, SessionNotFoundException, PersonNotInSessionException;
}
