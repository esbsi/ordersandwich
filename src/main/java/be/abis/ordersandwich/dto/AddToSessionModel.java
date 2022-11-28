package be.abis.ordersandwich.dto;

import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;

public class AddToSessionModel {
    private Session session;
    private Person person;

    public AddToSessionModel() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
