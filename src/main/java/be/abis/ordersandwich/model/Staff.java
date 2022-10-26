package be.abis.ordersandwich.model;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class Staff extends Person {


    public void orderSandwich(int pos,boolean club,boolean white,String comment,OrderToday o,Person p) throws TooManySandwichesException, TooLateException {
        o.orderSandwich(pos,club,white,comment,p);
    }

    public void removeOrder(Person p,OrderToday orderToday) throws TooLateException {
        p.removeMyOrder(orderToday);
    }

    public void noOrderToday(OrderToday o,Person person ) throws TooManySandwichesException, TooLateException {
        o.noOrder(person);

    }

    public OrderToday sendOrder(OrderToday orderToday,OrderHistory orderHistory,Shop shopTomorrow){
        orderToday.totalPrice();
        orderToday.setNow(LocalDateTime.now());
        orderHistory.addToOrderHistory(orderToday);

        List<Session> sessionList =orderToday.getOrder().stream().map(p->p.getPerson().getCourse()).distinct().collect(Collectors.toList());
        toFile(orderToday.toString(),false);

        for (Session session : sessionList){
            toFile(session.checkAllOrdered(orderToday),true);
        }


        return new OrderToday(shopTomorrow);
    }

    public void toFile(String writing, boolean bool){
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter("c:\\temp\\javacourses\\broodjes2.txt", bool);
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

    public  OrderToday changeShopCancelCurrent(Shop shop){
        return new OrderToday(shop);
    }

}
