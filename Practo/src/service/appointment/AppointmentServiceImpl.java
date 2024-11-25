package service.appointment;

import database.Database;
import model.appointment.Appointment;
import model.slot.Slot;
import model.slot.SlotStatus;
import utils.SlotUtils;

import java.util.List;
import java.util.Objects;

public class AppointmentServiceImpl implements AppointmentService {

    private final Database database;

    public AppointmentServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void bookAppointment(Integer id, String userName, String doctorName, String startTime) {
        if (!areSlotsAvailable(doctorName) ||
                isUserHasAppointmentWithOtherDoctor(userName, startTime)) {
            return;
        }

        List<Slot> slots = database.getSlots(doctorName);
        Integer time = SlotUtils.getTime(startTime);
        Slot bookingSlot = findBookingSlot(slots, time);

        if (Objects.isNull(bookingSlot)) {
            System.out.println("No slots available");
            return;
        }

        bookingSlot.setStatus(SlotStatus.BOOKED);
        Appointment appointment = new Appointment(id, userName, bookingSlot.getId());
        database.addAppointment(appointment);

        System.out.println("Booked. booking id " + id);

    }

    @Override
    public void cancelAppointment(Integer appointmentId) {
        Appointment appointment = database.getAppointment(appointmentId);
        if (Objects.isNull(appointment)) {
            System.out.println("No booking found");
            return;
        }

        Slot slot = database.getSlot(appointment.getSlotId());
        slot.setStatus(SlotStatus.AVAILABLE);
        database.deleteAppointment(appointmentId);
        System.out.println("Booking cancelled");

    }

    @Override
    public void showBookedAppointment(String name) {
        List<Appointment> appointments = database.getAppointments();
        if (Objects.isNull(appointments) || appointments.isEmpty()) {
            System.out.println("No appointment found for " + name);
        }

        List<Appointment> userAppointments = appointments.stream()
                .filter(appointment -> appointment.getUserName().equals(name))
                .toList();

        System.out.println("showAppointmentsBooked: " + name);
        for (Appointment appointment : userAppointments) {
            Slot slot = database.getSlot(appointment.getSlotId());
            System.out.println("Booking id " + appointment.getId() + " " + slot.getDoctorName() + " " + SlotUtils.getTimeString(slot.getStartTime()));
        }

    }

    private Slot findBookingSlot(List<Slot> slots, Integer time) {
        return slots.stream()
                .filter(slot -> Objects.equals(slot.getStartTime(), time) &&
                        slot.getStatus().equals(SlotStatus.AVAILABLE))
                .findAny()
                .orElse(null);
    }

    private boolean isUserHasAppointmentWithOtherDoctor(String userName, String startTime) {
        Integer time = SlotUtils.getTime(startTime);
        List<Appointment> appointments = database.getAppointments();
        if (Objects.nonNull(appointments) || !appointments.isEmpty()) {
            List<Appointment> userAppointments = appointments.stream()
                    .filter(appointment -> appointment.getUserName().equals(userName))
                    .toList();
            for (Appointment appointment : userAppointments) {
                Slot slot = database.getSlot(appointment.getSlotId());
                if (slot.getStartTime().equals(time)) {
                    System.out.println("Booking failed. " + userName + " has appointment with " + slot.getDoctorName() + " at " + startTime);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean areSlotsAvailable(String doctorName) {
        List<Slot> slots = database.getSlots(doctorName);
        if (Objects.isNull(slots) || slots.isEmpty()) {
            System.out.println("No slots available");
            return false;
        }
        return true;
    }

}
