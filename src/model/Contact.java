package model;

public class Contact {
    private int contact_id; // PK INT(10)
    private String contact_name; // VARCHAR(50)
    private String email; // VARCHAR(50)

    // Getters
    private int getContactID(){return contact_id;}
    private String getContactName(){ return contact_name;}
    private String getEmail(){ return email;}
    // Setters
    private void setContactID(int newID){ contact_id = newID;}
    private void setContactName(String name){ contact_name = name;}
    private void setEmail(String newEmail){ email = newEmail;}
}
