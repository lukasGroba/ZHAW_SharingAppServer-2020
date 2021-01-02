/*

package ch.zhaw.mas.sharingAppServer.serverSite.domain;

//import ch.zhaw.mas.sharingAppServer.serverSite.persistance.DbPersistance;
import ch.zhaw.mas.sharingAppServer.serverSite.persistance.FilePersistance;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceDb implements Serializable, UserInterfaceDb {

    private List<UserModelDb> usersDb = new ArrayList<>();

    //===>JDBC
    @Autowired
    private JdbcTemplate jtm;

    //===> CRUD methods
    @Override
    public List<UserModelDb> getAllUsersDb() {

        //With JdbcTemplate

        String sql = "SELECT * FROM USERMODEL";
        usersDb = jtm.query(sql, new BeanPropertyRowMapper<>(UserModelDb.class));
        return usersDb;

    }

    @Override
    public List<UserModelDb> addNewUserDb(UserModelDb user) {

        //With JdbcTemplate
        var sql = "INSERT INTO cars(name, price) VALUES (?, ?)";
        Object[] params = new Object[] {car.getName(), car.getPrice()};

        jdbcTemplate.update(sql, params);

    }
}
*/


