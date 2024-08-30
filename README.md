# Phonebook Manager Application

## Description
The **Phonebook Manager Application** is a robust and user-friendly tool designed to manage personal and professional contacts efficiently. Developed using **Java Swing** for the graphical user interface and **MySQL** with **JDBC** for database management, this application offers a comprehensive solution for organizing and maintaining contact information.

## Features
### **User Interface**
- **Java Swing Design:** The application features a graphical user interface (GUI) built with Java Swing, providing a user-friendly experience for managing contacts.
- **Intuitive Layout:** The layout includes easily navigable tabs or panels for different functions such as adding, editing, and viewing contacts.
- **Form Validation:** Input fields are validated to ensure that only valid data (e.g., proper phone numbers and email addresses) is entered.

### **CRUD Operations**
- **Create Contacts:** Users can add new contacts to the phonebook by filling out a form with details like name, phone number, email address, and address.
- **Read/View Contacts:** The application displays a list or table of contacts, allowing users to view details of each contact.
- **Update Contacts:** Users can edit existing contact information. Changes are updated in real-time and reflected across the application.
- **Delete Contacts:** Users can remove contacts from the phonebook, which updates the database and UI to reflect the deletion.

### **Search Functionality**
- **Quick Search:** A search bar allows users to quickly find contacts by entering a name or phone number. The results are dynamically filtered and displayed as the user types.
- **Advanced Filters:** Optionally, users can apply advanced filters to narrow down search results based on specific criteria like contact type or location.

### **Database Integration**
- **MySQL Database:** Utilizes MySQL to store and manage contact data securely. The database schema includes tables for storing contact details.
- **JDBC Connectivity:** Java Database Connectivity (JDBC) is used to interact with the MySQL database, ensuring efficient data retrieval and manipulation.

### **Data Management**
- **Efficient Data Handling:** CRUD operations are optimized for performance, ensuring that large numbers of contacts are managed efficiently without significant delays.
- **Error Handling:** Robust error handling mechanisms are in place to handle issues like database connection failures or invalid input.


## Technologies Used
- **Java Swing:** For building the graphical user interface.
- **MySQL:** For database management and storage.
- **JDBC:** For connecting Java applications to the MySQL database.


## Installation
### 1. Clone the Repository:
```git clone https://github.com/yourusername/phonebook-manager.git```

### 2. Setup MySQL Database:
- Create a database named phonebook.
- Run the SQL script provided in database/schema.sql to set up the necessary tables.

### 3. Configure JDBC Connection:
- Update the db.properties file with your MySQL database credentials.

### 4. Compile and Run:
- Use your preferred IDE (e.g., IntelliJ IDEA, Eclipse) to open the project.
- Compile and run the application.


## Usage
- **Add Contacts:** Use the “Add Contact” form to input new contact information.
- **Edit Contacts:** Modify existing contact details from the contact list.
- **Delete Contacts:** Remove contacts as needed.
- **Search Contacts:** Use the search bar to quickly locate a contact.
