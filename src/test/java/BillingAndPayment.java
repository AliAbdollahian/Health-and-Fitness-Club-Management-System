import java.util.Date;

public class BillingAndPayment {
    private int transactionId;
    private int memberId;
    private Date transactionDate;
    private double amount;
    private String status;

    public BillingAndPayment(int transactionId, int memberId, Date transactionDate, double amount, String status) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.status = status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
