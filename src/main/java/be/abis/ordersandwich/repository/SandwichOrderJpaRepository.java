package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.SandwichOrder;
import be.abis.ordersandwich.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SandwichOrderJpaRepository extends JpaRepository<SandwichOrder, Integer> {






}