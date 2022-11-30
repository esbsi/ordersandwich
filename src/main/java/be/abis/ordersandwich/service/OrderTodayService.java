package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.*;

import java.time.LocalTime;
import java.util.List;

public interface OrderTodayService {

    void orderSandwich(int i, boolean club, boolean grilledVegs, boolean white, String note, Person person) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException;

    void noOrder(Person person) throws TooManySandwichesException, TooLateException, NullInputException;
    void removeOrder(SandwichOrder sandwichOrder) throws TooLateException, NullInputException;
    double totalPrice() throws NullInputException;

    void sendOrder(Shop shop) throws NullInputException;

    List<SandwichOrder> checkMyOrderToday(Person person) throws NullInputException, PersonNotFoundException;

    void toFile(String writing, boolean bool);

    void setClosingTime(LocalTime closingTime);
    OrderToday getOrderToday();
    void setOrderToday(OrderToday orderToday);
    String checkAllOrderedString( Session session);
    List<Person> checkAllOrderedPersons( Session session);
    void overWriteOrder(Shop shop);

    void delete(int id) throws OrderTodayNotFoundException;
    OrderToday findById(int id)throws OrderTodayNotFoundException;

}
