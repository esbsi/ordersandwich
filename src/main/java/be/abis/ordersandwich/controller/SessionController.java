package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.dto.AddToSessionModel;
import be.abis.ordersandwich.dto.Name;
import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.*;
import be.abis.ordersandwich.service.PersonService;
import be.abis.ordersandwich.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/session")
@Validated
public class SessionController {

    @Autowired
    SessionService service;
    @Autowired
    PersonService personService;


    @GetMapping("")
    public List<Session> getAll()  {
        return service.getSessions();
    }

    @GetMapping("today")
    public List<Session> findSessionsToday() throws SessionNotFoundException {
        return service.findSessionsDuring(LocalDate.now());
    }

    @GetMapping("query")
    public List<Session> findSessionsDuring(@RequestParam("from") LocalDate fromDate, @RequestParam("until") LocalDate untilDate) throws SessionNotFoundException {
        return service.findSessionsDuring(fromDate, untilDate);
    }

    @GetMapping("{id}")
    public Session findSessionById(@PathVariable("id") int id) throws SessionNotFoundException {
        return service.findSession(id);
    }

    @PostMapping("name")
    public Session findByName(@RequestBody Name name ) throws  SessionNotFoundException {
        return service.findSessionsByName(name.getName()).stream()
                .sorted(Comparator.comparing(Session::getEndDate).reversed())
                .findFirst().orElseThrow(SessionNotFoundException::new);
    }

    //todo delete this method?
    @PostMapping("add")
    public void addPersonToSession2(@RequestBody AddToSessionModel model ) throws SessionNotFoundException, PersonAlreadyInSessionException, NullInputException {
         service.addPersonToSession(model.getSession(),model.getPerson());
    }

    //add a new person to the database (or find if exist), and subscribe to session.
    @PostMapping("{sessionid}/newperson")
    public void addPersonToSession(@PathVariable("sessionid") int sessionId, @RequestBody Person person) throws SessionNotFoundException, PersonAlreadyInSessionException, NullInputException, PersonNotFoundException {
        service.addPersonToSession(service.findSession(sessionId), person);
    }

    //use this for existing persons.
    @PostMapping("{sessionid}/subscribe")
    public void addPersonToSessionById(@PathVariable("sessionid") int sessionId, @RequestParam("personid") int personId) throws SessionNotFoundException, PersonAlreadyInSessionException, NullInputException, PersonNotFoundException {
        Person person = personService.findPerson(personId);
        service.addPersonToSession(service.findSession(sessionId), person);
    }

    @PostMapping("{sessionid}/unsubscribe")
    public void removePersonFromSession(@PathVariable("sessionid") int sessionId, @RequestParam("personid") int personId) throws SessionNotFoundException, PersonAlreadyInSessionException, NullInputException, PersonNotFoundException, PersonNotInSessionException {
        service.removePersonFromSession(service.findSession(sessionId), personService.findPerson(personId));
    }

    @PostMapping("persons")
    public List<Person> getPersonsInSession(@RequestBody Session session ) throws SessionNotFoundException {
        return  service.getAllPersonsFromSession(session);
    }

    @GetMapping("{id}/persons")
    public List<Person> getPersonsInSession(@PathVariable("id") int id) throws SessionNotFoundException {
        return service.findSession(id).getPersonList();
    }

    @PostMapping("")
    public Session addSession(@RequestBody Session session) throws SessionAlreadyExistsException {
        return service.addSession(session);
    }

    @DeleteMapping("{id}")
    public void removeSession(@PathVariable("id") int id) throws SessionNotFoundException {
        service.removeSession(id);
    }


}
