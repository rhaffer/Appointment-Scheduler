package model;

/** This class holds the data for customers. */
public class Customer {
    // Completed.
    private int customerID; //Auto Increment (PK)
    private String customerName; // VARCHAR(50)
    private String address; // VARCHAR (100)
    private String postalCode; // VARCHAR(50)
    private String phoneNumber; // VARCHAR(50)
    private String createDate; //DATETIME
    private String createdBy; // VARCHAR(50)
    private String lastUpdate; // TIMESTAMP
    private String lastUpdatedBy; //VARCHAR(50)
    private int divisionID; // INT(10) FK

    /** This constructor is used to create a Contact object from fields that do not auto populate.
     @param customer_name The name to be inserted
     @param new_address The address to be inserted
     @param postal_code The postal code to be inserted
     @param phone_number The phone number to be inserted
     @param create_date The date the Customer was created
     @param created_by The User who created the Customer
     @param last_updated_by The User who last updated the Customer
     @param division_id The FirstLevelDivision ID that correlates to this Customer */
    public Customer(String customer_name, String new_address, String postal_code, String phone_number,
                    String create_date, String created_by, String last_updated_by, int division_id){
        customerName = customer_name;
        address=new_address;
        postalCode=postal_code;
        phoneNumber=phone_number;
        createDate=create_date;
        createdBy=created_by;
        lastUpdatedBy=last_updated_by;
        divisionID=division_id;
    }

    /** This constructor is used to create a Customer object from database retrieval (all fields available).
     @param customer_id The customer ID to be inserted
     @param customer_name The customer name to be inserted
     @param new_address The address to be inserted
     @param postal_code The postal code to be inserted
     @param phone_number The phone number to be inserted
     @param create_date The date the Customer was created to be inserted
     @param created_by The User who created the Customer to be inserted
     @param last_update The time that the Customer was last updated
     @param last_updated_by The User who last updated the Customer
     @param division_id The FirstLevelDivision ID to be inserted */
    public Customer(int customer_id, String customer_name, String new_address, String postal_code, String phone_number,
                    String create_date, String created_by, String last_update, String last_updated_by, int division_id){
        customerID=customer_id;
        customerName=customer_name;
        address = new_address;
        postalCode = postal_code;
        phoneNumber = phone_number;
        createDate = create_date;
        createdBy = created_by;
        lastUpdate = last_update;
        lastUpdatedBy = last_updated_by;
        divisionID = division_id;
    }

    /** Generic constructor class. */
    public Customer() {
    }

    // Getters and Setters for Customer Class
    //  *** CustomerID and LastUpdate do not have setters ***
    /** Returns the Customer ID.
     @return int customerID */
    public int getCustomerID() { return customerID; }

    /** Returns the Customer name.
     @return String customerName */
    public String getCustomerName() { return customerName; }

    /** Returns the Customer address.
     @return String address*/
    public String getAddress() { return address; }

    /** Returns the Customer postal code.
     @return String postalCode */
    public String getPostalCode() { return postalCode; }

    /** Returns the Customer phone number.
     @return String phoneNumber */
    public String getPhoneNumber() { return phoneNumber; }

    /** Returns the Customer created date.
     @return String createDate */
    public String getCreateDate() { return createDate; }

    /** Returns the User who created the Customer.
     @return String createdBy */
    public String getCreatedBy() { return createdBy; }

    /** Returns the last time the Customer was updated.
     @return String lastUpdate */
    public String getLastUpdate() { return lastUpdate; }

    /** Returns the User who last updated the Customer.
     @return String lastUpdatedBy */
    public String getLastUpdatedBy() { return lastUpdatedBy; }

    /** Returns the FirstLevelDivision ID that correlates to the Customer.
     @return int divisionID */
    public int getDivisionID() { return divisionID; }

    /** Sets the Customer name.
     @param name The name to be inserted */
    public void setCustomerName(String name) { customerName = name; }

    /** Sets the Customer address.
     @param new_address The address to be inserted. */
    public void setAddress(String new_address) { address = new_address; }

    /** Sets the Customer postal code
     @param postal_code The postal code to be inserted */
    public void setPostalCode(String postal_code) { postalCode = postal_code; }

    /** Sets the Customer phone number.
     @param phone_number The phone number to be inserted*/
    public void setPhoneNumber(String phone_number) { phoneNumber = phone_number; }

    /** Sets the date the Customer was created.
     @param create_date The date the customer was created to be inserted */
    public void setCreateDate(String create_date) { createDate = create_date; }

    /** Sets the User who created the Customer.
     @param created_by The User to be inserted */
    public void setCreatedBy(String created_by) { createdBy = created_by; }

    /** Sets the User who last updated the Customer.
     @param last_updated_by The last User to update the Customer */
    public void setLastUpdatedBy(String last_updated_by) { lastUpdatedBy = last_updated_by; }

    /** Sets the FirstLevelDivision ID for the Customer.
     @param division_id The FirstLevelDivision ID to be inserted */
    public void setDivisionID(int division_id) { divisionID = division_id; }

    /** Overrides the toString method. Allows for Customer name to be viewed in ComboBoxes.
     @return String customerName */
    @Override
    public String toString() {
        return customerName;
    }
}
