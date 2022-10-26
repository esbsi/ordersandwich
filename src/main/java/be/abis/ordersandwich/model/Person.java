package be.abis.ordersandwich.model;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {

    private String name;
    private Session session;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    // business

    public void orderSandwich(int pos, boolean club, boolean white, String comment, OrderToday o) throws TooManySandwichesException, TooLateException {
        o.orderSandwich(pos,club,white,comment,this);
    }

    public void noOrderToday(OrderToday o) throws TooManySandwichesException, TooLateException {
        o.noOrder(this);
    }

    public List<Integer>checkMyOrderToday(OrderToday orderToday){
        int i=0;
        int j=0;
        List<Integer> integerList= new ArrayList<>();
        for(SandwichOrder order:orderToday.getOrder()){
            if(order.getPerson()==this){
                j++;
                System.out.println(this.getName()+" order "+j+": "+ order.getSandwichType().getName()+(order.isRauwkost() ? " club" : "") + (order.isWhite() ? " wit" : " grijs") + ".\n "  + ((order.getComment().equals("") ? "" : (": " + order.getComment()))));
                integerList.add(i);
            }
            i++;
        }
        return integerList;
    }

    public void removeMyOrder(OrderToday orderToday) throws TooLateException {
        List<Integer> integerList=checkMyOrderToday(orderToday);
        if (integerList.size()==0){
            System.out.println("nothing ordered");
        }else {
            Scanner scan = new Scanner(System.in);
            System.out.println("do you want to delete an order?");
            System.out.println("Y/N");
            String toDelete = scan.nextLine();
            if (toDelete.equals("Y")) {
                System.out.println("which order do you want to delete?");
                System.out.println("Choose from 1 to "+integerList.size());
                int oderToDelete = scan.nextInt();
                int correctPos=oderToDelete-1;
                orderToday.removeOrder(correctPos);
            } else {
                System.out.println("nothing will be deleted");
            }
        }
    }


    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Session getCourse() {
        return session;
    }

    public void setCourse(Session session) {
        this.session = session;
    }

}
