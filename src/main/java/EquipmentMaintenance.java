import java.util.Date;

public class EquipmentMaintenance {
    private int maintenanceId;
    private int equipmentId;
    private Date maintenanceDate;
    private String maintenanceType;

    public EquipmentMaintenance(int maintenanceId, int equipmentId, Date maintenanceDate, String maintenanceType) {
        this.maintenanceId = maintenanceId;
        this.equipmentId = equipmentId;
        this.maintenanceDate = maintenanceDate;
        this.maintenanceType = maintenanceType;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }
}
