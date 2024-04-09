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

    public void memberClicked() {
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
}
