import javax.swing.*;

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
        Trainer trainer =jdbcDatabaseOp.getTrainerById(idOfTrainer);
        System.out.println(trainer.getAvailability());
    }
}
