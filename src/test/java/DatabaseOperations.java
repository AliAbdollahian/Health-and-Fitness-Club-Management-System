import java.util.List;

public interface DatabaseOperations {
    // Member operations
    Member getMemberById(int memberId);
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

    // Administrative staff operations
    AdministrativeStaff getAdministrativeStaffById(int administrativeId);
    List<AdministrativeStaff> getAllAdministrativeStaff();
    void addAdministrativeStaff(AdministrativeStaff administrativeStaff);
    void updateAdministrativeStaff(AdministrativeStaff administrativeStaff);
    void deleteAdministrativeStaff(int administrativeId);
}

