import java.sql.Time;
import java.util.Date;

public class ClassSchedule {
    private int classId;
    private String className;
    private Date classDate;
    private Time startTime;
    private Time endTime;
    private int trainerId;

    public ClassSchedule(int classId, String className, Date classDate, Time startTime, Time endTime, int trainerId) {
        this.classId = classId;
        this.className = className;
        this.classDate = classDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainerId = trainerId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
