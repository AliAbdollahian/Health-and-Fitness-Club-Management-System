import java.util.Date;

public class Achievement {
    private int achievementId;
    private int memberId;
    private String description;
    private Date dateAchieved;

    // Constructor
    public Achievement(int achievementId, int memberId, String description, Date dateAchieved) {
        this.achievementId = achievementId;
        this.memberId = memberId;
        this.description = description;
        this.dateAchieved = dateAchieved;
    }

    // Getters and Setters
    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAchieved() {
        return dateAchieved;
    }

    public void setDateAchieved(Date dateAchieved) {
        this.dateAchieved = dateAchieved;
    }
}
