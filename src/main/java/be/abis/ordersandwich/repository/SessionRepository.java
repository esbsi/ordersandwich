package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;

import java.util.List;

public interface SessionRepository {

    List<Session> getSessions();
    Session findMostRecentSession(String sessionName) throws SessionNotFoundException;
    void addSession(String name, String startDate, String endDate);

}
