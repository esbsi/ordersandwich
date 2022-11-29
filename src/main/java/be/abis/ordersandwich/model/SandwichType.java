package be.abis.ordersandwich.model;

import javax.persistence.*;

@Entity
@Table(name="sandwichtypes")
public class SandwichType {
    @SequenceGenerator(name="seqGen",sequenceName="sandwichtype_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="id")
    private int id;
    @ManyToOne
    @JoinColumn(name="shop_id")
    private Shop shop;
    @Column (name="sandwich_name")
    private String name;
    @Column(name="price")
    private Double price;
    @Column (name="sandwich_category")
    private String category;
    @Column (name = "vegetarian")
    private Boolean vegetarian;
    @Column(name="description")
    private String description;


    public SandwichType() {
    }

    public SandwichType(String name) {
        this.name = name;
    }

    public SandwichType(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public SandwichType(String name, Double price, String category, Boolean vegetarian) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.vegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof SandwichType)){
            return false;
        } else return this.getName().equals(((SandwichType)o).getName());
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
