package ch.zhaw.mas.sharingAppServer.serverSite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Integer userId = 0;
    private boolean LoginOK;

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

        @PostMapping ("/login")
        public boolean checkLogin(@RequestBody String userName, String password) {

        //List<UserModel> users = new ArrayList<>();

        //users = UserPersistance.checkLogin(userName, password);

            LoginOK = UserPersistance.checkLogin(userName, password);
            return LoginOK;


        //return new ResponseEntity<>(users, HttpStatus.CREATED);

    }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<List<UserModel>> deletePost(@PathVariable Long id) {

            List<UserModel> users = new ArrayList<>();

            UserModel user;
            users = UserPersistance.deleteUserById(id);

            return new ResponseEntity<>(users, HttpStatus.OK);
        }


}
