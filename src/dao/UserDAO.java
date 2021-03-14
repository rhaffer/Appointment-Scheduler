package dao;

import model.User;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is the Data Access Object for the User class. This class performs all database queries for the
 * User class to include all create, read, update and delete functionalities. */
public class UserDAO{
    /** Returns a User by the username.
     @param conn The Connection object used to perform the query.
     @param searchUser The username to be searched for
     @return The User with the associated username if exists, null otherwise */
    public User get(Connection conn, String searchUser) throws SQLException {
            String selectStatement = "SELECT * FROM users WHERE User_Name = ?";
            DBQuery.setPreparedStatement(conn, selectStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setString(1, searchUser);

        try {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if(rs.next()){
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                String createDate = rs.getString("Create_Date");
                String createdBy = rs.getString("Created_By");
                String lastUpdate = rs.getTimestamp("Last_Update").toString();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                return new User(userID, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            }else{
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return null;
        }
    }

    /** This method saves a User to the database.
     @param conn The Connection object used to perform the query.
     @param user The User object to be stored
     @return True if insertion successful, false otherwise */
    public Boolean save(Connection conn, User user) throws SQLException {
        String insertStatement = "INSERT INTO users(User_name, Password) VALUES (?, ?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        try{
            statement.execute();
            return true;
        }catch(SQLException ex){
            if (ex.getErrorCode() == 1062){
                System.out.println("Duplicate Key Error.");
            }
            return false;
        }
    }
}
