package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ShopServiceTest {

    @Autowired
    ShopService shopService;

    @Transactional
    @Test
    void shouldAddShop() throws ShopAlreadyExistsException {
        Shop shop = new Shop("Johannesbrood.");
        shopService.addShop(shop);
    }

    @Transactional
    @Test
    void addShopShouldThrowExists() {
        Shop shop = new Shop("Vleugels");
        assertThrows(ShopAlreadyExistsException.class, () -> shopService.addShop(shop));
    }

    @Test
    void getShopsLengthShouldBe2(){
        assertEquals(2, shopService.getShops().size());
    }

    @Transactional
    @Test
    void removeShopShouldLeave1() throws ShopNotFoundException {
        shopService.removeShop(shopService.findShopById(1));
        assertEquals(1, shopService.getShops().size());
    }

}
