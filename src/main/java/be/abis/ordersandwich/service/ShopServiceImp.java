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
        shopRepository.save(shop);
    }

    @Override
    public void removeShop(Shop shop) throws ShopNotFoundException{
        findShopById(shop.getId());
        shopRepository.delete(shop);
    }

    @Override
    public Shop findShopById(int id) throws ShopNotFoundException{
        Shop s= shopRepository.findShopById(id);
        if (s==null) throw new ShopNotFoundException("shop not found");
        return s;
    }

    @Override
    public Shop findShop(String shopName) throws ShopNotFoundException{
        Shop s= shopRepository.findShopByName(shopName);
        if (s==null) throw new ShopNotFoundException("shop not found");
        return s;
    }

    @Override
    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

}
