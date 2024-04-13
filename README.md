# Health-and-Fitness-Club-Management-System

## Description

- This project is a Health and Fitness Club Management System that helps manage members, trainers, administrative staff, class schedules, room bookings, equipment maintenance, and billing/payment transactions.
- You can see the detailed explanation of the application on Vimeo using link below
- https://vimeo.com/934381497?share=copy

## Installation
- Ensure Java OpenJDK 21.0.2 is installed on your system. 
- Ensure Maven 3.8.4 is installed on your system.
- Clone this repository and navigate into the project directory.
- Compile the Java files using your preferred IDE that supports Java. That said, it is recommended to use IntelliJ IDEA 2023.2.5 (Community Edition) for best results. 


### Steps
1. **Clone the repository:** 
   ```bash
   git clone https://github.com/AliAbdollahian/Health-and-Fitness-Club-Management-System.git
   cd Health-and-Fitness-Club-Management-System

2. **Compile the project:**
- Open your IDE and import the project.
- Build the project to compile the Java files.

3. **Set up the database:**
- Ensure your PostgreSQL server is running.
- Execute the provided SQL scripts to set up the database schema and populate it with initial data.

4. **Configure database connection:**
- Update the database connection details in the `JdbcDatabaseOperations` class to match your PostgreSQL configuration.

5. **Run the application:**
- Execute the `main` method in the `GUI` class to start the application.

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
- Viewing achievements and feedback.
- Viewing a leaderboard of members ranked by their achievement count.
- Viewing a member's health metrics and calculating their BMI.

## Components

The system is composed of several classes, including:

- `Member`: Represents a member of the gym.
- `Trainer`: Represents a trainer at the gym.
- `AdministrativeStaff`: Represents an administrative staff member at the gym.
- `HealthMetrics`: Represents the health metrics of a member.
- `ClassSchedule`: Represents a class schedule at the gym.
- `RoomBooking`: Represents a room booking at the gym.
- `EquipmentMaintenance`: Represents an equipment maintenance record at the gym.
- `BillingAndPayment`: Represents a billing and payment transaction at the gym.
- `Achievement`: Represents an achievement of a member at the gym.
- `Feedback`: Represents feedback provided by a member for a class.
- `MemberAchievementCount`: Represents a member's achievement count.
- `MemberClassBooking`: Represents a class booking by a member.
- `DatabaseOperations`: An interface defining database operations related to various entities.
- `JdbcDatabaseOperations`: An implementation of the `DatabaseOperations` interface using JDBC.

## Features

- Adding, updating, or deleting member information.
- Viewing and managing a member's class schedule.
- Booking and cancelling classes for a member.
- Viewing a member's health metrics and calculating their BMI.
- Adding, updating, or deleting trainer information.
- Managing trainer availability.
- Adding, updating, or deleting administrative staff information.
- Adding, updating, or deleting health metrics for a member.
- Adding, updating, or deleting class schedules.
- Adding, updating, or deleting room bookings.
- Adding, updating, or deleting equipment maintenance records.
- Adding, updating, or deleting transactions.
- Adding, updating, or deleting achievements for a member.
- Displaying a member's achievements.
- Displaying a leaderboard of members ranked by their achievement count.
- Adding feedback provided by a member for a class.
- Retrieving all feedback records from the database.

## Contributors - Group Project
- Ali Abdollahian (101229396) 
- Harishan Amutheesan (101154757)
