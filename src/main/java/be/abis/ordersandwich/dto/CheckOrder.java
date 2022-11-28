package be.abis.ordersandwich.dto;

import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Session;

public class CheckOrder {

    private OrderToday orderToday;
    private Session session;

    public CheckOrder() {

    }

    public OrderToday getOrderToday() {
        return orderToday;
    }

    public void setOrderToday(OrderToday orderToday) {
        this.orderToday = orderToday;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
