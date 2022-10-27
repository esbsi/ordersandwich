package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository {

    void addSession(Session session);

    void removeSession(Session session) throws SessionNotFoundException;

    Session findSession(String sessionName, LocalDate startDate) throws SessionNotFoundException;
    Session findMostRecentSession(String sessionName) throws SessionNotFoundException;
    List<Session> findSessionsByName(String sessionName) throws SessionNotFoundException;
    List<Session> findSessionsByDate(LocalDate localDate) throws SessionNotFoundException;
    List<Session> getSessions();


}
