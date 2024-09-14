package validations;

import java.time.LocalDate;

public class Tarifs {

    private static final double SUMMER_ADDITIONAL_PERCENTAGE = 0.20;
    private static final double WINTER_DISCOUNT_PERCENTAGE = 0.10;

    public static double adjustPrice(double originalPrice, LocalDate checkInDate, LocalDate checkOutDate) {
        double totalAdjustedPrice = originalPrice;

        if (isInSummer(checkInDate, checkOutDate)) {
            totalAdjustedPrice += originalPrice * SUMMER_ADDITIONAL_PERCENTAGE;
        } else if (isInWinter(checkInDate, checkOutDate)) {
            totalAdjustedPrice -= originalPrice * WINTER_DISCOUNT_PERCENTAGE;
        }

        return totalAdjustedPrice;
    }

    private static boolean isInSummer(LocalDate checkInDate, LocalDate checkOutDate) {
        LocalDate summerStart = LocalDate.of(checkInDate.getYear(), 6, 1);
        LocalDate summerEnd = LocalDate.of(checkInDate.getYear(), 8, 31);

        return (checkInDate.isBefore(summerEnd) && checkOutDate.isAfter(summerStart));
    }

    private static boolean isInWinter(LocalDate checkInDate, LocalDate checkOutDate) {
        LocalDate winterStart = LocalDate.of(checkInDate.getYear(), 12, 1);
        LocalDate winterEnd = LocalDate.of(checkInDate.getYear() + 1, 2, 28); // Assuming non-leap year

        return (checkInDate.isBefore(winterEnd) && checkOutDate.isAfter(winterStart));
    }
}
