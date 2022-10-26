package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.model.Shop;

public interface ShopRepository {

    void addShop(String shopName);
    Shop findShop(String shopName);

}
