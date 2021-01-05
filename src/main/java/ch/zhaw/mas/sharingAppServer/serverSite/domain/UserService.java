package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import ch.zhaw.mas.sharingAppServer.serverSite.persistance.FilePersistance;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements Serializable, UserInterface {

    private List<UserModelWithPassword> users = new ArrayList<>();
    private List<UserModel> usersWithoutPassword = new ArrayList<>();
    FilePersistance filePersistance = new FilePersistance();

    //===> CRUD methods
    @Override
    public List<UserModel> getAllUsers() {


       List<UserModel> usersWithoutPassword = new ArrayList<>();
       UserModel userWithoutPassword = new UserModel();

       users = filePersistance.getUsersFromFile();
       System.out.println("Object has been deserialized getAllUsers");

       if (!users.isEmpty()) {
           for (int i = 0; i < users.size(); i++) {

               userWithoutPassword.setMail(users.get(i).getMail());
               userWithoutPassword.setUsername(users.get(i).getUsername());
               usersWithoutPassword.add(i, userWithoutPassword);

           }

           return usersWithoutPassword;
       }

       return usersWithoutPassword;

    }
    @Override
    public UserModel getUserByMail(String mail) {

        UserModelWithPassword user = null;
        List<UserModel> usersWithoutPassword = new ArrayList<>();
        UserModel userWithoutPassword = new UserModel();

        //With file persistance:
        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized getUserByMail");
        for (int i = 0; i < users.size(); i++) {
            user  = users.get(i);
            if (users.get(i).getMail().equals(mail)) {
                userWithoutPassword.setMail(users.get(i).getMail());
                userWithoutPassword.setUsername(users.get(i).getUsername());
                return userWithoutPassword;
            }
        }
        return userWithoutPassword = null;

    }
    @Override
    public List<UserModelWithPassword> addNewUser(UserModelWithPassword user) {

        FilePersistance filePersistance = new FilePersistance();

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized addNewUser");

        users.add(user);

        filePersistance.writeUsersToFile(users);

        System.out.println("Object has been serialized writeUserToFile");

        return users;

    }
    @Override
    public List<UserModelWithPassword> deleteUserByMail(String mail) {

        UserModel user;

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized deleteUserByMail");

        for (int i = 0; i < users.size(); i++) {
            //System.out.println(users.get(i));
            user  = users.get(i);
            if (user.getMail().equals(mail)) {
                users.remove(i);
            }
        }

        filePersistance.writeUsersToFile(users);

        System.out.println("Object has been serialized deleteUserByMail");

        return users;
    }
    @Override
    public List<UserModelWithPassword> deleteAllUser() {

        FilePersistance filePersistance = new FilePersistance();

        users = new ArrayList<>();

        filePersistance.writeUsersToFile(users);

        return users;

    }

    //===> support methods
    @Override
    public boolean checkMailAllreadyInUse(UserModelWithPassword user) {

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized checkMailExist");

        if (!users.isEmpty()) {

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getMail().equals(user.getMail())) {
                    return true;
                }
            }

        }

        return false;

    }
    @Override
    public boolean checkMailAllreadyInUse(String mail) {

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized checkMailExist(byMail)");

        if (!users.isEmpty()) {

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getMail().equals(mail)) {
                    return true;
                }
            }

        }

        return false;

    }
    @Override
    public boolean checkLogin(UserModelWithPassword user) {

        boolean checkLogin = false;

        users = filePersistance.getUsersFromFile();
        System.out.println("Object has been deserialized checkLogin");

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return checkLogin;

    }

}

