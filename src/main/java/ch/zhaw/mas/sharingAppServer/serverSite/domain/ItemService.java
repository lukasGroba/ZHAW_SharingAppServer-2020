package ch.zhaw.mas.sharingAppServer.serverSite;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModel;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserModel;
import ch.zhaw.mas.sharingAppServer.serverSite.persistance.FilePersistance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPersistance implements Serializable {

    private static List<ItemModel> items = new ArrayList<>();

    public static <items> List<ItemModel> getAllItems() throws IOException, ClassNotFoundException {

        items = FilePersistance.getItemsFromFile();

        return items;

    }

    public static List<ItemModel> addNewItem(ItemModel item) throws IOException, ClassNotFoundException {

        items = FilePersistance.getItemsFromFile();

        item.setHighestId(getHighestId()+1);

        String mail = item.getMailFromOwner(item);

        List<UserModel> users = new ArrayList<>();

        users = FilePersistance.getUsersFromFile();

        System.out.println("Object has been deserialized addNewUser");

        for (int i = 0; i < users.size(); i++) {
            UserModel user  = users.get(i);
            if (users.get(i).getMail().equals(mail)) {
                items.add(new ItemModel(item));
            }
        }

        FilePersistance.writeItemsToFile(items);

        return items;

    }

    public static List<ItemModel> deleteItemById(int id) {

        ItemModel item;

        items = FilePersistance.getItemsFromFile();

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
            item  = items.get(i);
            if (item.getId() == id) {
                items.remove(i);
            };
        }

        FilePersistance.writeItemsToFile(items);

        return items;
    }

    public static int getHighestId() {

        int id = 0;

        items = FilePersistance.getItemsFromFile();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() > id) {
                id = items.get(i).getId();
            };
        }

        return id;
    }

    public static List<ItemModel> updateItem(int id, ItemModel itemUpdate) {

        items = FilePersistance.getItemsFromFile();
        System.out.println("Received id: " + id);

        for (int i = 0; i < items.size(); i++) {

            ItemModel item  = items.get(i);

            if (item.getId() == id) {
                item.setDescription(itemUpdate.getDescription());
                item.setName(itemUpdate.getName());

            }

        }

        FilePersistance.writeItemsToFile(items);

        return items;

    }

    public static boolean isItemExist(int id) {

        ItemModel item = new ItemModel();

        items = FilePersistance.getItemsFromFile ();

        for (int i = 0; i < items.size(); i++) {
            item  = items.get(i);

            if (item.getId() == id) {
                return true;
            }
        }

        return false;

    }

    /*public static List<ItemModel> getItemsFromFile (){

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

    public static List<ItemModel> writeItemsToFile (List<ItemModel> items){

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

     */
}
