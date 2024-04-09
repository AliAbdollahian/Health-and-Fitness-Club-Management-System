import java.util.Date;

public class Trainer {
    private int trainerId;
    private String fullName;
    private Date availability;

    // Constructor
    public Trainer(int trainerId, String fullName, Date availability) {
        this.trainerId = trainerId;
        this.fullName = fullName;
        this.availability = availability;
    }

    // Getters
    public int getTrainerId() {
        return trainerId;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getAvailability() {
        return availability;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "trainerId=" + trainerId +
                ", fullName='" + fullName + '\'' +
                ", availability=" + availability +
                '}';
    }
}
