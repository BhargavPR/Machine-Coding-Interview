package model.appointment;

public class Appointment {

    private Integer id;
    private String userName;
    private String slotId;

    public Appointment(Integer id, String userName, String slotId) {
        this.id = id;
        this.userName = userName;
        this.slotId = slotId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }
}
