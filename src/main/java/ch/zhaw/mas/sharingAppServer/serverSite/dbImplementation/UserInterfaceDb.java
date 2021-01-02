package ch.zhaw.mas.sharingAppServer.serverSite.dbImplementation;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//import java.util.List;


@Repository
public interface UserInterfaceDb extends JpaRepository<UserModelDb, String> {

    List<UserModelDb> findByMailAndPassword(String mail, String password);

    //===> CRUD methods JDBC
    /*
    public abstract List<UserModelDb> getAllUsersDb();
    public abstract List<UserModelDb> addNewUserDb(UserModelDb user);
    public abstract List<UserModelDb> deleteUserByMailDb(String mail);
    public abstract UserModelDb getUserByMailDb(String mail);
    public abstract List<UserModelDb> deleteAllUserDb();

    //===> support methods
    public abstract boolean checkLoginDb(UserModelDb user);
    public abstract boolean checkMailAllreadyInUseDb(UserModelDb user);
    public abstract boolean checkMailAllreadyInUseDb(String mail);
    */

    //===> JPA
    /*
    public abstract List<UserModelDb> getUsersDb();
    public List<UserModelDb> getAllUserDb(UserInterfaceDb userInterfaceDb);
    */

}
