package ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Integer userId = 0;
    private static List<User> users = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {

        /*
        User user = new User();
        user.setId(String.valueOf(userId++));
        user.setFirstname("Firstname " + userId);
        user.setLastname("Lastname " + userId);

        users.add(user);
        */

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createCustomer(@RequestBody User user) {
        user.setId(String.valueOf(userId++));
        users.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

/*

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   @GetMapping
   public User user(@RequestParam(value = "name", defaultValue = "World") String name) {
    
        return new User(1, "Fischer", "Adrian");
    }

 */

}



