package be.abis.ordersandwich.model;

import be.abis.ordersandwich.repository.FileSandwichTypeRepository;

import java.util.ArrayList;
import java.util.List;
public class Shop {

    private int id;
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Shop)){
            return false;
        } else return this.getName().equals(((Shop)o).getName());
    }

    @Override
    public int hashCode(){
        return this.getName().length();
    }


    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
