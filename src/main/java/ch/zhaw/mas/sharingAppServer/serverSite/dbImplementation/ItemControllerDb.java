package ch.zhaw.mas.sharingAppServer.serverSite.dbImplementation;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModel;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModelDb;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/itemsDb")
public class ItemControllerDb {

    @Autowired
    private ItemInterfaceDb itemInterfaceDb;

    @Autowired
    private UserInterfaceDb userInterfaceDb;

    //private Integer itemId = 0;
    List<ItemModelDb> items = new ArrayList<>();
    ItemModelDb item = new ItemModelDb();

    @Operation(summary = "Get all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK: Found items", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ItemModelDb.class)) }),
            @ApiResponse(responseCode = "404", description = "NOT FOUND: No items found", content = @Content) })
    @GetMapping
    public ResponseEntity<List<ItemModelDb>> getAllItemsFromDb(){

        //ItemService itemService = new ItemService();
        /*
        try {
            items = itemInterfaceDb.findAll();
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<List<ItemModelDb>>(items, HttpStatus.NOT_FOUND);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

 */

        items = itemInterfaceDb.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);

    }
    /*

    @Operation(summary = "Add new Item (mail needs to be provided as id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED: Item created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ItemModel.class)) }),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN: Mail ist empty", content = @Content),
            @ApiResponse(responseCode = "406", description = "NOT ACCEPTABLE: No user with this mail exist", content = @Content),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR: Class not found", content = @Content) })
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

        }
        catch (NullPointerException n) {
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
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Item does not exist", HttpStatus.NOT_FOUND);
            //throw new ItemNotfoundException();
        }

    }

     */

    @PostMapping
    public ResponseEntity<Object> addNewItemToDb(@RequestBody ItemModelDb item) {

        itemInterfaceDb.save(item);
        return new ResponseEntity<>("New Item is created successfully with mail: " + item.getMailFromOwner(item), HttpStatus.CREATED);

        /*

        try {
            if (itemService.addItemCheckUserMailExist(item)){
                items = itemService.addNewItem(item);
                return new ResponseEntity<>("New item is created successfully with mail: " + item.getOwner().getMail(), HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("No user with this mail exist", HttpStatus.NOT_ACCEPTABLE);
            }

        }
        catch (NullPointerException n) {
            n.printStackTrace();
            return new ResponseEntity<>("Mail is empty", HttpStatus.FORBIDDEN);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }

         */

    }

    @DeleteMapping
    public ResponseEntity<Object> deleteItemByMailFromDb(@RequestParam(value = "mail") String mail) {

        items = itemInterfaceDb.findItemModelDbByOwner_Mail(mail);
        itemInterfaceDb.deleteInBatch(items);

        return new ResponseEntity<>("All Item with mail: " + mail + "DELETED", HttpStatus.OK);
     }

}

