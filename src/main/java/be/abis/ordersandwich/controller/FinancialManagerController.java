package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.model.FinanceModel;
import be.abis.ordersandwich.service.FinancialManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/finance")
@Validated
public class FinancialManagerController {

    @Autowired
    FinancialManager financialManager;

    @PostMapping("monthlyprice")
    public double monthlyPrice(@RequestBody FinanceModel model)   {

        return financialManager.getMonthlyPrice(model.getShop(), model.getMonth(), model.getYear());
    }

    @PostMapping("amountofsandwiches")
    public int amountOfSandiwiches(@RequestBody FinanceModel model)   {

        return financialManager.getAmountOfSandwichesOrdered(model.getShop(), model.getMonth(), model.getYear());
    }

    @PostMapping("average")
    public double average(@RequestBody FinanceModel model)   {

        return financialManager.averagePriceSandwich(model.getShop(), model.getMonth(), model.getYear());
    }



}
