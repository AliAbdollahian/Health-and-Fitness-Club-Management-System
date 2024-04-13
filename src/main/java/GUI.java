import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents the graphical user interface for the Health and Fitness Club Management System.
 * This class extends JFrame and provides methods to interact with the GUI elements.
 *
 *
 */
public class GUI extends JFrame {
    public Controller controller;
    public JLabel labelWhenOpening;
    public JFrame frame;
    public JButton member;
    private JPanel buttonPanel;
    public JButton trainer;
    public JButton admin;
    public JLabel memberProfileLabel;
    private JTextField memberIdField, transactionDateField, amountField, statusField;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Constructs a new GUI instance.
     * Initializes the controller and creates the main frame.
     */
    public GUI() {
        controller = new Controller(new JdbcDatabaseOperations(), this);
        makeFrame();
    }

    /**
     * Creates the main frame of the GUI.
     * Initializes UI components and sets up event listeners.
     */
    private void makeFrame() {
        frame = new JFrame();
        frame.setTitle("Health and Fitness Club Management System");
        frame.setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Create a JPanel to hold buttons with better spacing and padding
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Grid layout with 3 rows and 1 column
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Increase padding

        // Create a label with instructions
        labelWhenOpening = new JLabel("Please specify your role:");
        labelWhenOpening.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and increase size
        labelWhenOpening.setHorizontalAlignment(JLabel.CENTER); // Center-align the text

        // Create buttons with a modern look
        member = new JButton("Member");
        trainer = new JButton("Trainer");
        admin = new JButton("Admin");

        customizeButton(member);
        customizeButton(trainer);
        customizeButton(admin);

        member.addActionListener(e -> controller.memberClicked());
        trainer.addActionListener(e -> controller.trainerClicked());
        admin.addActionListener(e -> controller.adminClicked());

        // Create a welcoming label at the top
        JLabel welcome = new JLabel("Welcome to Health and Fitness Club", JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and increase size
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Increase padding

        // Assemble the components
        buttonPanel.add(member);
        buttonPanel.add(trainer);
        buttonPanel.add(admin);

        frame.add(welcome, BorderLayout.NORTH);
        frame.add(labelWhenOpening, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Customizes the appearance of buttons.
     */
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 150, 220)); // A soft blue background
        button.setForeground(Color.BLACK); // White text
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }


    /**
     * Updates the trainer profile UI with the provided trainer information.
     *
     * @param trainer The Trainer object representing the trainer whose profile is to be displayed.
     */
    public void updateTrainerProfile(Trainer trainer) {
        openTrainerProfile(trainer);
    }

    /**
     * Opens the member profile UI with the provided member information.
     *
     * @param member The Member object representing the member whose profile is to be displayed.
     */
    public void openMemberProfile(Member member) {
        JFrame memberFrame = new JFrame("Member Profile");
        memberFrame.setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Welcome Panel
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome, " + member.getFullName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomePanel.add(welcomeLabel);

        // Member Details Panel
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Dynamic grid layout for flexibility
        memberPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Labels and Buttons
        JLabel memberIdLabel = new JLabel("Member ID: " + member.getMemberId());
        JLabel fullNameLabel = new JLabel("Full Name: " + member.getFullName());
        JLabel dateOfBirthLabel = new JLabel("Date of Birth: " + member.getDateOfBirth());
        JLabel fitnessGoalLabel = new JLabel("Fitness Goal: " + member.getFitnessGoal());
        JLabel weightGoalLabel = new JLabel("Weight Goal: " + member.getWeightGoal());
        JLabel timeGoalLabel = new JLabel("Time Goal: " + member.getTimeGoal());
        JLabel heightLabel = new JLabel("Height: " + controller.getHeightMember(member));
        JLabel bmiLabel = new JLabel("BMI: " + controller.calculateBMI(member));

        JButton updateProfileButton = new JButton("Update Profile");
        JButton manageBookingButton = new JButton("Manage Booking");
        JButton viewAchievementsButton = new JButton("View Achievements");
        JButton viewLeaderboardButton = new JButton("View Leaderboard");
        JButton submitFeedbackButton = new JButton("Submit Feedback");

        // Styling Buttons and Adding Action Listeners
        styleButton(updateProfileButton);
        styleButton(manageBookingButton);
        styleButton(viewAchievementsButton);
        styleButton(viewLeaderboardButton);
        styleButton(submitFeedbackButton);

        updateProfileButton.addActionListener(e -> {
            controller.updateProfile(member);
            memberFrame.dispose();
        });
        manageBookingButton.addActionListener(e -> controller.manageSchedule(member));
        viewAchievementsButton.addActionListener(e -> controller.displayMemberAchievements(member.getMemberId()));
        viewLeaderboardButton.addActionListener(e -> controller.displayLeaderboard());
        submitFeedbackButton.addActionListener(e -> openFeedbackForm(member));

        // Adding components to the member panel
        addMemberInfo(memberPanel, memberIdLabel, fullNameLabel, dateOfBirthLabel, fitnessGoalLabel, weightGoalLabel, timeGoalLabel, heightLabel, bmiLabel);
        memberPanel.add(updateProfileButton);
        memberPanel.add(manageBookingButton);
        memberPanel.add(viewAchievementsButton);
        memberPanel.add(viewLeaderboardButton);
        memberPanel.add(submitFeedbackButton);

        memberFrame.add(welcomePanel, BorderLayout.NORTH);
        memberFrame.add(memberPanel, BorderLayout.CENTER);
        memberFrame.pack();
        memberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memberFrame.setVisible(true);
    }

    /**
     * Adds style to buttons to enhance their appearance.
     */
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(100, 150, 220)); // A soft blue background
        button.setForeground(Color.BLACK); // White text
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    /**
     * Helper method to add member info labels to the panel.
     */
    private void addMemberInfo(JPanel panel, JLabel... labels) {
        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(label);
        }
    }

    /**
     * Updates the graphical user interface (GUI) with the provided member's profile information.
     * This method delegates the task of opening the member's profile to the {@code openMemberProfile} method.
     *
     * @param member The Member object representing the member whose profile information is to be updated and displayed.
     */
    public void updateGUMemberProfile(Member member) {
        openMemberProfile(member);
    }

    /**
     * Opens a graphical user interface (GUI) window displaying the profile information of the specified trainer.
     * The trainer's profile includes their ID, full name, and availability status.
     * This method allows the trainer to set their availability and search for members by name.
     *
     * @param trainer The Trainer object representing the trainer whose profile is to be displayed.
     */
    public void openTrainerProfile(Trainer trainer) {
        JFrame trainerFrame = new JFrame("Trainer Profile");
        trainerFrame.setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Welcome Panel
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome, " + trainer.getFullName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomePanel.add(welcomeLabel);

        // Trainer Details Panel
        JPanel trainerPanel = new JPanel();
        trainerPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Dynamic grid layout for flexibility
        trainerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Labels for trainer information
        JLabel trainerIdLabel = new JLabel("Trainer ID: " + trainer.getTrainerId());
        JLabel fullNameLabel = new JLabel("Full Name: " + trainer.getFullName());
        JLabel availabilityLabel = new JLabel("Availability: " + trainer.getAvailability());

        // Buttons with functionality
        JButton setAvailabilityButton = new JButton("Set Availability");
        JButton searchMembersButton = new JButton("Search Members by Name");

        // Styling buttons and adding action listeners
        styleButton(setAvailabilityButton);
        styleButton(searchMembersButton);

        setAvailabilityButton.addActionListener(e -> {
            controller.setAvailability(trainer);
            trainerFrame.dispose();
        });
        searchMembersButton.addActionListener(e -> controller.searchMembersByName());

        // Adding components to the trainer panel
        trainerPanel.add(trainerIdLabel);
        trainerPanel.add(fullNameLabel);
        trainerPanel.add(availabilityLabel);
        trainerPanel.add(setAvailabilityButton);
        trainerPanel.add(searchMembersButton);

        // Assembling the frame
        trainerFrame.add(welcomePanel, BorderLayout.NORTH);
        trainerFrame.add(trainerPanel, BorderLayout.CENTER);

        trainerFrame.pack();
        trainerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        trainerFrame.setVisible(true);
    }



    /**
     * Prompts the user to enter their date of birth using a date chooser dialog.
     *
     * @return The selected date of birth entered by the user.
     */
    public Date promptForDateOfBirth() {
        JDateChooser dateChooser = new JDateChooser();
        int option = JOptionPane.showConfirmDialog(null, dateChooser, "Enter your date of birth", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return dateChooser.getDate();
        }
        return null;
    }

    /**
     * Opens a graphical user interface (GUI) window displaying the profile information of the specified administrative staff (admin).
     * The admin's profile includes their ID, full name, and email address.
     * Additionally, buttons are provided to perform various administrative tasks such as managing room bookings, monitoring equipment maintenance, updating class schedules, and processing billing and payments.
     *
     * @param admin The AdministrativeStaff object representing the administrative staff whose profile is to be displayed.
     */
    public void openAdminProfile(AdministrativeStaff admin) {
        JFrame adminFrame = new JFrame("Admin Profile");
        adminFrame.setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Welcome Panel
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome, " + admin.getFullName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomePanel.add(welcomeLabel);

        // Admin Details Panel
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Dynamic grid layout for flexibility
        adminPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Labels and buttons for admin information and tasks
        JLabel adminIdLabel = new JLabel("Admin ID: " + admin.getAdministrativeId());
        JLabel fullNameLabel = new JLabel("Full Name: " + admin.getFullName());
        JLabel emailLabel = new JLabel("Email: " + admin.getEmail());

        JButton roomBookingButton = new JButton("Room Booking Management");
        JButton equipmentMaintenanceButton = new JButton("Equipment Maintenance Monitoring");
        JButton classScheduleButton = new JButton("Class Schedule Updating");
        JButton billingButton = new JButton("Billing and Payment Processing");
        JButton addAchievementButton = new JButton("Add Achievement for Member");
        JButton viewFeedbackButton = new JButton("View Feedback");

        // Styling buttons and adding action listeners
        styleButton(roomBookingButton, equipmentMaintenanceButton, classScheduleButton, billingButton, addAchievementButton, viewFeedbackButton);

        roomBookingButton.addActionListener(e -> controller.roomBookingManagement());
        equipmentMaintenanceButton.addActionListener(e -> {
            try {
                controller.equipmentMaintenanceMonitoring();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        classScheduleButton.addActionListener(e -> {
            try {
                controller.classScheduleUpdating();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        billingButton.addActionListener(e -> controller.billingAndPaymentProcessing());
        addAchievementButton.addActionListener(e -> openAddAchievementForm());
        viewFeedbackButton.addActionListener(e -> displayFeedbackRecords());

        // Adding components to the admin panel
        adminPanel.add(adminIdLabel);
        adminPanel.add(fullNameLabel);
        adminPanel.add(emailLabel);
        adminPanel.add(new JLabel()); // Placeholder for alignment
        adminPanel.add(roomBookingButton);
        adminPanel.add(equipmentMaintenanceButton);
        adminPanel.add(classScheduleButton);
        adminPanel.add(billingButton);
        adminPanel.add(addAchievementButton);
        adminPanel.add(viewFeedbackButton);

        // Assembling the frame
        adminFrame.add(welcomePanel, BorderLayout.NORTH);
        adminFrame.add(adminPanel, BorderLayout.CENTER);

        adminFrame.pack();
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setVisible(true);
    }

    /**
     * Adds style to a series of buttons to enhance their appearance.
     */
    private void styleButton(JButton... buttons) {
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBackground(new Color(100, 150, 220)); // A soft blue background
            button.setForeground(Color.BLACK); // White text
            button.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }


    /**
     * Displays a graphical user interface (GUI) window presenting the equipment maintenance records.
     * The maintenance records include the ID, date, and type of each maintenance activity.
     *
     * @param maintenanceRecords A list of EquipmentMaintenance objects representing the maintenance records to be displayed.
     */
    public void displayEquipmentMaintenanceRecords(List<EquipmentMaintenance> maintenanceRecords) {
        JFrame maintenanceFrame = new JFrame("Equipment Maintenance Records");
        maintenanceFrame.setLayout(new BorderLayout(10, 10)); // Added spacing for aesthetics

        // Table setup
        String[] columns = {"Maintenance ID", "Maintenance Date", "Maintenance Type"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (EquipmentMaintenance maintenance : maintenanceRecords) {
            model.addRow(new Object[]{
                    maintenance.getMaintenanceId(),
                    maintenance.getMaintenanceDate(),
                    maintenance.getMaintenanceType()
            });
        }
        JTable maintenanceTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(maintenanceTable);
        maintenanceTable.setFillsViewportHeight(true);
        styleTable(maintenanceTable);

        // Back button
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> maintenanceFrame.dispose());

        maintenanceFrame.add(scrollPane, BorderLayout.CENTER);
        maintenanceFrame.add(backButton, BorderLayout.SOUTH);

        maintenanceFrame.pack();
        maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        maintenanceFrame.setVisible(true);
    }

    /**
     * Applies styling to the JTable to enhance its appearance.
     */
    private void styleTable(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
    }




    /**
     * Displays a graphical user interface (GUI) window presenting the room bookings.
     * Each room booking includes the booking ID, room ID, booking date, start time, end time, and an option to remove the booking.
     *
     * @param roomBookings A list of RoomBooking objects representing the room bookings to be displayed.
     */
    public void displayRoomBookings(List<RoomBooking> roomBookings) {
        JFrame bookingsFrame = new JFrame("Room Bookings");
        bookingsFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Booking ID", "Room ID", "Booking Date", "Start Time", "End Time", "Debug"};
        Object[][] rowData = new Object[roomBookings.size()][columnNames.length];

        for (int i = 0; i < roomBookings.size(); i++) {
            RoomBooking booking = roomBookings.get(i);
            rowData[i][0] = booking.getBookingId();
            rowData[i][1] = booking.getRoomId();
            rowData[i][2] = booking.getBookingDate();
            rowData[i][3] = booking.getStartTime();
            rowData[i][4] = booking.getEndTime();
            JButton removeButton = new JButton("Remove");
            styleButton(removeButton); // Apply uniform button styling
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.removeRoomBooking(booking.getBookingId());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            rowData[i][5] = removeButton;
        }

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        styleTable(table); // Apply uniform table styling
        JScrollPane scrollPane = new JScrollPane(table);
        bookingsFrame.add(scrollPane, BorderLayout.CENTER);

        bookingsFrame.setSize(800, 400);
        bookingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookingsFrame.setVisible(true);
    }


    /**
     * Handles the display of class schedule records in a graphical user interface (GUI) window.
     * The class schedule records include the class ID, class name, class date, start time, and end time.
     * Additionally, an option is provided to update the selected class schedule.
     *
     * @param classSchedules A list of ClassSchedule objects representing the class schedule records to be displayed.
     */
    public void handleClassScheduleRecords(List<ClassSchedule> classSchedules) {
        JFrame classSchedulesFrame = new JFrame("Class Schedules");
        classSchedulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        classSchedulesFrame.setSize(800, 500);

        // Create a table model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Class ID");
        tableModel.addColumn("Class Name");
        tableModel.addColumn("Class Date");
        tableModel.addColumn("Start Time");
        tableModel.addColumn("End Time");

        // Add rows to the table model
        for (ClassSchedule schedule : classSchedules) {
            tableModel.addRow(new Object[]{
                    schedule.getClassId(),
                    schedule.getClassName(),
                    schedule.getClassDate(),
                    schedule.getStartTime(),
                    schedule.getEndTime()
            });
        }

        JTable table = new JTable(tableModel);
        styleTable(table); // Apply uniform table styling
        JScrollPane scrollPane = new JScrollPane(table);
        classSchedulesFrame.add(scrollPane, BorderLayout.CENTER);

        // Create and style the update button
        JButton updateButton = new JButton("Update Selected Class Schedule");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(100, 150, 220)); // Soft blue background
        updateButton.setForeground(Color.BLACK); // White text
        updateButton.setBorder(BorderFactory.createRaisedBevelBorder());
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Retrieve selected class ID
                    int classId = (int) table.getValueAt(selectedRow, 0);
                    // Create and style the update frame
                    JFrame updateFrame = new JFrame("Update Class Schedule");
                    updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    updateFrame.setSize(400, 300);
                    JPanel panel = new JPanel(new GridLayout(6, 2));

                    // Create and style labels and fields
                    JLabel classNameLabel = new JLabel("Class Name:");
                    classNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JTextField classNameField = new JTextField();
                    classNameField.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel classDateLabel = new JLabel("Class Date (YYYY-MM-DD):");
                    classDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JTextField classDateField = new JTextField();
                    classDateField.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel startTimeLabel = new JLabel("Start Time (HH:MM:SS):");
                    startTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JTextField startTimeField = new JTextField();
                    startTimeField.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel endTimeLabel = new JLabel("End Time (HH:MM:SS):");
                    endTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JTextField endTimeField = new JTextField();
                    endTimeField.setFont(new Font("Arial", Font.PLAIN, 14));

                    // Create and style the update button
                    JButton updateDetailsButton = new JButton("Update");
                    updateDetailsButton.setFont(new Font("Arial", Font.BOLD, 14));
                    updateDetailsButton.setBackground(new Color(100, 150, 220)); // Soft blue background
                    updateDetailsButton.setForeground(Color.BLACK); // White text
                    updateDetailsButton.setBorder(BorderFactory.createRaisedBevelBorder());

                    // Add action listener to the update button
                    updateDetailsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String updatedClassName = classNameField.getText();
                            String updatedClassDateStr = classDateField.getText();
                            String updatedStartTimeStr = startTimeField.getText();
                            String updatedEndTimeStr = endTimeField.getText();
                            // Parse updated date and time
                            Date updatedClassDate = controller.parseDate(updatedClassDateStr);
                            Time updatedStartTime = controller.parseTime(updatedStartTimeStr);
                            Time updatedEndTime = controller.parseTime(updatedEndTimeStr);
                            try {
                                // Update class schedule
                                controller.updateClassSchedule(classId, updatedClassName, updatedClassDate, updatedStartTime, updatedEndTime);
                                // Close update frame and class schedules frame
                                updateFrame.dispose();
                                classSchedulesFrame.dispose();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });

                    // Add components to the panel
                    panel.add(classNameLabel);
                    panel.add(classNameField);
                    panel.add(classDateLabel);
                    panel.add(classDateField);
                    panel.add(startTimeLabel);
                    panel.add(startTimeField);
                    panel.add(endTimeLabel);
                    panel.add(endTimeField);
                    panel.add(updateDetailsButton); // Add update button

                    // Add panel to the update frame
                    updateFrame.add(panel);
                    updateFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(classSchedulesFrame, "Please select a class schedule to update.");
                }
            }
        });

        // Create and style the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        classSchedulesFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame visibility
        classSchedulesFrame.setVisible(true);
    }


    /**
     * Displays a graphical user interface (GUI) window for handling billing and payments.
     * The window includes input fields for member ID, transaction date, amount, and status, along with a button to add a transaction.
     * Additionally, a table displays existing billing and payment transactions.
     */
    public void billingAndPaymentsGUI() {
        JFrame billingFrame = new JFrame("Billing and Payments");
        billingFrame.setSize(800, 500);
        billingFrame.setLocationRelativeTo(null);

        // Create input panel with GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        // Add input labels and fields
        inputPanel.add(new JLabel("Member ID:"));
        memberIdField = new JTextField();
        inputPanel.add(memberIdField);
        inputPanel.add(new JLabel("Transaction Date (YYYY-MM-DD):"));
        transactionDateField = new JTextField();
        inputPanel.add(transactionDateField);
        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        inputPanel.add(statusField);

        // Create and style the "Add Transaction" button
        JButton addButton = new JButton("Add Transaction");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(100, 150, 220)); // Soft blue background
        addButton.setForeground(Color.BLACK); // White text
        addButton.setBorder(BorderFactory.createRaisedBevelBorder());
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addTransaction();
                    billingFrame.dispose();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Create button panel and add the "Add Transaction" button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        buttonPanel.add(addButton);

        // Create table model and add columns
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Member ID");
        tableModel.addColumn("Transaction Date");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Status");

        // Populate table with existing billing and payment transactions
        List<BillingAndPayment> transactions;
        try {
            transactions = controller.billingAndPayments();
            for (BillingAndPayment transaction : transactions) {
                tableModel.addRow(new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getAmount(),
                        transaction.getStatus()
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQLException appropriately
        }

        // Create table with the populated table model and add it to a scroll pane
        table = new JTable(tableModel);
        styleTable(table); // Apply uniform table styling
        JScrollPane scrollPane = new JScrollPane(table);

        // Set layout and add components to the billing frame
        billingFrame.getContentPane().setLayout(new BorderLayout());
        billingFrame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        billingFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        billingFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set frame visibility
        billingFrame.setVisible(true);
    }


    /**
     * Adds a new transaction using the input data provided in the GUI window for billing and payments.
     * The method retrieves data from input fields, parses them, and sends them to the controller for handling the addition of the transaction.
     * In case of parsing errors or database exceptions, appropriate handling is performed.
     *
     * @throws SQLException If a database access error occurs.
     */
    private void addTransaction() throws SQLException {
        try {
            int memberId = Integer.parseInt(memberIdField.getText());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date transactionDate = dateFormat.parse(transactionDateField.getText());

            double amount = Double.parseDouble(amountField.getText());
            String status = statusField.getText();

            // Check if any of the fields are empty
            if (memberIdField.getText().isEmpty() || transactionDateField.getText().isEmpty() || amountField.getText().isEmpty() || statusField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the amount is non-negative
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Amount must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call the controller method to add the transaction
            controller.handleAddTransaction(memberId, transactionDate, amount, status);
        } catch (NumberFormatException e) {
            // Handle parsing errors for memberId and amount
            JOptionPane.showMessageDialog(null, "Member ID and Amount must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            // Handle parsing error for transaction date
            JOptionPane.showMessageDialog(null, "Invalid date format! Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays achievements for a specific member in a graphical user interface (GUI) window.
     *
     * @param achievements The list of Achievement objects representing the achievements of the member.
     * @param memberId     The ID of the member for whom achievements are being displayed.
     */
    public void displayAchievements(List<Achievement> achievements, int memberId) {
        JFrame frame = new JFrame("Achievements for Member ID: " + memberId);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create table model with columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Achievement ID");
        model.addColumn("Description");
        model.addColumn("Date Achieved");

        // Populate table with achievement data
        for (Achievement achievement : achievements) {
            model.addRow(new Object[]{achievement.getAchievementId(), achievement.getDescription(), achievement.getDateAchieved()});
        }

        // Create table with the populated table model and add it to a scroll pane
        JTable table = new JTable(model);
        styleTable(table); // Apply uniform table styling
        JScrollPane scrollPane = new JScrollPane(table);

        // Customize table appearance
        table.setRowHeight(30); // Set row height
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size and style

        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Achievement ID
        table.getColumnModel().getColumn(1).setPreferredWidth(300); // Description
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Date Achieved

        // Set alignment of text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Achievement ID
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Date Achieved

        // Add scroll pane to the frame's content pane
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame only
        frame.setVisible(true); // Make the frame visible
    }

    /**
     * Opens a graphical user interface (GUI) window for adding achievements.
     * The window includes input fields for member ID, description, date achieved, and a button to submit the achievement.
     * When the user submits the achievement, the details are sent to the controller for processing.
     * If any errors occur during the process, appropriate error messages are displayed to the user.
     */
    private void openAddAchievementForm() {
        JFrame frame = new JFrame("Add Achievement");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed

        // Create panel for form elements
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        // Member ID
        JLabel memberIdLabel = new JLabel("Member ID:");
        panel.add(memberIdLabel, gbc);
        gbc.gridx++;
        JTextField memberIdField = new JTextField(15); // Set preferred text field width
        panel.add(memberIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        // Description
        JLabel descriptionLabel = new JLabel("Description:");
        panel.add(descriptionLabel, gbc);
        gbc.gridx++;
        JTextField descriptionField = new JTextField(15); // Set preferred text field width
        panel.add(descriptionField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        // Date Achieved
        JLabel dateAchievedLabel = new JLabel("Date Achieved:");
        panel.add(dateAchievedLabel, gbc);
        gbc.gridx++;
        JDateChooser dateAchievedChooser = new JDateChooser();
        dateAchievedChooser.setDate(new Date()); // Set current date by default
        dateAchievedChooser.setDateFormatString("yyyy-MM-dd");
        panel.add(dateAchievedChooser, gbc);

        // Create submit button
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        panel.add(submitButton, gbc);

        // Add action listener to submit button
        submitButton.addActionListener(e -> {
            try {
                int memberId = Integer.parseInt(memberIdField.getText().trim());
                String description = descriptionField.getText().trim();
                Date dateAchieved = dateAchievedChooser.getDate();
                if (dateAchieved == null) {
                    JOptionPane.showMessageDialog(frame, "Please select a valid date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Achievement achievement = new Achievement(0, memberId, description, dateAchieved); // Assuming Achievement constructor
                controller.addAchievement(achievement);
                JOptionPane.showMessageDialog(frame, "Achievement added successfully!");
                frame.dispose(); // Close the frame
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid member ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Failed to add achievement: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true); // Make the frame visible
    }



    /**
     * Opens a graphical user interface (GUI) window for submitting feedback.
     * The window includes input fields for class ID, trainer ID, rating, comments, and a button to submit the feedback.
     * Upon submission, the feedback details are sent to the controller for processing.
     *
     * @param member The Member object representing the member providing the feedback.
     */
    private void openFeedbackForm(Member member) {
        JFrame feedbackFrame = new JFrame("Submit Feedback");
        feedbackFrame.setSize(400, 300);
        feedbackFrame.setLocationRelativeTo(null); // Center the frame on the screen
        feedbackFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed

        JPanel feedbackPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        JLabel classIdLabel = new JLabel("Class ID:");
        feedbackPanel.add(classIdLabel, gbc);
        gbc.gridx++;
        JTextField classIdField = new JTextField(10);
        feedbackPanel.add(classIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel trainerIdLabel = new JLabel("Trainer ID:");
        feedbackPanel.add(trainerIdLabel, gbc);
        gbc.gridx++;
        JTextField trainerIdField = new JTextField(10);
        feedbackPanel.add(trainerIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel ratingLabel = new JLabel("Rating (1-5):");
        feedbackPanel.add(ratingLabel, gbc);
        gbc.gridx++;
        JTextField ratingField = new JTextField(10);
        feedbackPanel.add(ratingField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JLabel commentsLabel = new JLabel("Comments:");
        feedbackPanel.add(commentsLabel, gbc);
        gbc.gridx++;
        JTextArea commentsArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(commentsArea);
        feedbackPanel.add(scrollPane, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

        JButton submitButton = new JButton("Submit");
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        feedbackPanel.add(submitButton, gbc);

        // Add action listener to submit button
        submitButton.addActionListener(e -> {
            try {
                int classId = Integer.parseInt(classIdField.getText().trim());
                int trainerId = Integer.parseInt(trainerIdField.getText().trim());
                int rating = Integer.parseInt(ratingField.getText().trim());
                String comments = commentsArea.getText();
                controller.addFeedback(member.getMemberId(), classId, trainerId, rating, comments, new Date());
                JOptionPane.showMessageDialog(feedbackFrame, "Feedback submitted successfully!");
                feedbackFrame.dispose(); // Close the frame
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(feedbackFrame, "Please enter valid input for Class ID, Trainer ID, and Rating.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(feedbackFrame, "Failed to submit feedback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add panel to frame
        feedbackFrame.add(feedbackPanel);
        feedbackFrame.setVisible(true); // Make the frame visible
    }

    /**
     * Displays a graphical user interface (GUI) window presenting feedback records.
     * The feedback records include various details such as feedback ID, member ID, class ID, trainer ID, rating, comments, and date.
     * These records are fetched from the controller and displayed in a JTable within a scrollable pane.
     * If there's an error accessing the database, an error message dialog is shown to the user.
     */
    public void displayFeedbackRecords() {
        JFrame feedbackFrame = new JFrame("Feedback Records");
        feedbackFrame.setSize(800, 400);
        feedbackFrame.setLocationRelativeTo(null); // Center the frame on the screen
        feedbackFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed

        try {
            // Fetch feedback records from the controller
            List<Feedback> feedbackList = controller.getAllFeedback();

            // Create column names for the table
            String[] columnNames = {"Feedback ID", "Member ID", "Class ID", "Trainer ID", "Rating", "Comments", "Date"};

            // Create a table model with the column names
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Add rows to the table model
            for (Feedback feedback : feedbackList) {
                model.addRow(new Object[]{
                        feedback.getFeedbackId(),
                        feedback.getMemberId(),
                        feedback.getClassId(),
                        feedback.getTrainerId(),
                        feedback.getRating(),
                        feedback.getComments(),
                        feedback.getFeedbackDate().toString()
                });
            }

            // Create a JTable with the model
            JTable table = new JTable(model);

            // Style the table
            styleTable(table);

            // Add the table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the frame
            feedbackFrame.add(scrollPane, BorderLayout.CENTER);
        } catch (SQLException e) {
            // Display an error message if there's an exception
            JOptionPane.showMessageDialog(feedbackFrame, "Error fetching feedback: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Make the frame visible
        feedbackFrame.setVisible(true);
    }




    public static void main(String[] args) {
        new GUI();
    }
}
