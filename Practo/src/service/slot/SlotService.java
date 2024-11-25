package service.slot;

import java.util.List;

public interface SlotService {

    void createSlots(String doctorName, List<String> timings);

    void displaySlotsBySpeciality(String speciality);


}
