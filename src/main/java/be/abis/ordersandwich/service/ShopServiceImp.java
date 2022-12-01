package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.ShopJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImp implements ShopService {

    @Autowired
    ShopJpaRepository shopRepository;

    @Override
    public Shop addShop(Shop shop) throws ShopAlreadyExistsException{
        try {findShop(shop.getName());
        } catch (ShopNotFoundException e) {
            return shopRepository.save(shop);
        } throw new ShopAlreadyExistsException("Shop already exists.");
    }

    @Override
    public void removeShopById(int id) throws ShopNotFoundException, DataIntegrityViolationException {
        findShopById(id);
        try{shopRepository.deleteById(id);}
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("It is impossible to remove a shop, if sandwichtypes or orders are tied to this shop.");
        }
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
