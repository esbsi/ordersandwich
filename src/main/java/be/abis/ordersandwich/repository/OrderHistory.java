package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.OrderTodayNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderHistory implements OrderRepository{

    private List<OrderToday> orderHistory = new ArrayList<>();

    public OrderHistory() {}


    // business

    @Override
    public void addToOrderHistory(OrderToday orderToday) {
        orderHistory.add(orderToday);
    }

    @Override
    public void removeOrderToday(OrderToday orderToday){
        orderHistory.remove(orderToday);
    }

    @Override
    public List<OrderToday> findOrdersToday(LocalDate localDate) throws OrderTodayNotFoundException {
        List<OrderToday> ordersFiltered = orderHistory.stream()
                .filter(orderToday -> orderToday.getDate().isEqual(localDate))
                .collect(Collectors.toList());
        if (ordersFiltered.size() == 0){
            throw new OrderTodayNotFoundException();
        } else return ordersFiltered;
    }

    @Override
    public OrderToday findLastOrderToday(LocalDate localDate) throws OrderTodayNotFoundException {
        return orderHistory.stream()
                .filter(orderToday -> orderToday.getDate().isEqual(localDate))
                .sorted(Comparator.comparing(OrderToday::getClosingTime).reversed())
                .findFirst().orElseThrow(OrderTodayNotFoundException::new);
    }


    // getset

    @Override
    public List<OrderToday> getOrderHistory() {
        return orderHistory;
    }

}
