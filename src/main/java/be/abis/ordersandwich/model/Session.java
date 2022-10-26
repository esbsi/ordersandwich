package be.abis.ordersandwich.model;

import be.abis.ordersandwich.exception.PersonAlreadyInSessionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Person> personList= new ArrayList<>();

    public Session(String name) {
        this.name = name;
    }

    public Session(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String toString() {
        return name;
    }


    // business

    public void addPerson(Person p) throws PersonAlreadyInSessionException {
        if(personList.contains(p)) throw new PersonAlreadyInSessionException("person is already in the course");
        if(personList.stream().map(per->per.getName()).anyMatch(x->x.equals(p.getName()))){
            p.setName(p.getName()+"2");
            System.out.println("2 is added to "+p.getName()+" because there are 2 people with the same name");
        }
        personList.add(p);
        p.setCourse(this);
    }

    public void removePerson(Person p){
        personList.remove(p);
        p.setCourse(null);
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

}
