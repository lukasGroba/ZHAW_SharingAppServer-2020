package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import java.util.List;


public interface UserInterfaceDb {

    //===> CRUD methods
    public abstract List<UserModelDb> getAllUsersDb();
    /*
    public abstract List<UserModelDb> addNewUserDb(UserModelDb user);
    public abstract List<UserModelDb> deleteUserByMailDb(String mail);
    public abstract UserModelDb getUserByMailDb(String mail);
    public abstract List<UserModelDb> deleteAllUserDb();

    //===> support methods
    public abstract boolean checkLoginDb(UserModelDb user);
    public abstract boolean checkMailAllreadyInUseDb(UserModelDb user);
    public abstract boolean checkMailAllreadyInUseDb(String mail);


     */
}
