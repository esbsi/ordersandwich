package be.abis.ordersandwich.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="shops")
public class Shop {

    @SequenceGenerator(name="seqGen",sequenceName="shop_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="id")
    private int id;
    @Column(name="shop_name")
    private String name;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }

    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
    }


    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id == shop.id && name.equals(shop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
