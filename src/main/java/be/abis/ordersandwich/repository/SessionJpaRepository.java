package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Integer> {




    Session findSessionById(int id);

    Session findSessionByNameAndStartDate(String sessionName, LocalDate startDate) ;
    //Session findMostRecentSession(String sessionName) throws SessionNotFoundException;
    List<Session> findSessionsByName(String sessionName) ;
    List<Session> findSessionsByStartDate(LocalDate localDate) ;
    //List<Session> getSessions();


}
