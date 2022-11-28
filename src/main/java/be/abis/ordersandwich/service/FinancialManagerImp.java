package be.abis.ordersandwich.service;

import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class FinancialManagerImp implements FinancialManager{
   @Autowired
   OrderJpaRepository history;

    public double getMonthlyPrice(Shop shop, Month month, int year){
        List<OrderToday> orderHistory=history.getOrderHistory();
        double price=0;
        LocalDate dt= LocalDate.of(year,month,1);
        for (int i =0;i<orderHistory.size();i++){
            OrderToday order= orderHistory.get(orderHistory.size()-i-1);
            if (order.getDate().getMonth() == month && order.getDate().getYear() == year && order.getShop() == shop) {
                price += order.getTotalPrice();
            }
            if (order.getDate().isBefore(dt)) {
                break;
            }
        }
        return price;
    }

    public int getAmountOfSandwichesOrdered(Shop shop, Month month,int year){
        List<OrderToday> orderHistory=history.getOrderHistory();
        int totalSandwiches=0;
        LocalDate dt= LocalDate.of(year,month,1);
        for (int i =0;i<orderHistory.size();i++){
            OrderToday order= orderHistory.get(orderHistory.size()-i-1);

            if (order.getDate().getMonth() == month && order.getDate().getYear() == year && order.getShop() == shop) {

                totalSandwiches += order.getOrder().stream().filter(x->x.getSandwichType()!=null).count();


            }
            if (order.getDate().isBefore(dt)) {
                break;
            }
        }
        return totalSandwiches;
    }
/*
    public double totalPriceOfSession(Session session){
        double sum=0;
        List<OrderToday> orderHistory=history.getOrderHistory();
        List<OrderToday> sessionOrders=orderHistory.stream().filter(x->x.getDate().compareTo(session.getStartDate())>=0 && x.getDate().compareTo(session.getEndDate())<=0).collect(Collectors.toList());
        //need to fix this later
        for(OrderToday order:sessionOrders){
            order.getOrder().stream().filter(x->session.getPersonList().contains(x.getPerson()));
        }
        /*
        for(OrderToday order:sessionOrders){
            sum+=order.getOrder().stream().filter(x->x.getPerson().getSession()== session).map(x->x.getSandwichType().getPrice()).mapToDouble(Double::doubleValue).sum();
        }


        return sum;

    }

    public double dayPriceOfSession(Session session, LocalDate day){
        double sum=0;
        List<OrderToday> orderHistory=history.getOrderHistory();
        List<OrderToday> sessionOrders=orderHistory.stream().filter(x->x.getDate().equals(day)).collect(Collectors.toList());
        /*
        for(OrderToday order:sessionOrders){
            sum+=order.getOrder().stream().filter(x->x.getPerson().getSession()== session).map(x->x.getSandwichType().getPrice()).mapToDouble(Double::doubleValue).sum();
        }


        return sum;

    }
    */


    public double averagePriceSandwich(Shop shop, Month month,int year){
        return getMonthlyPrice(shop, month, year)/getAmountOfSandwichesOrdered(shop, month, year);
    }
}
