package ch.zhaw.mas.sharingAppServer.serverSite;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private static Integer itemId = 0;

    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItems() throws IOException, ClassNotFoundException {

        List<ItemModel> items = new ArrayList<>();

        try {
            items = ItemPersistance.getAllItems();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<List<ItemModel>>(items, HttpStatus.NOT_FOUND);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<List<ItemModel>> addNewItem(@RequestBody ItemModel item) {

        List<ItemModel> items = new ArrayList<>();

        try {
            items = ItemPersistance.addNewItem(item);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(items, HttpStatus.OK);
        //Try/Catch: File not found Error -> Status senden
        //Try/Catch: ...

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<ItemModel>> deleteItemById(@PathVariable int id) {

        List<ItemModel> items = new ArrayList<>();

        ItemModel item;
        items = ItemPersistance.deleteItemById(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateItem(@PathVariable("id") int id,
                                                 @RequestBody ItemModel item)
    {
        int id1 = id;
        boolean isItemExist = ItemPersistance.isItemExist(id);

        System.out.println("Boolean/isItemExist: " + isItemExist);
        System.out.println("id1: " + id1);
        System.out.println("id: " + id);

        if (isItemExist)
        {
            ItemPersistance.updateItem(id1, item);
            return new ResponseEntity<>("Item is updated successsfully", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Item does not exist", HttpStatus.NOT_FOUND);
            //throw new ItemNotfoundException();
        }

    }

}
