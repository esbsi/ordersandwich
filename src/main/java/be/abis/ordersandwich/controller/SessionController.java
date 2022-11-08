package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.model.CheckOrder;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.service.PersonService;
import be.abis.ordersandwich.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
@Validated
public class SessionController {


    @Autowired
    SessionService service;
// willen hier wrsz iets andrs terug als een string
    @PostMapping("allordered")
    public void allOrdered(@RequestBody CheckOrder checkOrder )  {
        service.checkAllOrdered(checkOrder.getOrderToday(), checkOrder.getSession());
    }

    @GetMapping("")
    public List<Session> getAll()  {
        return service.getSessions();
    }
    /*
    @PostMapping("add")
    public void addsession(@RequestBody Session session)  {
        service.addSession(session);
    }

     */



}
