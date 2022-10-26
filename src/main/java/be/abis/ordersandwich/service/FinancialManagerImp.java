package be.abis.ordersandwich.service;

import be.abis.ordersandwich.model.Session;
import be.abis.ordersandwich.repository.OrderHistory;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FinancialManagerImp implements FinancialManager{
   @Autowired
   OrderHistory history;

    public double getMonthlyPrice(Shop shop, Month month, int year){
        List<OrderToday> orderHistory=history.getOrderHistory();
        double price=0;
        LocalDateTime dt= LocalDateTime.of(year,month,1,1,0);
        for (int i =0;i<orderHistory.size();i++){
            OrderToday order= orderHistory.get(orderHistory.size()-i-1);
            if (order.getNow().getMonth() == month && order.getNow().getYear() == year && order.getShop() == shop) {
                price += order.getTotalPrice();
            }
            if (order.getNow().isBefore(dt)) {
                break;
            }
        }
        return price;
    }

    public int getAmountOfSandwichesOrdered(Shop shop, Month month,int year){
        List<OrderToday> orderHistory=history.getOrderHistory();
        int totalSandwiches=0;
        LocalDateTime dt= LocalDateTime.of(year,month,1,1,0);
        for (int i =0;i<orderHistory.size();i++){
            OrderToday order= orderHistory.get(orderHistory.size()-i-1);

            if (order.getNow().getMonth() == month && order.getNow().getYear() == year && order.getShop() == shop) {

                totalSandwiches += order.getOrder().stream().filter(x->x.getSandwichType()!=null).count();


            }
            if (order.getNow().isBefore(dt)) {
                break;
            }
        }
        return totalSandwiches;
    }

    public double totalPriceOfCourse(Session session){
        double sum=0;
        List<OrderToday> orderHistory=history.getOrderHistory();
        List<OrderToday> courseOrders=orderHistory.stream().filter(x->x.getDate().compareTo(session.getStartDate())>=0 && x.getDate().compareTo(session.getEndDate())<=0).collect(Collectors.toList());
        for(OrderToday order:courseOrders){
            sum+=order.getOrder().stream().filter(x->x.getPerson().getCourse()== session).map(x->x.getSandwichType().getPrice()).mapToDouble(Double::doubleValue).sum();
        }
        return sum;

    }

    public double dayPriceOfSession(Session session, LocalDate day){
        double sum=0;
        List<OrderToday> orderHistory=history.getOrderHistory();
        List<OrderToday> courseOrders=orderHistory.stream().filter(x->x.getDate().equals(day)).collect(Collectors.toList());
        for(OrderToday order:courseOrders){
            sum+=order.getOrder().stream().filter(x->x.getPerson().getCourse()== session).map(x->x.getSandwichType().getPrice()).mapToDouble(Double::doubleValue).sum();
        }
        return sum;

    }


    public double averagePriceSandwich(Shop shop, Month month,int year){
        return getMonthlyPrice(shop, month, year)/getAmountOfSandwichesOrdered(shop, month, year);
    }
}
