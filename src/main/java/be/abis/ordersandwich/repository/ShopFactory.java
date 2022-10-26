package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.model.Shop;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopFactory implements ShopRepository{
    private static ShopFactory shopFactory =new ShopFactory();

    private List<Shop> shops = new ArrayList<>();

    private ShopFactory() {
        this.shops.add(new Shop("Vleugels"));
        this.shops.add(new Shop("Pinkys"));
    }
    public static ShopFactory getInstance(){
        return shopFactory;
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
