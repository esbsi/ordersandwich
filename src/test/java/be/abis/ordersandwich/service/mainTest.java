package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.ShopFactory;
import be.abis.ordersandwich.repository.ShopRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
@SpringBootTest
public class mainTest {
    public static void main(String[] args) throws ShopNotFoundException, SandwichTypeNotFoundException, TooLateException, TooManySandwichesException, NullInputException {
        PersonService personService=new PersonServiceImp();
        OrderTodayService orderTodayService=new OrderTodayServiceImp();
        ShopRepository shopRepository=new ShopFactory();
        Shop shop=shopRepository.findShop("Vleugels");
        OrderToday orderToday=new OrderToday();
        orderToday.setShop(shop);
        orderToday.setClosingTime(LocalTime.MAX);
        Person person=new Person("sim");
        Person person2=new Person("sim2");

        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person2,orderToday);
        orderTodayService.orderSandwich(1,true,true,"",person,orderToday);
        personService.removeMyOrder(person,orderToday);

    }
}
