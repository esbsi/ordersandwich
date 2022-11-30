package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;

import java.util.List;

public interface SandwichTypeService {


    List<SandwichType> getSandwichTypes();

    SandwichType findSandwichType(String sandwichName, int shopId) throws SandwichTypeNotFoundException;

    SandwichType addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException;

    List<SandwichType> findSandwichTypesByShop(Shop shop) throws SandwichTypeNotFoundException, ShopNotFoundException;

    SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException;

    void removeSandwichType(int id) throws SandwichTypeNotFoundException;
}
