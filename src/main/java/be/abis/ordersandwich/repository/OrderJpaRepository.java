package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.OrderTodayNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderToday, Integer> {

    //query

    //OrderToday getLastOrderToday();

    List<OrderToday> findOrderTodayByDate(LocalDate localDate) throws OrderTodayNotFoundException;

    OrderToday findOrderTodayById(int id) throws OrderTodayNotFoundException;


    //query
   // OrderToday findLastOrderToday(LocalDate localDate) throws OrderTodayNotFoundException;




}
