package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.ShopJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImp implements ShopService {

    @Autowired
    ShopJpaRepository shopRepository;

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException{
        shopRepository.addShop(shop);
    }

    @Override
    public void removeShop(Shop shop) throws ShopNotFoundException{
        shopRepository.removeShop(shop);
    }

    @Override
    public Shop findShopById(int id) throws ShopNotFoundException{
        return shopRepository.findShopById(id);
    }

    @Override
    public Shop findShop(String shopName) throws ShopNotFoundException{
        return shopRepository.findShop(shopName);
    }

    @Override
    public List<Shop> getShops() {
        return shopRepository.getShops();
    }

}
