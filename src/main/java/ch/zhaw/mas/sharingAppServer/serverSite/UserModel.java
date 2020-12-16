package ch.zhaw.mas.sharingAppServer.serverSite;

import lombok.Data;

import java.io.Serializable;
//@Data
//@Entity

public class UserModel implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String eMail;

    private static Long countingID = Long.valueOf(0);

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }

    public UserModel(UserModel user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.eMail = user.geteMail();
        this.id = countingID;
        countingID++;
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

    public String geteMail() {
        return eMail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setId(String valueOf) {
    }

    /*public Long getID() {
        return id;
    }

     */

}
