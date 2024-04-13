public class HealthMetrics {
    private int metricId;
    private int memberId;
    private double weight;
    private double height;

    public HealthMetrics(int metricId, int memberId, double weight, double height) {
        this.metricId = metricId;
        this.memberId = memberId;
        this.weight = weight;
        this.height = height;
    }

    // Method to calculate BMI
    public double calculateBMI() {
        // Formula: BMI = weight (kg) / (height (m) * height (m))
        double heightInMeters = height / 100; // Convert height from cm to meters
        return weight / (heightInMeters * heightInMeters);
    }

    // Getters and setters
    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metricId) {
        this.metricId = metricId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

