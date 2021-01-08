package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import java.io.Serializable;

/**
 *
 * @author Adrian Fischer
 */

public class UserModel implements Serializable {

    private String username;
    private String mail;

    public UserModel() {
    }

    public UserModel(UserModel user) {
        this.username = user.username;
        this.mail = user.mail;
    }

    public UserModel(String username, String mail) {
        this.username = username;
        this.mail = mail;
    }

    //@Override
    public String toString() {
        return String.format(
                "User[username=%s, mail='%s']", username, mail);
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
