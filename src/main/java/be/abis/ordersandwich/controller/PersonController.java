package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.dto.Name;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.dto.SandwichOrderModel;

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



    @GetMapping("")
    public List<Person> allPersons( )  {
        return service.getPersonList();
    }


    @PostMapping("add")
    public void add(@RequestBody Person person ) throws PersonAlreadyInExistException {
         service.addPerson(person);
    }
    @DeleteMapping("")
    public void delete( @RequestBody Person person) throws PersonNotFoundException {
         service.removePerson(person);
    }

    @DeleteMapping("{id}")
    public void deleteById( @PathVariable int id ) throws PersonNotFoundException {
        service.removePerson(id);
    }

    @PostMapping("name")
    public Person findByName(@RequestBody Name name ) throws PersonNotFoundException {


        return service.findPerson(name.getFirstName(), name.getLastName());
    }

    @GetMapping("{id}")
    public Person findById(@PathVariable  int id ) throws PersonNotFoundException {


        return service.findPerson(id);
    }



}
