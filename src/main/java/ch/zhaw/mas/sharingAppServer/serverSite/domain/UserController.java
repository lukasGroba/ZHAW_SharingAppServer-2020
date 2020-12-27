package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController<T> {

    private static Integer userId = 0;
    private boolean LoginOK;

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers() {

            List<UserModel> users = new ArrayList<>();

            users = UserService.getAllUsers();

            return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping ("/user")
    public ResponseEntity<UserModel> getUserByMail(@RequestParam (value = "user") String mail) {

        //List<UserModel> users = new ArrayList<>();

        UserModel user = UserService.getUserByMail(mail);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {

        //List<UserModel> users = new ArrayList<>();

        if (UserService.checkMailExist(user)) {
            return new ResponseEntity<>(user, HttpStatus.IM_USED);
        }
        else {
            UserService.addNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping ("/login")
    public HttpStatus checkLogin(@RequestBody UserModel user) {

        if (UserService.checkLogin(user)) {
            return HttpStatus.OK;
        }
        return HttpStatus.UNAUTHORIZED;

        }

    @DeleteMapping
    public ResponseEntity<List<UserModel>> deleteUserByMail(@RequestParam(value = "user") String mail) {

        List<UserModel> users = new ArrayList<>();

        users = UserService.deleteUserByMail(mail);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping ("/deleteAll")
    public ResponseEntity<List<UserModel>> deleteAllItems() {

        List<UserModel> users = new ArrayList<>();

        users = UserService.deleteAllItem();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
