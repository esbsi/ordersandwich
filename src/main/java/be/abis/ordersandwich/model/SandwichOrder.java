package be.abis.ordersandwich.model;

import javax.persistence.*;
@Entity
@Table(name="sessions")
public class     SandwichOrder {
    @SequenceGenerator(name="seqGen",sequenceName="session_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="id")
    private int id;
    @ManyToOne
    @JoinColumn (name="sandwichtype_id")
    private SandwichType sandwichType;
    @Column(name="rauwkost")
    private boolean rauwkost;
    @Column(name="grilledvegs")
    private boolean grilledVegs;
    //    private boolean noButter;
    @Column(name="white")
    private boolean white;
    @Column(name="comment")
    private String comment;
    @ManyToOne
    @JoinColumn (name="person_id")
    private Person person;

    public SandwichOrder() {
    }

    public SandwichOrder(Person person){
        this();
        this.person = person;
    }

    public SandwichOrder(SandwichType sandwichType, boolean rauwkost, boolean grilledVegs, boolean white, String comment, Person person) {
        this(person);
        this.sandwichType = sandwichType;
        this.rauwkost = rauwkost;
        this.grilledVegs = grilledVegs;
        this.white = white;
        this.comment = comment;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGrilledVegs() {
        return grilledVegs;
    }

    public void setGrilledVegs(boolean grilledVegs) {
        this.grilledVegs = grilledVegs;
    }
}
