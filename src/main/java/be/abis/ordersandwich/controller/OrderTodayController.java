package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.dto.SandwichOrderModel;
import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.*;
import be.abis.ordersandwich.service.OrderTodayService;
import be.abis.ordersandwich.service.PersonService;
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

    @Autowired
    PersonService personService;

    @PostMapping("")
    public void order(@RequestBody SandwichOrderModel model) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
         service.orderSandwich(model.getI(),model.isRauwkost(), model.isGrilledVegs(), model.isWhite(), model.getNote(), model.getPerson());
    }

    @PostMapping("none")
    public void noOrder(@RequestBody Person person) throws TooLateException, TooManySandwichesException, NullInputException {
        /*
        Person person2 = personService.findPerson(person.getId());
        if(!person2.getName().equals(person2.getName())) throw new PersonNotFoundException("person not found");


 */
        service.noOrder(person);
    }
/*
    @PostMapping("closingtime")
    public void closeTime(@RequestBody LocalTime closingTime) {
        service.setClosingTime(closingTime);
    }

 */
    @PostMapping("new/tomorrow")
    public void newOrderTomorrow(@RequestBody Shop shop) {
        OrderToday orderToday = new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.now());
        service.setOrderToday(orderToday);
    }
    @PostMapping("new/today")
    public void newOrderToday(@RequestBody Shop shop) {
        OrderToday orderToday = new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.MAX);
        service.setOrderToday(orderToday);
    }

    @GetMapping("")
    public OrderToday getOrderToday()  {
        return service.getOrderToday();

    }
    @PostMapping("check/person")
    public List<SandwichOrder> check(@RequestBody Person person ) throws NullInputException, PersonNotFoundException {
        System.out.println("hey");
/*
        Person person2 = personService.findPerson(person.getId());
        if(!person2.getName().equals(person2.getName())) throw new PersonNotFoundException("person not found");


 */



        return  service.checkMyOrderToday(person);

    }
// to do
    @DeleteMapping("")
    public void remove(@RequestBody SandwichOrder sandwichOrder ) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, OrderNotFoundException {
        SandwichOrder order=service.getOrderToday().getOrder().stream()
                        .filter(x-> x.getId()==sandwichOrder.getId())
                        .findFirst().orElseThrow(()->new OrderNotFoundException("order not found"));

        if(!sandwichOrder.getPerson().getFirstName().equals(order.getPerson().getFirstName())) throw new OrderNotFoundException("order not found");

        service.removeOrder(order);
    }

    @GetMapping("price")
    public double price() throws NullInputException {
        return service.totalPrice();
    }

    @PostMapping("send")
    public void send() throws NullInputException {
        service.sendOrder(new Shop());
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
