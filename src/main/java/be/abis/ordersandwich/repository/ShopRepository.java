package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;

public interface ShopRepository {

    void addShop(Shop shop);

    void removeShop(Shop shop);

    Shop findShop(String shopName) throws ShopNotFoundException;

}