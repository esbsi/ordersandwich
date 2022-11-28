package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.OrderTodayNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderToday, Integer> {

    void addToOrderHistory(OrderToday orderToday);

    void removeOrderToday(OrderToday orderToday);

    List<OrderToday> findOrdersToday(LocalDate localDate) throws OrderTodayNotFoundException;

    OrderToday findOrderToday(int id) throws OrderTodayNotFoundException;

    OrderToday findLastOrderToday(LocalDate localDate) throws OrderTodayNotFoundException;

    // should be removed?
    List<OrderToday> getOrderHistory();

}
