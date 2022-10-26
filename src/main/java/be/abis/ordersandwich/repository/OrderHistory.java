package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.model.OrderToday;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory implements OrderRepository{

    private static OrderHistory history=new OrderHistory();
    private List<OrderToday> orderHistory = new ArrayList<>();

    private OrderHistory() {}

    public static OrderHistory getInstance(){
        return history;
    }


    // business

    public void addToOrderHistory(OrderToday orderToday) {
        orderHistory.add(orderToday);
    }


    // getset

    public List<OrderToday> getOrderHistory() {
        return orderHistory;
    }

}
