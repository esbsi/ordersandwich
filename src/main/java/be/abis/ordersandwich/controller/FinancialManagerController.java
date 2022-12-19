package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.dto.FinanceModel;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.service.FinancialManager;
import be.abis.ordersandwich.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Month;

@RestController
@RequestMapping("/finance")
@Validated
@CrossOrigin(origins="http://localhost:4200")
public class FinancialManagerController {

    @Autowired
    FinancialManager financialManager;
    @Autowired
    ShopService shopService;

    @PostMapping("monthlyprice")
    public double monthlyPrice(@RequestBody FinanceModel model)   {
        System.out.println(model.getShop());
        System.out.println(model.getYear());
        System.out.println(model.getMonth());

        return financialManager.getMonthlyPrice(Month.of(model.getMonth()), model.getYear());
    }

    @PostMapping("monthlyprice/shop")
    public double monthlyPriceShop(@RequestBody FinanceModel model) throws ShopNotFoundException {

        return financialManager.getMonthlyPrice(shopService.findShopById(model.getShop()), Month.of(model.getMonth()), model.getYear());
    }

    @PostMapping("amountofsandwiches")
    public int amountOfSandwiches(@RequestBody FinanceModel model)   {

        return financialManager.getAmountOfSandwichesOrdered( Month.of(model.getMonth()), model.getYear());
    }

    @PostMapping("amountofsandwiches/shop")
    public int amountOfSandwichesShop(@RequestBody FinanceModel model) throws ShopNotFoundException {

        return financialManager.getAmountOfSandwichesOrdered(shopService.findShopById(model.getShop()), Month.of(model.getMonth()), model.getYear());
    }

    @PostMapping("average")
    public double average(@RequestBody FinanceModel model) throws ShopNotFoundException {

        return financialManager.averagePriceSandwich(shopService.findShopById(model.getShop()), Month.of(model.getMonth()), model.getYear());
    }



}
