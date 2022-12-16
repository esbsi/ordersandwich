package be.abis.ordersandwich.controller;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.service.OrderTodayService;
import be.abis.ordersandwich.service.SandwichTypeService;
import be.abis.ordersandwich.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sandwich")
@Validated
@CrossOrigin(origins="http://localhost:4200")
public class SandwichTypeController {

    @Autowired
    SandwichTypeService sandwichTypeService;
    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    ShopService shopService;


    @GetMapping("{id}")
    public ResponseEntity<?> findSandwichTypeById(@PathVariable int id) throws SandwichTypeNotFoundException {
        SandwichType sandwichType = sandwichTypeService.findSandwichTypeById(id);
        return new ResponseEntity<>(sandwichType, HttpStatus.OK);
    }

    @GetMapping("shop/{id}")
    public List<SandwichType> findSandwichTypesByShop(@PathVariable int id) throws ShopNotFoundException, SandwichTypeNotFoundException {
        return sandwichTypeService.findSandwichTypesByShop(shopService.findShopById(id));
    }

    @GetMapping("query")
    public ResponseEntity<?> findSandwichType(@RequestParam("name") String name, @RequestParam("shopid") int shopId) throws SandwichTypeNotFoundException, ShopNotFoundException {
        SandwichType sandwichType = sandwichTypeService.findSandwichType(name, shopId);
        return new ResponseEntity<>(sandwichType, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void removeSandwichType(@PathVariable int id) throws SandwichTypeNotFoundException {
        sandwichTypeService.removeSandwichType(id);
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
