import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Controller {
    public JdbcDatabaseOperations jdbcDatabaseOp;
    public GUI gui;

    public Controller(JdbcDatabaseOperations jdbcDatabaseOp, GUI gui) {
        this.jdbcDatabaseOp = jdbcDatabaseOp;
        this.gui = gui;
    }

    /**
     * Handles the event when the "Admin" button is clicked in the GUI.
     * Prompts the user to enter their admin ID and retrieves the administrative staff information from the database.
     * If the admin is found, opens the admin profile in the GUI; otherwise, displays a "Admin not found!" message.
     */
    public void adminClicked() {
        int adminId = Integer.parseInt(JOptionPane.showInputDialog("Enter your admin ID: "));
        AdministrativeStaff admin = jdbcDatabaseOp.getAdministrativeStaffById(adminId);

        if (admin != null) {
            gui.openAdminProfile(admin);
        } else {
            JOptionPane.showMessageDialog(null, "Admin not found!");
        }
    }

    /**
     * Handles the event when the "Member" button is clicked in the GUI.
     * Prompts the user to choose between registering as a new member or logging in as an existing member.
     * If the user chooses to log in, prompts for the member ID and retrieves the member information from the database.
     * If the member is found, opens the member profile in the GUI; otherwise, displays a "Member not found!" message.
     */
    public void memberClicked() {
        Object[] options = {"New Member", "Existing Member"};
        int choice = JOptionPane.showOptionDialog(null, "Are you a new member or an existing member?", "Member Login", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            registerNewMember();
        } else if (choice == 1) {
            int memberId = Integer.parseInt(JOptionPane.showInputDialog("Enter your member ID: "));
            Member member = jdbcDatabaseOp.getMemberById(memberId);

            if (member != null) {
                gui.openMemberProfile(member);
            } else {
                JOptionPane.showMessageDialog(null, "Member not found!");
            }
        }
    }

    /**
     * Registers a new member by collecting member information via input dialogs and saving it to the database.
     */
    private void registerNewMember() {
        String fullName = JOptionPane.showInputDialog("Enter your full name:");
        JDateChooser dateChooser = new JDateChooser();
        int option = JOptionPane.showConfirmDialog(null, dateChooser, "Select your date of birth", JOptionPane.OK_CANCEL_OPTION);
        Date dateOfBirth = null;
        if (option == JOptionPane.OK_OPTION) {
            dateOfBirth = dateChooser.getDate();
        } else {
            System.out.println("Operation canceled.");
            return;
        }
        String fitnessGoal = JOptionPane.showInputDialog("Enter your fitness goal:");
        double weightGoal = Double.parseDouble(JOptionPane.showInputDialog("Enter your weight goal:"));

        Member newMember = new Member(0, fullName, dateOfBirth, fitnessGoal, weightGoal, 0);
        jdbcDatabaseOp.addMember(newMember);
    }

    /**
     * Handles the event when the "Trainer" button is clicked in the GUI.
     * Prompts the user to enter their trainer ID and retrieves the trainer information from the database.
     * If the trainer is found, opens the trainer profile in the GUI; otherwise, displays a "Trainer not found!" message.
     */
    public void trainerClicked() {
        int idOfTrainer = Integer.parseInt(JOptionPane.showInputDialog("Enter your trainer ID: "));
        Trainer trainer = jdbcDatabaseOp.getTrainerById(idOfTrainer);

        if (trainer != null) {
            gui.openTrainerProfile(trainer);
        } else {
            JOptionPane.showMessageDialog(null, "Trainer not found!");
        }
    }

    /**
     * Sets the availability of the specified trainer by prompting the user to select an availability date.
     * Updates the trainer's availability in the database and updates the trainer profile in the GUI.
     *
     * @param trainer The Trainer object whose availability is to be set.
     */
    void setAvailability(Trainer trainer) {
        System.out.println("Current availability: " + trainer.getAvailability());

        JDateChooser dateChooser = new JDateChooser();
        int option = JOptionPane.showConfirmDialog(null, dateChooser, "Select availability date", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Date availability = dateChooser.getDate();
            trainer.setAvailability(availability);
            jdbcDatabaseOp.updateTrainer(trainer);
            System.out.println("Availability updated successfully.");
            gui.updateTrainerProfile(trainer);
        } else {
            System.out.println("Operation canceled.");
        }
    }

    /**
     * Searches for a member by name in the database and displays the member's information if found.
     * Otherwise, displays a "Member not found!" message.
     */
    void searchMembersByName() {
        String memberName = JOptionPane.showInputDialog("Enter the name of the member: ");
        Member member = jdbcDatabaseOp.getMemberByName(memberName);

        if (member != null) {
            JOptionPane.showMessageDialog(null, "Member found:\n" + member);
        } else {
            JOptionPane.showMessageDialog(null, "Member not found!");
        }
    }

    /**
     * Allows a member to update their profile information based on the chosen option.
     * Prompts the user to select the information to update and provides input dialogs for entering new values.
     * Updates the member's information in the database and updates the member profile in the GUI.
     *
     * @param member The Member object whose profile is to be updated.
     */
    void updateProfile(Member member) {
        String[] options = {"Full Name", "Date of Birth", "Fitness Goal", "Weight Goal", "Time Goal"};
        int choice = JOptionPane.showOptionDialog(null, "What information would you like to update?", "Update Profile", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            return;
        }

        String newValue;
        switch (choice) {
            case 0:
                newValue = JOptionPane.showInputDialog("Enter your full name: ");
                member.setFullName(newValue);
                break;
            case 1:
                Date dateOfBirth = gui.promptForDateOfBirth();
                member.setDateOfBirth(dateOfBirth);
                break;
            case 2:
                newValue = JOptionPane.showInputDialog("Enter your fitness goal: ");
                member.setFitnessGoal(newValue);
                break;
            case 3:
                newValue = JOptionPane.showInputDialog("Enter your weight goal: ");
                member.setWeightGoal(Double.parseDouble(newValue));
                break;
            case 4:
                newValue = JOptionPane.showInputDialog("Enter your time goal: ");
                member.setTimeGoal(Integer.parseInt(newValue));
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        jdbcDatabaseOp.updateMember(member);
        JOptionPane.showMessageDialog(null, "Profile updated successfully.");
        gui.updateGUMemberProfile(member);
    }

    /**
     * Retrieves the height of the specified member from the database.
     * Returns the member's height if available; otherwise, returns 0.0.
     *
     * @param member The Member object for which the height is to be retrieved.
     * @return The height of the member.
     */
    public double getHeightMember(Member member) {
        List<HealthMetrics> healthMetrics = jdbcDatabaseOp.getHealthMetricsByMemberId(member.getMemberId());
        double height = 0.0;
         if (!healthMetrics.isEmpty()) {
           HealthMetrics latestMetrics = healthMetrics.get(healthMetrics.size() - 1);
            height = latestMetrics.getHeight();
        }
        return height;
    }

    /**
     * Retrieves all room bookings from the database and displays them in the GUI for room booking management.
     * Displays an error message if there is an error fetching room bookings.
     */
    public void roomBookingManagement() {
        try {
            List<RoomBooking> roomBookings = jdbcDatabaseOp.getAllRoomBookings();
            gui.displayRoomBookings(roomBookings);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching room bookings from the database!");
        }
    }

    /**
     * Manages the class schedule for the specified member by providing options to view, book, or cancel classes.
     * Invokes corresponding methods based on the user's choice.
     *
     * @param member The Member object whose class schedule is to be managed.
     */
    public void manageSchedule(Member member) {
        String[] options = {"View Schedule", "Book Class", "Cancel Class"};
        int choice = JOptionPane.showOptionDialog(gui.frame, "Choose an action:", "Manage Schedule",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                viewMemberSchedule(member);
                break;
            case 1:
                bookClassForMember(member);
                break;
            case 2:
                cancelClassForMember(member);
                break;
            default:
                JOptionPane.showMessageDialog(gui.frame, "Invalid option.");
                break;
        }
    }

    /**
     * Retrieves the scheduled classes for the specified member from the database and displays them in a message dialog.
     * Displays the class name, date, and booking status for each scheduled class.
     *
     * @param member The Member object for whom to view the schedule.
     */
    private void viewMemberSchedule(Member member) {
        try {
            List<MemberClassBooking> bookings = jdbcDatabaseOp.getBookingsByMemberId(member.getMemberId());
            StringBuilder sb = new StringBuilder("Your Scheduled Classes:\n");

            for (MemberClassBooking booking : bookings) {
                ClassSchedule schedule = jdbcDatabaseOp.getClassScheduleById(booking.getClassId());
                String formattedDate = schedule.getClassDate().toString(); // Convert LocalDate to String
                sb.append("Class: ").append(schedule.getClassName())
                        .append(", Date: ").append(formattedDate)
                        .append(", Status: ").append(booking.getBookingStatus()).append("\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString(), "Schedule", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error fetching schedule.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Displays available classes retrieved from the database in an option dialog for the member to choose from.
     * Books the selected class for the specified member by adding a booking entry to the database.
     * Displays a success message upon successful booking.
     *
     * @param member The Member object for whom to book the class.
     */
    private void bookClassForMember(Member member) {
        try {
            List<ClassSchedule> schedules = jdbcDatabaseOp.getAllClassSchedules();
            String[] options = schedules.stream().map(schedule ->
                    "ID: " + schedule.getClassId() +
                            " - " + schedule.getClassName() +
                            ", Date: " + schedule.getClassDate().toString() // Convert LocalDate to String
            ).toArray(String[]::new);

            int classChoice = JOptionPane.showOptionDialog(gui.frame,
                    "Select a class to book:", "Available Classes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (classChoice >= 0) {
                ClassSchedule selectedSchedule = schedules.get(classChoice);
                LocalDate classDate = selectedSchedule.getClassDate();
                jdbcDatabaseOp.addBooking(member.getMemberId(), selectedSchedule.getClassId(), "Booked", java.sql.Date.valueOf(classDate));
                JOptionPane.showMessageDialog(null, "Class booked successfully!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error booking class.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the bookings for the specified member from the database and displays them in an option dialog.
     * Cancels the selected booking for the member by deleting the booking entry from the database.
     * Displays a success message upon successful cancellation.
     *
     * @param member The Member object for whom to cancel the booking.
     */
    private void cancelClassForMember(Member member) {
        try {
            List<MemberClassBooking> bookings = jdbcDatabaseOp.getBookingsByMemberId(member.getMemberId());
            String[] options = bookings.stream().map(booking -> "Booking ID: " + booking.getBookingId() + ", Class ID: " + booking.getClassId()).toArray(String[]::new);

            int bookingChoice = JOptionPane.showOptionDialog(gui.frame, "Select a booking to cancel:", "Your Bookings",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (bookingChoice >= 0) {
                MemberClassBooking selectedBooking = bookings.get(bookingChoice);
                jdbcDatabaseOp.deleteBooking(selectedBooking.getBookingId());
                JOptionPane.showMessageDialog(null, "Booking cancelled successfully.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error cancelling booking.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Removes the room booking with the specified ID from the database.
     *
     * @param bookingId The ID of the room booking to be removed.
     * @throws SQLException If an SQL exception occurs while accessing the database.
     */
    public void removeRoomBooking(int bookingId) throws SQLException {
        jdbcDatabaseOp.deleteRoomBooking(bookingId);
    }

    /**
     * Retrieves all equipment maintenance records from the database and displays them in the GUI for monitoring.
     * Calls the GUI method to display the equipment maintenance records.
     *
     * @throws SQLException If an SQL exception occurs while accessing the database.
     */
    public void equipmentMaintenanceMonitoring() throws SQLException {
        List<EquipmentMaintenance> maintenanceRecords = getEquipmentMaintenanceRecords();
        gui.displayEquipmentMaintenanceRecords(maintenanceRecords);
    }
    public List<EquipmentMaintenance> getEquipmentMaintenanceRecords() throws SQLException {
        return jdbcDatabaseOp.getAllEquipmentMaintenanceRecords();
    }

    /**
     * Calculates the BMI (Body Mass Index) for the specified member based on their height and weight goals.
     *
     * @param member The Member object for whom to calculate the BMI.
     * @return The calculated BMI value.
     */

    public double calculateBMI(Member member) {
        double height = getHeightMember(member)/100;
        double weight = member.getWeightGoal();

        if (height == 0) {
            return 0;
        }
        double bmi = weight / (height * height);

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(bmi));
    }

    /**
     * Retrieves all class schedules from the database and delegates the handling of class schedule records to the GUI.
     * Calls the GUI method to handle the class schedule records.
     *
     * @throws SQLException If an SQL exception occurs while accessing the database.
     */
    public void classScheduleUpdating() throws SQLException {
        List<ClassSchedule> classScheduleList = getClassScheduleList();
        gui.handleClassScheduleRecords(classScheduleList);
    }

    private List<ClassSchedule> getClassScheduleList() throws SQLException {
        return jdbcDatabaseOp.getAllClassSchedules();
    }


    /**
     * Initiates the billing and payment processing by calling the corresponding method in the GUI.
     */
    public void billingAndPaymentProcessing() {
        gui.BillingAndPaymentsGUI();
    }

    /**
     * Updates the details of a class schedule in the database with the specified information.
     * After updating the class schedule, triggers the GUI to handle the updated class schedule records.
     *
     * @param classId         The ID of the class schedule to be updated.
     * @param updatedClassName    The updated class name.
     * @param updatedClassDate    The updated class date.
     * @param updatedStartTime    The updated start time.
     * @param updatedEndTime    The updated end time.
     * @throws SQLException If an SQL exception occurs while accessing the database.
     */
    public void updateClassSchedule(int classId, String updatedClassName, Date updatedClassDate, Time updatedStartTime, Time updatedEndTime) throws SQLException {
        jdbcDatabaseOp.updateClassSchedule(classId,updatedClassName,updatedClassDate,updatedStartTime,updatedEndTime);
        classScheduleUpdating();
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" into a Date object.
     *
     * @param dateStr The date string to parse.
     * @return The parsed Date object.
     * @throws IllegalArgumentException If the date string has an invalid format.
     */
    Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

    /**
     * Parses a time string in the format "HH:mm:ss" into a Time object.
     *
     * @param timeStr The time string to parse.
     * @return The parsed Time object.
     * @throws IllegalArgumentException If the time string has an invalid format.
     */
    Time parseTime(String timeStr) {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            return new Time(timeFormat.parse(timeStr).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please use HH:mm:ss format.");
        }
    }

    /**
     * Retrieves all billing and payment transactions from the database.
     *
     * @return A list of BillingAndPayment objects representing the transactions.
     * @throws SQLException If an SQL exception occurs while accessing the database.
     */
    public List<BillingAndPayment> billingAndPayments() throws SQLException {
        return jdbcDatabaseOp.getAllTransactions();
    }


    public void handleAddTransaction(int memberId, Date transactionDate, double amount, String status) throws SQLException {
        jdbcDatabaseOp.addTransaction(memberId, (java.sql.Date) transactionDate,amount,status);
        gui.BillingAndPaymentsGUI();
    }

    public void displayMemberAchievements(int memberId) {
        try {
            List<Achievement> achievements = jdbcDatabaseOp.getAchievementsByMemberId(memberId);
            gui.displayAchievements(achievements, memberId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching achievements: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void addAchievement(Achievement achievement) {
        jdbcDatabaseOp.addAchievement(achievement);
    }

    /**
     * Opens a window for the admin to add an achievement to a member.
     */
    public void addAchievementToMember() {
        JTextField memberIdField = new JTextField();
        JTextField descriptionField = new JTextField();
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");

        Object[] message = {
                "Member ID:", memberIdField,
                "Description:", descriptionField,
                "Date Achieved:", dateChooser
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Achievement", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int memberId = Integer.parseInt(memberIdField.getText());
                String description = descriptionField.getText();
                Date dateAchieved = dateChooser.getDate();
                Achievement achievement = new Achievement(0, memberId, description, dateAchieved);
                jdbcDatabaseOp.addAchievement(achievement);
                JOptionPane.showMessageDialog(gui.frame, "Achievement added successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(gui.frame, "Error adding achievement: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Displays the leaderboard in a new window.
     */
    public void displayLeaderboard() {
        try {
            List<MemberAchievementCount> leaderboard = jdbcDatabaseOp.getMembersAchievementCount();
            JFrame leaderboardFrame = new JFrame("Leaderboard");
            leaderboardFrame.setLayout(new BorderLayout());
            String[] columnNames = {"Member ID", "Name", "Achievement Count"};
            Object[][] data = new Object[leaderboard.size()][3];
            int i = 0;
            for (MemberAchievementCount mac : leaderboard) {
                data[i][0] = mac.getMemberId();
                data[i][1] = mac.getFullName();
                data[i][2] = mac.getAchievementCount();
                i++;
            }
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            leaderboardFrame.add(scrollPane, BorderLayout.CENTER);
            leaderboardFrame.setSize(400, 300);
            leaderboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            leaderboardFrame.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.frame, "Error retrieving leaderboard: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addFeedback(int memberId, int classId, int trainerId, int rating, String comments, Date feedbackDate) {
        try {
            Feedback feedback = new Feedback(0, memberId, classId, trainerId, rating, comments, feedbackDate);
            jdbcDatabaseOp.addFeedback(feedback);
            JOptionPane.showMessageDialog(gui.frame, "Feedback submitted successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(gui.frame, "Failed to submit feedback: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public List<Feedback> getAllFeedback() throws SQLException {
        return jdbcDatabaseOp.getAllFeedback();
    }



}


