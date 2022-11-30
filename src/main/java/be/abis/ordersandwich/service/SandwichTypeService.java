package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.SandwichType;

import java.util.List;

public interface SandwichTypeService {


    List<SandwichType> getSandwichTypes();

    SandwichType findSandwichType(String sandwichName, int shopId) throws SandwichTypeNotFoundException;

    SandwichType addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException;

    SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException;

    void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException;
}
