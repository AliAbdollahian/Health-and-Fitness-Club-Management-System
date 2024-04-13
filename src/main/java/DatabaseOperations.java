import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * This interface defines database operations related to various entities such as members, trainers,
 * administrative staff, health metrics, class schedules, room bookings, equipment maintenance, transactions,
 * and achievements.
 */
public interface DatabaseOperations {
    // Member operations

    /**
     * Retrieves a member by their ID.
     *
     * @param memberId The ID of the member to retrieve.
     * @return The member object if found, otherwise null.
     */
    Member getMemberById(int memberId);

    /**
     * Retrieves a member by their full name.
     *
     * @param fullName The full name of the member to retrieve.
     * @return The member object if found, otherwise null.
     */
    Member getMemberByName(String fullName);

    /**
     * Retrieves all members.
     *
     * @return A list of all members.
     */
    List<Member> getAllMembers();

    /**
     * Adds a new member.
     *
     * @param member The member to add.
     */
    void addMember(Member member);

    /**
     * Updates an existing member.
     *
     * @param member The member to update.
     */
    void updateMember(Member member);

    /**
     * Deletes a member by their ID.
     *
     * @param memberId The ID of the member to delete.
     */
    void deleteMember(int memberId);

    // Trainer operations

    /**
     * Retrieves a trainer by their ID.
     *
     * @param trainerId The ID of the trainer to retrieve.
     * @return The trainer object if found, otherwise null.
     */
    Trainer getTrainerById(int trainerId);

    /**
     * Retrieves all trainers.
     *
     * @return A list of all trainers.
     */
    List<Trainer> getAllTrainers();

    /**
     * Adds a new trainer.
     *
     * @param trainer The trainer to add.
     */
    void addTrainer(Trainer trainer);

    /**
     * Updates an existing trainer.
     *
     * @param trainer The trainer to update.
     */
    void updateTrainer(Trainer trainer);

    /**
     * Deletes a trainer by their ID.
     *
     * @param trainerId The ID of the trainer to delete.
     */
    void deleteTrainer(int trainerId);

    /**
     * Adds availability date for a trainer.
     *
     * @param trainerId         The ID of the trainer.
     * @param availabilityDate  The availability date.
     */
    void addTrainerAvailabilityDate(int trainerId, Date availabilityDate);

    /**
     * Retrieves availability date for a trainer.
     *
     * @param trainerId The ID of the trainer.
     * @return The availability date.
     */
    Date getTrainerAvailabilityDate(int trainerId);

    // Administrative staff operations

    /**
     * Retrieves administrative staff by their ID.
     *
     * @param administrativeId The ID of the administrative staff.
     * @return The administrative staff object if found, otherwise null.
     */
    AdministrativeStaff getAdministrativeStaffById(int administrativeId);

    /**
     * Retrieves all administrative staff.
     *
     * @return A list of all administrative staff.
     */
    List<AdministrativeStaff> getAllAdministrativeStaff();

    /**
     * Adds a new administrative staff.
     *
     * @param administrativeStaff The administrative staff to add.
     */
    void addAdministrativeStaff(AdministrativeStaff administrativeStaff);

    /**
     * Updates an existing administrative staff.
     *
     * @param administrativeStaff The administrative staff to update.
     */
    void updateAdministrativeStaff(AdministrativeStaff administrativeStaff);

    /**
     * Deletes an administrative staff by their ID.
     *
     * @param administrativeId The ID of the administrative staff to delete.
     */
    void deleteAdministrativeStaff(int administrativeId);

    // Health Metrics Operations

    /**
     * Adds a health metric.
     *
     * @param healthMetric The health metric to add.
     */
    void addHealthMetric(HealthMetrics healthMetric);

    /**
     * Retrieves health metrics by member ID.
     *
     * @param memberId The ID of the member.
     * @return A list of health metrics associated with the member.
     */
    List<HealthMetrics> getHealthMetricsByMemberId(int memberId);

    /**
     * Updates a health metric.
     *
     * @param healthMetric The health metric to update.
     */
    void updateHealthMetric(HealthMetrics healthMetric);

    /**
     * Deletes a health metric by its ID.
     *
     * @param metricId The ID of the health metric to delete.
     */
    void deleteHealthMetric(int metricId);

    // ClassSchedule Operations

    /**
     * Adds a class schedule.
     *
     * @param schedule The class schedule to add.
     * @throws SQLException If an SQL exception occurs.
     */
    void addClassSchedule(ClassSchedule schedule) throws SQLException;

    /**
     * Retrieves all class schedules.
     *
     * @return A list of all class schedules.
     * @throws SQLException If an SQL exception occurs.
     */
    List<ClassSchedule> getAllClassSchedules() throws SQLException;

    /**
     * Updates a class schedule.
     *
     * @param schedule The class schedule to update.
     * @throws SQLException If an SQL exception occurs.
     */
    void updateClassSchedule(ClassSchedule schedule) throws SQLException;

    /**
     * Deletes a class schedule by its ID.
     *
     * @param classId The ID of the class schedule to delete.
     * @throws SQLException If an SQL exception occurs.
     */
    void deleteClassSchedule(int classId) throws SQLException;

    // RoomBooking Operations

    /**
     * Adds a room booking.
     *
     * @param booking The room booking to add.
     * @throws SQLException If an SQL exception occurs.
     */
    void addRoomBooking(RoomBooking booking) throws SQLException;

    /**
     * Retrieves all room bookings.
     *
     * @return A list of all room bookings.
     * @throws SQLException If an SQL exception occurs.
     */
    List<RoomBooking> getAllRoomBookings() throws SQLException;

    /**
     * Updates a room booking.
     *
     * @param booking The room booking to update.
     * @throws SQLException If an SQL exception occurs.
     */
    void updateRoomBooking(RoomBooking booking) throws SQLException;

    /**
     * Deletes a room booking by its ID.
     *
     * @param bookingId The ID of the room booking to delete.
     * @throws SQLException If an SQL exception occurs.
     */
    void deleteRoomBooking(int bookingId) throws SQLException;

    // Equipment Maintenance Operations

    /**
     * Adds an equipment maintenance record.
     *
     * @param maintenance The equipment maintenance record to add.
     * @throws SQLException If an SQL exception occurs.
     */
    void addEquipmentMaintenance(EquipmentMaintenance maintenance) throws SQLException;

    /**
     * Retrieves all equipment maintenance records.
     *
     * @return A list of all equipment maintenance records.
     * @throws SQLException If an SQL exception occurs.
     */
    List<EquipmentMaintenance> getAllEquipmentMaintenanceRecords() throws SQLException;

    /**
     * Updates an equipment maintenance record.
     *
     * @param maintenance The equipment maintenance record to update.
     * @throws SQLException If an SQL exception occurs.
     */
    void updateEquipmentMaintenance(EquipmentMaintenance maintenance) throws SQLException;

    /**
     * Deletes an equipment maintenance record by its ID.
     *
     * @param maintenanceId The ID of the equipment maintenance record to delete.
     * @throws SQLException If an SQL exception occurs.
     */
    void deleteEquipmentMaintenance(int maintenanceId) throws SQLException;

    /**
     * Retrieves all billing and payment transactions.
     *
     * @return A list of all billing and payment transactions.
     * @throws SQLException If an SQL exception occurs.
     */
    List<BillingAndPayment> getAllTransactions() throws SQLException;

    /**
     * Adds a billing and payment transaction.
     *
     * @param memberId        The ID of the member associated with the transaction.
     * @param transactionDate The date of the transaction.
     * @param amount          The amount of the transaction.
     * @param status          The status of the transaction.
     * @throws SQLException If an SQL exception occurs.
     */
    void addTransaction(int memberId, Date transactionDate, double amount, String status) throws SQLException;

    /**
     * Updates a class schedule with the specified information.
     *
     * @param classId           The ID of the class schedule to be updated.
     * @param updatedClassName  The updated class name.
     * @param updatedClassDate  The updated class date.
     * @param updatedStartTime  The updated start time.
     * @param updatedEndTime    The updated end time.
     * @throws SQLException If an SQL exception occurs.
     */
    void updateClassSchedule(int classId, String updatedClassName, java.util.Date updatedClassDate, Time updatedStartTime, Time updatedEndTime) throws SQLException;

    /**
     * Retrieves bookings for a member by their ID.
     *
     * @param memberId The ID of the member.
     * @return A list of bookings associated with the member.
     * @throws SQLException If an SQL exception occurs.
     */
    List<MemberClassBooking> getBookingsByMemberId(int memberId) throws SQLException;

    /**
     * Deletes a booking by its ID.
     *
     * @param bookingId The ID of the booking to delete.
     * @throws SQLException If an SQL exception occurs.
     */
    void deleteBooking(int bookingId) throws SQLException;

    /**
     * Adds a booking for a member.
     *
     * @param memberId      The ID of the member.
     * @param classId       The ID of the class.
     * @param bookingStatus The booking status.
     * @param bookingDate   The booking date.
     * @throws SQLException If an SQL exception occurs.
     */
    void addBooking(int memberId, int classId, String bookingStatus, Date bookingDate) throws SQLException;

    /**
     * Retrieves a class schedule by its ID.
     *
     * @param classId The ID of the class schedule.
     * @return The class schedule object if found, otherwise null.
     * @throws SQLException If an SQL exception occurs.
     */
    ClassSchedule getClassScheduleById(int classId) throws SQLException;

    /**
     * Adds an achievement.
     *
     * @param achievement The achievement to add.
     * @throws SQLException If an SQL exception occurs.
     */
    void addAchievement(Achievement achievement) throws SQLException;

    /**
     * Retrieves all achievements for a member by their ID.
     *
     * @param memberId The ID of the member.
     * @return A list of achievements associated with the member.
     * @throws SQLException If an SQL exception occurs.
     */
    List<Achievement> getAchievementsByMemberId(int memberId) throws SQLException;
}
