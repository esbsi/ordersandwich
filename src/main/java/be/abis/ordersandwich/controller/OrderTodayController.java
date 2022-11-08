package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.*;
import be.abis.ordersandwich.service.FinancialManager;
import be.abis.ordersandwich.service.OrderTodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/order")
@Validated
public class OrderTodayController {

    @Autowired
    OrderTodayService service;

    @PostMapping("")
    public void order(@RequestBody SandwichOrderModel model) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {

         service.orderSandwich(model.getI(),model.isRauwkost(), model.isWhite(), model.getComment(), model.getPerson());
    }

    @PostMapping("none")
    public void noOrder(@RequestBody Person person) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        service.noOrder(person);
    }

    @PostMapping("closingtime")
    public void closeTime(@RequestBody LocalTime closingTime) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        service.setClosingTime(closingTime);
    }
    @PostMapping("new")
    public void newOrder(@RequestBody Shop shop) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        OrderToday orderToday= new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.NOON);
        service.setOrderToday(orderToday);
    }


}
