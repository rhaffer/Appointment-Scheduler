package model;

public class Appointment {
    private int appointment_id; // PK INT(10)
    private String title; // VARCHAR (50)
    private String description; // VARCHAR (50)
    private String location; // VARCHAR(50)
    private String type; // VARCHAR(50)
    private String start; // DATETIME
    private String end; // DATETIME
    private String create_date; // DATETIME
    private String created_by; // VARCHAR(50)
    private String last_update; // TIMESTAMP
    private String last_updated_by; // VARCHAR(50)
    private int customer_id; // FK INT(10)
    private int user_id; // FK INT(10)
    private int contact_id; // FK INT(10)

    // Constructor to create object from non-populating fields
    // Ex: Sending an insert to DB
    public Appointment(String new_title, String new_description, String new_location, String new_type,
                       String new_start, String new_end, String new_create_date, String new_created_by,
                       String new_last_update, String new_last_updated_by, int new_customer_id, int new_user_id,
                       int new_contact_id){
        title = new_title;
        description = new_description;
        location = new_location;
        type = new_type;
        start = new_start;
        end = new_end;
        create_date = new_create_date;
        created_by = new_created_by;
        last_update = new_last_update;
        last_updated_by = new_last_updated_by;
        customer_id = new_customer_id;
        user_id = new_user_id;
        contact_id = new_contact_id;
    }

    // Constructor to create object from all fields
    // Ex: Retrieving data from DB
    public Appointment(int new_appointment_id, String new_title, String new_description, String new_location, String new_type,
                       String new_start, String new_end, String new_create_date, String new_created_by,
                       String new_last_update, String new_last_updated_by, int new_customer_id, int new_user_id,
                       int new_contact_id){
        appointment_id = new_appointment_id;
        title = new_title;
        description = new_description;
        location = new_location;
        type = new_type;
        start = new_start;
        end = new_end;
        create_date = new_create_date;
        created_by = new_created_by;
        last_update = new_last_update;
        last_updated_by = new_last_updated_by;
        customer_id = new_customer_id;
        user_id = new_user_id;
        contact_id = new_contact_id;
    }

    // Getters
    private int getAppointmentID() { return appointment_id; }
    private String getTitle() { return title;}
    private String getDescription(){ return description; }
    private String getLocation(){ return location; }
    private String getType(){ return type; }
    private String getStartTime(){ return start; }
    private String getEndTime(){ return end; }
    private String getCreateDate() { return create_date; }
    private String getCreatedBy() { return created_by; }
    private String getLastUpdate() { return last_update; }
    private String getLastUpdatedBy() { return last_updated_by; }
    private int getCustomerID() { return customer_id; }
    private int getUserID() { return user_id; }
    private int getContactID() { return contact_id; }
    // Setters
    private void setAppointmentID(int id){ appointment_id = id;}
    private void setTitle(String newTitle) { title = newTitle; }
    private void setDescription(String newDesc){ description = newDesc; }
    private void setLocation(String newLocation){ location = newLocation; }
    private void setType(String newType){ type = newType;}
    private void setStartTime(String newStart) { start = newStart; }
    private void setEndTime(String newEnd){ end = newEnd;}
    private void setCreateDate(String newCreateDate){ create_date = newCreateDate; }
    private void setCreatedBy(String newCreatedBy){ created_by = newCreatedBy; }
    private void setLastUpdate(String newUpdate){ last_update = newUpdate; }
    private void setLastUpdatedby(String newUser){ last_updated_by = newUser; }
    private void setCustomerID (int newCust){ customer_id = newCust; }
    private void setUserID (int newUser){ user_id = newUser; }
    private void setContactID (int newContact) { contact_id = newContact; }
}
