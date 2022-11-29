package be.abis.ordersandwich.tablemapper;

import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;

import java.io.Serializable;
import java.util.Objects;

public class SessionParticipantsKey implements Serializable {

    private Session session;
    private Person person;

    public SessionParticipantsKey() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionParticipantsKey that = (SessionParticipantsKey) o;
        return session.equals(that.session) && person.equals(that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, person);
    }
}
