package model;

public class Customer {
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

    // Constructor to create object from non-populating fields
    //  Ex: Sending an insert to DB
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

    // Constructor to create object from all fields
    //  Ex: Retrieving data from DB
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

    public Customer() {
    }

    // Getters and Setters for Customer Class
    //  *** CustomerID and LastUpdate do not have setters ***

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setCustomerName(String name) {
        customerName = name;
    }

    public void setAddress(String new_address) {
        address = new_address;
    }

    public void setPostalCode(String postal_code) {
        postalCode = postal_code;
    }

    public void setPhoneNumber(String phone_number) {
        phoneNumber = phone_number;
    }

    public void setCreateDate(String create_date) {
        createDate = create_date;
    }

    public void setCreatedBy(String created_by) {
        createdBy = created_by;
    }

    public void setLastUpdatedBy(String last_updated_by) {
        lastUpdatedBy = last_updated_by;
    }

    public void setDivisionID(int division_id) {
        divisionID = division_id;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
