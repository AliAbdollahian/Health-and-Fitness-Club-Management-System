public class AdministrativeStaff {
    private int administrativeId;
    private String fullName;
    private String email;

    // Constructor
    public AdministrativeStaff(int administrativeId, String fullName, String email) {
        this.administrativeId = administrativeId;
        this.fullName = fullName;
        this.email = email;
    }

    // Getters
    public int getAdministrativeId() {
        return administrativeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "AdministrativeStaff{" +
                "administrativeId=" + administrativeId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
