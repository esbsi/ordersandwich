package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StaffServiceImp implements StaffService{

    @Autowired
    SessionService sessionService;

    public OrderToday sendOrder(OrderToday orderToday, OrderHistory orderHistory, Shop shopTomorrow){
        orderToday.getTotalPrice();
        orderToday.setNow(LocalDateTime.now());
        orderHistory.addToOrderHistory(orderToday);

        List<Session> sessionList =orderToday.getOrder().stream().map(p->p.getPerson().getCourse()).distinct().collect(Collectors.toList());
        toFile(orderToday.toString(),false);

        for (Session session : sessionList){
            toFile(sessionService.checkAllOrdered(orderToday, session),true);
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
