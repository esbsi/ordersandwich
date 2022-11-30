package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SandwichTypeServiceTest {

    @Autowired
    SandwichTypeService sandwichTypeService;
    @Autowired
    ShopService shopService;

    Shop testShop = new Shop("Johannesbrood");


    @Test
    void getSandwichTypesSizeShouldBe(){
        assertEquals(12, sandwichTypeService.getSandwichTypes().size());
    }

    @Test
    void findSandwichTypesByShopSizeShouldBe() throws ShopNotFoundException, SandwichTypeNotFoundException {
        assertEquals(7, sandwichTypeService.findSandwichTypesByShop(shopService.findShopById(1)).size());
    }

    @Test
    void findSandwichTypesByShopShouldThrowShopNotFound() {
        assertThrows(ShopNotFoundException.class, () -> sandwichTypeService.findSandwichTypesByShop(testShop));
    }

    @Test
    void findSandwichTypesByShopShouldThrowSandwichTypeNotFound() throws ShopAlreadyExistsException {
        shopService.addShop(testShop);
        assertThrows(SandwichTypeNotFoundException.class, () -> sandwichTypeService.findSandwichTypesByShop(testShop));
    }

    @Test
    void findById() throws SandwichTypeNotFoundException {
        assertEquals("Americain", sandwichTypeService.findSandwichTypeById(1).getName());
    }

    @Test
    void findByIdShouldThrowSandwichTypeNotFound() {
        assertThrows(SandwichTypeNotFoundException.class, () -> sandwichTypeService.findSandwichTypeById(99999));
    }

    @Test
    void findByNameAndShop() throws SandwichTypeNotFoundException {
        assertEquals(1, sandwichTypeService.findSandwichType("Americain", 1).getId());
    }

    @Test
    void findByNameAndShopShouldThrowSandwichTypeNotFound() {
        assertThrows(SandwichTypeNotFoundException.class, () -> sandwichTypeService.findSandwichType("Broodje Aap", 1).getId());
    }

    @Transactional
    @Test
    void addSandwichType() throws ShopNotFoundException, SandwichTypeNotFoundException, SandwichTypeAlreadyExistsException {
        Shop shop = shopService.findShopById(1);
        int size = sandwichTypeService.findSandwichTypesByShop(shop).size();
        SandwichType testSandwich = new SandwichType(shop, "Broodje Aap", 7.77, "Test", false, "Aap, brood");
        sandwichTypeService.addSandwichType(testSandwich);
        assertEquals(size+1, sandwichTypeService.findSandwichTypesByShop(shop).size());
    }

    @Transactional
    @Test
    void addSandwichTypeShouldThrowSandwichTypeAlreadyExists() throws ShopNotFoundException, SandwichTypeAlreadyExistsException {
        Shop shop = shopService.findShopById(1);
        SandwichType testSandwich = new SandwichType(shop, "Broodje Aap", 7.77, "Test", false, "Aap, brood");
        sandwichTypeService.addSandwichType(testSandwich);
        assertThrows(SandwichTypeAlreadyExistsException.class, () -> sandwichTypeService.addSandwichType(testSandwich));
    }

    @Transactional
    @Test
    void removeSandwichType() throws SandwichTypeNotFoundException {
        int size = sandwichTypeService.getSandwichTypes().size();
        sandwichTypeService.removeSandwichType(5);
        assertEquals(size-1, sandwichTypeService.getSandwichTypes().size());
    }

    //ToDo: doesn't throw, while it should (and previously did). Same issue in ShopServiceTest.
    @Transactional
    @Test
    void removeSandwichTypeShouldThrowDataIntegrityViolationException() throws SandwichTypeNotFoundException {
        assertThrows(DataIntegrityViolationException.class, () -> sandwichTypeService.removeSandwichType(1));
    }

    @Transactional
    @Test
    void removeSandwichTypeShouldThrowSandwichTypeNotFound() {
        assertThrows(SandwichTypeNotFoundException.class, () -> sandwichTypeService.removeSandwichType(99999));
    }




}
