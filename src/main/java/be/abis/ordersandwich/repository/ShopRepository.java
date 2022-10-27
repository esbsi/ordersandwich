package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;

public interface ShopRepository {

    void addShop(String shopName);

    void removeShop(String shopName) throws ShopNotFoundException;

    Shop findShop(String shopName) throws ShopNotFoundException;

}
