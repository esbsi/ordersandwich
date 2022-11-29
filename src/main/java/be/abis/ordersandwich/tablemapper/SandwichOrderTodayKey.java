package be.abis.ordersandwich.tablemapper;

import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.SandwichOrder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

public class SandwichOrderTodayKey implements Serializable {
    private SandwichOrder sandwichOrder_id;

    private OrderToday orderHistory_id;

    public SandwichOrderTodayKey() {
    }

    public SandwichOrder getSandwichOrder_id() {
        return sandwichOrder_id;
    }

    public void setSandwichOrder_id(SandwichOrder sandwichOrder_id) {
        this.sandwichOrder_id = sandwichOrder_id;
    }

    public OrderToday getOrderHistory_id() {
        return orderHistory_id;
    }

    public void setOrderHistory_id(OrderToday orderHistory_id) {
        this.orderHistory_id = orderHistory_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SandwichOrderTodayKey that = (SandwichOrderTodayKey) o;
        return sandwichOrder_id.equals(that.sandwichOrder_id) && orderHistory_id.equals(that.orderHistory_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sandwichOrder_id, orderHistory_id);
    }
}
