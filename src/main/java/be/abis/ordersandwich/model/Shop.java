package be.abis.ordersandwich.model;

import be.abis.ordersandwich.repository.FileSandwichTypeRepository;

import java.util.ArrayList;
import java.util.List;
public class Shop {

    private String name;
    private List<SandwichType> sandwichTypeList = new ArrayList<>();

    public Shop(String name) {
        this.name = name;
        FileSandwichTypeRepository f = new FileSandwichTypeRepository(this);
        this.sandwichTypeList = f.getSandwichTypes();
    }


    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SandwichType> getSandwichTypeList() {
        return sandwichTypeList;
    }

    public void setSandwichTypeList(List<SandwichType> sandwichTypeList) {
        this.sandwichTypeList = sandwichTypeList;
    }

}
