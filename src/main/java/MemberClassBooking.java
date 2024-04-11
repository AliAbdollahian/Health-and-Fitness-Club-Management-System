import java.util.Date;

public class MemberClassBooking {
    private int bookingId;
    private int memberId;
    private int classId;
    private String bookingStatus;
    private Date bookingDate;

    // Constructor
    public MemberClassBooking(int bookingId, int memberId, int classId, String bookingStatus, Date bookingDate) {
        this.bookingId = bookingId;
        this.memberId = memberId;
        this.classId = classId;
        this.bookingStatus = bookingStatus;
        this.bookingDate = bookingDate;
    }

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
