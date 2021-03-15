package report;

/**
 * This class handles the data required for the Contact Schedule Report.
 */
public class ContactScheduleReport {
    private final String contact_name;
    private final String contact_id;
    private final String appointment_id;
    private final String title;
    private final String description;
    private final String start;
    private final String end;
    private final String customer_id;

    /**
     * Constructor used to create the new ContactScheduleReport
     * @param newContactName The new contact name
     * @param newContactID The new contact id
     * @param newAppointmentID The new appointment id
     * @param newTitle The new title
     * @param newDescription The new description
     * @param newStart The new start
     * @param newEnd The new end
     * @param newCustomerID The new customer id
     */
    public ContactScheduleReport(String newContactName, String newContactID, String newAppointmentID, String newTitle,
                                 String newDescription, String newStart, String newEnd, String newCustomerID){
        contact_name = newContactName;
        contact_id = newContactID;
        appointment_id = newAppointmentID;
        title = newTitle;
        description = newDescription;
        start = newStart;
        end = newEnd;
        customer_id = newCustomerID;
    }

    /**
     * Returns the contact name
     * @return String contact_name
     */
    public String getContact_name(){return contact_name;}

    /**
     * Returns the contact id
     * @return String contact_id
     */
    public String getContact_id(){return contact_id;}

    /**
     * Returns the appointment id
     * @return String appointment_id
     */
    public String getAppointment_id(){return appointment_id;}

    /**
     * Returns the title
     * @return String title
     */
    public String getTitle(){return title;}

    /**
     * Returns the description
     * @return String description
     */
    public String getDescription(){return description;}

    /**
     * Returns the start time
     * @return String start
     */
    public String getStart(){return start;}

    /**
     * Returns the end time
     * @return String end
     */
    public String getEnd(){return end;}

    /**
     * Returns the customer id
     * @return String customer_id
     */
    public String getCustomer_id(){return customer_id;}
}
