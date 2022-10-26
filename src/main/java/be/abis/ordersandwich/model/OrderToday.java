package be.abis.ordersandwich.model;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class OrderToday {

    private List<SandwichOrder> order = new ArrayList<>();
    private double totalPrice;
    private Shop shop;
    private Logger log= LogManager.getLogger("exceptionLogger") ;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private LocalDateTime now = LocalDateTime.now();
    private LocalDate date=LocalDate.now();
    private LocalTime closingTime=LocalTime.now();

    public OrderToday() {}
    public OrderToday(Shop shop) {
        this.shop = shop;
    }


    // business

    public String toString() {
        StringBuilder orderStringBuilder = new StringBuilder();
        int i = 0;
        for (SandwichOrder sandwichOrder : order) {
            if(sandwichOrder.getSandwichType()!=null) {
                i += 1;
                if (shop.getName().equals("Pinkys")) {
                    orderStringBuilder.append(i + ". " + sandwichOrder.getSandwichType().getName() + (sandwichOrder.isRauwkost() ? " club" : "") + (sandwichOrder.isWhite() ? " wit" : " grijs") + ".\n " + sandwichOrder.getPerson().getName() + ((sandwichOrder.getComment().equals("") ? "" : (": " + sandwichOrder.getComment()))) + "\n\n");
                } else if (shop.getName().equals("Vleugels")) {
                    orderStringBuilder.append(i + ". " + sandwichOrder.getSandwichType().getName() + (sandwichOrder.isRauwkost() ? " rauwkost" : "") + (sandwichOrder.isWhite() ? " wit" : " grijs") + ".\n " + sandwichOrder.getPerson().getName() + ((sandwichOrder.getComment().equals("") ? "" : (": " + sandwichOrder.getComment()))) + "\n\n");
                } else {
                    orderStringBuilder.append(i + ". " + sandwichOrder.getSandwichType().getName() + (sandwichOrder.isRauwkost() ? " rauwkost" : "") + (sandwichOrder.isWhite() ? " wit" : " grijs") + ".\n " + sandwichOrder.getPerson().getName() + ((sandwichOrder.getComment().equals("") ? "" : (": " + sandwichOrder.getComment()))) + "\n\n");
                }
            }
        } return orderStringBuilder.toString();
    }

    public void orderSandwich(int i,boolean club,boolean white,String comment,Person person) throws TooManySandwichesException, TooLateException {
        if (LocalTime.now().compareTo(closingTime)>0 && date.equals(LocalDate.now())){
            log.error("too late, order is closed");
            throw new TooLateException("too late, order is closed");
        }
        if (order.stream().filter(x->x.getPerson()==person).count()>1) {
            log.error(person.getName()+" already ordered");
            throw new TooManySandwichesException("you already ordered");
        }
        SandwichOrder sandwichOrder = new SandwichOrder(shop.getSandwichTypeList().get(i), club,white,comment,person);
        order.add(sandwichOrder);
    }

    public void noOrder(Person person) throws TooManySandwichesException, TooLateException {
        if(LocalTime.now().compareTo(closingTime)>0 && date.equals(LocalDate.now())){
            log.error(person.getName()+ " too late, order is closed");
            throw new TooLateException("too late, order is closed");
        }
        if(order.stream().anyMatch(x->x.getPerson()==person)) {
            log.error(person.getName()+" already ordered");
            throw new TooManySandwichesException("you already ordered");
        }
        SandwichOrder sandwichOrder = new SandwichOrder(person);
        order.add(sandwichOrder);
        System.out.println(sandwichOrder);
    }

    public void removeOrder(int index) throws TooLateException {
        if(LocalTime.now().compareTo(closingTime)>0 && date.equals(LocalDate.now())){
            log.error("too late, order is closed");
            throw new TooLateException("too late, order is closed");
        }
        order.remove(index);
    }

    public double totalPrice(){
        double sum=0;
        for (SandwichOrder sandwich:order){
            if(sandwich.getSandwichType()!=null && sandwich.getSandwichType().getPrice()!=null) {
                sum += sandwich.getSandwichType().getPrice();
            }
        }
        this.totalPrice=sum;
        return sum;
    }


    // getset

    public DateTimeFormatter getDtf() {
        return dtf;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public Shop getShop() {
        return shop;
    }

    public double getTotalPrice() {
        return totalPrice();
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setDtf(DateTimeFormatter dtf) {
        this.dtf = dtf;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public List<SandwichOrder> getOrder() {
        return order;
    }

    public void setOrder(List<SandwichOrder> order) {
        this.order = order;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
