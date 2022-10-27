package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopFactory implements ShopRepository{

    private List<Shop> shops = new ArrayList<>();

    public ShopFactory() {
        this.shops.add(new Shop("Vleugels"));
        this.shops.add(new Shop("Pinkys"));
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistsException {
        if(!shops.contains(shop)){
            shops.add(shop);
        } else throw new ShopAlreadyExistsException();
    }

    @Override
    public void removeShop(Shop shop) throws ShopNotFoundException {
        if(!shops.remove(shop)){
            throw new ShopNotFoundException();
        }
    }

    @Override
    public Shop findShop(String shopName) throws ShopNotFoundException {
        return shops.stream()
            .filter(shop -> shopName.equals(shop.getName()))
            .findFirst().orElseThrow(ShopNotFoundException::new);
    }
}
