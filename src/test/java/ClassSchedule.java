import java.time.LocalDate;
import java.time.LocalTime;

public class ClassSchedule {
    private int classId;
    private String className;
    private LocalDate classDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int trainerId;

    // Constructor
    public ClassSchedule(int classId, String className, LocalDate classDate, LocalTime startTime, LocalTime endTime, int trainerId) {
        this.classId = classId;
        this.className = className;
        this.classDate = classDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainerId = trainerId;
    }

    // Getters
    public int getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public LocalDate getClassDate() {
        return classDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getTrainerId() {
        return trainerId;
    }

    // Setters
    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassDate(LocalDate classDate) {
        this.classDate = classDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
