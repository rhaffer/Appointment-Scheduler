package model;

import java.time.LocalDateTime;

/** This class holds the data for appointments. */
public class Appointment {
    private int appointment_id; // PK INT(10)
    private String title; // VARCHAR (50)
    private String description; // VARCHAR (50)
    private String location; // VARCHAR(50)
    private String type; // VARCHAR(50)
    private LocalDateTime start; // DATETIME
    private LocalDateTime end; // DATETIME
    private LocalDateTime create_date; // DATETIME
    private String created_by; // VARCHAR(50)
    private String last_update; // TIMESTAMP
    private String last_updated_by; // VARCHAR(50)
    private int customer_id; // FK INT(10)
    private int user_id; // FK INT(10)
    private int contact_id; // FK INT(10)

    /** This constructor is used to create an Appointment object from fields that do not auto populate.
     @param new_title New title
     @param new_description New description
     @param new_location New location
     @param new_type New type
     @param new_start New start
     @param new_end New end
     @param new_create_date New created date
     @param new_created_by New created by (who created)
     @param new_last_update Timestamp for last update time
     @param new_last_updated_by New last updated by (who updated)
     @param new_customer_id Customer associated with Appointment
     @param new_user_id User that created the Appointment
     @param new_contact_id Contact information for the customer
     */
    public Appointment(String new_title, String new_description, String new_location, String new_type,
                       LocalDateTime new_start, LocalDateTime new_end, LocalDateTime new_create_date, String new_created_by,
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

    /** This constructor is used to create an Appointment object from database retrieval (all fields available).
     @param new_appointment_id  New appointment ID
     @param new_title New title
     @param new_description New description
     @param new_location New location
     @param new_type New type
     @param new_start New start
     @param new_end New end
     @param new_create_date New created date
     @param new_created_by New created by (who created)
     @param new_last_update Timestamp for last update time
     @param new_last_updated_by New last updated by (who updated)
     @param new_customer_id Customer associated with Appointment
     @param new_user_id User that created the Appointment
     @param new_contact_id Contact information for the customer
     */
    public Appointment(int new_appointment_id, String new_title, String new_description, String new_location, String new_type,
                       LocalDateTime new_start, LocalDateTime new_end, LocalDateTime new_create_date, String new_created_by,
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

    /**
     * Additional overloaded Constructor used when updating appointments
     * @param newAppointmentId new appointment id
     * @param newTitle new title
     * @param newDescription new description
     * @param newLocation new location
     * @param newType new type
     * @param newStartTime new start time
     * @param newEndTime new end time
     * @param newLastUpdate new last update timestamp
     * @param newLastUpdatedBy new last updated by
     * @param newCustomerId new customer id
     * @param newUserId new user id
     * @param newContactId new contact id
     */
    public Appointment(int newAppointmentId, String newTitle, String newDescription, String newLocation, String newType,
                       LocalDateTime newStartTime, LocalDateTime newEndTime, String newLastUpdate, String newLastUpdatedBy,
                       int newCustomerId, int newUserId, int newContactId) {
        appointment_id = newAppointmentId;
        title = newTitle;
        description = newDescription;
        location = newLocation;
        type = newType;
        start = newStartTime;
        end = newEndTime;
        last_update = newLastUpdate;
        last_updated_by = newLastUpdatedBy;
        customer_id = newCustomerId;
        user_id = newUserId;
        contact_id = newContactId;
    }

    /**
     * Returns the Appointment ID.
     *
     * @return int appointment_id
     */
    public int getAppointment_id() {
        return appointment_id;
    }

    /** Returns the Appointment title.
     @return String title */
    public String getTitle() { return title;}

    /** Returns Appointment description.
     @return String description */
    public String getDescription(){ return description; }

    /** Returns Appointment location.
     @return String location */
    public String getLocation(){ return location; }

    /** Returns the type of Appointment.
     @return String type */
    public String getType(){
        return type;
    }

    /**
     * Returns the Appointment start time.
     *
     * @return LocalDateTime start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Returns the Appointment end time.
     *
     * @return LocalDateTime end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Returns the date the Appointment was scheduled.
     *
     * @return LocalDateTime create_date
     */
    public LocalDateTime getCreate_date() {
        return create_date;
    }

    /** Returns who created the Appointment (User).
     @return String created_by */
    public String getCreated_by() { return created_by; }

    /** Returns when the Appointment was last updated.
     @return String last_update */
    public String getLastUpdate() { return last_update; }

    /** Returns who last updated the Appointment.
     @return String last_updated_by */
    public String getLastUpdatedBy() {
        return last_updated_by;
    }

    /** Returns the Customer ID associated with the Appointment.
     @return int customer_id */
    public int getCustomer_id() { return customer_id; }

    /** Returns the User ID associated with the Appointment.
     @return int user_id */
    public int getUserID() {
        return user_id;
    }

    /** Returns the Contact ID associated with the Appointment.
     @return int contact_id */
    public int getContact_id() { return contact_id; }

    /** Sets the Appointment ID.
     @param id The new Appointment ID to be set. */
    public void setAppointmentID(int id){ appointment_id = id;}

    /** Sets the title to a new Appointment title.
     @param newTitle The new title to be set. */
    public void setTitle(String newTitle) { title = newTitle; }

    /** Sets the Appointment description.
     @param newDesc The description to be set. */
    public void setDescription(String newDesc){ description = newDesc; }

    /** Sets the new Appointment location.
     @param newLocation The location to be set. */
    public void setLocation(String newLocation){ location = newLocation; }

    /** Sets the new Appointment type.
     @param newType The type to be set. */
    public void setType(String newType){ type = newType;}

    /** Sets the new Appointment start time.
     @param newStart The new Appointment start time. */
    public void setStartTime(LocalDateTime newStart) { start = newStart; }

    /** Sets the new Appointment end time.
     @param newEnd The new Appointment end time. */
    public void setEndTime(LocalDateTime newEnd){ end = newEnd;}

    /** Sets the new Appointment create date.
     @param newCreateDate  The new Appointment create date. */
    public void setCreateDate(LocalDateTime newCreateDate){ create_date = newCreateDate; }

    /** Sets who created the Appointment.
     @param newCreatedBy The user who created the Appointment. */
    public void setCreatedBy(String newCreatedBy){ created_by = newCreatedBy; }

    /** Sets when the Appointment was last updated.
     @param newUpdate When the Appointment was last updated. */
    public void setLastUpdate(String newUpdate){ last_update = newUpdate; }

    /** Sets who last updated the Appointment.
     @param newUser The user who last updated the Appointment. */
    public void setLastUpdatedby(String newUser){ last_updated_by = newUser; }

    /** Sets the new Customer ID.
     @param newCust The customer ID to be added. */
    public void setCustomerID (int newCust){ customer_id = newCust; }

    /** Sets the new User ID.
     @param newUser The User ID to be added. */
    public void setUserID (int newUser){ user_id = newUser; }

    /** Sets the new Contact ID.
     @param newContact The new contact to be added. */
    public void setContactID (int newContact) { contact_id = newContact; }
}
