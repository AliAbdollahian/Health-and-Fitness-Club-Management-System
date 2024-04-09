import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        JdbcDatabaseOperations jdbcOperations = new JdbcDatabaseOperations();

        testGetMemberById(jdbcOperations);
        testGetAllMembers(jdbcOperations);

        // Test Trainer methods
        testGetTrainerById(jdbcOperations);
        testGetAllTrainers(jdbcOperations);

        // Test AdministrativeStaff methods
        testGetAdministrativeStaffById(jdbcOperations);
        testGetAllAdministrativeStaff(jdbcOperations);

        // Display welcome message
        System.out.println("Welcome to the Health and Fitness Club Management System!");

        // Prompt user to specify role
        System.out.println("\nPlease specify your role:");
        System.out.println("1. Member");
        System.out.println("2. Trainer");
        System.out.println("3. Administrative Staff");

        // Prompt user for input
        System.out.print("Enter your choice: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Perform actions based on user role
        switch (roleChoice) {
            case 1:
                memberMenu();
                break;
//            case 2:
//                trainerMenu();
//                break;
//            case 3:
//                adminMenu();
//                break;
            default:
                System.out.println("Invalid choice. Exiting program.");
        }
    }

    private static void testGetMemberById(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getMemberById:");
        Member member = jdbcOperations.getMemberById(1);
        System.out.println(member);
    }

    private static void testGetAllMembers(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getAllMembers:");
        List<Member> members = jdbcOperations.getAllMembers();
        for (Member member : members) {
            System.out.println(member);
        }
    }
    private static void testGetTrainerById(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getTrainerById:");
        Trainer trainer = jdbcOperations.getTrainerById(1);
        System.out.println(trainer);
    }

    private static void testGetAllTrainers(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getAllTrainers:");
        List<Trainer> trainers = jdbcOperations.getAllTrainers();
        for (Trainer trainer : trainers) {
            System.out.println(trainer);
        }
    }

    private static void testGetAdministrativeStaffById(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getAdministrativeStaffById:");
        AdministrativeStaff staff = jdbcOperations.getAdministrativeStaffById(1);
        System.out.println(staff);
    }

    private static void testGetAllAdministrativeStaff(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getAllAdministrativeStaff:");
        List<AdministrativeStaff> staffMembers = jdbcOperations.getAllAdministrativeStaff();
        for (AdministrativeStaff staff : staffMembers) {
            System.out.println(staff);
        }
    }


    // Member menu
    private static void memberMenu() {
        // Display member menu options
        System.out.println("\nMember Menu:");
        System.out.println("1. Register as a new member");
        System.out.println("2. Manage your profile");
        System.out.println("3. View dashboard");
        System.out.println("4. Manage schedule");
        System.out.println("5. Exit");

        // Prompt user for input
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
//
//        // Perform actions based on user choice
//        switch (choice) {
//            case 1:
//                registerMember();
//                break;
//            case 2:
//                manageProfile();
//                break;
//            case 3:
//                viewDashboard();
//                break;
//            case 4:
//                manageSchedule();
//                break;
//            case 5:
//                System.out.println("Exiting program. Goodbye!");
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//        }
//    }
//
//    // Trainer menu
//    private static void trainerMenu() {
//        // Display trainer menu options
//        System.out.println("\nTrainer Menu:");
//        System.out.println("1. Manage schedule");
//        System.out.println("2. View member profiles");
//        System.out.println("3. Exit");
//
//        // Prompt user for input
//        System.out.print("Enter your choice: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        // Perform actions based on user choice
//        switch (choice) {
//            case 1:
//                manageSchedule();
//                break;
//            case 2:
//                viewMemberProfiles();
//                break;
//            case 3:
//                System.out.println("Exiting program. Goodbye!");
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//        }
//    }
//
//    // Administrative staff menu
//    private static void adminMenu() {
//        // Display administrative staff menu options
//        System.out.println("\nAdministrative Staff Menu:");
//        System.out.println("1. Manage room bookings");
//        System.out.println("2. Monitor equipment maintenance");
//        System.out.println("3. Update class schedules");
//        System.out.println("4. Process billing and payments");
//        System.out.println("5. Exit");
//
//        // Prompt user for input
//        System.out.print("Enter your choice: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        // Perform actions based on user choice
//        switch (choice) {
//            case 1:
//                manageRoomBookings();
//                break;
//            case 2:
//                monitorEquipmentMaintenance();
//                break;
//            case 3:
//                updateClassSchedules();
//                break;
//            case 4:
//                processBillingAndPayments();
//                break;
//            case 5:
//                System.out.println("Exiting program. Goodbye!");
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//        }
//    }
    }
}

