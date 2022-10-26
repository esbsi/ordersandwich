package be.abis.ordersandwich.model;

import be.abis.ordersandwich.repository.FileSandwichTypeRepository;

import java.util.ArrayList;
import java.util.List;
public class Shop {

    private String name;


    public Shop(String name) {
        this.name = name;

    }

    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
