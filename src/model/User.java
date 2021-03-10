package model;

/** This class holds the data for users. */
public class User {
    // Completed.
    private int user_id; // Auto increment / (PK)
    private String user_name; // VARCHAR(50) UNIQUE
    private String password; // TEXT
    private String create_date; // DATETIME
    private String created_by; // VARCHAR(50)
    private String last_update; // TIMESTAMP (AUTO POPULATED)
    private String last_updated_by; // VARCHAR(50)

    /** This constructor is used to create an User object from fields that do not auto populate.
     @param name The User's name
     @param newPassword The password associated with the User
     @param date The date the User was created
     @param author The author who created the User
     @param createdAuthor The author who last updated the User information
     */
    public User(String name, String newPassword, String date, String author, String createdAuthor){
        user_name = name;
        password = newPassword;
        create_date = date;
        created_by = author;
        last_updated_by = createdAuthor;
    }

    /** This constructor is used to create an User object from database retrieval (all fields available).
     @param id The new User ID
     @param name The User's name
     @param newPassword The password associated with the user
     @param date The date the User was created
     @param author The User who created the new User
     @param lastUpdated The date the User was last updated
     @param updateAuthor The User who last updated the new User
     */
    public User(int id, String name, String newPassword, String date, String author, String lastUpdated, String updateAuthor){
        user_id = id;
        user_name = name;
        password = newPassword;
        create_date = date;
        created_by = author;
        last_update = lastUpdated;
        last_updated_by = updateAuthor;
    }

    /** Returns the User ID.
     @return int user_id */
    public int getUserId(){ return user_id; }

    /** Returns the username.
     @return String user_name */
    public String getUserName() { return user_name; }

    /** Returns the User's password.
     @return password */
    public String getPassword() { return password; }

    /** Returns the date the User was created.
     @return String create_date */
    public String getCreateDate() { return create_date; }

    /** Returns the User who created the new User.
     @return created_by*/
    public String getCreatedBy() { return created_by; }

    /** Returns the date the User was last updated.
     @return String last_update */
    public String getLastUpdate() { return last_update; }

    /** Returns the User who last updated the User.
     @return String last_updated_by */
    public String getLastUpdatedBy() { return last_updated_by;}

    /** Sets the username.
     @param userName The new username */
    public void setUserName(String userName){ user_name = userName;}

    /** Sets the password.
     @param newPassword The password for the new user */
    public void setPassword(String newPassword) { password = newPassword;}

    /** Sets the date the User was created.
     @param createDate The date the User was created */
    public void setCreateDate(String createDate) { create_date = createDate;}

    /** Sets the User who created the new User.
     @param author The author who created the User */
    public void setCreatedBy(String author){ created_by = author;}

    /** Sets the date the User was last updated.
     @param updateAuthor The author who last updated the User */
    public void setLastUpdatedBy(String updateAuthor) { last_updated_by = updateAuthor;}


}
