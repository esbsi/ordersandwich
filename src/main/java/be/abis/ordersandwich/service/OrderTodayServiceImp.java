package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.SandwichOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
@Service
public class OrderTodayServiceImp implements OrderTodayService{
    private Logger log= LogManager.getLogger("exceptionLogger") ;

    public void orderSandwich(int i, boolean club, boolean white, String comment, Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException {
        if (LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){
            log.error("too late, order is closed");
            throw new TooLateException("too late, order is closed");
        }
        if (orderToday.getOrder().stream().filter(x->x.getPerson()==person).count()>1) {
            log.error(person.getName()+" already ordered");
            throw new TooManySandwichesException("you already ordered");
        }
        SandwichOrder sandwichOrder = new SandwichOrder(orderToday.getShop().getSandwichTypeList().get(i), club,white,comment,person);
        orderToday.getOrder().add(sandwichOrder);
    }

    public void noOrder(Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException {
        if(LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){
            log.error(person.getName()+ " too late, order is closed");
            throw new TooLateException("too late, order is closed");
        }
        if(orderToday.getOrder().stream().anyMatch(x->x.getPerson()==person)) {
            log.error(person.getName()+" already ordered");
            throw new TooManySandwichesException("you already ordered");
        }
        SandwichOrder sandwichOrder = new SandwichOrder(person);
        orderToday.getOrder().add(sandwichOrder);
        //System.out.println(sandwichOrder);
    }

    public void removeOrder(int index,OrderToday orderToday) throws TooLateException {
        if(LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){
            log.error("too late, order is closed");
            throw new TooLateException("too late, order is closed");
        }
        orderToday.getOrder().remove(index);
    }

    public double totalPrice(OrderToday orderToday){
        double sum=0;
        for (SandwichOrder sandwich:orderToday.getOrder()){
            if(sandwich.getSandwichType()!=null && sandwich.getSandwichType().getPrice()!=null) {
                sum += sandwich.getSandwichType().getPrice();
            }
        }
        orderToday.setTotalPrice(sum);
        return sum;
    }
}
