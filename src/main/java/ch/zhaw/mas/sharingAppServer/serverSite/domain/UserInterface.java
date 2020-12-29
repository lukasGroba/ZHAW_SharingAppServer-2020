package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import java.util.List;


public interface UserInterface {

    //===> CRUD methods
    public abstract List<UserModel> getAllUsers();
    public abstract List<UserModel> addNewUser(UserModel user);
    public abstract List<UserModel> deleteUserByMail(String mail);
    public abstract UserModel getUserByMail(String mail);
    public abstract List<UserModel> deleteAllUser();

    //===> support methods
    public abstract boolean checkLogin(UserModel user);
    public abstract boolean checkMailAllreadyInUse(UserModel user);
    public abstract boolean checkMailAllreadyInUse(String mail);

}
