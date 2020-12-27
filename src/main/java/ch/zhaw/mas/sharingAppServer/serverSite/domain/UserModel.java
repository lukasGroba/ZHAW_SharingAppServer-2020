package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import java.io.Serializable;
//@Data
//@Entity

public class UserModel implements Serializable {

    private String username;
    private String mail;
    private String password;

    public UserModel() {
    }

    public UserModel(UserModel user) {
        this.username = user.username;
        this.mail = user.mail; //also ID
        this.password = user.password; //password
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s']", username);
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String valueOf) {
    }

}
