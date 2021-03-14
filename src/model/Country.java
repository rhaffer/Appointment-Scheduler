package model;

/** This class holds the data for countries. */
public class Country {
    private int country_id; //Auto Increment (PK)
    private String country_name; // VARCHAR(50)
    private String create_date; // DateTime
    private String created_by; // VARCHAR (50)
    private String last_update; // Timestamp (auto populated)
    private String last_updated_by; // VARCHAR (50)

    /** This constructor is used to create a Contact object from fields that do not auto populate.
     @param name The name to insert
     @param date The created date
     @param author The User who created the entry
     @param update_author The User who last updated the entry */
    public Country(String name, String date, String author, String update_author){
        country_name = name;
        create_date = date;
        created_by = author;
        last_updated_by = update_author;
    }

    /** This constructor is used to create a Country object from database retrieval (all fields available).
     @param id The id to insert
     @param name The name to insert
     @param date The created date
     @param author The User who created the entry
     @param update The last time the country was updated
     @param update_author The last User who updated the country */
    public Country(int id, String name, String date, String author, String update, String update_author){
        country_id = id;
        country_name = name;
        create_date = date;
        created_by = author;
        last_update = update;
        last_updated_by = update_author;
    }

    /** Returns the Country ID.
     @return int country_id */
    public int getCountryID(){ return country_id; }

    /** Returns the Country name.
     @return String country_name */
    public String getCountryName() { return country_name; }

    /**  Returns the created date.
     @return  String create_date */
    public String getCreateDate() { return create_date; }

    /** Returns the User who created the Country.
     @return String created_by */
    public String getCreatedBy() { return created_by; }

    /** Returns the last time the Country was updated.
     @return String last_update */
    public String getLastUpdate() { return last_update; }

    /** Returns the User who last updated the Country.
     @return String last_updated_by */
    public String getLastUpdatedBy() { return last_updated_by; }

    /** Sets the Country name.
     @param name The name to be set */
    public void setCountryName(String name) { country_name = name; }

    /** Sets the created date.
     @param date The date to be set */
    public void setCreateDate(String date) { create_date = date; }

    /** Sets the User who created the Country.
     @param author The User who created the Country. */
    public void setCreatedBy(String author) { created_by = author; }

    /** Sets the last User who updated the Country.
     @param update_author The last User who updated the country. */
    public void setLastUpdatedBy(String update_author) { last_updated_by = update_author; }

    /** Overrides the toString() method. Allows for the country name to appear in ComboBoxes. */
    @Override
    public String toString() { return country_name; }
}
