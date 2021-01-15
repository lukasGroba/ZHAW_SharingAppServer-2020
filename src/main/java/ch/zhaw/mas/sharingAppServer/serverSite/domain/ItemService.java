package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import ch.zhaw.mas.sharingAppServer.serverSite.persistance.FilePersistance;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemService class defines the request logic for the endpoints used in the ItemController class.
 * @author Adrian Fischer
 */

@Service
public class ItemService implements Serializable, ItemInterface {

    private List<ItemModel> items = new ArrayList<>();
    FilePersistance filePersistance = new FilePersistance();

    //===> CRUD methods
    /**
     * getAllItems read the data from the file storage and return it to the controller.
     * @author Adrian Fischer
     */
    @Override
    public List<ItemModel> getAllItems() throws IOException, ClassNotFoundException {

        items = filePersistance.getItemsFromFile();

        return items;

    }

    /**
     * addNewItem reads the item list from the file storage, receive a new item over the controller, add it to the item list and store this in the file storage.
     * @author Adrian Fischer
     */
    @Override
    public List<ItemModel> addNewItem(ItemModel item) throws IOException, ClassNotFoundException {

        items = filePersistance.getItemsFromFile();

        item.setHighestId(getHighestId()+1);

        items.add(new ItemModel(item));

        FilePersistance.writeItemsToFile(items);

        return items;

    }

    /**
     * deleteItemById reads the item list from the file storage, receive a item to delete by id over the controller, delete it on the item list and store the list in the file storage.
     * @author Adrian Fischer
     */
    @Override
    public List<ItemModel> deleteItemById(int id) {

        ItemModel item;

        items = filePersistance.getItemsFromFile();

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

    /**
     * deleteItemByMail reads the item list from the file storage, receive a item to delete by mail over the controller, delete it on the item list and store the list in the file storage.
     * @author Adrian Fischer
     */
    @Override
    public void deleteItemByMail(String mail) {

        ItemModel item;

        items = filePersistance.getItemsFromFile();

        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
            item  = items.get(i);
            if (item.getMailFromOwner(item).equals(mail)) {
                items.remove(i);
            };
        }

        FilePersistance.writeItemsToFile(items);

    }

    /**
     * updateItem reads the item list from the file storage, receive an item update by id over the controller, update the item in the list and store the list in the file storage.
     * @author Adrian Fischer
     */
    @Override
    public List<ItemModel> updateItem(int id, ItemModel itemUpdate) {

        items = filePersistance.getItemsFromFile();
        System.out.println("Received id: " + id);

        for (int i = 0; i < items.size(); i++) {

            ItemModel item  = items.get(i);

            if (item.getId() == id) {
                item.setName(itemUpdate.getName());
                item.setDescription(itemUpdate.getDescription());
                item.setLent(itemUpdate.isLent());
                item.setRating(itemUpdate.getRating());
                item.setName(itemUpdate.getName()); // geht noch nicht!
                item.setLentFrom(itemUpdate.getLentFrom());
                item.setLentTill(itemUpdate.getLentTill());

            }

        }

        FilePersistance.writeItemsToFile(items);

        return items;

    }

    //===> support methods
    /**
     * getHighestId is a helper method for add a new item with a unique id to the item list.
     * @author Adrian Fischer
     */
    @Override
    public int getHighestId() {

        int id = 0;

        items = filePersistance.getItemsFromFile();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() > id) {
                id = items.get(i).getId();
            };
        }

        return id;
    }

    /**
     * isItemExist is a helper method to check if an item based on a certain id exist.
     * @author Adrian Fischer
     */
    @Override
    public boolean isItemExist(int id) {

        ItemModel item = new ItemModel();

        items = filePersistance.getItemsFromFile ();

        for (int i = 0; i < items.size(); i++) {
            item  = items.get(i);

            if (item.getId() == id) {
                return true;
            }
        }

        return false;

    }

    /**
     * addItemCheckUserMailExist is a helper method to check the dependency add item to the list and user mail exist (key).
     * @author Adrian Fischer
     */
    @Override
    public boolean addItemCheckUserMailExist(ItemModel item) throws NullPointerException {

        //try {
            String mail = item.getMailFromOwner(item);
            List<UserModelWithPassword> users = new ArrayList<>();
            users = filePersistance.getUsersFromFile();
            System.out.println("Object has been deserialized AddItemCheckUserMailExist");

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getMail().equals(mail)) {
                    return true;
                }

            }
        //} catch (Exception e) {System.out.println(e);}

        return false;
    }

}
