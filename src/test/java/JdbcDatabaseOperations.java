import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations{
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
        return null;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return null;
    }

    @Override
    public void addTrainer(Trainer trainer) {

    }

    @Override
    public void updateTrainer(Trainer trainer) {

    }

    @Override
    public void deleteTrainer(int trainerId) {

    }

    @Override
    public AdministrativeStaff getAdministrativeStaffById(int administrativeId) {
        return null;
    }

    @Override
    public List<AdministrativeStaff> getAllAdministrativeStaff() {
        return null;
    }

    @Override
    public void addAdministrativeStaff(AdministrativeStaff administrativeStaff) {

    }

    @Override
    public void updateAdministrativeStaff(AdministrativeStaff administrativeStaff) {

    }

    @Override
    public void deleteAdministrativeStaff(int administrativeId) {

    }
}
