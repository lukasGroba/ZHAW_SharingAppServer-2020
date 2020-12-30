package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usersDb")
public class UserControllerDb {

    private Integer userId = 0;
    private boolean LoginOK;

    @Autowired
    private UserInterfaceDb userServiceDb;
    //UserService userService = new UserService();

    @GetMapping
    public ResponseEntity<List<UserModelDb>> getUsersDb() {

        List<UserModelDb> usersDb = new ArrayList<>();

        usersDb = userServiceDb.getAllUsersDb();
        return new ResponseEntity<>(usersDb, HttpStatus.OK);

    }

    /*
    @GetMapping ("/userDb")
    public ResponseEntity<Object> getUserByMailDb(@RequestParam (value = "user") String mail) {

        if (userService.checkMailAllreadyInUse(mail)){
            UserModel user = userService.getUserByMail(mail);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No user found with this mail", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Object> addUserDb(@RequestBody UserModel user) {

        if (user.getPassword() == null || user.getUsername() == null || user.getMail() == null ) {
            return new ResponseEntity<>("Data in request are missing", HttpStatus.BAD_REQUEST);
        }
        else if (userService.checkMailAllreadyInUse(user)) {
            return new ResponseEntity<>("Mail already used by another user", HttpStatus.IM_USED);
        }
        else {
            userService.addNewUser(user);
            return new ResponseEntity<>("New user is created successfully with mail: " + user.getMail(), HttpStatus.CREATED);
        }
    }

    @PostMapping ("/loginDb")
    public ResponseEntity<Object> checkLoginDb(@RequestBody UserModel user) {

        if (userService.checkLogin(user)) {
            return new ResponseEntity<>("Login check successful", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Login check not successful", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping
    public ResponseEntity<List<UserModel>> deleteUserByMail(@RequestParam(value = "user") String mail) {

        List<UserModel> users = new ArrayList<>();

        users = userService.deleteUserByMail(mail);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping ("/deleteAllUserDb")
    public ResponseEntity<List<UserModel>> deleteAllUserDb() {

        List<UserModel> users = new ArrayList<>();

        users = userService.deleteAllUser();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

     */

}

