package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;

import java.util.List;

public interface SessionService {

    String checkAllOrdered(OrderToday orderToday, Session session);


    // repository methods

    List<Session> getSessions();
    Session findMostRecentSession(String sessionName) throws SessionNotFoundException;
    void addSession(Session session);

}
