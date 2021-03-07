package model;

public class Contact {
    private int contact_id; // PK INT(10)
    private String contact_name; // VARCHAR(50)
    private String email; // VARCHAR(50)

    public Contact(int contactID, String contactName, String contactEmail) {
        contact_id = contactID;
        contact_name = contactName;
        email = contactEmail;
    }

    public Contact(String contactName, String contact_email) {
        contact_name = contactName;
        email = contact_email;
    }

    // Getters
    public int getContactID(){return contact_id;}
    public String getContactName(){ return contact_name;}
    public String getEmail(){ return email;}
    // Setters
    public void setContactID(int newID){ contact_id = newID;}
    public void setContactName(String name){ contact_name = name;}
    public void setEmail(String newEmail){ email = newEmail;}
}
