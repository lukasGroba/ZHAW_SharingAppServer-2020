package ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.controller;

public class User {

    private Long id;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String firstName, String lastname) {
        this.firstName = firstName;
        this.lastName = lastname;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String valueOf) {
    }
}
