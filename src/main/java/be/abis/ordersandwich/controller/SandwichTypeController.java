package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.error.ApiError;
import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.service.OrderTodayService;
import be.abis.ordersandwich.service.SandwichTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.net.ssl.SSLEngineResult;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sandwich")
@Validated
public class SandwichTypeController {

    @Autowired
    SandwichTypeService sandwichTypeService;

    @Autowired
    OrderTodayService orderTodayService;

    @GetMapping("currentshop")
    public Shop getShop(){
        return orderTodayService.getOrderToday().getShop();
    }

    @PostMapping("currentshop")
    public void setShop(@RequestBody Shop shop){
        orderTodayService.getOrderToday().setShop(shop);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findSandwichTypeById(@PathVariable int id) throws SandwichTypeNotFoundException {
        SandwichType sandwichType = sandwichTypeService.findSandwichTypeById(id);
        return new ResponseEntity<>(sandwichType, HttpStatus.OK);
    }

    @GetMapping("query")
    public ResponseEntity<?> findSandwichType(@RequestParam String name) throws SandwichTypeNotFoundException{
        SandwichType sandwichType = sandwichTypeService.findSandwichType(name);
        return new ResponseEntity<>(sandwichType, HttpStatus.OK);
    }

    @DeleteMapping("")
    public void removeSandwichType(@RequestBody SandwichType sandwichType) throws SandwichTypeNotFoundException {
        sandwichTypeService.removeSandwichType(sandwichType);
    }

    @PostMapping("")
    public void addSandwichType(@RequestBody SandwichType sandwichType) throws SandwichTypeAlreadyExistsException {
        sandwichTypeService.addSandwichType(sandwichType);
    }

    @GetMapping("")
    public ResponseEntity<?> getSandwichTypes() {
        List<SandwichType> sandwichTypes = sandwichTypeService.getSandwichTypes();
        return new ResponseEntity<>(sandwichTypes, HttpStatus.OK);
    }

}
