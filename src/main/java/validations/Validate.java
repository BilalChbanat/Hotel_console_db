package validations;

import classes.Reservation;
import java.time.LocalDate;
import java.util.List;

public class Validate {

    public static boolean interval(List<Reservation> existingReservations, LocalDate checkIn, LocalDate checkOut) {
        if (!isValidDates(checkIn, checkOut)) {
            return false;
        }

        for (Reservation reservation : existingReservations) {
            LocalDate reservedCheckIn = reservation.getCheck_in_date();
            LocalDate reservedCheckOut = reservation.getCheck_out_date();

            if (datesOverlap(reservedCheckIn, reservedCheckOut, checkIn, checkOut)) {
                System.out.println("Room is already reserved between " + reservedCheckIn + " and " + reservedCheckOut);
                return false;
            }
        }
        return true;
    }

    private static boolean datesOverlap(LocalDate existingCheckIn, LocalDate existingCheckOut, LocalDate newCheckIn, LocalDate newCheckOut) {
        return (newCheckIn.isBefore(existingCheckOut) && newCheckOut.isAfter(existingCheckIn)) ||
                (newCheckIn.equals(existingCheckIn) || newCheckOut.equals(existingCheckOut)) ||
                (newCheckIn.isBefore(existingCheckIn) && newCheckOut.isAfter(existingCheckOut));
    }

    private static boolean isValidDates(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            System.out.println("Dates cannot be null.");
            return false;
        }

        if (checkIn.isBefore(LocalDate.now())) {
            System.out.println("Check-in date cannot be in the past.");
            return false;
        }

        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            System.out.println("Check-out date must be after the check-in date.");
            return false;
        }

        return true;
    }
}
