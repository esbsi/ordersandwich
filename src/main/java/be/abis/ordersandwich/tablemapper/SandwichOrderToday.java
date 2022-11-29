package be.abis.ordersandwich.tablemapper;

import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.SandwichOrder;

import javax.persistence.*;

@Entity
@Table(name="sandwichordertoday")
@IdClass(SandwichOrderTodayKey.class)
public class SandwichOrderToday {
    @Id
    @ManyToOne
    @JoinColumn(name="sandwichOrder_id")
    private SandwichOrder sandwichOrder_id;
    @Id
    @ManyToOne
    @JoinColumn (name="orderHistory_id")
    private OrderToday orderHistory_id;

}
