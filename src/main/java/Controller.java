import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.Date;

public class Controller {
    public JdbcDatabaseOperations jdbcDatabaseOp;
    public GUI gui;

    public Controller(JdbcDatabaseOperations jdbcDatabaseOp, GUI gui) {
        this.jdbcDatabaseOp = jdbcDatabaseOp;
        this.gui = gui;


    }

    public void adminClicked() {
    }

    public void trainerClicked() {
        int idOfTrainer = Integer.parseInt(JOptionPane.showInputDialog("Enter your id "));
        Trainer trainer = jdbcDatabaseOp.getTrainerById(idOfTrainer);

        if (trainer != null) {
            JOptionPane.showMessageDialog(null, "Welcome, " + trainer.getFullName() + "!");
            String[] options = {"Set your availability", "Search members by name"};
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "Trainer Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    setAvailability(trainer);
                    break;
                case 1:
                    searchMembersByName();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Trainer not found!");
        }
    }

    private void setAvailability(Trainer trainer) {
        System.out.println("Current availability: " + trainer.getAvailability());

        JDateChooser dateChooser = new JDateChooser();
        int option = JOptionPane.showConfirmDialog(null, dateChooser, "Select availability date", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Date availability = dateChooser.getDate();
            trainer.setAvailability(availability);
            jdbcDatabaseOp.updateTrainer(trainer);
            System.out.println("Availability updated successfully.");
        } else {
            System.out.println("Operation canceled.");
        }
    }

    private void searchMembersByName() {
        String memberName = JOptionPane.showInputDialog("Enter the name of the member: ");
        Member member = jdbcDatabaseOp.getMemberByName(memberName);

        if (member != null) {
            JOptionPane.showMessageDialog(null, "Member found:\n" + member);
        } else {
            JOptionPane.showMessageDialog(null, "Member not found!");
        }
    }

    public void memberClicked() {
        String[] options = {"Register", "Member Login"};
        int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Member Portal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            registerMember();
        } else if (choice == 1) {
            memberLogin();
        }
    }

    private void registerMember() {
        String name = JOptionPane.showInputDialog("Enter your full name:");
        JDateChooser dobChooser = new JDateChooser();
        JOptionPane.showMessageDialog(null, dobChooser, "Enter your date of birth:", JOptionPane.QUESTION_MESSAGE);
        Date dob = new Date(dobChooser.getDate().getTime());

        String fitnessGoal = JOptionPane.showInputDialog("Enter your fitness goal:");
        double weightGoal = Double.parseDouble(JOptionPane.showInputDialog("Enter your weight goal:"));
        int timeGoal = Integer.parseInt(JOptionPane.showInputDialog("Enter your time goal (in days):"));

        // Assuming memberId is auto-generated, use 0 as a placeholder
        Member newMember = new Member(0, name, dob, fitnessGoal, weightGoal, timeGoal);
        jdbcDatabaseOp.addMember(newMember);

        JOptionPane.showMessageDialog(null, "Registration successful. Please log in.");
    }

    private void memberLogin() {
        int memberId = Integer.parseInt(JOptionPane.showInputDialog("Enter your Member ID:"));
        Member member = jdbcDatabaseOp.getMemberById(memberId);
        if (member != null) {
            JOptionPane.showMessageDialog(null, "Login successful. Welcome, " + member.getFullName() + "!");
            postLoginMemberOptions(memberId);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Member ID or Member not found.");
        }
    }

    private void postLoginMemberOptions(int memberId) {
        String[] options = {"Manage Profile", "View Dashboard", "Manage Schedule"};
        int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "Member Portal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                updateMemberProfile(memberId);
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Dashboard functionality coming soon.");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Schedule management functionality coming soon.");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice made.");
                break;
        }
    }

    private void updateMemberProfile(int memberId) {
        Member member = jdbcDatabaseOp.getMemberById(memberId);
        if (member == null) {
            JOptionPane.showMessageDialog(null, "Member not found.");
            return;
        }

        String newFitnessGoal = JOptionPane.showInputDialog("Enter your new fitness goal:", member.getFitnessGoal());
        double newWeightGoal = Double.parseDouble(JOptionPane.showInputDialog("Enter your new weight goal:", member.getWeightGoal()));
        int newTimeGoal = Integer.parseInt(JOptionPane.showInputDialog("Enter your new time goal (in days):", member.getTimeGoal()));

        member.setFitnessGoal(newFitnessGoal);
        member.setWeightGoal(newWeightGoal);
        member.setTimeGoal(newTimeGoal);
        jdbcDatabaseOp.updateMember(member);

        JOptionPane.showMessageDialog(null, "Profile updated successfully.");
    }
}