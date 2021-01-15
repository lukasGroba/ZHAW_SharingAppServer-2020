package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Item controller class. Defines the server item endpoints which can be called from a client.
 *
 * @author Adrian Fischer
 */

@RestController
@RequestMapping("/items")
public class ItemController {

    private Integer itemId = 0;
    List<ItemModel> items = new ArrayList<>();

    /**
     * GET Methode: http://localhost:8080/items
     * This endpoint will get all the users from storage.
     * @author Adrian Fischer
     */

    @Operation(summary = "Get all items") // Swagger Doku
    @ApiResponses(value = { //Swagger Doku
            @ApiResponse(responseCode = "200", description = "OK: Found items", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ItemModel.class)) }),
            @ApiResponse(responseCode = "404", description = "NOT FOUND: No items found", content = @Content) })

    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItems(@RequestParam("userLoggedInMail") String userLoggedInMail) throws IOException, ClassNotFoundException {

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

    /**
     * POST Methode: http://localhost:8080/items
     * This endpoint will add a new item to the storage to the specific mail in the request body.
     * @author Adrian Fischer
     */

    @Operation(summary = "Add new Item (mail needs to be provided as id)") // Swagger Doku
    @ApiResponses(value = { // Swagger Doku
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

    /**
     * DELETE Methode: http://localhost:8080/items?id=...
     * This endpoint deletes an item based on the id as request input parameter.
     * @author Adrian Fischer
     */
    @DeleteMapping
    public ResponseEntity<Object> deleteItemById(@RequestParam("id") int id) {

        ItemService itemService = new ItemService();

        if (itemService.isItemExist(id)){
            items = itemService.deleteItemById(id);
            return new ResponseEntity<>("Successfully delete item with id: " + id , HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Item id does not exist: " + id , HttpStatus.NOT_FOUND);
        }

    }

    /**
     * PUT Methode: http://localhost:8080/items?id=...
     * This endpoint updates an item based on the id as request input parameter.
     * @author Adrian Fischer
     */

    @PutMapping
    public ResponseEntity<Object> updateItem(@RequestParam("id") int id, @RequestBody ItemModel item)
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

}
