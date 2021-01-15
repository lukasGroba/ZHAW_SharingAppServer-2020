package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import java.io.Serializable;

/**
 * Model for a User containing the password of the User. Used for hiding password in the response to the client.
 * @author Adrian Fischer
 */

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
