package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private Integer itemId = 0;
    List<ItemModel> items = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItems() throws IOException, ClassNotFoundException {

        ItemService itemService = new ItemService();

        try {
            items = itemService.getAllItems();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<List<ItemModel>>(items, HttpStatus.NOT_FOUND);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> addNewItem(@RequestBody ItemModel item) {

        ItemService itemService = new ItemService();

        try {
            if (itemService.addItemCheckUserMailExist(item)){
                items = itemService.addNewItem(item);
                return new ResponseEntity<>("New item is created successfully with mail: " + item.getOwner().getMail(), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("No user with this mail exist", HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (NullPointerException n) {
            n.printStackTrace();
            return new ResponseEntity<>("Mail is empty", HttpStatus.FORBIDDEN);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteItemById(@PathVariable int id) {

        ItemService itemService = new ItemService();

        if (itemService.isItemExist(id)){
            items = itemService.deleteItemById(id);
            return new ResponseEntity<>("Successfully delete item with id: " + id , HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Item id does not exist: " + id , HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateItem(@PathVariable("id") int id, @RequestBody ItemModel item)
    {

        ItemService itemService = new ItemService();

        boolean isItemExist = itemService.isItemExist(id);

        if (isItemExist)
        {
            itemService.updateItem(id, item);
            return new ResponseEntity<>("Item is updated successsfully", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Item does not exist", HttpStatus.NOT_FOUND);
            //throw new ItemNotfoundException();
        }

    }

}
