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
}

