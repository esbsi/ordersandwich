package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Integer> {

    void addSession(Session session);

    void removeSession(Session session) throws SessionNotFoundException;

    Session findSession(int id) throws SessionNotFoundException;

    Session findSession(String sessionName, LocalDate startDate) throws SessionNotFoundException;
    Session findMostRecentSession(String sessionName) throws SessionNotFoundException;
    List<Session> findSessionsByName(String sessionName) throws SessionNotFoundException;
    List<Session> findSessionsByDate(LocalDate localDate) throws SessionNotFoundException;
    List<Session> getSessions();


}
