package be.abis.ordersandwich.service;

import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class FinancialManagerTest {

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void monthlyPriceShouldBeXXXX(){

    }

}
