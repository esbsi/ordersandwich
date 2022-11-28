package be.abis.ordersandwich.model;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;
import be.abis.ordersandwich.exception.PersonNotInSessionException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sessions")
public class Session {
    @SequenceGenerator(name="seqGen",sequenceName="session_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seqGen")
    @Column(name="id")
    private int id;
    @Column(name="session_name")
    private String name;
    @Column(name="startdate")
    private LocalDate startDate;
    @Column(name="enddate")
    private LocalDate endDate;
    @ManyToMany
    private List<Person> personList= new ArrayList<>();

    public Session() {
    }

    public Session(String name) {
        this.name = name;
    }

    public Session(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Session(int id, String name, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return name;
    }


    // business

    public void addPerson(Person p) throws PersonAlreadyInSessionException, NullInputException {
        if(p==null)throw new NullInputException("input is null");
        if(personList.contains(p)) throw new PersonAlreadyInSessionException("person is already in the session");
        if(personList.stream().map(per->per.getFirstName()).anyMatch(x->x.equals(p.getFirstName()))){
            p.setFirstName(p.getFirstName()+"2");
            System.out.println("2 is added to "+p.getFirstName()+" because there are 2 people with the same name");
        }
        personList.add(p);

    }

    public void removePerson(Person p) throws PersonNotInSessionException, NullInputException {
        if(p==null)throw new NullInputException("input is null");
        if (!personList.contains(p)) throw new PersonNotInSessionException(p.getFirstName()+" is not in this session");
        personList.remove(p);

    }


    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
