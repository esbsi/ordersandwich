package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.OrderHistory;
import be.abis.ordersandwich.repository.OrderRepository;

public interface StaffService {

    public void toFile(String writing, boolean bool);
    public OrderToday sendOrder(OrderToday orderToday, OrderRepository orderHistory, Shop shopTomorrow) throws NullInputException;
}
