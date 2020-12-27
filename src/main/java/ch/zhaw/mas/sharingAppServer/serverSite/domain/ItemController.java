package ch.zhaw.mas.sharingAppServer.serverSite.domain;

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
            items = ItemService.getAllItems();
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
            items = ItemService.addNewItem(item);
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
        items = ItemService.deleteItemById(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateItem(@PathVariable("id") int id, @RequestBody ItemModel item)
    {

        boolean isItemExist = ItemService.isItemExist(id);

        if (isItemExist)
        {
            ItemService.updateItem(id, item);
            return new ResponseEntity<>("Item is updated successsfully", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Item does not exist", HttpStatus.NOT_FOUND);
            //throw new ItemNotfoundException();
        }

    }

}
