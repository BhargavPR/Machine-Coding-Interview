import constants.Constants;
import database.Database;
import service.appointment.AppointmentService;
import service.appointment.AppointmentServiceImpl;
import service.slot.SlotService;
import service.slot.SlotServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import validator.SlotValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));

        Database database = Database.getInstance();
        UserService userService = new UserServiceImpl(database);
        SlotService slotService = new SlotServiceImpl(database, new SlotValidator());
        AppointmentService appointmentService = new AppointmentServiceImpl(database);


        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case Constants.REGISTER_DOCTOR -> {
                    userService.registerDoctor(input[1], input[2]);
                }
                case Constants.REGISTER_PATIENT -> {
                    userService.registerUser(input[1]);
                }
                case Constants.MARK_DOCTOR_AVAILABLE -> {
                    List<String> slots = Arrays.asList(input[2].split(","));
                    slotService.createSlots(input[1], slots);
                }
                case Constants.SHOW_SLOTS_BY_SPECIALITY -> {
                    slotService.displaySlotsBySpeciality(input[1]);
                }
                case Constants.BOOK_APPOINTMENT -> {
                    appointmentService.bookAppointment(Integer.valueOf(input[1]), input[2], input[3], input[4]);
                }
                case Constants.CANCEL_BOOKING -> {
                    appointmentService.cancelAppointment(Integer.valueOf(input[1]));
                }
                case Constants.SHOW_BOOKED_APPOINTMENT -> {
                    appointmentService.showBookedAppointment(input[1]);
                }
            }
        }
    }
}