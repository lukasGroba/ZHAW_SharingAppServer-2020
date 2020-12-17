package ch.zhaw.mas.sharingAppServer.serverSite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private static Integer itemId = 0;

    @GetMapping
    public ResponseEntity<List<ItemModel>> getItems() {

        List<ItemModel> items = new ArrayList<>();

        items = ItemPersistance.getAllItems();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<ItemModel>> addItem(@RequestBody ItemModel item) {

        List<ItemModel> items = new ArrayList<>();

        items = ItemPersistance.addNewItem(item);

        return new ResponseEntity<>(items, HttpStatus.CREATED);

    }

}
