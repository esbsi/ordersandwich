package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.SandwichType;

import java.util.List;

public interface SandwichTypeRepository {

    List<SandwichType> getSandwichTypes();
    SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException;

}
