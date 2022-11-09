package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.error.ApiError;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
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

@RestController
@RequestMapping("/sandwich")
@Validated
public class SandwichTypeController {

    @Autowired
    SandwichTypeService sandwichTypeService;

    @GetMapping("shop")
    public Shop getShop(){
        return sandwichTypeService.getShop();
    }

    @PostMapping("shop")
    public void setShop(@RequestBody Shop shop){
        sandwichTypeService.setShop(shop);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findSandwichTypeById(@PathVariable int id) throws SandwichTypeNotFoundException {
        SandwichType sandwichType = sandwichTypeService.findSandwichTypeById(id);
        return new ResponseEntity<>(sandwichType, HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<?> findSandwichType(String name) throws SandwichTypeNotFoundException{
        SandwichType sandwichType = sandwichTypeService.findSandwichType(name);
        return new ResponseEntity<>(sandwichType, HttpStatus.OK);
    }

}
