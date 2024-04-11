# Health-and-Fitness-Club-Management-System

## Description
- This project is a Health and Fitness Club Management System that helps manage members, trainers, administrative staff, class schedules, room bookings, equipment maintenance, and billing/payment transactions.

## Installation
- Ensure Java OpenJDK 20.0.2 is installed on your system. 
- Clone this repository and navigate into the project directory.
- Compile the Java files using your preferred IDE that supports Java. That said, it is recommended to use IntelliJ IDEA 2023.2.5 (Community Edition) for best results. 

## Usage
- Import the project into your preferred Java IDE.
- Ensure that the PostgreSQL database server is running on your local machine.
- Update the database connection details in the JdbcDatabaseOperations class if necessary.
- Run the application.
- Use the provided GUI to interact with the Health and Fitness Club Management System.
- You can perform operations such as:
- Logging in as an admin, member, or trainer.
- registering as a member.
- Adding, updating, or deleting member information.
- Adding, updating, or deleting trainer information.
- Viewing class schedules and booking rooms.
- Managing equipment maintenance records.
- Handling billing and payment transactions.
- And more.


## Components

  - `GUI.java`: This class represents the graphical user interface (GUI) for the Health and Fitness Club Management System. It extends the `JFrame` class and provides methods to interact with the GUI elements.
  - `Controller.java`: This class serves as the controller for the Health and Fitness Club Management System, facilitating communication between the GUI and the database operations.
  - `DatabaseOperations.java`: This interface defines methods for performing various operations related to the Health and Fitness Club Management System in the database.
  - `JdbcDatabaseOperations.java`: The JdbcDatabaseOperations class implements the DatabaseOperations interface to interact with the database for the Health and Fitness Club Management System using JDBC.

## Contributing - Group Project
- Ali Abdollahian (101229396) 
- Harishan Amutheesan ()
