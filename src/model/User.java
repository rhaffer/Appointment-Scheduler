package model;

public class User {
    private int user_id; // Auto increment / (PK)
    private String user_name; // VARCHAR(50) UNIQUE
    private String password; // TEXT
    private String create_date; // DATETIME
    private String created_by; // VARCHAR(50)
    private String last_update; // TIMESTAMP (AUTO POPULATED)
    private String last_updated_by; // VARCHAR(50)

    // Constructor to create object from non-populating fields
    //  Ex: Sending an insert to DB
    public User(String name, String newPassword, String date, String author, String createdAuthor){
        user_name = name;
        password = newPassword;
        create_date = date;
        created_by = author;
        last_updated_by = createdAuthor;
    }

    // Constructor to create object from all fields
    //  Ex: Retrieving data from DB
    public User(int id, String name, String newPassword, String date, String author, String lastUpdated, String updateAuthor){
        user_id = id;
        user_name = name;
        password = newPassword;
        create_date = date;
        created_by = author;
        last_update = lastUpdated;
        last_updated_by = updateAuthor;
    }

    // Getters and Setters
    public int getUserId(){ return user_id; }
    public String getUserName() { return user_name; }
    public String getPassword() { return password; }
    public String getCreateDate() { return create_date; }
    public String getCreatedBy() { return created_by; }
    public String getLastUpdate() { return last_update; }
    public String getLastUpdatedBy() { return last_updated_by;}

    public void setUserName(String userName){ user_name = userName;}
    public void setPassword(String newPassword) { password = newPassword;}
    public void setCreateDate(String createDate) { create_date = createDate;}
    public void setCreatedBy(String author){ created_by = author;}
    public void setLastUpdatedBy(String updateAuthor) { last_updated_by = updateAuthor;}


}
