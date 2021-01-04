package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import lombok.Data;

import java.io.Serializable;

public class UserModelWithPassword extends UserModel implements Serializable {

    private String password;

    public UserModelWithPassword(String username, String mail, String password) {
        super(username, mail);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}
