package model.audit;

public class RideAuditRecord {

    public static final String TYPE_OFFERED = "OFFERED";
    public static final String TYPE_TAKEN = "TAKEN";

    private String rideId;
    private String riderName;
    private String type;

    public RideAuditRecord(String rideId, String riderName, String type) {
        this.rideId = rideId;
        this.riderName = riderName;
        this.type = type;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
