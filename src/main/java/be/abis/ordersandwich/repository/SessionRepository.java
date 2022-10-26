package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository {

    void addSession(String name, String startDate, String endDate);
    List<Session> getSessions();
    Session findSession(String sessionName, LocalDate startDate) throws SessionNotFoundException;
    Session findMostRecentSession(String sessionName) throws SessionNotFoundException;
    List<Session> findSessionsByName(String sessionName) throws SessionNotFoundException;
    List<Session> findSessionsByDate(LocalDate localDate) throws SessionNotFoundException;

}
