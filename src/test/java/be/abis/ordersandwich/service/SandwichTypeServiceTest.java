package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SandwichTypeServiceTest {

    @Autowired
    SandwichTypeService sandwichTypeService;
    @Autowired
    ShopService shopService;

    @Test
    void getSandwichTypesSizeShouldBe(){
        assertEquals(12, sandwichTypeService.getSandwichTypes().size());
    }

    @Test
    void getSandwichTypesByShopSizeShouldBe() throws ShopNotFoundException, SandwichTypeNotFoundException {
        assertEquals(7, sandwichTypeService.findSandwichtypesByShop(shopService.findShopById(1)).size());
    }

    @Test
    void findById() throws SandwichTypeNotFoundException {
        assertEquals("Americain", sandwichTypeService.findSandwichTypeById(1).getName());
    }

    @Test
    void findByNameAndShop() throws SandwichTypeNotFoundException {
        assertEquals(1, sandwichTypeService.findSandwichType("Americain", 1).getId());
    }

}
