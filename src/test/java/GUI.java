import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public Controller controller;
    public JLabel labelWhenOpening;
    public JFrame frame;
    public JButton member;
    public JButton trainer;
    public JButton admin;

    public GUI() {
        controller = new Controller(new JdbcDatabaseOperations(),this);
        makeFrame();
    }

    private void makeFrame() {
        frame = new JFrame();
        frame.setTitle("Health and Fitness Club Management System");
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        labelWhenOpening = new JLabel("Please specify your role:");
        member = new JButton("Member");
        member.addActionListener(e->controller.memberClicked());
        trainer = new JButton("Trainer");
        trainer.addActionListener(e->controller.trainerClicked());
        admin = new JButton("Admin");
        admin.addActionListener(e->controller.adminClicked());

        buttonPanel.add(member);
        buttonPanel.add(trainer);
        buttonPanel.add(admin);


        frame.add(labelWhenOpening,BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        new GUI();
    }
}
