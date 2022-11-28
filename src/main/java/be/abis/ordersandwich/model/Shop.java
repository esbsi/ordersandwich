package be.abis.ordersandwich.model;

import javax.persistence.*;

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

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Shop)){
            return false;
        } else return this.getName().equals(((Shop)o).getName());
    }

    @Override
    public int hashCode(){
        return this.getName().length();
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
}
