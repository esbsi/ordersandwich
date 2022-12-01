package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Integer> {

    Session findSessionById(int id);
    List<Session> findSessionsByName(String sessionName);
    @Query("select s from Session s where s.startDate <= :untilDate and s.endDate >= :fromDate")
    List<Session> findSessionsDuring(@Param("fromDate") LocalDate fromDate, @Param("untilDate") LocalDate untilDate);

    Session findSessionByNameAndStartDate(String sessionName, LocalDate startDate) ;
    List<Session> findSessionsByStartDate(LocalDate localDate) ;

}
