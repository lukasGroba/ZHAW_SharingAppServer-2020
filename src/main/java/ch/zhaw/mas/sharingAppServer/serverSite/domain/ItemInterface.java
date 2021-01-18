package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import java.io.IOException;
import java.util.List;

/**
 * Item interface description implements in ItemService
 * @author Adrian Fischer
 * @version 1.0
 */

public interface ItemInterface {

    //===> CRUD methods
    public abstract List<ItemModel> getAllItems() throws IOException, ClassNotFoundException;
    public abstract List<ItemModel> addNewItem(ItemModel item) throws IOException, ClassNotFoundException;
    public abstract List<ItemModel> deleteItemById(int id);
    public abstract List<ItemModel> updateItem(int id, ItemModel itemUpdate);

    //===> support methods
    public void deleteItemByMail(String mail); //Support Methode beim Löschen eines Users
    public abstract boolean isItemExist(int id);
    public abstract int getHighestId();
    public abstract boolean addItemCheckUserMailExist(ItemModel item) throws NullPointerException;

}
