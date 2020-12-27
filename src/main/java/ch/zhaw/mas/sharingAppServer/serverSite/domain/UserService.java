package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import ch.zhaw.mas.sharingAppServer.serverSite.persistance.FilePersistance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements Serializable, UserInterface {

    private List<UserModel> users = new ArrayList<>();
    FilePersistance filePersistance = new FilePersistance();

    //===> CRUD methods
    public List<UserModel> getAllUsers() {

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized getAllUsers");

        return users;

    }
    public List<UserModel> addNewUser(UserModel user) {

        FilePersistance filePersistance = new FilePersistance();

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized addNewUser");

        users.add(user);

        filePersistance.writeUsersToFile(users);

        System.out.println("Object has been serialized writeUserToFile");

        return users;

    }
    public List<UserModel> deleteUserByMail(String mail) {

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
    public UserModel getUserByMail(String mail) {

        UserModel user = null;

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized getUserByMail");

        for (int i = 0; i < users.size(); i++) {
            user  = users.get(i);
            if (users.get(i).getMail().equals(mail)) {
                return user;
            }
        }

        return user = null;

    }
    public List<UserModel> deleteAllItem() {

        FilePersistance filePersistance = new FilePersistance();

        users = new ArrayList<>();

        filePersistance.writeUsersToFile(users);

        return users;

    }

    //===> support methods
    public boolean checkLogin(UserModel user) {

        boolean checkLogin = false;

        users = filePersistance.getUsersFromFile();
        System.out.println("Object has been deserialized checkLogin");

        System.out.println(users.get(0).getPassword());
        System.out.println(user.getPassword());
        System.out.println(users.get(0).getMail());
        System.out.println(user.getMail());
        checkLogin = (users.get(0).getPassword().equals(user.getPassword())) && (users.get(0).getMail().equals(user.getMail()));

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return checkLogin;

    }
    public boolean checkMailAllreadyInUse(UserModel user) {

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

}

