package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SandwichTypeJpaRepository extends JpaRepository<SandwichType, Integer> {

    void setShop(Shop shop);
    Shop getShop();
    List<SandwichType> getSandwichTypes();

    void addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException;

    SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException;

    SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException;
    void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException;

}
