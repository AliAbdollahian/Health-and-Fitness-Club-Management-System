import java.util.Date;

public class Trainer {
    private int trainerId;
    private String fullName;
    private Date availability;

    public Trainer(int trainerId, String fullName, Date availability) {
        this.trainerId = trainerId;
        this.fullName = fullName;
        this.availability = availability;
    }

}
