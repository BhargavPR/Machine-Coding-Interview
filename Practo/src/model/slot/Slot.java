package model.slot;

import java.util.UUID;

public class Slot {

    private String id;
    private String doctorName;
    private Integer startTime;
    private Integer endTime;
    private SlotStatus status;

    public Slot(String doctorName, Integer startTime, Integer endTime, SlotStatus status) {
        this.id = UUID.randomUUID().toString();
        this.doctorName = doctorName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

}
