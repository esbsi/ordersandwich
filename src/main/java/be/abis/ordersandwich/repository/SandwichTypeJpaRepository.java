package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SandwichTypeJpaRepository extends JpaRepository<SandwichType, Integer> {


    List<SandwichType> getSandwichTypeByShop(Shop shop);


    SandwichType findSandwichTypeById(int id);

    SandwichType findSandwichTypeByName(String sandwichName) throws SandwichTypeNotFoundException;

}
