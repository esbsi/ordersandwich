package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.SandwichOrder;
import be.abis.ordersandwich.repository.SandwichTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Service
public class OrderTodayServiceImp implements OrderTodayService{

    @Autowired
    SandwichTypeRepository sandwichTypeRepository;

    public void orderSandwich(int i, boolean club, boolean white, String comment, Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException {
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        if (LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){

            throw new TooLateException("too late, order is closed");
        }
        if (orderToday.getOrder().size()>0) {
            SandwichOrder sandwichOrder =orderToday.getOrder().stream().filter(x -> x.getPerson() == person).findFirst().get();
            if (sandwichOrder.getSandwichType()== null ) {
                orderToday.remove(sandwichOrder);

            }
        }
        if (orderToday.getOrder().stream().filter(x->x.getPerson()==person).count()>1) {

            throw new TooManySandwichesException("you already ordered");
        }
        sandwichTypeRepository.setShop(orderToday.getShop());

        if (i>sandwichTypeRepository.getSandwichTypes().size()-1) throw new SandwichTypeNotFoundException("index too high");
        SandwichOrder sandwichOrder = new SandwichOrder(sandwichTypeRepository.getSandwichTypes().get(i), club,white,comment,person);
        orderToday.getOrder().add(sandwichOrder);
    }

    public void noOrder(Person person, OrderToday orderToday) throws TooManySandwichesException, TooLateException, NullInputException {
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        if(LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){

            throw new TooLateException("too late, order is closed");
        }
        if(orderToday.getOrder().stream().anyMatch(x->x.getPerson()==person)) {

            throw new TooManySandwichesException("you already ordered");
        }
        SandwichOrder sandwichOrder = new SandwichOrder(person);
        orderToday.add(sandwichOrder);
        //System.out.println(sandwichOrder);
    }

    public void removeOrder(int index,OrderToday orderToday) throws TooLateException, NullInputException {
        if ( orderToday==null ) throw new NullInputException("null input");
        if(LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){

            throw new TooLateException("too late, order is closed");
        }
        orderToday.getOrder().remove(index);
    }

    public double totalPrice(OrderToday orderToday) throws NullInputException {
        if ( orderToday==null ) throw new NullInputException("null input");
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
