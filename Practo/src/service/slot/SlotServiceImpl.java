package service.slot;

import database.Database;
import model.doctor.Doctor;
import model.doctor.Speciality;
import model.slot.Slot;
import model.slot.SlotStatus;
import utils.SlotUtils;
import validator.SlotValidator;

import java.util.ArrayList;
import java.util.List;

public class SlotServiceImpl implements SlotService {

    private final Database database;
    private final SlotValidator slotValidator;

    public SlotServiceImpl(Database database, SlotValidator slotValidator) {
        this.database = database;
        this.slotValidator = slotValidator;
    }

    @Override
    public void createSlots(String doctorName, List<String> timings) {
        if (timings.isEmpty()) {
            return;
        }

        List<Slot> slots = new ArrayList<>();
        for (String timing : timings) {
            Integer startTime = SlotUtils.getStartTime(timing);
            Integer endTime = SlotUtils.getEndTime(timing);
            slots.add(new Slot(doctorName, startTime, endTime, SlotStatus.AVAILABLE));
        }

        if (!slotValidator.validateSlots(slots)) {
            return;
        }

        database.addSlots(doctorName, slots);
        System.out.println("Done doc!");
    }

    @Override
    public void displaySlotsBySpeciality(String speciality) {
        System.out.println("showAvailByspeciality: " + speciality);
        List<Doctor> doctors = database.getDoctors().stream()
                .filter(doctor -> doctor.getSpeciality().equals(Speciality.valueOf(speciality.toUpperCase().replace(" ", "_"))))
                .toList();
        if (doctors.isEmpty()) {
            return;
        }

        for (Doctor doctor : doctors) {
            List<Slot> slots = database.getSlots(doctor.getName());
            for (Slot slot : slots) {
                if (slot.getStatus().equals(SlotStatus.AVAILABLE)) {
                    System.out.println("Dr. " + doctor.getName()
                            + ": " + SlotUtils.getTimeString(slot.getStartTime())
                            + " - " + SlotUtils.getTimeString(slot.getEndTime()));
                }
            }
        }

    }


}
