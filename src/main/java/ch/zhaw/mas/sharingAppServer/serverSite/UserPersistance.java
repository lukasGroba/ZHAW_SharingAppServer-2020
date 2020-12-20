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

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized addNewUser");

            users.add(user);

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("IOException is caught");
        }

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

    public static List<UserModel> deleteUserByMail(String mail) {

        UserModel user;
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

            for (int i = 0; i < users.size(); i++) {
                //System.out.println(users.get(i));
                user  = users.get(i);
                if (user.getMail().equals(mail)) {
                    users.remove(i);
                }
            }
        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

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

    public static boolean checkLogin(UserModel user) {

        //UserModel user;
        boolean checkLogin = false;

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized checkLogin ");

            System.out.println(users.get(0).getPassword());
            System.out.println(user.getPassword());
            System.out.println(users.get(0).getMail());
            System.out.println(user.getMail());
            checkLogin = (users.get(0).getPassword().equals(user.getPassword())) && (users.get(0).getMail().equals(user.getMail()));

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("IOException is caught");
        }

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

        return checkLogin;

    }

    public static UserModel getUserByMail(String mail) {

        UserModel user = null;
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized getUserByMail");

            System.out.println(users.get(0).getMail());
            System.out.println(mail);
            boolean f = (users.get(0).getMail().equals(mail));
            System.out.println(f);

            for (int i = 0; i < users.size(); i++) {
                user  = users.get(i);
                if (users.get(i).getMail().equals(mail)) {
                    return user;
                }
            }
        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

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

        user = null;
        return user;

    }

    public static boolean checkMailExist(UserModel user) {

        boolean userExist = false;

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized mailExist");

            if (!users.isEmpty()) {
                System.out.println(users.get(0).getMail());
                System.out.println(user.getMail());
                System.out.println(users.size());
                boolean f = users.get(0).getMail().equals(user.getMail());
                System.out.println(f);

                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getMail().equals(user.getMail())) {
                        userExist = true;
                    }
                }

            }

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("IOException is caught");
        }

        /* try
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

         */

        return userExist;

    }

    public static List<UserModel> deleteAllItem() {

        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModel>) in.readObject();

            in.close();
            file.close();

            users = new ArrayList<>();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("IOException is caught");
        }

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

}

