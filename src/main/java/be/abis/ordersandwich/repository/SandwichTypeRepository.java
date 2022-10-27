package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;

import java.util.List;

public interface SandwichTypeRepository {

    void setShop(Shop shop);
    Shop getShop();
    List<SandwichType> getSandwichTypes();

    void addSandwichType(SandwichType sandwichType);
    SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException;
    void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException;

}
