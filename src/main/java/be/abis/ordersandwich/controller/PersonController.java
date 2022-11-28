package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.apibody.Name;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.apibody.SandwichOrderModel;

import be.abis.ordersandwich.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@Validated
public class PersonController {

    @Autowired
    PersonService service;

//naar orderToday verplaatsen
    @PostMapping("check")
    public void check(@RequestBody SandwichOrderModel model) throws SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {


    }
    @GetMapping("")
    public List<Person> allPersons( )  {
        return service.getPersonList();
    }

    /*
    @PostMapping("add")
    public void add(@RequestBody Person person )  {
         service.addPerson(person);
    }
    @DeleteMapping("")
    public void delete( @RequestBody Person person) throws PersonNotFoundException {
         service.removePerson(person);
    }
     */
    @PostMapping("name")
    public Person findByName(@RequestBody Name name ) throws PersonNotFoundException {


        return null;
    }



}
