package ch.zhaw.mas.sharingAppServer.serverSite.dbImplementation;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usersDb")
public class UserControllerDb {

    //===> JPA
    @Autowired
    private UserInterfaceDb userInterfaceDb;

    @Autowired
    private ItemInterfaceDb itemInterfaceDb;

    @Autowired
    private ItemControllerDb itemControllerDb;

    List<UserModelDb> users = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<UserModelDb>> getAllUserFromDb() {
        //return userServiceDb.findAll();
        return new ResponseEntity<>(userInterfaceDb.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody UserModelDb user) {

        if (user.getPassword() == null || user.getUsername() == null || user.getMail() == null ) {
            return new ResponseEntity<>("Data in request are missing", HttpStatus.BAD_REQUEST);
        }
        else if (userInterfaceDb.existsById(user.getMail())) {
            return new ResponseEntity<>("Mail already used by another user", HttpStatus.IM_USED);
        }
        else {
            userInterfaceDb.save(user);
            return new ResponseEntity<>("New user is created successfully with mail: " + user.getMail(), HttpStatus.CREATED);
            //return new ResponseEntity<UserModelDb>(userServiceDb.save(user), HttpStatus.CREATED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUserByMail(@RequestParam(value = "mail") String mail) {

        if (userInterfaceDb.existsById(mail)){
            itemControllerDb.deleteItemByMailFromDb(mail);
            userInterfaceDb.deleteById(mail);
            return new ResponseEntity<>("User with mail: " + mail + " DELETED", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User with mail: " + mail + " NOT FOUND", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping ("/user")
    public ResponseEntity<Object> getUserByMail(@RequestParam (value = "mail") String mail) {

        if (userInterfaceDb.existsById(mail)){
            Optional<UserModelDb> userModelDb = userInterfaceDb.findById(mail);
            return new ResponseEntity<>(userModelDb, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No user found with this mail", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping ("/login")
    public ResponseEntity<Object> checkLogin(@RequestBody UserModel user) {

        if (user.getPassword() == null || user.getMail() == null ) {
            return new ResponseEntity<>("Data in request are missing", HttpStatus.BAD_REQUEST);
        }
        else if (!userInterfaceDb.existsById(user.getMail())) {
            return new ResponseEntity<>("No User with this mail exist in DB", HttpStatus.NOT_FOUND);
        }
        else if (userInterfaceDb.findByMailAndPassword(user.getMail(), user.getPassword()).isEmpty()){
            return new ResponseEntity<>("Login check not successful", HttpStatus.UNAUTHORIZED);
        }
        else {
            //users = userInterfaceDb.findByMailAndPassword(user.getMail(), user.getPassword());
            return new ResponseEntity<>("Login check successful", HttpStatus.ACCEPTED);
        }

    }

    //===> JDBC
    /*
    private Integer userId = 0;
    private boolean LoginOK;

    @Autowired
    private UserInterfaceDb userServiceDb;
    //UserService userService = new UserService();

    @GetMapping
    public ResponseEntity<List<UserModelDb>> getUsersDb() {

        List<UserModelDb> usersDb = new ArrayList<>();

        Object userInterfaceDb = null;
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

