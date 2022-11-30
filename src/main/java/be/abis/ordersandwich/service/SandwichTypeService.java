package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;

import java.util.List;

public interface SandwichTypeService {


    List<SandwichType> getSandwichTypes();

    SandwichType findSandwichType(String sandwichName, int shopId) throws SandwichTypeNotFoundException;

    SandwichType addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException;

    List<SandwichType> findSandwichtypesByShop(Shop shop) throws SandwichTypeNotFoundException;

    SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException;

    void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException;
}
