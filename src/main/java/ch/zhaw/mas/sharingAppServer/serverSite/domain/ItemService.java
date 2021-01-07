package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import ch.zhaw.mas.sharingAppServer.serverSite.persistance.FilePersistance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements Serializable, ItemInterface {

    private List<ItemModel> items = new ArrayList<>();
    FilePersistance filePersistance = new FilePersistance();

    //===> CRUD methods
    @Override
    public List<ItemModel> getAllItems() throws IOException, ClassNotFoundException {

        items = filePersistance.getItemsFromFile();

        return items;

    }
    @Override
    public List<ItemModel> addNewItem(ItemModel item) throws IOException, ClassNotFoundException {

        items = filePersistance.getItemsFromFile();

        item.setHighestId(getHighestId()+1);

        items.add(new ItemModel(item));

        FilePersistance.writeItemsToFile(items);

        return items;

    }
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
