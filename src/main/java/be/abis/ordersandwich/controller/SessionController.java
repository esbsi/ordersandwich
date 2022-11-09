package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.PersonNotFoundException;
import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.*;
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

    @PostMapping("name")
    public Session findByName(@RequestBody Name name ) throws  SessionNotFoundException {


        return service.findMostRecentSession(name.getName());
    }

    @PostMapping("add")
    public void add(@RequestBody AddToSessionModel model ) throws SessionNotFoundException, PersonAlreadyInSessionException, NullInputException {


         service.addPersonToSession(model.getSession(),model.getPerson());
    }

    @PostMapping("persons")
    public List<Person> persons(@RequestBody Session session ) throws SessionNotFoundException, PersonAlreadyInSessionException, NullInputException {

        return  service.getAllPersonsFromSession(session);

    }


}
