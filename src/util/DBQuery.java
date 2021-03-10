package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** This class creates the PreparedStatement object for performing database operations. */
public class DBQuery {
    // Completed.
    private static PreparedStatement statement;

    // Creates Statement Object
    /** This method creates the PreparedStatement object.
     @param conn The database Connection object used to create the PreparedStatement
     @param sqlStatement The SQL statement to be executed */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    /** This method returns the PreparedStatement object.
     @return PreparedStatement statement */
    public static PreparedStatement getPreparedStatement(){
        return statement;
    }

}
