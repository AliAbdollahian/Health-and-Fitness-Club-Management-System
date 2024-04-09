import java.util.Date;

public class Member {
    private int memberId;
    private String fullName;
    private Date dateOfBirth;
    private String fitnessGoal;
    private double weightGoal;
    private int timeGoal;


    public Member(int memberId, String fullName, Date dateOfBirth, String fitnessGoal, double weightGoal, int timeGoal) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.fitnessGoal = fitnessGoal;
        this.weightGoal = weightGoal;
        this.timeGoal = timeGoal;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public int getTimeGoal() {
        return timeGoal;
    }

    public void setTimeGoal(int timeGoal) {
        this.timeGoal = timeGoal;
    }
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", fitnessGoal='" + fitnessGoal + '\'' +
                ", weightGoal=" + weightGoal +
                ", timeGoal=" + timeGoal +
                '}';
    }
}
