-- Creating tables for Health and Fitness Club Management System

-- Members Table
CREATE TABLE IF NOT EXISTS member (
    memberId SERIAL PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    fitnessGoal VARCHAR(255) NOT NULL,
    weightGoal DECIMAL(10, 2) NOT NULL,
    timeGoal INTEGER NOT NULL
);

-- Trainers Table
CREATE TABLE IF NOT EXISTS trainer (
    trainerId SERIAL PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    availability DATE NOT NULL
);

-- Administrative Staff Table
CREATE TABLE IF NOT EXISTS administrativeProfiles (
    administrativeId SERIAL PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Class Schedule Table
CREATE TABLE IF NOT EXISTS classSchedules (
    classId SERIAL PRIMARY KEY,
    className VARCHAR(255) NOT NULL,
    classDate DATE NOT NULL,
    startTime TIME NOT NULL,
    endTime TIME NOT NULL,
    trainerId INTEGER NOT NULL,
    FOREIGN KEY (trainerId) REFERENCES trainer(trainerId) ON DELETE SET NULL
);

-- Room Bookings Table
CREATE TABLE IF NOT EXISTS roombookings (
    bookingId SERIAL PRIMARY KEY,
    roomId INTEGER NOT NULL,
    bookingDate DATE NOT NULL,
    startTime TIME NOT NULL,
    endTime TIME NOT NULL
);

-- Achievements Table
CREATE TABLE IF NOT EXISTS achievements (
    achievementId SERIAL PRIMARY KEY,
    memberId INTEGER NOT NULL,
    description TEXT NOT NULL,
    dateAchieved DATE NOT NULL,
    FOREIGN KEY (memberId) REFERENCES member(memberId) ON DELETE CASCADE
);

-- Feedback Table
CREATE TABLE IF NOT EXISTS feedback (
    feedbackId SERIAL PRIMARY KEY,
    memberId INTEGER NOT NULL,
    classId INTEGER NOT NULL,
    trainerId INTEGER NOT NULL,
    rating INTEGER NOT NULL,
    comments TEXT,
    feedbackDate DATE NOT NULL,
    FOREIGN KEY (memberId) REFERENCES member(memberId) ON DELETE CASCADE,
    FOREIGN KEY (classId) REFERENCES classSchedules(classId) ON DELETE SET NULL,
    FOREIGN KEY (trainerId) REFERENCES trainer(trainerId) ON DELETE SET NULL
);

-- Health Metrics Table
CREATE TABLE IF NOT EXISTS healthMetrics (
    metricId SERIAL PRIMARY KEY,
    memberId INTEGER NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    height DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (memberId) REFERENCES member(memberId) ON DELETE CASCADE
);

-- Billing and Payments Table
CREATE TABLE IF NOT EXISTS billingandpayments (
    transactionId SERIAL PRIMARY KEY,
    memberId INTEGER NOT NULL,
    transactionDate DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (memberId) REFERENCES member(memberId) ON DELETE CASCADE
);

-- Equipment Maintenance Table
CREATE TABLE IF NOT EXISTS equipmentmaintenance (
    maintenanceId SERIAL PRIMARY KEY,
    equipmentId INTEGER NOT NULL,
    maintenanceDate DATE NOT NULL,
    maintenanceType VARCHAR(255) NOT NULL
);

-- Member Class Bookings Table
CREATE TABLE MemberClassBookings (
    bookingId SERIAL PRIMARY KEY,
    memberId INT NOT NULL,
    classId INT NOT NULL,
    bookingStatus VARCHAR(50) DEFAULT 'Booked',
    bookingDate DATE NOT NULL,
    FOREIGN KEY (memberId) REFERENCES Member(memberId),
    FOREIGN KEY (classId) REFERENCES ClassSchedules(classId)
);

