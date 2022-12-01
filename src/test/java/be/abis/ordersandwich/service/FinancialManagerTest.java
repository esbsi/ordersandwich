package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.OrderJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FinancialManagerTest {

    @Autowired
    FinancialManager financialManager;

    @Autowired
    ShopService shopService;


    @Test
    public void monthlyPrice(){
        System.out.println(financialManager.getMonthlyPrice(Month.NOVEMBER,2022));
        assertEquals(60,financialManager.getMonthlyPrice(Month.NOVEMBER,2022));
    }
    @Test
    public void monthlyAmount(){

        assertEquals(2,financialManager.getAmountOfSandwichesOrdered(Month.NOVEMBER,2022));
    }

    @Test
    public void monthlyPriceShouldBeXXXX() throws ShopNotFoundException {
        Shop shop= shopService.findShopById(1);

       assertEquals(60,financialManager.getMonthlyPrice(shop,Month.NOVEMBER,2022));
    }

    @Test
    public void average() throws ShopNotFoundException {
        Shop shop= shopService.findShopById(1);

        assertEquals(30,financialManager.averagePriceSandwich(shop,Month.NOVEMBER,2022));
    }

    @Test
    public void monthlyPriceShouldBeXX() throws ShopNotFoundException {
        Shop shop= shopService.findShopById(2);
        System.out.println(financialManager.getMonthlyPrice(shop,Month.DECEMBER,2022));
        assertEquals(0,financialManager.getMonthlyPrice(shop,Month.NOVEMBER,2022));
    }

}
