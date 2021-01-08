package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import java.util.List;


public interface UserInterface {

    //===> CRUD methods
    public abstract List<UserModel> getAllUsers();
    public abstract List<UserModelWithPassword> addNewUser(UserModelWithPassword user);
    public abstract List<UserModelWithPassword> deleteUserByMail(String mail);
    public abstract UserModel getUserByMail(String mail);
    public abstract List<UserModelWithPassword> deleteAllUser();

    //===> support methods
    public abstract boolean checkLogin(UserModelWithPassword user);
    public abstract boolean checkMailAllreadyInUse(UserModelWithPassword user);
    public abstract boolean checkMailAllreadyInUse(String mail);

}
