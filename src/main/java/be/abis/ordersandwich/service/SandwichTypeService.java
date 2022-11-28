package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;

import java.util.List;

public interface SandwichTypeService {


    List<SandwichType> getSandwichTypes();



    SandwichType addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException;

    SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException;

    SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException;

    void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException;
}
