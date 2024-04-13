public class MemberAchievementCount {
    private int memberId;
    private String fullName;
    private int achievementCount;

    public MemberAchievementCount(int memberId, String fullName, int achievementCount) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.achievementCount = achievementCount;
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

    public int getAchievementCount() {
        return achievementCount;
    }

    public void setAchievementCount(int achievementCount) {
        this.achievementCount = achievementCount;
    }
}
