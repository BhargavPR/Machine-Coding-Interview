package database;

import model.appointment.Appointment;
import model.doctor.Doctor;
import model.slot.Slot;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

    private final HashMap<String, Doctor> doctorHashMap = new HashMap<>();
    private final HashMap<String, User> userHashMap = new HashMap<>();
    private final HashMap<String, List<Slot>> slotHashMap = new HashMap<>();
    private final HashMap<String, Slot> slotMap = new HashMap<>();
    private final HashMap<Integer, Appointment> appointmentHashMap = new HashMap<>();

    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database() {
    }

    public void addDoctor(Doctor doctor) {
        doctorHashMap.put(doctor.getName(), doctor);
    }

    public Doctor getDoctor(String name) {
        return doctorHashMap.get(name);
    }

    public List<Doctor> getDoctors() {
        return List.copyOf(doctorHashMap.values());
    }

    public void addUser(User user) {
        userHashMap.put(user.getName(), user);
    }

    public User getUser(String name) {
        return userHashMap.get(name);
    }

    public void addSlots(String doctorName, List<Slot> slots) {
        for (Slot slot : slots) {
            slotMap.put(slot.getId(), slot);
        }
        if (!slotHashMap.containsKey(doctorName)) {
            slotHashMap.put(doctorName, new ArrayList<>());
        }
        slotHashMap.get(doctorName).addAll(slots);
    }

    public List<Slot> getSlots(String doctorName) {
        return slotHashMap.get(doctorName);
    }

    public Slot getSlot(String slotId) {
        return slotMap.get(slotId);
    }

    public void addAppointment(Appointment appointment) {
        appointmentHashMap.put(appointment.getId(), appointment);
    }

    public Appointment getAppointment(Integer id) {
        return appointmentHashMap.get(id);
    }

    public void deleteAppointment(Integer id) {
        appointmentHashMap.remove(id);
    }

    public List<Appointment> getAppointments() {
        return List.copyOf(appointmentHashMap.values());
    }

}
