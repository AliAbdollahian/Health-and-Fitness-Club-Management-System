import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface DatabaseOperations {
    // Member operations
    Member getMemberById(int memberId);

    Member getMemberByName(String fullName);

    List<Member> getAllMembers();
    void addMember(Member member);
    void updateMember(Member member);
    void deleteMember(int memberId);

    // Trainer operations
    Trainer getTrainerById(int trainerId);
    List<Trainer> getAllTrainers();
    void addTrainer(Trainer trainer);
    void updateTrainer(Trainer trainer);
    void deleteTrainer(int trainerId);

    void addTrainerAvailabilityDate(int trainerId, Date availabilityDate);

    Date getTrainerAvailabilityDate(int trainerId);

    // Administrative staff operations
    AdministrativeStaff getAdministrativeStaffById(int administrativeId);
    List<AdministrativeStaff> getAllAdministrativeStaff();
    void addAdministrativeStaff(AdministrativeStaff administrativeStaff);
    void updateAdministrativeStaff(AdministrativeStaff administrativeStaff);
    void deleteAdministrativeStaff(int administrativeId);

    //Health Metrics Operations
    void addHealthMetric(HealthMetrics healthMetric);
    List<HealthMetrics> getHealthMetricsByMemberId(int memberId);
    void updateHealthMetric(HealthMetrics healthMetric);
    void deleteHealthMetric(int metricId);

    // ClassSchedule Operations
    void addClassSchedule(ClassSchedule schedule) throws SQLException;

    List<ClassSchedule> getAllClassSchedules() throws SQLException;

    void updateClassSchedule(ClassSchedule schedule) throws SQLException;


    void deleteClassSchedule(int classId) throws SQLException;

    //RoomBooking Operations
    void addRoomBooking(RoomBooking booking) throws SQLException;

    List<RoomBooking> getAllRoomBookings() throws SQLException;

    void updateRoomBooking(RoomBooking booking) throws SQLException;

    void deleteRoomBooking(int bookingId) throws SQLException;

    //Equipment Maintenance Operations
    void addEquipmentMaintenance(EquipmentMaintenance maintenance) throws SQLException;

    List<EquipmentMaintenance> getAllEquipmentMaintenanceRecords() throws SQLException;

    void updateEquipmentMaintenance(EquipmentMaintenance maintenance) throws SQLException;

    void deleteEquipmentMaintenance(int maintenanceId) throws SQLException;

    List<BillingAndPayment> getAllTransactions() throws SQLException;

    void addTransaction(int memberId, Date transactionDate, double amount, String status) throws SQLException;

    void updateClassSchedule(int classId, String updatedClassName, java.util.Date updatedClassDate, Time updatedStartTime, Time updatedEndTime) throws SQLException;

    List<MemberClassBooking> getBookingsByMemberId(int memberId) throws SQLException;

    void deleteBooking(int bookingId) throws SQLException;

    void addBooking(int memberId, int classId, String bookingStatus, Date bookingDate) throws SQLException;

    ClassSchedule getClassScheduleById(int classId) throws SQLException;

    // Add an achievement
    void addAchievement(Achievement achievement) throws SQLException;

    // Get all achievements for a member
    List<Achievement> getAchievementsByMemberId(int memberId) throws SQLException;
}

