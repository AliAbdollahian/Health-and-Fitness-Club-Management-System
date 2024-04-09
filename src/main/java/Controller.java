import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.Date;

public class Controller {
    private JDateChooser dateChooser;
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
}
