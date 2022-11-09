package be.abis.ordersandwich.model;

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
