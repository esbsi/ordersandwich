package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.OrderTodayNotFoundException;
import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Session;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public interface OrderRepository {

    void addToOrderHistory(OrderToday orderToday);

    void removeOrderToday(OrderToday orderToday);

    List<OrderToday> findOrdersToday(LocalDate localDate) throws OrderTodayNotFoundException;

    OrderToday findLastOrderToday(LocalDate localDate) throws OrderTodayNotFoundException;

    // should be removed?
    List<OrderToday> getOrderHistory();

}
