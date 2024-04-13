import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations {
    private static final String URL = "jdbc:postgresql://localhost:5432/yoyo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
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
    public Member getMemberByName(String fullName) {
        Member member = null;
        String query = "SELECT * FROM Member WHERE fullName = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fullName);
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
    public void addTrainerAvailabilityDate(int trainerId, Date availabilityDate) {
        String query = "UPDATE trainer SET availability = ? WHERE trainerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(availabilityDate.getTime()));
            preparedStatement.setInt(2, trainerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Date getTrainerAvailabilityDate(int trainerId) {
        Date availabilityDate = null;
        String query = "SELECT availability FROM trainer WHERE trainerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trainerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                availabilityDate = resultSet.getDate("availability");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availabilityDate;
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


    //Health Metrics Operations
    // Add a new health metric record
    @Override
    public void addHealthMetric(HealthMetrics healthMetric) {
        String query = "INSERT INTO healthMetrics (memberId, weight, height) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, healthMetric.getMemberId());
            preparedStatement.setBigDecimal(2, BigDecimal.valueOf(healthMetric.getWeight()));
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(healthMetric.getHeight()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get health metrics by memberId
    @Override
    public List<HealthMetrics> getHealthMetricsByMemberId(int memberId) {
        List<HealthMetrics> metrics = new ArrayList<>();
        String query = "SELECT * FROM healthMetrics WHERE memberId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HealthMetrics metric = new HealthMetrics(
                        resultSet.getInt("metricId"),
                        resultSet.getInt("memberId"),
                        resultSet.getBigDecimal("weight").doubleValue(),
                        resultSet.getBigDecimal("height").doubleValue()
                );
                metrics.add(metric);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return metrics;
    }

    // Update a health metric
    @Override
    public void updateHealthMetric(HealthMetrics healthMetric) {
        String query = "UPDATE healthMetrics SET weight = ?, height = ? WHERE metricId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBigDecimal(1, BigDecimal.valueOf(healthMetric.getWeight()));
            preparedStatement.setBigDecimal(2, BigDecimal.valueOf(healthMetric.getHeight()));
            preparedStatement.setInt(3, healthMetric.getMetricId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a health metric
    @Override
    public void deleteHealthMetric(int metricId) {
        String query = "DELETE FROM healthMetrics WHERE metricId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, metricId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ClassSchedule Operations
    @Override
    public void addClassSchedule(ClassSchedule schedule) throws SQLException {
        String query = "INSERT INTO classSchedules (className, classDate, startTime, endTime, trainerId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, schedule.getClassName());
            preparedStatement.setDate(2, Date.valueOf(schedule.getClassDate()));
            preparedStatement.setTime(3, Time.valueOf(schedule.getStartTime()));
            preparedStatement.setTime(4, Time.valueOf(schedule.getEndTime()));
            preparedStatement.setInt(5, schedule.getTrainerId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<ClassSchedule> getAllClassSchedules() throws SQLException {
        List<ClassSchedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM classSchedules";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                schedules.add(new ClassSchedule(
                        resultSet.getInt("classId"),
                        resultSet.getString("className"),
                        resultSet.getDate("classDate").toLocalDate(),
                        resultSet.getTime("startTime").toLocalTime(),
                        resultSet.getTime("endTime").toLocalTime(),
                        resultSet.getInt("trainerId")));
            }
        }
        return schedules;
    }

    @Override
    public void updateClassSchedule(ClassSchedule schedule) throws SQLException {
        String query = "UPDATE classSchedules SET className = ?, classDate = ?, startTime = ?, endTime = ?, trainerId = ? WHERE classId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, schedule.getClassName());
            preparedStatement.setDate(2, Date.valueOf(schedule.getClassDate()));
            preparedStatement.setTime(3, Time.valueOf(schedule.getStartTime()));
            preparedStatement.setTime(4, Time.valueOf(schedule.getEndTime()));
            preparedStatement.setInt(5, schedule.getTrainerId());
            preparedStatement.setInt(6, schedule.getClassId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteClassSchedule(int classId) throws SQLException {
        String query = "DELETE FROM classSchedules WHERE classId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.executeUpdate();
        }
    }

    //RoomBooking Operations
    @Override
    public void addRoomBooking(RoomBooking booking) throws SQLException {
        String query = "INSERT INTO roomBookings (roomId, bookingDate, startTime, endTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, booking.getRoomId());
            preparedStatement.setDate(2, new java.sql.Date(booking.getBookingDate().getTime()));
            preparedStatement.setTime(3, new java.sql.Time(booking.getStartTime().getTime()));
            preparedStatement.setTime(4, new java.sql.Time(booking.getEndTime().getTime()));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<RoomBooking> getAllRoomBookings() throws SQLException {
        List<RoomBooking> bookings = new ArrayList<>();
        String query = "SELECT * FROM roomBookings";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                bookings.add(new RoomBooking(
                        resultSet.getInt("bookingId"),
                        resultSet.getInt("roomId"),
                        new Date(resultSet.getDate("bookingDate").getTime()),
                        new Date(resultSet.getTime("startTime").getTime()),
                        new Date(resultSet.getTime("endTime").getTime())));
            }
        }
        return bookings;
    }

    @Override
    public void updateRoomBooking(RoomBooking booking) throws SQLException {
        String query = "UPDATE roomBookings SET roomId = ?, bookingDate = ?, startTime = ?, endTime = ? WHERE bookingId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, booking.getRoomId());
            preparedStatement.setDate(2, new java.sql.Date(booking.getBookingDate().getTime()));
            preparedStatement.setTime(3, new java.sql.Time(booking.getStartTime().getTime()));
            preparedStatement.setTime(4, new java.sql.Time(booking.getEndTime().getTime()));
            preparedStatement.setInt(5, booking.getBookingId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteRoomBooking(int bookingId) throws SQLException {
        String query = "DELETE FROM roomBookings WHERE bookingId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        }
    }

    //Equipment Maintenance Operations
    @Override
    public void addEquipmentMaintenance(EquipmentMaintenance maintenance) throws SQLException {
        String query = "INSERT INTO equipmentMaintenance (equipmentId, maintenanceDate, maintenanceType) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, maintenance.getEquipmentId());
            preparedStatement.setDate(2, new java.sql.Date(maintenance.getMaintenanceDate().getTime())); // Convert java.util.Date to java.sql.Date
            preparedStatement.setString(3, maintenance.getMaintenanceType());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<EquipmentMaintenance> getAllEquipmentMaintenanceRecords() throws SQLException {
        List<EquipmentMaintenance> maintenanceRecords = new ArrayList<>();
        String query = "SELECT * FROM equipmentMaintenance";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                maintenanceRecords.add(new EquipmentMaintenance(
                        resultSet.getInt("maintenanceId"),
                        resultSet.getInt("equipmentId"),
                        new Date(resultSet.getDate("maintenanceDate").getTime()), // Convert java.sql.Date to java.util.Date
                        resultSet.getString("maintenanceType")));
            }
        }
        return maintenanceRecords;
    }

    @Override
    public void updateEquipmentMaintenance(EquipmentMaintenance maintenance) throws SQLException {
        String query = "UPDATE equipmentMaintenance SET equipmentId = ?, maintenanceDate = ?, maintenanceType = ? WHERE maintenanceId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, maintenance.getEquipmentId());
            preparedStatement.setDate(2, new java.sql.Date(maintenance.getMaintenanceDate().getTime()));
            preparedStatement.setString(3, maintenance.getMaintenanceType());
            preparedStatement.setInt(4, maintenance.getMaintenanceId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteEquipmentMaintenance(int maintenanceId) throws SQLException {
        String query = "DELETE FROM equipmentMaintenance WHERE maintenanceId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, maintenanceId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<BillingAndPayment> getAllTransactions() throws SQLException {
        List<BillingAndPayment> transactions = new ArrayList<>();
        String query = "SELECT * FROM billingAndPayments";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                transactions.add(new BillingAndPayment(
                        resultSet.getInt("memberId"),
                        new Date(resultSet.getDate("transactionDate").getTime()),
                        resultSet.getDouble("amount"),
                        resultSet.getString("status")));
            }
        }
        return transactions;
    }

    @Override
    public void addTransaction(int memberId, Date transactionDate, double amount, String status) throws SQLException {
        String query = "INSERT INTO billingAndPayments (memberId, transactionDate, amount, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.setDate(2, new java.sql.Date(transactionDate.getTime()));
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, status);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateClassSchedule(int classId, String updatedClassName, java.util.Date updatedClassDate, Time updatedStartTime, Time updatedEndTime) throws SQLException {
        String query = "UPDATE classSchedule SET className = ?, classDate = ?, startTime = ?, endTime = ? WHERE classId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, updatedClassName);
            preparedStatement.setDate(2, new java.sql.Date(updatedClassDate.getTime()));
            preparedStatement.setTime(3, updatedStartTime);
            preparedStatement.setTime(4, updatedEndTime);
            preparedStatement.setInt(5, classId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<MemberClassBooking> getBookingsByMemberId(int memberId) throws SQLException {
        List<MemberClassBooking> bookings = new ArrayList<>();
        String query = "SELECT * FROM memberClassBookings WHERE memberId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int bookingId = resultSet.getInt("bookingId");
                    int classId = resultSet.getInt("classId");
                    String bookingStatus = resultSet.getString("bookingStatus");
                    Date bookingDate = new Date(resultSet.getDate("bookingDate").getTime());

                    MemberClassBooking booking = new MemberClassBooking(bookingId, memberId, classId, bookingStatus, bookingDate);
                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }


    @Override
    public void deleteBooking(int bookingId) throws SQLException {
        String query = "DELETE FROM memberClassBookings WHERE bookingId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookingId);
            preparedStatement.executeUpdate();
        }
    }


    @Override
    public void addBooking(int memberId, int classId, String bookingStatus, Date bookingDate) throws SQLException {
        String query = "INSERT INTO memberClassBookings (memberId, classId, bookingStatus, bookingDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, classId);
            preparedStatement.setString(3, bookingStatus);
            preparedStatement.setDate(4, new java.sql.Date(bookingDate.getTime()));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ClassSchedule getClassScheduleById(int classId) throws SQLException {
        ClassSchedule classSchedule = null;
        String query = "SELECT * FROM ClassSchedules WHERE classId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, classId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String className = resultSet.getString("className");
                    LocalDate classDate = resultSet.getDate("classDate").toLocalDate();
                    LocalTime startTime = resultSet.getTime("startTime").toLocalTime();
                    LocalTime endTime = resultSet.getTime("endTime").toLocalTime();
                    int trainerId = resultSet.getInt("trainerId");

                    classSchedule = new ClassSchedule(classId, className, classDate, startTime, endTime, trainerId);
                }
            }
        }
        return classSchedule;
    }

    // Add an achievement
    @Override
    public void addAchievement(Achievement achievement) {
        String query = "INSERT INTO Achievements (memberId, description, dateAchieved) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, achievement.getMemberId());
            preparedStatement.setString(2, achievement.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(achievement.getDateAchieved().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Get all achievements for a member
    @Override
    public List<Achievement> getAchievementsByMemberId(int memberId) throws SQLException {
        List<Achievement> achievements = new ArrayList<>();
        String query = "SELECT * FROM Achievements WHERE memberId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                achievements.add(new Achievement(
                        resultSet.getInt("achievementId"),
                        memberId,
                        resultSet.getString("description"),
                        resultSet.getDate("dateAchieved")
                ));
            }
        }
        return achievements;
    }



        /**
         * Retrieves a list of members along with their total number of achievements.
         * @return List of MemberAchievementCount representing the leaderboard.
         */
        public List<MemberAchievementCount> getMembersAchievementCount() throws SQLException {
            List<MemberAchievementCount> members = new ArrayList<>();
            String query = "SELECT m.memberId, m.fullName, COUNT(a.achievementId) as achievementCount " +
                    "FROM Member m LEFT JOIN Achievements a ON m.memberId = a.memberId " +
                    "GROUP BY m.memberId, m.fullName " +
                    "ORDER BY achievementCount DESC";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int memberId = resultSet.getInt("memberId");
                    String fullName = resultSet.getString("fullName");
                    int achievementCount = resultSet.getInt("achievementCount");
                    members.add(new MemberAchievementCount(memberId, fullName, achievementCount));
                }
            }
            return members;
        }

    public void addFeedback(Feedback feedback) throws SQLException {
        String query = "INSERT INTO Feedback (memberId, classId, trainerId, rating, comments, feedbackDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, feedback.getMemberId());
            preparedStatement.setInt(2, feedback.getClassId());
            preparedStatement.setInt(3, feedback.getTrainerId());
            preparedStatement.setInt(4, feedback.getRating());
            preparedStatement.setString(5, feedback.getComments());
            preparedStatement.setDate(6, new java.sql.Date(feedback.getFeedbackDate().getTime()));
            preparedStatement.executeUpdate();
        }
    }

    public List<Feedback> getFeedbackByClassId(int classId) throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM Feedback WHERE classId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, classId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                feedbacks.add(new Feedback(
                        resultSet.getInt("feedbackId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("classId"),
                        resultSet.getInt("trainerId"),
                        resultSet.getInt("rating"),
                        resultSet.getString("comments"),
                        resultSet.getDate("feedbackDate")
                ));
            }
        }
        return feedbacks;
    }

    public List<Feedback> getAllFeedback() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT * FROM Feedback";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                feedbackList.add(new Feedback(
                        resultSet.getInt("feedbackId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("classId"),
                        resultSet.getInt("trainerId"),
                        resultSet.getInt("rating"),
                        resultSet.getString("comments"),
                        resultSet.getDate("feedbackDate")
                ));
            }
        }
        return feedbackList;
    }

    public void addHealthMetrics(int memberId, double weight, double height) {
        String query = "INSERT INTO healthmetrics (memberId, weight, height) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.setDouble(2, weight);
            preparedStatement.setDouble(3, height);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding health metrics failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







}
