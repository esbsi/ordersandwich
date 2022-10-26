package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.model.OrderToday;

import java.util.List;

public interface OrderRepository {

    void addToOrderHistory(OrderToday orderToday);

    // should be replaced?
    List<OrderToday> getOrderHistory();

}
