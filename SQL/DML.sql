-- Insert data into Members table
INSERT INTO member (memberId, fullName, dateOfBirth, fitnessGoal, weightGoal, timeGoal)
VALUES

    (1, 'Harishan Amutheesan', '2001-01-01', 'Power Lifting', 100, 60),
    (2, 'Ali Abdollahian', '2001-01-01', 'Kinesthetics', 100, 60),
    (3, 'Cathy Lee', '1979-11-30', 'Improve endurance', 60, 60),
    (4, 'David Wilson', '2001-06-18', 'Gain muscle', 75, 60),
    (5, 'Ella Brown', '1995-09-24', 'General fitness', 65, 60),
    (6, 'Frankie Lim', '1988-10-10', 'Increase flexibility', 50, 60),
    (7, 'Grace Field', '1975-01-15', 'Lose weight', 70, 60),
    (8, 'Hector Soto', '1980-05-22', 'Build stamina',83, 60),
    (9, 'Irina Shayk', '1993-11-09', 'General fitness', 55, 60),
    (10, 'John Doe', '1990-02-25', 'Gain muscle', 85, 60);

-- Reset the sequence for the memberId in the member table
SELECT setval('member_memberid_seq', COALESCE((SELECT MAX(memberId) + 1 FROM member), 1), false);

-- Insert data into Trainers table
INSERT INTO trainer (trainerId, fullName, availability)
VALUES
    (1, 'Frank Miller', '2024-04-21'),
    (2, 'Georgia Kite', '2024-04-22'),
    (3, 'Harry Borg', '2024-04-23'),
    (4, 'Isabella Torres', '2024-04-24'),
    (5, 'Jason Mamoa', '2024-04-25');

-- Insert data into AdministrativeStaff table
INSERT INTO administrativeProfiles (administrativeId, fullName, email)
VALUES
    (1, 'Harishan Amutheesan', 'harishanamutheesan@cmail.carleton.ca'),
    (2, 'Ali Abdollahian', 'aliabdollahian@cmail.carleton.ca'),
    (3, 'Karen Stone', 'karen.stone@example.com');

-- Insert data into ClassSchedule table
INSERT INTO classSchedules (classId, className, classDate, startTime, endTime, trainerId)
VALUES
    (1, 'Yoga Basics', '2024-04-15', '08:00', '09:00', 1),
    (2, 'Advanced Cardio', '2024-04-15', '10:00', '11:30', 2),
    (3, 'Weight Training', '2024-04-16', '07:00', '08:30', 3),
    (4, 'Pilates Introduction', '2024-04-17', '09:00', '10:00', 4),
    (5, 'High-Intensity Training', '2024-04-18', '18:00', '19:30', 5);
SELECT setval('classschedules_classid_seq', COALESCE((SELECT MAX(classId) + 1 FROM classschedules), 1), false);

-- Insert data into RoomBookings table
INSERT INTO roombookings (bookingId, roomId, bookingDate, startTime, endTime)
VALUES
    (1, 101, '2024-04-15', '08:00', '09:00'),
    (2, 102, '2024-04-15', '10:00', '11:30'),
    (3, 103, '2024-04-16', '07:00', '08:30'),
    (4, 104, '2024-04-17', '09:00', '10:00'),
    (5, 105, '2024-04-18', '18:00', '19:30');
SELECT setval('roombookings_bookingid_seq', COALESCE((SELECT MAX(bookingId) + 1 FROM roombookings), 1), false);

-- Insert data into Achievements table
INSERT INTO achievements (achievementId, memberId, description, dateAchieved)
VALUES
    (1, 1, 'Completed 10 Yoga Sessions', '2024-03-10'),
    (2, 2, 'Lost 5 kg', '2024-03-20'),
    (3, 3, 'Ran First Marathon', '2024-03-15'),
    (4, 1, 'Benched 50 kg', '2024-04-01'),
    (5, 4, 'First Pilates Class', '2024-03-25'),
    (6, 5, 'Completed 5 HIIT Sessions', '2024-03-30');

SELECT setval('achievements_achievementid_seq', COALESCE((SELECT MAX(achievementId) + 1 FROM achievements), 1), false);

-- Insert data into Feedback table
INSERT INTO feedback (feedbackId, memberId, classId, trainerId, rating, comments, feedbackDate)
VALUES
    (1, 1, 1, 1, 5, 'Great class!', '2024-03-10'),
    (2, 2, 2, 2, 4, 'Very intense, but rewarding.', '2024-03-20'),
    (3, 3, 3, 3, 5, 'Excellent guidance!', '2024-03-25'),
    (4, 4, 4, 4, 3, 'Good introduction to Pilates.', '2024-03-27');
SELECT setval('feedback_feedbackid_seq', COALESCE((SELECT MAX(feedbackId) + 1 FROM feedback), 1), false);


-- Health Metrics Data Insertion
INSERT INTO healthMetrics (metricId, memberId, weight, height) VALUES
                                                                   (1, 1, 54, 160),
                                                                   (2, 2, 82, 175),
                                                                   (3, 3, 70, 165),
                                                                   (4, 4, 78, 180);

-- Reset the sequence for the metricId in the healthMetrics table
SELECT setval('healthMetrics_metricId_seq', COALESCE((SELECT MAX(metricId) + 1 FROM healthmetrics), 1), false);

-- Billing and Payments Data Insertion
INSERT INTO billingandpayments (transactionId, memberId, transactionDate, amount, status) VALUES
                                                                                              (1, 1,'2024-03-01', 30, 'Paid'),
                                                                                              (2, 2, '2024-03-02', 25, 'Paid'),
                                                                                              (3, 3, '2024-03-03', 20, 'Pending'),
                                                                                              (4, 5, '2024-03-04', 45, 'Paid');
SELECT setval('billingandpayments_transactionid_seq', COALESCE((SELECT MAX(transactionId) + 1 FROM billingandpayments), 1), false);

-- Insert data into EquipmentMaintenance table
INSERT INTO equipmentmaintenance (maintenanceId, equipmentId, maintenanceDate, maintenanceType)
VALUES
    (1, 1, '2024-03-01', 'Repair'),
    (2, 2, '2024-03-02', 'Routine Check'),
    (3, 3, '2024-03-05', 'Replacement');
SELECT setval('equipmentmaintenance_maintenanceid_seq', COALESCE((SELECT MAX(maintenanceId) + 1 FROM equipmentmaintenance), 1), false);

