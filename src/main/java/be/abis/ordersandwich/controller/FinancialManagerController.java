package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.dto.FinanceModel;
import be.abis.ordersandwich.service.FinancialManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finance")
@Validated
@CrossOrigin(origins="http://localhost:4200")
public class FinancialManagerController {

    @Autowired
    FinancialManager financialManager;

    @PostMapping("monthlyprice")
    public double monthlyPrice(@RequestBody FinanceModel model)   {

        return financialManager.getMonthlyPrice( model.getMonth(), model.getYear());
    }

    @PostMapping("monthlyprice/shop")
    public double monthlyPriceShop(@RequestBody FinanceModel model)   {

        return financialManager.getMonthlyPrice(model.getShop(), model.getMonth(), model.getYear());
    }

    @PostMapping("amountofsandwiches")
    public int amountOfSandwiches(@RequestBody FinanceModel model)   {

        return financialManager.getAmountOfSandwichesOrdered( model.getMonth(), model.getYear());
    }

    @PostMapping("amountofsandwiches/shop")
    public int amountOfSandwichesShop(@RequestBody FinanceModel model)   {

        return financialManager.getAmountOfSandwichesOrdered(model.getShop(), model.getMonth(), model.getYear());
    }

    @PostMapping("average")
    public double average(@RequestBody FinanceModel model)   {

        return financialManager.averagePriceSandwich(model.getShop(), model.getMonth(), model.getYear());
    }



}
