package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.SessionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessionServiceImp implements SessionService{

    @Autowired
    SessionJpaRepository sessionRepository;


    // repository methods

    @Override
    public List<Session> getSessions(){
        return sessionRepository.findAll();
    }

    @Override
    public Session findSession(int id) throws SessionNotFoundException {
        Session s= sessionRepository.findSessionById(id);
        if (s==null) throw new SessionNotFoundException("session not found");
        return s;
    }

    @Override
    public List<Session> findSessionsByName(String name) throws SessionNotFoundException {
        List<Session> sessions = sessionRepository.findSessionsByName(name);
        if (sessions == null) throw new SessionNotFoundException("session not found");
        return sessions;
    }

    @Override
    public List<Session> findSessionsDuring(LocalDate fromDate, LocalDate untilDate) throws SessionNotFoundException {
        List<Session> sessions = sessionRepository.findSessionsDuring(fromDate, untilDate);
        if (sessions.size() == 0) throw new SessionNotFoundException("No sessions found.");
        return sessions;
    }

    @Override
    public List<Session> findSessionsDuring(LocalDate fromDate) throws SessionNotFoundException {
        return this.findSessionsDuring(fromDate, fromDate);
    }

    @Override
    public Session addSession(Session session) throws SessionAlreadyExistsException {
        List<Session> sessions = null;
        try {sessions = findSessionsByName(session.getName());
        } catch (SessionNotFoundException e){
        } for (Session s : sessions){
            if (session.equals(s)){
                throw new SessionAlreadyExistsException("Session already exists.");
            }
        } return sessionRepository.save(session);
    }

    @Override
    public void removeSession(int id) throws SessionNotFoundException {
        try{sessionRepository.deleteById(id);}
        catch (EmptyResultDataAccessException e){
            throw new SessionNotFoundException("Failed to delete. Session may not exist.");
        }
    }

    @Override
    public void removeSession(Session session) throws SessionNotFoundException {
        removeSession(session.getId());
    }

    @Override
    public List<Person> getAllPersonsFromSession(Session session) throws SessionNotFoundException {
        session = findSession(session.getId());
        return session.getPersonList();
    }

    @Override
    public Person findPersonInSession(Session session, int id) throws PersonNotInSessionException, SessionNotFoundException {
        return getAllPersonsFromSession(session)
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElseThrow(() -> new PersonNotInSessionException("person not in this session"));
    }

    @Override
    public Session addPersonToSession(Session session, Person person) throws PersonAlreadyInSessionException, NullInputException, SessionNotFoundException {
        if (person == null) throw new NullInputException("Person input is null");
        List<Person> personList = getAllPersonsFromSession(session);
        if(personList.contains(person)) throw new PersonAlreadyInSessionException("person is already in the session");
        personList.add(person);
        session.setPersonList(personList);
        return sessionRepository.save(session);
    }

    @Override
    public Session removePersonFromSession(Session session, Person person) throws NullInputException, SessionNotFoundException, PersonNotInSessionException {
        if (person == null) throw new NullInputException("Person input is null");
        List<Person> personList = getAllPersonsFromSession(session);
        if(!personList.contains(person)) throw new PersonNotInSessionException(person.getFirstName() + " " + person.getLastName() + " is not in this session.");
        personList.remove(person);
        session.setPersonList(personList);
        return sessionRepository.save(session);
    }

}

