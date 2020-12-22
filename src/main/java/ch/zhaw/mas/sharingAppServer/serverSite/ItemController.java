package ch.zhaw.mas.sharingAppServer.serverSite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private static Integer itemId = 0;

    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItems() {

        List<ItemModel> items = new ArrayList<>();

        items = ItemPersistance.getAllItems();

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

}
