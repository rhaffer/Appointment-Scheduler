package util;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class creates the database Connection object. */
public class DBConnection {
    // Completed.
    private static final String username = "U07mtP";
    private static final String password = "53689072744";
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";

    /*
        Server Name: wgudb.ucertify.com
        Port: 3306
        Database Name: WJ07mtP
    */
    private static final String ipAddress = "//wgudb.ucertify.com/WJ07mtP";

    // JDBC URL
    private static final String jdbcURL = protocol + vendor + ipAddress;

    // Driver and Connection Database References
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    /** This method opens the database Connection. */
    public static Connection openConnection(){
        try{
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection) DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Established");
        }
        catch(ClassNotFoundException e){
            System.out.println("Class Error: " + e.getMessage());
        }
        catch(SQLException e){
            System.out.println("SQL Error: " + e.getMessage());
        }
        return conn;
    }

    /** This method closes the database Connection. */
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection Closed");
        }
        catch(SQLException e){
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
