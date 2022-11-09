package be.abis.ordersandwich.controller;


import be.abis.ordersandwich.error.ApiError;
import be.abis.ordersandwich.exception.PersonNotFoundException;
import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.FinanceModel;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.ShopRepository;
import be.abis.ordersandwich.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/shop")
@Validated
public class ShopController {

    @Autowired
    ShopService shopService;


    @GetMapping("{id}")
    public ResponseEntity<?> findShopById(@PathVariable int id) throws ShopNotFoundException {
        Shop shop = shopService.findShopById(id);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping("query")
    public ResponseEntity<?> findShop(@RequestParam String name) throws ShopNotFoundException {
        Shop shop = shopService.findShop(name);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> removeShop(@RequestBody Shop shop) throws ShopNotFoundException {
        shopService.removeShop(shop);
        return new ResponseEntity<>(Void.class, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addShop(@RequestBody String name) throws ShopAlreadyExistsException {
        Shop shop = new Shop(name);
        shopService.addShop(shop);
        return new ResponseEntity<>(Void.class, HttpStatus.OK);
    }

}
