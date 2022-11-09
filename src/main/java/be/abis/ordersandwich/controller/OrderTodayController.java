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
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/order")
@Validated
public class OrderTodayController {

    @Autowired
    OrderTodayService service;

    @PostMapping("")
    public void order(@RequestBody SandwichOrderModel model) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
         service.orderSandwich(model.getI(),model.isRauwkost(), model.isGrilledVegs(), model.isWhite(), model.getComment(), model.getPerson());
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
    public void newOrder(@RequestBody OrderToday orderToday) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {

        service.setOrderToday(orderToday);
    }

    @GetMapping("")
    public OrderToday getOrderToday() throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        return service.getOrderToday();

    }
    @PostMapping("check/person")
    public List<SandwichOrder> check(@RequestBody Person person ) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        return  service.checkMyOrderToday(person);

    }
// to do
    @PostMapping("remove")
    public void remove(@RequestBody SandwichOrder sandwichOrder ) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
         service.removeOrder(sandwichOrder);
    }

    @GetMapping("price")
    public double price() throws NullInputException {
        return service.totalPrice();
    }

    @PostMapping("send")
    public void send() throws NullInputException {
        service.sendOrder();
    }

    @PostMapping("check/all")
    public String checkallorderString(@RequestBody Session session ) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        return  service.checkAllOrderedString(session);

    }
    @PostMapping("check/allperson")
    public List<Person> checkallorderPersons(@RequestBody Session session ) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        return  service.checkAllOrderedPersons(session);

    }


}
