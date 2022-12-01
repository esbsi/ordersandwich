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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="sessionparticipants",
     joinColumns = @JoinColumn(name = "session_id"),
     inverseJoinColumns = @JoinColumn(name = "person_id"))
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

    @Override
    public int hashCode(){
        return name.length();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Session)) return false;
        return this.name.equals(((Session) o).getName())
                && this.startDate.equals(((Session) o).getStartDate())
                && this.endDate.equals(((Session) o).getEndDate());
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

    //Use SessionService.addPersonToSession(), which saves to database. (this is used in some tests)
    public void addPerson(Person person){
        personList.add(person);
    }

}
