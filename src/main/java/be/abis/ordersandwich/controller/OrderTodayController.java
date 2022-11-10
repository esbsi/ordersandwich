package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.apibody.SandwichOrderModel;
import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.*;
import be.abis.ordersandwich.service.OrderTodayService;
import be.abis.ordersandwich.service.SessionService;
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

    @Autowired
    SessionService sessionService;

    @PostMapping("")
    public void order(@RequestBody SandwichOrderModel model) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
         service.orderSandwich(model.getI(),model.isRauwkost(), model.isGrilledVegs(), model.isWhite(), model.getComment(), model.getPerson());
    }

    @PostMapping("none")
    public void noOrder(@RequestBody Person person) throws TooLateException, TooManySandwichesException, NullInputException {
        service.noOrder(person);
    }
/*
    @PostMapping("closingtime")
    public void closeTime(@RequestBody LocalTime closingTime) {
        service.setClosingTime(closingTime);
    }

 */
    @PostMapping("new/tomorrow")
    public void newOrderTomorrow(@RequestBody OrderToday orderToday) {

        service.setOrderToday(orderToday);
    }
    @PostMapping("new/today")
    public void newOrderToday(@RequestBody OrderToday orderToday) {
        orderToday.setClosingTime(LocalTime.MAX);
        service.setOrderToday(orderToday);
    }

    @GetMapping("")
    public OrderToday getOrderToday()  {
        return service.getOrderToday();

    }
    @PostMapping("check/person")
    public List<SandwichOrder> check(@RequestBody Person person ) throws NullInputException {
        return  service.checkMyOrderToday(person);

    }
// to do
    @DeleteMapping("")
    public void remove(@RequestBody SandwichOrder sandwichOrder ) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, OrderNotFoundException {
        SandwichOrder order=service.getOrderToday().getOrder().stream()
                        .filter(x-> x.getId()==sandwichOrder.getId())
                        .findFirst().orElseThrow(()->new OrderNotFoundException("order not found"));
        if(!sandwichOrder.getPerson().getName().equals(order.getPerson().getName())) throw new OrderNotFoundException("order not found");

        service.removeOrder(order);
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
    public String checkallorderString(@RequestBody Session session ) throws SessionNotFoundException {
        Session session2;
        session2 = sessionService.findSession(session.getId());
        if(!session2.getName().equals(session.getName())) throw new SessionNotFoundException("session not found");
        return  service.checkAllOrderedString(session);

    }
    @PostMapping("check/allperson")
    public List<Person> checkallorderPersons(@RequestBody Session session ) throws SessionNotFoundException {
        Session session2= sessionService.findSession(session.getId());
        if(!session2.getName().equals(session.getName())) throw new SessionNotFoundException("session not found");
        return  service.checkAllOrderedPersons(session);

    }


}
