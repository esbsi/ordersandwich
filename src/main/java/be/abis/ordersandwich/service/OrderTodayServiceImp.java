package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.*;
import be.abis.ordersandwich.repository.OrderJpaRepository;
import be.abis.ordersandwich.repository.SandwichTypeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderTodayServiceImp implements OrderTodayService{

    @Autowired
    SandwichTypeJpaRepository sandwichTypeRepository;
    @Autowired
    OrderJpaRepository orderHistory;
    OrderToday orderToday;

    @PostConstruct
    public void init() {
        orderToday=orderHistory.getLastOrderToday().get(0);
    }

    // business

    @Override
    @Transactional
    public void orderSandwich(int i, boolean club, boolean grilledVegs, boolean white, String note, Person person) throws TooManySandwichesException, TooLateException, NullInputException, SandwichTypeNotFoundException {
        if (person==null || orderToday==null ) {
            throw new NullInputException("null input");
        }
        if (LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){
            throw new TooLateException("too late, order is closed");
        }
        if (orderToday.getOrder().stream().filter(x -> x.getPerson().equals(person)).collect(Collectors.toList()).size()>0) {
            SandwichOrder sandwichOrder =orderToday.getOrder().stream().filter(x -> x.getPerson().equals(person)).findFirst().get();
            if (sandwichOrder.getSandwichType()== null ) {
                orderToday.remove(sandwichOrder);
            }
        }
        if (orderToday.getOrder().stream().filter(x->x.getPerson().equals(person)).count()>1) {
            throw new TooManySandwichesException("you already ordered");
        }
        SandwichOrder sandwichOrder = new SandwichOrder(sandwichTypeRepository.findSandwichTypeById(i), club, grilledVegs, white, note, person);
        orderToday.getOrder().add(sandwichOrder);
        orderHistory.save(orderToday);
        orderToday=orderHistory.getLastOrderToday().get(0);
    }

    @Override
    public void noOrder(Person person) throws TooManySandwichesException, TooLateException, NullInputException {
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        if(LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){

            throw new TooLateException("too late, order is closed");
        }
        if(orderToday.getOrder().stream().anyMatch(x->x.getPerson().equals(person))) {

            throw new TooManySandwichesException("you already ordered");
        }
        SandwichOrder sandwichOrder = new SandwichOrder(person);
        orderToday.add(sandwichOrder);
        //System.out.println(sandwichOrder);
        orderHistory.save(orderToday);
        orderToday=orderHistory.getLastOrderToday().get(0);
    }

    @Override
    public void removeOrder(SandwichOrder sandwichOrder) throws TooLateException, NullInputException {
        if ( orderToday==null || sandwichOrder==null ) throw new NullInputException("null input");
        if(LocalTime.now().compareTo(orderToday.getClosingTime())>0 && orderToday.getDate().equals(LocalDate.now())){

            throw new TooLateException("too late, order is closed");
        }
        orderToday.getOrder().remove(sandwichOrder);
        orderHistory.save(orderToday);
        orderToday=orderHistory.getLastOrderToday().get(0);
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
    public void sendOrder(Shop shop) throws NullInputException {
        if(orderHistory==null || orderToday== null) throw new NullInputException("some of the inputs are null");
        this.totalPrice();
        orderToday.getTotalPrice();
        orderToday.setDate(LocalDate.now());
        orderHistory.save(orderToday);
        toFile(orderToday.toString(),false);
        setOrderToday(new OrderToday(shop));
/*
        List<Session> sessionList =orderToday.getOrder().stream().map(p->p.getPerson().getSession()).distinct().collect(Collectors.toList());
        for (Session session : sessionList){
            toFile(sessionService.checkAllOrdered(orderToday, session),true);
        }
 */
    }

    @Override
    public List<SandwichOrder> checkMyOrderToday(Person person) throws NullInputException {
        System.out.println("hey");
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        List<Person> personList=getOrderToday().getOrder().stream().map(x->x.getPerson()).collect(Collectors.toList());
        List<SandwichOrder> orderList = new ArrayList<>();
        if(!personList.contains(person)) return orderList;
        for(SandwichOrder order:orderToday.getOrder()){
            if(order.getPerson().equals(person)){
                orderList.add(order);
            }
        } return orderList;
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

    @Override
    public String checkAllOrderedString(Session session){
        List<Person> personList=checkAllOrderedPersons(session);

        String string="";
        if ( personList.size()>0){
            for(Person person : personList){
                string+=session.getName() + ": " + person.getFirstName() + " hasn't ordered." +"\n";
            }
        }else {
            string+="All students in " + session.getName() + " session have ordered." +"\n";
        }

        return string;
    }

    public List<Person> checkAllOrderedPersons(Session session){

        List<Person> personList =new ArrayList<>();

        for (Person person : session.getPersonList()) {
            boolean personOrdered = false;
            for (SandwichOrder sandwichOrder : orderToday.getOrder()){
                if (sandwichOrder.getPerson().equals(person)){
                    personOrdered = true;
                }
            } if (!personOrdered){
                personList.add(person);
            }
        }
        return personList;
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
    //todo everything id saved now otherwise  you cant  get it back after a restart
    //so a new thing for the set method is needed to delete the last one if neccesarry
    public void setOrderToday(OrderToday orderToday) {
        this.orderToday = orderToday;
        orderHistory.save(orderToday);
    }
    public void overWriteOrder(Shop shop){
        orderHistory.delete(orderToday);
        setOrderToday(new OrderToday(shop));
    }
    public void delete(int id) throws OrderTodayNotFoundException {
        OrderToday o=    orderHistory.findOrderTodayById(id);
        if(o==null) throw new OrderTodayNotFoundException("order not found");
        orderHistory.delete(o);
    }

    @Override
    public OrderToday findById(int id) throws OrderTodayNotFoundException {
        OrderToday o=    orderHistory.findOrderTodayById(id);
        if(o==null) throw new OrderTodayNotFoundException("order not found");
        return o;
    }

    @Override
    public String orderTodayToString() throws NullInputException {
        if(orderHistory==null || orderToday== null) throw new NullInputException("some of the inputs are null");
        return orderToday.toString();
        }


}
