package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.SandwichOrder;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImp implements SessionService{

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public String checkAllOrdered(OrderToday orderToday, Session session){
        int count = 0;
        String string="";
        for (Person person : session.getPersonList()) {
            boolean personOrdered = false;
            for (SandwichOrder sandwichOrder : orderToday.getOrder()){
                if (sandwichOrder.getPerson() == person){
                    personOrdered = true;
                }
            } if (!personOrdered){
                string+=session.getName() + ": " + person.getName() + " hasn't ordered." +"\n";
            } else {++count;}
        } if (count == session.getPersonList().size()){
            string+="All students in " + session.getName() + " session have ordered.";
        }
        return string;
    }


    // repository methods

    @Override
    public List<Session> getSessions(){
        return sessionRepository.getSessions();
    }

    @Override
    public Session findMostRecentSession(String sessionName) throws SessionNotFoundException{
        return sessionRepository.findMostRecentSession(sessionName);
    }

    @Override
    public void addSession(String name, String startDate, String endDate){
        sessionRepository.addSession(name, startDate, endDate);
    }

}
