package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopRepositoryTest {

    @Autowired
    ShopJpaRepository shopRepository;

    @Test
    @Order(1)
    public void shouldFindVleugels() throws ShopNotFoundException {
        assertEquals("Vleugels", shopRepository.findShop("Vleugels").getName());
    }

    @Test
    public void shouldThrowShopNotFoundException(){
        assertThrows(ShopNotFoundException.class, () -> shopRepository.findShop("UFO"));
    }

    @Test
    public void removedShouldThrowShopNotFoundException() throws ShopNotFoundException, ShopAlreadyExistsException {
        Shop shop = new Shop("test");
        shopRepository.addShop(shop);
        shopRepository.removeShop(shop);
        assertThrows(ShopNotFoundException.class, () -> shopRepository.findShop("test"));
    }

    @Test
    public void shouldThrowShopAlreadyExistsException(){
        assertThrows(ShopAlreadyExistsException.class, () -> shopRepository.addShop(new Shop("Vleugels")));
    }


}
