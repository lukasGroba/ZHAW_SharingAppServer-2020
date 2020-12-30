package ch.zhaw.mas.sharingAppServer.serverSite.domain;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
//@Data

//@Entity //Map Entity UserModel to table UserModel
public class UserModelDb implements Serializable {

    private String username;
    @Id
    private String mail;
    private String password;

    public UserModelDb() {
    }

    public UserModelDb(UserModelDb user) {
        this.username = user.username;
        this.mail = user.mail; //also ID
        this.password = user.password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[username=%s, mail='%s', password='%s']", username, mail, password);
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

