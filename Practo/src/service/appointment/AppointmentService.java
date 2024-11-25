package service.appointment;

public interface AppointmentService {

    void bookAppointment(Integer id, String userName, String doctorName, String startTime);

    void cancelAppointment(Integer appointmentId);

    void showBookedAppointment(String name);

}
