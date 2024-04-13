import java.util.Date;

public class Feedback {
    private int feedbackId;
    private int memberId;
    private int classId;
    private Integer trainerId; // Can be null if feedback is not specifically for a trainer
    private int rating;
    private String comments;
    private Date feedbackDate;

    public Feedback(int feedbackId, int memberId, int classId, Integer trainerId, int rating, String comments, Date feedbackDate) {
        this.feedbackId = feedbackId;
        this.memberId = memberId;
        this.classId = classId;
        this.trainerId = trainerId;
        this.rating = rating;
        this.comments = comments;
        this.feedbackDate = feedbackDate;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", memberId=" + memberId +
                ", classId=" + classId +
                ", trainerId=" + trainerId +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                ", feedbackDate=" + feedbackDate +
                '}';
    }
}
