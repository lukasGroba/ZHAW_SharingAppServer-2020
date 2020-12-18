package ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

//@RestController
public class UserControllerExtended {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();
    //private long testNumber = 393939393;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/ZHAW_SharingAppServer-2020/SharingAppDB";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    @GetMapping("/user")
    public User user(@RequestParam(value = "id", defaultValue = "1") int id) {

        System.out.println(id);

        Connection conn = null;
        Statement stmt = null;

        String firstName = null;
        String lastName = null;
        
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT id, LASTNAME, FIRSTNAME FROM USER WHERE ID = " + id ;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                id  = rs.getInt("id");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", First: " + firstName);
                System.out.println(", Last: " + lastName);

                //return new User(id, lastname, firstname);
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
    
        return new User(firstName, lastName);
}
}


