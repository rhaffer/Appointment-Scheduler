package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserDAO{

    private final ObservableList<User> users = FXCollections.observableArrayList();

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

    public ObservableList<User> getAll(Connection conn) throws SQLException {
        String selectAllStatement = "SELECT * FROM users";
        DBQuery.setPreparedStatement(conn, selectAllStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getTimestamp("Last_Update").toString();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            users.add(new User(userID, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy));
        }
        return users;
    }

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

    //TODO Make update functionality
    public void update(User user, String[] params){
        user.setUserName(Objects.requireNonNull(params[0], "Name cannot be null"));
        user.setPassword(Objects.requireNonNull(params[1], "Password cannot be null"));
        users.add(user);
    }

    // TODO Make delete functionality
    public void delete(User user){
        users.remove(user);
    }
}
