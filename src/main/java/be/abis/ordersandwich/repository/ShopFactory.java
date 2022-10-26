package be.abis.ordersandwich.repository;

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
    public void addShop(String shopName){
        shops.add(new Shop(shopName));
    }

    @Override
    public Shop findShop(String shopName){
        return shops.stream()
            .filter(shop -> shopName.equals(shop.getName()))
            .findFirst().orElse(null);
    }
}
