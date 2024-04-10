import java.sql.Date;

class BillingAndPayment {

    private int transactionId;
    private java.util.Date transactionDate;
    private double amount;
    private String status;

    public BillingAndPayment(int transactionId, java.util.Date transactionDate, double amount, String status) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.status= status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public java.util.Date getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}