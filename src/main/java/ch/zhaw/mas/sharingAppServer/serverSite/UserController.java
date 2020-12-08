package ch.zhaw.mas.sharingAppServer.serverSite;

import ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.controller.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

        private static Integer userId = 0;

        @GetMapping
        public ResponseEntity<List<UserModel>> getUsers() {

            List<UserModel> users = new ArrayList<>();

            users = UserPersistance.getAllUsers();

            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<List<UserModel>> addUser(@RequestBody UserModel user) {

            List<UserModel> users = new ArrayList<>();
            //user.setId(String.valueOf(userId++));
            users = UserPersistance.addNewUser(user);

            return new ResponseEntity<>(users, HttpStatus.CREATED);

        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Long> deletePost(@PathVariable Long id) {

            List<UserModel> users = new ArrayList<>();

            users = UserPersistance.deleteUserById(id);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }


}
