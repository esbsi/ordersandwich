package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.SessionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessionServiceImp implements SessionService{

    @Autowired
    SessionJpaRepository sessionRepository;


    // repository methods

    @Override
    public void addPersonToSession(Session session, Person person) throws PersonAlreadyInSessionException, NullInputException {
        session.addPerson(person);
    }

    @Override
    public List<Session> getSessions(){
        return sessionRepository.findAll();
    }

    //todo
    @Override
    public Session addSession(Session session) throws SessionAlreadyExistsException {
        List<Session> sessions;
        try {sessions = findSessionsByName(session.getName());
        } catch (SessionNotFoundException e){
            return sessionRepository.save(session);
        } for (Session s : sessions){
            if (session.equals(s)){
                throw new SessionAlreadyExistsException("Session already exists.");
            }
        } return sessionRepository.save(session);
    }

    @Override
    public List<Person> getAllPersonsFromSession(Session session) throws SessionNotFoundException {
        Session session2=findSession(session.getId());
        return session2.getPersonList();
    }

    @Override
    public Person findPersonInSession(Session session,int id) throws PersonNotInSessionException, SessionNotFoundException {
        Session session2=findSession(session.getId());
        return session2.getPersonList().stream().filter(x->x.getId()==id).findFirst().orElseThrow(()->new PersonNotInSessionException("person not in this session"));
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
        if (sessions == null) throw new SessionNotFoundException("No sessions found.");
        return sessions;
    }

    @Override
    public List<Session> findSessionsDuring(LocalDate fromDate) throws SessionNotFoundException {
        List<Session> sessions = sessionRepository.findSessionsDuring(fromDate);
        if (sessions == null) throw new SessionNotFoundException("No sessions found.");
        return sessions;
    }

}

