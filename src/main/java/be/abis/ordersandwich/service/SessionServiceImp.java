package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.SandwichOrder;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessionServiceImp implements SessionService{

    @Autowired
    SessionRepository sessionRepository;




    // repository methods

    @Override
    public void addPersonToSession(Session session, Person person) throws PersonAlreadyInSessionException, NullInputException {
        session.addPerson(person);
    }

    @Override
    public List<Session> getSessions(){
        return sessionRepository.getSessions();
    }

    @Override
    public Session findMostRecentSession(String sessionName) throws SessionNotFoundException{
        return sessionRepository.findMostRecentSession(sessionName);
    }

    @Override
    public void addSession(Session session){
        sessionRepository.addSession(session);
    }

}
