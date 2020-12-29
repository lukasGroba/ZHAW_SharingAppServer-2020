/* package ch.zhaw.mas.sharingAppServer.serverSite.persistance;

import ch.zhaw.mas.sharingAppServer.serverSite.domain.UserModel;
import ch.zhaw.mas.sharingAppServer.serverSite.domain.ItemModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbPersistance {
    public static List<UserModel> getUsersFromDb() {
        List<UserModel> users = new ArrayList<>();
        UserModel user = new UserModel();

        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/ZHAW_SharingAppServer-2020/src/sharingAppDb";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "sa";
        //System.out.println(id);

        Connection conn = null;
        Statement stmt = null;

        String mail = null;
        String username = null;
        String password = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT MAIL, USERNAME, PASSWORD FROM USERMODEL";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                mail  = rs.getString("mail");
                username = rs.getString("username");
                password = rs.getString("password");

                // Display values
                System.out.print("Mail: " + mail);
                System.out.print(", Username: " + username);
                System.out.println(", Password: " + password);

                //return new User(id, lastname, firstname);
                user.setMail(mail);
                user.setUsername(username);
                user.setPassword(password);

                users.add(user);

                for (int i = 0; i < users.size(); i++) {
                    System.out.println(users.get(i));
                }

            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");

        return users;
    }
}

 */
