package model;

public class FirstLevelDivision {
    private int division_id; // Int(10) / Auto Increment / PK
    private String division_name; // VARCHAR (50)
    private String create_date; // DATETIME
    private String created_by; // VARCHAR (50)
    private String last_update; // TIMESTAMP (Auto populate)
    private String last_updated_by; // VARCHAR(50)
    private int country_id; // Int(10) FK

    // Constructor to create object from non-populating fields
    //  Ex: Sending an insert to DB
    public FirstLevelDivision(String divisionName, String date, String author, String updateAuthor, int countryID){
        division_name = divisionName;
        create_date = date;
        created_by = author;
        last_updated_by = updateAuthor;
        country_id = countryID;
    }

    // Constructor to create object from all fields
    //  Ex: Retrieving data from DB
    public FirstLevelDivision(int id, String divisionName, String date, String author, String update, String update_author, int countryID){
        division_id = id;
        division_name = divisionName;
        create_date = date;
        created_by = author;
        last_update = update;
        last_updated_by = update_author;
        country_id = countryID;
    }

    // Getters and Setters for FirstLevelDivision Class
    // *** Divison_ID and Last_Update do not have setters ***
    public int getDivisionID(){ return division_id;}
    public String getDivisionName() { return division_name; }
    public String getCreateDate() { return create_date; }
    public String getCreatedBy() { return created_by; }
    public String getLastUpdate() { return last_update; }
    public String getLastUpdatedBy() { return last_updated_by; }
    public int getCountryID() { return country_id; }

    public void setDivisionName(String name) { division_name = name; }
    public void setCreateDate(String date) { create_date = date; }
    public void setCreatedBy(String author) { created_by = author; }
    public void setLastUpdatedBy(String updateAuthor) { last_updated_by = updateAuthor; }
    public void setCountryID (int countryID) { country_id = countryID;}

}
