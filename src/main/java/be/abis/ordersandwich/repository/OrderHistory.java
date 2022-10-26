package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.model.OrderToday;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderHistory implements OrderRepository{

    private static OrderHistory history=new OrderHistory();
    private List<OrderToday> orderHistory = new ArrayList<>();

    private OrderHistory() {}

    public static OrderHistory getInstance(){
        return history;
    }


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
