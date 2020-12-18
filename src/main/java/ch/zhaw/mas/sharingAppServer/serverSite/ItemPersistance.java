package ch.zhaw.mas.sharingAppServer.serverSite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPersistance implements Serializable {

    private static List<ItemModel> items = new ArrayList<>();

    public static <items> List<ItemModel> getAllItems() {

        //List<ItemModel> items = new ArrayList<>();

        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/item.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            items = (List<ItemModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

        return items;

    }

    public static List<ItemModel> addNewItem(ItemModel item) {

        //item.setId("10");
        items.add(new ItemModel(item));

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

        return items;

    }

    public static List<ItemModel> deleteItemById(int id) {

        ItemModel item;
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("src/item.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            items = (List<ItemModel>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
        }

        catch(IOException | ClassNotFoundException ex)
        {
            System.out.println("IOException is caught");
        }

        //void removeAll(List<Integer> list, Integer element){
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
            item  = items.get(i);
            if (item.getItemID() == id) {
                items.remove(i);
            };
        }

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

        return items;
    }

}
