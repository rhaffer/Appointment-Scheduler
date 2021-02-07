package model;

public class Country {
    private int country_id; //Auto Increment (PK)
    private String country_name; // VARCHAR(50)
    private String create_date; // DateTime
    private String created_by; // VARCHAR (50)
    private String last_update; // Timestamp (auto populated)
    private String last_updated_by; // VARCHAR (50)

    // Constructor to create object from non-populating fields
    //  Ex: Sending an insert to DB
    public Country(String name, String date, String author, String update_author){
        country_name = name;
        create_date = date;
        created_by = author;
        last_updated_by = update_author;
    }

    // Constructor to create object from all fields
    //  Ex: Retrieving data from DB
    public Country(int id, String name, String date, String author, String update, String update_author){
        country_id = id;
        country_name = name;
        create_date = date;
        created_by = author;
        last_update = update;
        last_updated_by = update_author;
    }

    // Getters and Setters for Country Class
    //  *** Country_ID and Last_Updated do not have setters ***
    public int getCountryID(){ return country_id; }

    public String getCountryName() {
        return country_name;
    }

    public String getCreateDate() {
        return create_date;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public String getLastUpdate() {
        return last_update;
    }

    public String getLastUpdatedBy() {
        return last_updated_by;
    }

    public void setCountryName(String name) {
        country_name = name;
    }

    public void setCreateDate(String date) {
        create_date = date;
    }

    public void setCreatedBy(String author) {
        created_by = author;
    }

    public void setLastUpdatedBy(String update_author) {
        last_updated_by = update_author;
    }

    @Override
    public String toString() {
        return country_name;
    }
}
