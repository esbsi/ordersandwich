package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;

import java.util.List;

public interface ShopRepository {

    void addShop(Shop shop) throws ShopAlreadyExistsException;

    void removeShop(Shop shop) throws ShopNotFoundException;

    Shop findShopById(int id) throws ShopNotFoundException;

    Shop findShop(String shopName) throws ShopNotFoundException;

    List<Shop> getShops();
}