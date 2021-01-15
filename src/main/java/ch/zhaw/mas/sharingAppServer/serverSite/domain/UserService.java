package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import ch.zhaw.mas.sharingAppServer.serverSite.persistance.FilePersistance;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserService class defines the request logic for the endpoints used in the UserController class.
 * @author Adrian Fischer
 */

@Service
public class UserService implements Serializable, UserInterface {

    private List<UserModelWithPassword> users = new ArrayList<>();
    private List<UserModel> usersWithoutPassword = new ArrayList<>();
    FilePersistance filePersistance = new FilePersistance();

    //===> CRUD methods

    /**
     * getAllUser read the data from the file storage and return the users (without the password) to the controller.
     * @author Adrian Fischer
     */
    @Override
    public List<UserModel> getAllUsers() {

       List<UserModel> usersWithoutPassword = new ArrayList<UserModel>();

       users = filePersistance.getUsersFromFile();
       System.out.println("Object has been deserialized getAllUsers");

       if (!users.isEmpty()) {
           for (int i = 0; i < users.size(); i++) {

               UserModel userWithoutPassword = new UserModel();
               userWithoutPassword.setMail(users.get(i).getMail());
               userWithoutPassword.setUsername(users.get(i).getUsername());
               usersWithoutPassword.add(userWithoutPassword);

           }

           return usersWithoutPassword;
       }

       return usersWithoutPassword;

    }

    /**
     * getUserByMail read the data from the file storage and return a user for a specifc mail (without the password) to the controller.
     * @author Adrian Fischer
     */
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

    /**
     * addNewUser reads the user list from the file storage, receive a new user over the controller, add it to the user list and store this in the file storage.
     * @author Adrian Fischer
     */
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

    /**
     * deleteUserByMail reads the user list from the file storage, receive a user to delete by mail over the controller, delete it on the user list and store the list in the file storage.
     * @author Adrian Fischer
     */
    @Override
    public List<UserModelWithPassword> deleteUserByMail(String mail) {

        ItemService itemService = new ItemService();

        UserModel user;

        users = filePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized deleteUserByMail");

        for (int i = 0; i < users.size(); i++) {
            //System.out.println(users.get(i));
            user  = users.get(i);
            if (user.getMail().equals(mail)) {
                itemService.deleteItemByMail(mail);
                users.remove(i);
            }
        }

        filePersistance.writeUsersToFile(users);

        System.out.println("Object has been serialized deleteUserByMail");

        return users;
    }

    /**
     * deleteAllUser create and write this new user list to the file storage.
     * @author Adrian Fischer
     */
    @Override
    public List<UserModelWithPassword> deleteAllUser() {

        FilePersistance filePersistance = new FilePersistance();

        users = new ArrayList<>();

        filePersistance.writeUsersToFile(users);

        return users;

    }

    //===> support methods

    /**
     * checkMailAllreadyInUse is a helper method to check if a mail is already in use based on the user as input and therefore can't be used for a new user.
     * @author Adrian Fischer
     */
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

    /**
     * checkMailAllreadyInUse is a helper method to check if a mail is already in use based on mail as input parameter and therefore can't be used for a new user.
     * @author Adrian Fischer
     */
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

    /**
     * checkLogin is a helper method to check if a mail and password exists and match together.
     * @author Adrian Fischer
     */
    @Override
    public boolean checkLogin(UserModelWithPassword user) {

        boolean checkLogin = false;

        users = filePersistance.getUsersFromFile();
        System.out.println("Object has been deserialized checkLogin");

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(user.getPassword()) && users.get(i).getMail().equals(user.getMail())) {
                return true;
            }
        }

        return checkLogin;

    }

}

