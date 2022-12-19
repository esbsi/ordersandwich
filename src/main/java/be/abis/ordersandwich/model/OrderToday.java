package be.abis.ordersandwich.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="orderhistory")
public class OrderToday {

    @SequenceGenerator(name="seqGen",sequenceName="orderhistory_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="id")
    private int id;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="sandwichorderstoday",
            joinColumns = @JoinColumn(name = "orderhistory_id"),
            inverseJoinColumns = @JoinColumn(name = "sandwichorder_id"))
    private List<SandwichOrder> order = new ArrayList<>();
    @Column (name="totalprice")
    private double totalPrice;
    @ManyToOne
    @JoinColumn (name="shop_id")
    private Shop shop;
    @Column(name="senddate")
    private LocalDate date = LocalDate.now();
    @Column(name="closingtime")
    private LocalTime closingTime;

    public OrderToday() {
    }

    public OrderToday(Shop shop) {
        this();
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
                    orderStringBuilder.append(i + ". " + sandwichOrder.getSandwichType().getName() + (sandwichOrder.isRauwkost() ? " club" : "") + (sandwichOrder.isWhite() ? " wit" : " grijs") + ".\n " + sandwichOrder.getPerson().getFirstName() + ((sandwichOrder.getNote().equals("") ? "" : (": " + sandwichOrder.getNote()))) + "\n\n");
                } else if (shop.getName().equals("Vleugels")) {
                    orderStringBuilder.append(i + ". " + sandwichOrder.getSandwichType().getName() + (sandwichOrder.isRauwkost() ? " rauwkost" : "") + (sandwichOrder.isGrilledVegs() ? " gegrilde groenten" : "") + (sandwichOrder.isWhite() ? " wit" : " grijs") + ".\n " + sandwichOrder.getPerson().getFirstName() + ((sandwichOrder.getNote().equals("") ? "" : (": " + sandwichOrder.getNote()))) + "\n\n");
                } else {
                    orderStringBuilder.append(i + ". " + sandwichOrder.getSandwichType().getName() + (sandwichOrder.isRauwkost() ? " rauwkost" : "") + (sandwichOrder.isWhite() ? " wit" : " grijs") + ".\n " + sandwichOrder.getPerson().getFirstName() + ((sandwichOrder.getNote().equals("") ? "" : (": " + sandwichOrder.getNote()))) + "\n\n");
                }
            }
        } return orderStringBuilder.toString();
    }

    public void add(SandwichOrder sandwichOrder){
        order.add(sandwichOrder);
    }
    public void remove(SandwichOrder sandwichOrder){
        order.remove(sandwichOrder);
    }


    // getset

    public Shop getShop() {
        return shop;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
