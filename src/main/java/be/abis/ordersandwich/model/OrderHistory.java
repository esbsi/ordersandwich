package be.abis.ordersandwich.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {

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
