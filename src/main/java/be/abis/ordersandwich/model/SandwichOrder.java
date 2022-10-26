package be.abis.ordersandwich.model;

public class SandwichOrder {

    private SandwichType sandwichType;
    private boolean rauwkost;
//    private boolean noButter;
    private boolean white;
    private String comment;
    private Person person;

    public SandwichOrder() {}

    public SandwichOrder(Person person){
        this.person = person;
    }

    public SandwichOrder(SandwichType sandwichType, boolean rauwkost, boolean white, String comment, Person person) {
        this.sandwichType = sandwichType;
        this.rauwkost = rauwkost;
        this.white = white;
        this.comment = comment;
        this.person = person;
    }


    // getset

    public SandwichType getSandwichType() {
        return sandwichType;
    }

    public void setSandwichType(SandwichType sandwichType) {
        this.sandwichType = sandwichType;
    }

    public boolean isRauwkost() {
        return rauwkost;
    }

    public void setRauwkost(boolean rauwkost) {
        this.rauwkost = rauwkost;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
