package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.SandwichOrder;
import be.abis.ordersandwich.repository.OrderRepository;
import be.abis.ordersandwich.repository.SandwichTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderTodayServiceImp implements OrderTodayService{

    @Autowired
    SandwichTypeRepository sandwichTypeRepository;
    @Autowired
    OrderRepository orderHistory;
    OrderToday orderToday;


    // business

    @Override
    public void orderSandwich(int i, boolean club, boolean grilledVegs, boolean white, String comment, Person person) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException {
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        if (LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){

            throw new TooLateException("too late, order is closed");
        }
        if (orderToday.getOrder().stream().filter(x -> x.getPerson() == person).collect(Collectors.toList()).size()>0) {
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
        SandwichOrder sandwichOrder = new SandwichOrder(sandwichTypeRepository.getSandwichTypes().get(i), club, grilledVegs, white, comment, person);
        orderToday.getOrder().add(sandwichOrder);
    }

    @Override
    public void noOrder(Person person) throws TooManySandwichesException, TooLateException, NullInputException {
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

    @Override
    public void removeOrder(int index) throws TooLateException, NullInputException {
        if ( orderToday==null ) throw new NullInputException("null input");
        if(LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){

            throw new TooLateException("too late, order is closed");
        }
        orderToday.getOrder().remove(index);
    }

    @Override
    public double totalPrice() throws NullInputException {
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

    @Override
    public void sendOrder() throws NullInputException {
        if(orderHistory==null || orderToday== null) throw new NullInputException("some of the inputs are null");
        this.totalPrice();
        orderToday.getTotalPrice();
        orderToday.setNow(LocalDateTime.now());
        orderHistory.addToOrderHistory(orderToday);
        toFile(orderToday.toString(),false);
/*
        List<Session> sessionList =orderToday.getOrder().stream().map(p->p.getPerson().getSession()).distinct().collect(Collectors.toList());
        for (Session session : sessionList){
            toFile(sessionService.checkAllOrdered(orderToday, session),true);
        }
 */
    }

    @Override
    public List<Integer> checkMyOrderToday(Person person) throws NullInputException {
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        int i=0;
        int j=0;
        List<Integer> integerList= new ArrayList<>();
        for(SandwichOrder order:orderToday.getOrder()){
            if(order.getPerson()==person){
                if(order.getSandwichType()!=null){
                    j++;
                    System.out.println(person.getName()+" order "+j+": "+ order.getSandwichType().getName()+(order.isRauwkost() ? " club" : "") + (order.isWhite() ? " wit" : " grijs") + ".\n "  + ((order.getComment().equals("") ? "" : (": " + order.getComment()))));
                    integerList.add(i);
                }else {
                    integerList.add(i);
                    System.out.println("doesn't want anything today");
                }
            }
            i++;
        }
        return integerList;
    }

    @Override
    public void toFile(String writing, boolean bool){
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("c:\\temp\\javasessions\\broodjes2.txt", bool);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(writing);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            }
            catch (IOException io) {}
        }
    }




    // getset

    @Override
    public void setClosingTime(LocalTime closingTime) {
        orderToday.setClosingTime(closingTime);
    }

    @Override
    public OrderToday getOrderToday() {
        return orderToday;
    }

    @Override
    public void setOrderToday(OrderToday orderToday) {
        this.orderToday = orderToday;
    }
}
