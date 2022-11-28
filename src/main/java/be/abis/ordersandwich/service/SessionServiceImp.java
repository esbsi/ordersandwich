package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.PersonNotInSessionException;
import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.SessionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    // to do
    public Session findMostRecentSession(String sessionName) throws SessionNotFoundException{
        return null;
    }

    @Override
    //to do
    public void addSession(Session session){
        sessionRepository.save(session);
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

}
