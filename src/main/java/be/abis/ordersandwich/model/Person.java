package be.abis.ordersandwich.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="person")
@Table(name="persons")
public class Person {
    @SequenceGenerator(name="seqGen",sequenceName="person_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="id")
    private int id;
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;

    public Person() {}

    public Person(String name) {
        this.firstName = name;
    }

    public Person(int id, String name) {
        this.id = id;
        this.firstName = name;
    }

    @Override
    public String toString() {
        return firstName;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && firstName.equals(person.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
    }
}
