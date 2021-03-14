package model;

/** This class holds the data for contacts. */
public class Contact {
    private int contact_id; // PK INT(10)
    private String contact_name; // VARCHAR(50)
    private String email; // VARCHAR(50)

    /** This constructor is used to create an Appointment object from database retrieval (all fields available).
     @param contactID The contact ID to insert
     @param contactName The contact name to insert
     @param contactEmail The email address to insert */
    public Contact(int contactID, String contactName, String contactEmail) {
        contact_id = contactID;
        contact_name = contactName;
        email = contactEmail;
    }

    /** This constructor is used to create a Contact object from fields that do not auto populate.
     @param contactName The contact name to insert/retrieve
     @param contact_email The email address to insert/retrieve */
    public Contact(String contactName, String contact_email) {
        contact_name = contactName;
        email = contact_email;
    }

    /** Returns the Contact ID.
     @return int contact_id */
    public int getContactID(){return contact_id;}

    /** Returns the Contact name.
     @return String contact_name */
    public String getContactName(){ return contact_name;}

    /** Returns the Contact email.
     @return String email */
    public String getEmail(){ return email;}

    /** Sets the Contact ID.
     @param newID The ID to set. */
    public void setContactID(int newID){ contact_id = newID;}

    /** Sets the Contact name.
     @param name The name to set. */
    public void setContactName(String name){ contact_name = name;}

    /** Sets the Contact email.
     @param newEmail The email address to set. */
    public void setEmail(String newEmail){ email = newEmail;}
}
