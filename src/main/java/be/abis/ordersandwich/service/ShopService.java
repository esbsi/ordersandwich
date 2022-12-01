package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface ShopService {
    Shop addShop(Shop shop) throws ShopAlreadyExistsException;

    void removeShopById(int id) throws ShopNotFoundException, DataIntegrityViolationException;

    Shop findShopById(int id) throws ShopNotFoundException;

    Shop findShop(String shopName) throws ShopNotFoundException;

    List<Shop> getShops();
}
