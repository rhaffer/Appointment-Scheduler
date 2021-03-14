package model;

/** This class holds the data for first level division (state/province) data. */
public class FirstLevelDivision {
    private int division_id; // Int(10) / Auto Increment / PK
    private String division_name; // VARCHAR (50)
    private String create_date; // DATETIME
    private String created_by; // VARCHAR (50)
    private String last_update; // TIMESTAMP (Auto populate)
    private String last_updated_by; // VARCHAR(50)
    private int country_id; // Int(10) FK

    /** This constructor is used to create a FirstLevelDivision object from fields that do not auto populate.
     @param divisionName The new division name
     @param date The new division created on date
     @param author The User who created the new division
     @param updateAuthor The User who last updated the division
     @param countryID The new Country ID that correlates to the division
     */
    public FirstLevelDivision(String divisionName, String date, String author, String updateAuthor, int countryID){
        division_name = divisionName;
        create_date = date;
        created_by = author;
        last_updated_by = updateAuthor;
        country_id = countryID;
    }

    /** This constructor is used to create a FirstLevelDivision object from database retrieval (all fields available).
     @param id The new division ID
     @param divisionName The new division name
     @param date The date the new division was created
     @param author The User who created the new division
     @param update The date the new division was last updated
     @param update_author The User who last updated the new division
     @param countryID The Country ID that correlates to the division
     */
    public FirstLevelDivision(int id, String divisionName, String date, String author, String update, String update_author, int countryID){
        division_id = id;
        division_name = divisionName;
        create_date = date;
        created_by = author;
        last_update = update;
        last_updated_by = update_author;
        country_id = countryID;
    }

    /** Returns the Division ID.
     @return int division_id */
    public int getDivisionID(){ return division_id;}

    /** Returns the Division name.
     @return String division_name */
    public String getDivisionName() { return division_name; }

    /** Returns the created date.
     @return String create_date */
    public String getCreateDate() { return create_date; }

    /** Returns the user who created the division.
     @return String created_by */
    public String getCreatedBy() { return created_by; }

    /** Returns the date the division was last updated.
     @return String last_update */
    public String getLastUpdate() { return last_update; }

    /** Returns the User who last updated the division.
     @return String last_updated_by */
    public String getLastUpdatedBy() { return last_updated_by; }

    /** Returns the Country ID associated with the division.
     @return int country_id */
    public int getCountryID() { return country_id; }

    /** Sets the new division name.
     @param name The new division name */
    public void setDivisionName(String name) { division_name = name; }

    /** Sets the new date the division was created on.
     @param date The new date the division was created */
    public void setCreateDate(String date) { create_date = date; }

    /** Sets the author who created the division.
     @param author The author who created the division */
    public void setCreatedBy(String author) { created_by = author; }

    /** Sets the User who last updated the division.
     @param updateAuthor The User who last updated the division */
    public void setLastUpdatedBy(String updateAuthor) { last_updated_by = updateAuthor; }

    /** Sets the Country ID associated with the division.
     @param countryID The country ID associated with the division. */
    public void setCountryID(int countryID) { country_id = countryID; }

    /** Overrides the toString method. Allows for the division name to appear in ComboBoxes. */
    @Override
    public String toString() { return division_name; }
}
