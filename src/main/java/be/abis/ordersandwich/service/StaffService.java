package be.abis.ordersandwich.service;

import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.OrderHistory;

public interface StaffService {
    public OrderToday changeShopCancelCurrent(Shop shop);
    public void toFile(String writing, boolean bool);
    public OrderToday sendOrder(OrderToday orderToday, OrderHistory orderHistory, Shop shopTomorrow);
}
