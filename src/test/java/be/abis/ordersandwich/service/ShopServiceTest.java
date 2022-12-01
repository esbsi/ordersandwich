package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ShopServiceTest {

    @Autowired
    ShopService shopService;

    @Test
    void findShopShouldThrowNotFound(){
        assertThrows(ShopNotFoundException.class, () -> shopService.findShop("Johannesbrood"));
    }

    @Transactional
    @Test
    void shouldAddShop() throws ShopAlreadyExistsException, ShopNotFoundException {
        Shop shop = new Shop("Johannesbrood");
        shopService.addShop(shop);
        assertEquals("Johannesbrood", shopService.findShop("Johannesbrood").getName());
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
    void removeShopShouldLeave2() throws ShopNotFoundException, ShopAlreadyExistsException {
        Shop shop = new Shop("Johannesbrood");
        shopService.addShop(shop);
        shopService.removeShopById(shopService.findShop("Johannesbrood").getId());
        assertEquals(2, shopService.getShops().size());
    }

    //It is currently (and should be?) impossible to remove a shop, if existing sandwichtypes or orderTodays have this shop.
    @Test
    void removeShopShouldThrowDataIntegrityViolationException() {
       assertThrows(DataIntegrityViolationException.class, () -> shopService.removeShopById(shopService.findShop("Vleugels").getId()));
    }

}
