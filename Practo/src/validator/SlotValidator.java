package validator;

import model.slot.Slot;

import java.util.List;

public class SlotValidator {

    public boolean validateSlot(Slot slot) {
        if (slot.getEndTime() < slot.getStartTime()) {
            System.out.println("End time should be greater than start time");
            return false;
        }
        if (slot.getEndTime() - slot.getStartTime() != 60) {
            System.out.println("Each slot should be 60 minutes only");
            return false;
        }
        if ((slot.getStartTime() % 60 != 0) ||
                (slot.getEndTime() % 60 != 0)) {
            System.out.println("Invalid slot time");
            return false;
        }
        return true;
    }

    public boolean validateSlots(List<Slot> slots) {
        if (slots.isEmpty()) {
            return true;
        }
        for (Slot slot : slots) {
            if (!validateSlot(slot)) {
                return false;
            }
        }
        return true;
    }

}
