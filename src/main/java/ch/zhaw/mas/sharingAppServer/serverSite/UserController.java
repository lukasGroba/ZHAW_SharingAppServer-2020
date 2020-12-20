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
        public HttpStatus addUser(@RequestBody UserModel user) {

            List<UserModel> users = new ArrayList<>();

            users = UserPersistance.addNewUser(user);

            //return new ResponseEntity<>(users, HttpStatus.CREATED);
            return HttpStatus.CREATED;

        }

        @PostMapping ("/login")
        public ResponseEntity<List<UserModel>> checkLogin(@RequestBody UserModel user) {

        List<UserModel> users = new ArrayList<>();

        users = UserPersistance.checkLogin(user.getMail(), user.getPassword());

        //LoginOK = UserPersistance.checkLogin(mail, password);

            return new ResponseEntity<>(users, HttpStatus.CREATED);

        //return new ResponseEntity<>(users, HttpStatus.CREATED);

        }

        /*
        @PostMapping ("/login")
        public boolean checkLogin(@RequestBody String mail, String password) {

        //List<UserModel> users = new ArrayList<>();

        //users = UserPersistance.checkLogin(userName, password);

            LoginOK = UserPersistance.checkLogin(mail, password);
            return LoginOK;


        //return new ResponseEntity<>(users, HttpStatus.CREATED);

        }

         */

    @DeleteMapping
    public ResponseEntity<List<UserModel>> deleteUser(@RequestParam(value = "user") String mail) {

        List<UserModel> users = new ArrayList<>();

        users = UserPersistance.deleteUserByMail(mail);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*
        @DeleteMapping(value = "/{mail}")
        public ResponseEntity<List<UserModel>> deletePost(@PathVariable String mail) {

            List<UserModel> users = new ArrayList<>();

            UserModel user;
            users = UserPersistance.deleteUserByMail(mail);

            return new ResponseEntity<>(users, HttpStatus.OK);
        }
     */

}
