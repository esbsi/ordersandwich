package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.model.OrderToday;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderHistory implements OrderRepository{


    private List<OrderToday> orderHistory = new ArrayList<>();

    public OrderHistory() {}




    // business

    @Override
    public void addToOrderHistory(OrderToday orderToday) {
        orderHistory.add(orderToday);
    }


    // getset

    @Override
    public List<OrderToday> getOrderHistory() {
        return orderHistory;
    }

}
