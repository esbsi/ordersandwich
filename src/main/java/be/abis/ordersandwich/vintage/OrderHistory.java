package be.abis.ordersandwich.vintage;

import be.abis.ordersandwich.exception.OrderTodayNotFoundException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.repository.OrderJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderHistory implements OrderJpaRepository {

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
    public OrderToday findOrderToday(int id) throws OrderTodayNotFoundException {
        return orderHistory.stream()
                .filter(orderToday -> id == orderToday.getId())
                .findFirst().orElseThrow(OrderTodayNotFoundException::new);
    }

    // Redundant method?
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
