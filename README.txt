Title: Appointment Scheduler
Purpose: This program is used to schedule appointments. All of the data within the application to include Countries,
FirstLevelDivisions, Customers, Contacts, and Appointments are saved within a database. This allows for the user to
quickly and accurately schedule and retrieve appointment information.
*******************************
Author: Rick Haffer
Contact: rhaffer@wgu.edu
Student ID: 001375723
Application Version: 1.0
IDE Version: IntelliJ IDEA 2020.3.2
JDK Version: Amazon Corretto 11.0.10 (Ran on Apple M1 Mac -- alternative JDK version 11.0.10 runs as well)
JavaFX Version: 11.0.2
MySQL JDBC Driver: 5.1.49
*******************************
Requirements:
A1:
The login form allows for a user ID and password to be entered. If an incorrect login or password is provided an
error message is shown. Two default usernames and passwords are preset upon submission.

    Username: user Password: user
    Username: admin Password: admin

Additionally, if the user of the application wishes to create their own username and password, a registration screen
is available by clicking on "Create One" highlighted in blue on the login screen. The passwords must match in order for
a user to be created successfully.

The logged in user's location is display in the lower left hand corner of the login screen. If the default Locale is
set to French, all of the login and registration screens will be translated to French. This includes input fields as
well as error messages.
--------------------------------

A2:
Customer and appointment records can both be created, read, updated and deleted. Customers and appointments each have
their own screens that can be selected after logging into the application. When deleting a Customer, a confirmation
dialogue appears confirming that this will also delete all of the appointments associated with a Customer. When adding
and updating a customer, text fields are used to collect the following data: customer name, address, postal code, and
phone number. Customer IDs are auto-generated, and first-level division (i.e., states, provinces) and country data are
collected using separate combo boxes. When updating a Customer, the customer name must be selected from the first
ComboBox and then all fields will auto-populate that are associated with a Customer. All of the original data is
populated with the Customer_ID being disabled.

Country and first level divisions auto populate based on the country selected. Once a country is selected, all of the
first level divisions associated with that country populate in the ComboBox below the country ComboBox.

A table view is available with all of the data associated with every customer. This table view is not editable, and in
order to update or auto populate fields, the user must choose a Customer name from the ComboBox.
--------------------------------

A3a:
Appointments are able to created, updated, and deleted. Creating appointments has its own screen while updating and
deleting are on the same screen. A contact (customer) name is provided in a ComboBox on the create and update/delete
screens. When deleting appointments, a custom message is provided with the Appointment ID and Type of appointment
cancelled.The Appointment_ID is displayed, but disabled throughout the application where applicable. When adding and
updating an appointment, text fields are used to record the following data: Appointment_ID, title, description,
location, contact, type, start date and time, end date and time, Customer_ID, and User_ID.  All of the original
appointment information is displayed on the update form in local time zone. All of the appointment fields can be
updated except Appointment_ID, which is disabled.
--------------------------------

A3b:
Table views are made available with the following sort functionality: weekly view, monthly view, and all appointments.
All table views have the same information displayed and display all of the available data associated with the
appointment.
--------------------------------

A3c:
Appointment times are available to be adjusted/updated. All times are displayed in local time and are inserted in
the database in UTC time. A comparison is made with the open and closed business hours that are in EST.
--------------------------------

A3d:
A user cannot schedule an appointment outside of available business hours. The Start and End time ComboBoxes do not
allow for this. Additionally, when selecting a date that is on a weekend, an error is provided that will not allow a
user to select that day.
Overlapping appointments are impossible to create due to how the Start and End ComboBoxes are generated. If a start time
is in the database, it is omitted from being selected as a Start time for a newly created or updated appointment.
If a user inserts an incorrect username or password on the Login screen, a error message is displayed.

A check is present to consider for overlapping appointments. See Create_new_appointment.java line 185.
--------------------------------

A3e:
An alert is triggered when a user logins and there is an appointment within 15 minutes of logging in.
Note** this alert is only triggered upon login. There is not a Listener that will alert the user after they have
been logged in for any period of time.
--------------------------------

A3f:
There are three reports included within the application. Each one fall under the "Reports" screen on the Navigation
panel. The first is the total number of appointments seperated by Type and Month. The second is a schedule by Contact
that includes the contact name, contact ID, appointment ID, title, description, start date, end date, and customer ID.
The third is the number of appointments that occur by date.
--------------------------------

B:
There are two lambda expressions in the "Update_Delete_Customer.java" file.
The first is located on line 170 and filters responses made on the Confirmation dialogue box. If the user selects a
button type of "OK" then a variable is written that will then be used to determine whether or not all appointments will
be deleted.
The second lambda expression is on line 171. This lambda looks for the variable created above, and if it exists, then
deletes all appointments and the customer associated.
--------------------------------

C:
Login attempts are logged and saved to login_activity.txt. The attempts are appended to the file with a timestamp
and status of the login attempt.
--------------------------------

D:
Javadoc comments are made throughout 100% of the code and are located in javadoc/index.html.
--------------------------------

E:
Additional report description:
The additional report that I chose to include was the number of appointments that occur on each day. This report scans
all of the appointments and checks to see how many appointments were created on that day.
--------------------------------










