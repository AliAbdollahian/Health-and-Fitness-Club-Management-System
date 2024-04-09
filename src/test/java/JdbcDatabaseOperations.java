import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations {
    private static final String URL = "jdbc:postgresql://localhost:5432/Health and Fitness Club Management System";
    private static final String USER = "postgres";
    private static final String PASSWORD = "987654Aa@";
    private Connection connection;

    public JdbcDatabaseOperations() {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            // Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Member getMemberById(int memberId) {
        Member member = null;
        String query = "SELECT * FROM Member WHERE memberId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                member = new Member(
                        resultSet.getInt("memberId"),
                        resultSet.getString("fullName"),
                        resultSet.getDate("dateOfBirth"),
                        resultSet.getString("fitnessGoal"),
                        resultSet.getDouble("weightGoal"),
                        resultSet.getInt("timeGoal")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Member";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Member member = new Member(
                        resultSet.getInt("memberId"),
                        resultSet.getString("fullName"),
                        resultSet.getDate("dateOfBirth"),
                        resultSet.getString("fitnessGoal"),
                        resultSet.getDouble("weightGoal"),
                        resultSet.getInt("timeGoal")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public void addMember(Member member) {
        String query = "INSERT INTO Member (fullName, dateOfBirth, fitnessGoal, weightGoal, timeGoal) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setDate(2, new java.sql.Date(member.getDateOfBirth().getTime()));
            preparedStatement.setString(3, member.getFitnessGoal());
            preparedStatement.setDouble(4, member.getWeightGoal());
            preparedStatement.setInt(5, member.getTimeGoal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMember(Member member) {
        String query = "UPDATE Member SET fullName = ?, dateOfBirth = ?, fitnessGoal = ?, weightGoal = ?, timeGoal = ? " +
                "WHERE memberId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setDate(2, new java.sql.Date(member.getDateOfBirth().getTime()));
            preparedStatement.setString(3, member.getFitnessGoal());
            preparedStatement.setDouble(4, member.getWeightGoal());
            preparedStatement.setInt(5, member.getTimeGoal());
            preparedStatement.setInt(6, member.getMemberId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(int memberId) {
        String query = "DELETE FROM Member WHERE memberId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Trainer getTrainerById(int trainerId) {
        Trainer trainer = null;
        String query = "SELECT * FROM trainer WHERE trainerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trainerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                trainer = new Trainer(
                        resultSet.getInt("trainerId"),
                        resultSet.getString("fullName"),
                        resultSet.getDate("availability")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String query = "SELECT * FROM trainer";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Trainer trainer = new Trainer(
                        resultSet.getInt("trainerId"),
                        resultSet.getString("fullName"),
                        resultSet.getDate("availability")
                );
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }

    @Override
    public void addTrainer(Trainer trainer) {
        String query = "INSERT INTO trainer (trainerId, fullName, availability) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trainer.getTrainerId());
            preparedStatement.setString(2, trainer.getFullName());
            preparedStatement.setDate(3, new java.sql.Date(trainer.getAvailability().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        String query = "UPDATE trainer SET fullName = ?, availability = ? WHERE trainerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, trainer.getFullName());
            preparedStatement.setDate(2, new java.sql.Date(trainer.getAvailability().getTime()));
            preparedStatement.setInt(3, trainer.getTrainerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrainer(int trainerId) {
        String query = "DELETE FROM trainer WHERE trainerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trainerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AdministrativeStaff getAdministrativeStaffById(int administrativeId) {
        AdministrativeStaff staff = null;
        String query = "SELECT * FROM administrativeProfiles WHERE administrativeId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, administrativeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                staff = new AdministrativeStaff(
                        resultSet.getInt("administrativeId"),
                        resultSet.getString("fullName"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public List<AdministrativeStaff> getAllAdministrativeStaff() {
        List<AdministrativeStaff> staffMembers = new ArrayList<>();
        String query = "SELECT * FROM administrativeProfiles";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                AdministrativeStaff staff = new AdministrativeStaff(
                        resultSet.getInt("administrativeId"),
                        resultSet.getString("fullName"),
                        resultSet.getString("email")
                );
                staffMembers.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffMembers;
    }

    @Override
    public void addAdministrativeStaff(AdministrativeStaff administrativeStaff) {
        String query = "INSERT INTO administrativeProfiles (administrativeId, fullName, email) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, administrativeStaff.getAdministrativeId());
            preparedStatement.setString(2, administrativeStaff.getFullName());
            preparedStatement.setString(3, administrativeStaff.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdministrativeStaff(AdministrativeStaff administrativeStaff) {
        String query = "UPDATE administrativeProfiles SET fullName = ?, email = ? WHERE administrativeId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, administrativeStaff.getFullName());
            preparedStatement.setString(2, administrativeStaff.getEmail());
            preparedStatement.setInt(3, administrativeStaff.getAdministrativeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdministrativeStaff(int administrativeId) {
        String query = "DELETE FROM administrativeProfiles WHERE administrativeId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, administrativeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
