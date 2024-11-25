package model.doctor;

public class Doctor {

    private String name;
    private Speciality speciality;

    public Doctor(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

}
