package ch.zhaw.mas.sharingAppServer.serverSite.persistance;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModel;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserModelWithPassword;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersistance {

    private List<ItemModel> items = new ArrayList<>();
    private List<UserModelWithPassword> users = new ArrayList<>();

    public List<ItemModel> getItemsFromFile(){

        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/item.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            items = (List<ItemModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized getItemsFromFile");

        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

        return items;

    }

    public static void writeItemsToFile(List<ItemModel> items){

        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("src/item.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(items);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

    }

    public List<UserModelWithPassword> getUsersFromFile(){

        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/user.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (List<UserModelWithPassword>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized getUsersFromFile");

        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

        return users;
    }

    public void writeUsersToFile(List<UserModelWithPassword> users){

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

    }

}
