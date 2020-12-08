package ch.zhaw.mas.sharingAppServer.serverSite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserPersistance implements Serializable {

    private static List<UserModel> users = new ArrayList<>();

    public static <users> List<UserModel> getAllUsers() {

        //List<UserModel> users = new ArrayList<>();

        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

        return users;

    }

    public static List<UserModel> addNewUser(UserModel user) {

        user.setId("10");
        users.add(new UserModel(user));

        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("src/user.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(users);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        return users;

    }

    public static List<UserModel> deleteUserById(Long id) {

        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

        //void removeAll(List<Integer> list, Integer element) {
        while (users.contains(id)) {
                users.remove(id);
        }


        return users;
    }
}
