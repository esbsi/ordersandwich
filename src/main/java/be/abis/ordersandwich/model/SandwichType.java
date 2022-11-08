package be.abis.ordersandwich.model;

public class SandwichType {

    private int id;
    private String name;
    private Double price;
    private String category;
    private Boolean vegetarian;
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
}
