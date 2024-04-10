import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
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
        frame.setLayout(new BorderLayout());

        // Create a JPanel to hold buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5)); // Increase rows to accommodate extra space
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Create a label with instructions
        labelWhenOpening = new JLabel("Please specify your role:        ");
        labelWhenOpening.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font
        labelWhenOpening.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); // Add bottom padding

        // Create buttons
        member = new JButton("Member");
        trainer = new JButton("Trainer");
        admin = new JButton("Admin");


        customizeButton(member);
        customizeButton(trainer);
        customizeButton(admin);


        member.addActionListener(e -> controller.memberClicked());
        trainer.addActionListener(e -> controller.trainerClicked());
        admin.addActionListener(e -> controller.adminClicked());

        JLabel welcome = new JLabel("Welcome to Health and Fitness Club");
        welcome.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font
        welcome.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); // Add bottom padding

        buttonPanel.add(labelWhenOpening);
        buttonPanel.add(member);
        buttonPanel.add(Box.createVerticalStrut(10)); // Add vertical space
        buttonPanel.add(trainer);
        buttonPanel.add(Box.createVerticalStrut(10)); // Add vertical space
        buttonPanel.add(admin);


        memberProfileLabel = new JLabel("Member Profile");

        frame.add(welcome,BorderLayout.NORTH);
        frame.add(labelWhenOpening, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private void customizeButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
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
        JFrame memberFrame = new JFrame();
        memberFrame.setTitle("Member Profile");
        memberFrame.setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + member.getFullName() + "!");
        welcomePanel.add(welcomeLabel);

        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new GridLayout(5, 1));

        JLabel memberIdLabel = new JLabel("Member ID: " + member.getMemberId());
        JLabel fullNameLabel = new JLabel("Full Name: " + member.getFullName());
        JLabel dateOfBirthLabel = new JLabel("Date of Birth: " + member.getDateOfBirth());
        JLabel fitnessGoalLabel = new JLabel("Fitness Goal: " + member.getFitnessGoal());
        JLabel weightGoalLabel = new JLabel("Weight Goal: " + member.getWeightGoal());
        JLabel timeGoalLabel = new JLabel("Time Goal: " + member.getTimeGoal());
        JLabel heightLabel = new JLabel("Height: " + controller.getHeightMember(member));
        JLabel bmiLabel = new JLabel("BMI: " + controller.calculateBMI(member));

        JButton updateProfileButton = new JButton("Update Profile");
        updateProfileButton.addActionListener(e -> {controller.updateProfile(member);
            memberFrame.dispose();
        });
        JButton manageBookingButton = new JButton("Manage Booking");
        manageBookingButton.addActionListener(e -> controller.manageSchedule(member));

        memberFrame.add(welcomePanel, BorderLayout.NORTH);
        memberPanel.add(memberIdLabel);
        memberPanel.add(fullNameLabel);
        memberPanel.add(dateOfBirthLabel);
        memberPanel.add(fitnessGoalLabel);
        memberPanel.add(weightGoalLabel);
        memberPanel.add(timeGoalLabel);
        memberPanel.add(heightLabel);
        memberPanel.add(bmiLabel);
        memberPanel.add(updateProfileButton);
        memberPanel.add(manageBookingButton);
        memberFrame.add(memberPanel, BorderLayout.CENTER);
        memberFrame.pack();
        memberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memberFrame.setVisible(true);
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
        JFrame trainerFrame = new JFrame();
        trainerFrame.setTitle("Trainer Profile");
        trainerFrame.setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + trainer.getFullName() + "!");
        welcomePanel.add(welcomeLabel);

        JPanel trainerPanel = new JPanel();
        trainerPanel.setLayout(new GridLayout(3, 1));

        JLabel trainerIdLabel = new JLabel("Trainer ID: " + trainer.getTrainerId());
        JLabel fullNameLabel = new JLabel("Full Name: " + trainer.getFullName());
        JLabel availabilityLabel = new JLabel("Availability: " + trainer.getAvailability());
        JLabel empty = new JLabel();

        JButton setAvailabilityButton = new JButton("Set Availability");
        setAvailabilityButton.addActionListener(e -> {
            controller.setAvailability(trainer);
            trainerFrame.dispose();
        });

        JButton searchMembersButton = new JButton("Search Members by Name");
        searchMembersButton.addActionListener(e -> controller.searchMembersByName());

        welcomePanel.add(welcomeLabel);
        trainerFrame.add(welcomePanel, BorderLayout.NORTH);


        trainerPanel.add(trainerIdLabel);
        trainerPanel.add(fullNameLabel);
        trainerPanel.add(availabilityLabel);
        trainerPanel.add(empty);
        trainerPanel.add(setAvailabilityButton);
        trainerPanel.add(searchMembersButton);

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
        JFrame adminFrame = new JFrame();
        adminFrame.setTitle("Admin Profile");
        adminFrame.setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome, " + admin.getFullName() + "!");
        welcomePanel.add(welcomeLabel);

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridLayout(4, 1));

        JLabel adminIdLabel = new JLabel("Admin ID: " + admin.getAdministrativeId());
        JLabel fullNameLabel = new JLabel("Full Name: " + admin.getFullName());
        JLabel emailLabel = new JLabel("Email: " + admin.getEmail());
        JLabel empty = new JLabel();
        JButton roomBookingButton = new JButton("Room Booking Management");
        roomBookingButton.addActionListener(e -> controller.roomBookingManagement());

        JButton equipmentMaintenanceButton = new JButton("Equipment Maintenance Monitoring");
        equipmentMaintenanceButton.addActionListener(e -> {
            try {
                controller.equipmentMaintenanceMonitoring();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton classScheduleButton = new JButton("Class Schedule Updating");
        classScheduleButton.addActionListener(e -> {
            try {
                controller.classScheduleUpdating();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton billingButton = new JButton("Billing and Payment Processing");
        billingButton.addActionListener(e -> controller.billingAndPaymentProcessing());

        welcomePanel.add(welcomeLabel);
        adminFrame.add(welcomePanel, BorderLayout.NORTH);

        adminPanel.add(adminIdLabel);
        adminPanel.add(fullNameLabel);
        adminPanel.add(emailLabel);
        adminPanel.add(empty);
        adminPanel.add(roomBookingButton);
        adminPanel.add(equipmentMaintenanceButton);
        adminPanel.add(classScheduleButton);
        adminPanel.add(billingButton);

        adminFrame.add(adminPanel, BorderLayout.CENTER);

        adminFrame.pack();
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setVisible(true);
    }

    /**
     * Displays a graphical user interface (GUI) window presenting the equipment maintenance records.
     * The maintenance records include the ID, date, and type of each maintenance activity.
     *
     * @param maintenanceRecords A list of EquipmentMaintenance objects representing the maintenance records to be displayed.
     */
    public void displayEquipmentMaintenanceRecords(List<EquipmentMaintenance> maintenanceRecords) {
        JFrame maintenanceFrame = new JFrame();
        maintenanceFrame.setTitle("Equipment Maintenance Records");
        maintenanceFrame.setLayout(new BorderLayout());
        JPanel maintenancePanel = new JPanel(new GridLayout(maintenanceRecords.size() + 1, 1));
        maintenancePanel.add(new JLabel("<html><b>Maintenance ID</b></html>\t" +
                "<html><b>Maintenance Date</b></html>\t" +
                "<html><b>Maintenance Type</b></html>"));

        for (EquipmentMaintenance maintenance : maintenanceRecords) {
            maintenancePanel.add(new JLabel(maintenance.getMaintenanceId() + "\t" +
                    maintenance.getMaintenanceDate() + "\t" +
                    maintenance.getMaintenanceType()));
        }
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> maintenanceFrame.dispose());
        maintenanceFrame.add(maintenancePanel, BorderLayout.CENTER);
        maintenanceFrame.add(backButton, BorderLayout.SOUTH);

        maintenanceFrame.pack();
        maintenanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        maintenanceFrame.setVisible(true);
    }

    /**
     * Displays a graphical user interface (GUI) window presenting the room bookings.
     * Each room booking includes the booking ID, room ID, booking date, start time, end time, and an option to remove the booking.
     *
     * @param roomBookings A list of RoomBooking objects representing the room bookings to be displayed.
     */
    public void displayRoomBookings(List<RoomBooking> roomBookings) {
        JFrame bookingsFrame = new JFrame();
        bookingsFrame.setTitle("Room Bookings");
        bookingsFrame.setLayout(new BorderLayout());

        JPanel bookingsPanel = new JPanel(new GridLayout(roomBookings.size() + 1, 1));

        bookingsPanel.add(new JLabel("<html><b>Booking ID</b></html>\t" +
                "<html><b>Room ID</b></html>\t" +
                "<html><b>Booking Date</b></html>\t" +
                "<html><b>Start Time</b></html>\t" +
                "<html><b>End Time</b></html>\t" +
                "<html><b>Actions</b></html>"));

        for (RoomBooking booking : roomBookings) {
            JPanel bookingPanel = new JPanel(new GridLayout(1, 6)); // 6 columns
            bookingPanel.add(new JLabel(booking.getBookingId() + ""));
            bookingPanel.add(new JLabel(booking.getRoomId() + ""));
            bookingPanel.add(new JLabel(booking.getBookingDate() + ""));
            bookingPanel.add(new JLabel(booking.getStartTime() + ""));
            bookingPanel.add(new JLabel(booking.getEndTime() + ""));

            JButton removeButton = new JButton("Remove");
            int bookingId = booking.getBookingId();
            removeButton.addActionListener(e -> {
                try {
                    controller.removeRoomBooking(bookingId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            bookingPanel.add(removeButton);

            bookingsPanel.add(bookingPanel);
        }
        bookingsFrame.add(new JScrollPane(bookingsPanel), BorderLayout.CENTER);
        bookingsFrame.pack();
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
        JFrame class_schedules = new JFrame("Class Schedules");
        class_schedules.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        class_schedules.setSize(600, 400);

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
        JScrollPane scrollPane = new JScrollPane(table);
        class_schedules.add(scrollPane, BorderLayout.CENTER);
        JButton updateButton = new JButton("Update Selected Class Schedule");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int classId = (int) table.getValueAt(selectedRow, 0);
                    JFrame updateFrame = new JFrame("Update Class Schedule");
                    updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    updateFrame.setSize(400, 300);
                    JTextField classNameField = new JTextField();
                    JTextField classDateField = new JTextField();
                    JTextField startTimeField = new JTextField();
                    JTextField endTimeField = new JTextField();
                    JButton updateDetailsButton = new JButton("Update");
                    updateFrame.setVisible(true);
                    updateDetailsButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String updatedClassName = classNameField.getText();
                            String updatedClassDateStr = classDateField.getText();
                            String updatedStartTimeStr = startTimeField.getText();
                            String updatedEndTimeStr = endTimeField.getText();
                            Date updatedClassDate = controller.parseDate(updatedClassDateStr);
                            Time updatedStartTime = controller.parseTime(updatedStartTimeStr);
                            Time updatedEndTime = controller.parseTime(updatedEndTimeStr);
                            try {
                                controller.updateClassSchedule(classId, updatedClassName, updatedClassDate, updatedStartTime, updatedEndTime);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            updateFrame.dispose();
                            class_schedules.dispose();
                        }
                    });
                    // Create a panel to hold the components
                    JPanel panel = new JPanel(new GridLayout(5, 2));
                    panel.add(new JLabel("Class Name:"));
                    panel.add(classNameField);
                    panel.add(new JLabel("Class Date (YYYY-MM-DD):"));
                    panel.add(classDateField);
                    panel.add(new JLabel("Start Time (HH:MM:SS):"));
                    panel.add(startTimeField);
                    panel.add(new JLabel("End Time (HH:MM:SS):"));
                    panel.add(endTimeField);
                    panel.add(updateDetailsButton);
                    updateFrame.add(panel);
                    updateFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(class_schedules, "Please select a class schedule to update.");
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        class_schedules.add(buttonPanel, BorderLayout.SOUTH);
        class_schedules.setVisible(true);
    }

    /**
     * Displays a graphical user interface (GUI) window for handling billing and payments.
     * The window includes input fields for member ID, transaction date, amount, and status, along with a button to add a transaction.
     * Additionally, a table displays existing billing and payment transactions.
     */
    public void BillingAndPaymentsGUI() {
        JFrame billingFrame = new JFrame("Billing and Payments");
        billingFrame.setSize(600, 400);
        billingFrame.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
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

        JButton addButton = new JButton("Add Transaction");
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Member ID");
        tableModel.addColumn("Transaction Date");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Status");

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
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        billingFrame.getContentPane().setLayout(new BorderLayout());
        billingFrame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        billingFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        billingFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

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
        int memberId = Integer.parseInt(memberIdField.getText());
        Date transactionDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            transactionDate = dateFormat.parse(transactionDateField.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        double amount = Double.parseDouble(amountField.getText());
        String status = statusField.getText();
        controller.handleAddTransaction(memberId, transactionDate, amount, status);
    }
    public static void main(String[] args) {
        new GUI();
    }
}
