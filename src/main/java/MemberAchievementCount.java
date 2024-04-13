/**
 * This class represents a member's achievement count in a gym or fitness club system.
 * It holds the member's ID, full name, and the count of their achievements.
 */
public class MemberAchievementCount {
    private int memberId;
    private String fullName;
    private int achievementCount;

    /**
     * Constructs a new MemberAchievementCount object with the given member ID, full name, and achievement count.
     *
     * @param memberId The ID of the member.
     * @param fullName The full name of the member.
     * @param achievementCount The count of the member's achievements.
     */
    public MemberAchievementCount(int memberId, String fullName, int achievementCount) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.achievementCount = achievementCount;
    }

    /**
     * Returns the member's ID.
     *
     * @return The member's ID.
     */
    public int getMemberId() {
        return memberId;
    }

    /**
     * Sets the member's ID.
     *
     * @param memberId The new ID of the member.
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    /**
     * Returns the member's full name.
     *
     * @return The member's full name.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the member's full name.
     *
     * @param fullName The new full name of the member.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the count of the member's achievements.
     *
     * @return The count of the member's achievements.
     */
    public int getAchievementCount() {
        return achievementCount;
    }

    /**
     * Sets the count of the member's achievements.
     *
     * @param achievementCount The new count of the member's achievements.
     */
    public void setAchievementCount(int achievementCount) {
        this.achievementCount = achievementCount;
    }
}