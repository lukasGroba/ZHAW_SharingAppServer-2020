package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User controller class. Defines the server user endpoints which can be called from a client.
 * @see <a href="http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config">Swagger UI</a>
 * @author Adrian Fischer
 * @version 1.0
 * -link http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private Integer userId = 0;
    private boolean LoginOK;

    @Autowired
    private UserInterface userService;

    /**
     * GET Methode: http://localhost:8080/users
     * This endpoint will get all the users from storage.
     * @author Adrian Fischer
     */

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers() {

        List<UserModel> users = new ArrayList<>();

        users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    /**
     * GET Methode: http://localhost:8080/users/user?mail=...,
     * This endpoint will get the user which belongs to a specific mail.
     * @author Adrian Fischer
     */

    @GetMapping ("/user")
    public ResponseEntity<Object> getUserByMail(@RequestParam (value = "mail") String mail) {

        if (userService.checkMailAllreadyInUse(mail)){
            UserModel user = userService.getUserByMail(mail);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No user found with this mail", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * POST Methode: http://localhost:8080/users
     * This endpoint will add a new user to the storage. This controller checks if all necessary information are sent in the request body and if the mail already exists.
     * @author Adrian Fischer
     */

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody UserModelWithPassword user) {

        if (user.getPassword()== null || user.getUsername() == null || user.getMail() == null ) {
            return new ResponseEntity<>("Data in request are missing", HttpStatus.BAD_REQUEST);
        }
        else if (userService.checkMailAllreadyInUse(user)) {
                return new ResponseEntity<>("Mail already used by another user", HttpStatus.FORBIDDEN);
        }
        else {
            userService.addNewUser(user);
            return new ResponseEntity<>("New user is created successfully with mail: " + user.getMail(), HttpStatus.CREATED);
        }
    }

    /**
     * POST Methode: http://localhost:8080/users/login
     * This endpoint checks if mail and password exists in the storage and match together. will add a new user to the storage.
     * @author Adrian Fischer
     */

    @PostMapping ("/login")
    public ResponseEntity<Object> checkLogin(@RequestBody UserModelWithPassword user) {

        if (userService.checkLogin(user)) {
            return new ResponseEntity<>("Login check successful", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Login check not successful", HttpStatus.UNAUTHORIZED);
    }

    /**
     * DELETE Methode: http://localhost:8080/users?mail=...
     * This endpoint deletes a user based on the mail as input parameter.
     * @author Adrian Fischer
     */

    @DeleteMapping
    public ResponseEntity<List<UserModelWithPassword>> deleteUserByMail(@RequestParam(value = "mail") String mail) {

        List<UserModelWithPassword> users = new ArrayList<>();

        users = userService.deleteUserByMail(mail);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * DELETE Methode: http://localhost:8080/users/deleteAll
     * This endpoint deletes all users AND all the items in the storage.
     * @author Adrian Fischer
     */

    @DeleteMapping ("/deleteAllUser")
    public ResponseEntity<List<UserModelWithPassword>> deleteAllUser() {

        List<UserModelWithPassword> users = new ArrayList<>();

        users = userService.deleteAllUser();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
