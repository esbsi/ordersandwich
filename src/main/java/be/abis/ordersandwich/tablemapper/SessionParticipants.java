package be.abis.ordersandwich.tablemapper;

import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.Session;

import javax.persistence.*;

@Entity
@Table(name="sessionparticipants")
@IdClass(SessionParticipantsKey.class)
public class SessionParticipants {
    @Id
    @ManyToOne
    @JoinColumn(name="session_id")
    private Session session;
    @Id
    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    public SessionParticipants() {
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
