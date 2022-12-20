package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.dto.SandwichOrderModel;
import be.abis.ordersandwich.dto.Str;
import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.*;
import be.abis.ordersandwich.service.OrderTodayService;
import be.abis.ordersandwich.service.PersonService;
import be.abis.ordersandwich.service.SessionService;
import be.abis.ordersandwich.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@Validated
@CrossOrigin(origins="http://localhost:4200")
public class OrderTodayController {

    @Autowired
    OrderTodayService service;

    @Autowired
    SessionService sessionService;

    @Autowired
    PersonService personService;

    @Autowired
    ShopService shopService;

    @PostMapping("")
    public void order(@RequestBody SandwichOrderModel model) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException, PersonNotFoundException {
        Person p=personService.checkPerson(model.getPerson());

        service.orderSandwich(model.getI(),model.isRauwkost(), model.isGrilledVegs(), model.isWhite(), model.getNote(),p);
    }

    @PostMapping("none")
    public void noOrder(@RequestBody Person person) throws TooLateException, TooManySandwichesException, NullInputException, PersonNotFoundException {
        Person p=personService.checkPerson(person);
        service.noOrder(p);

    }
/*
    @PostMapping("closingtime")
    public void closeTime(@RequestBody LocalTime closingTime) {
        service.setClosingTime(closingTime);
    }

 */
    @PostMapping("new/tomorrow/{id}")
    public void newOrderTomorrow(@PathVariable int id) throws ShopNotFoundException {

        Shop shop=shopService.findShopById(id);
        OrderToday orderToday = new OrderToday(shop);
        orderToday.setClosingTime(LocalTime.now());
        service.setOrderToday(orderToday);
    }
    @PostMapping("new/today/{id}")
    public void newOrderToday(@PathVariable int id) throws ShopNotFoundException {
        Shop shop=shopService.findShopById(id);
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
/*        Person person2 = personService.findPerson(person.getId());
        if(!person2.getName().equals(person2.getName())) throw new PersonNotFoundException("person not found");
 */
        Person p = personService.checkPerson(person);
        return service.checkMyOrderToday(p);
    }

// todo
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

    @PostMapping("send/shop/{id}")
    public void send(@PathVariable int id, @RequestParam String today) throws NullInputException, ShopNotFoundException {
        service.sendOrder(shopService.findShopById(id));
        if (Boolean.parseBoolean(today)) {newOrderToday(id);}
    }

    @GetMapping("check/all")
    public String checkallorderString( ) throws SessionNotFoundException {
        List<Session> sessionList= sessionService.findSessionsDuring(LocalDate.now());
        String r ="";
        for(Session session:sessionList) {
           r+= service.checkAllOrderedString(session);
        }
        return r;
    }
    @GetMapping("check/allpersons")
    public List<Person> checkallorderPersons( ) throws SessionNotFoundException {
        List<Session> sessionList= sessionService.findSessionsDuring(LocalDate.now());
        List<Person> personList= new ArrayList<>();
        for(Session session:sessionList) {
            personList.addAll( service.checkAllOrderedPersons(session));
        }
        return personList;
    }

    @GetMapping("string")
    public Str orderTodayToString() throws NullInputException {
        Str str=new Str();
        str.setString( service.orderTodayToString());
        return str;
    }


}
