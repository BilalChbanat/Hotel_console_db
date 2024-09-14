package Services;

import Repositories.CustomerRepository;
import Repositories.RoomRepository;
import classes.Reservation;
import classes.Room;
import classes.Customer;
import interfaces.ReservationRepositoryInterface;
import Repositories.ReservationRepository;
import validations.Validate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private final ReservationRepositoryInterface reservationRepository;
    private final Scanner scanner;

    public ReservationService() {
        this.reservationRepository = new ReservationRepository();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n═════════════════════ Reservation Management ═════════════════════");
            System.out.println("\n║\t1: Add a new reservation\t\t\t║");
            System.out.println("║\t2: View all reservations\t\t\t║");
            System.out.println("║\t3: Update a reservation\t\t\t\t║");
            System.out.println("║\t4: Delete a reservation\t\t\t\t║");
            System.out.println("║\t5: Exit\t\t\t\t\t\t\t\t║");
            System.out.print("\t Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addReservation();
                    break;
                case 2:
                    viewAllReservations();
                    break;
                case 3:
                    updateReservation();
                    break;
                case 4:
                    deleteReservation();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addReservation() {
        try {
            System.out.print("Enter room ID: ");
            int roomId = scanner.nextInt();
            System.out.print("Enter client ID: ");
            int clientId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            String checkInDate = scanner.nextLine();
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            String checkOutDate = scanner.nextLine();

            LocalDate checkIn = LocalDate.parse(checkInDate);
            LocalDate checkOut = LocalDate.parse(checkOutDate);

            Room room = new RoomRepository().findById(roomId); // Get the room
            Customer customer = new CustomerRepository().findById(clientId); // Get the client

            List<Reservation> existingReservations = reservationRepository.findByRoom(roomId);

            if (Validate.interval(existingReservations, checkIn, checkOut)) {
                Reservation reservation = new Reservation(0, room, customer, checkIn, checkOut);
                reservationRepository.create(reservation);
                System.out.println("Reservation added successfully.");
            } else {
                System.out.println("The room is not available for the selected dates.");
            }
        } catch (Exception e) {
            System.out.println("Failed to add reservation: " + e.getMessage());
        }
    }

    private void viewAllReservations() {
        HashMap<Integer, Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations.values()) {
                System.out.println(reservation);
            }
        }
    }

    private void updateReservation() {
        try {
            System.out.print("Enter reservation ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Reservation reservation = reservationRepository.findById(id);
            if (reservation == null) {
                System.out.println("Reservation not found.");
                return;
            }

            System.out.println("Current reservation details: " + reservation);
            System.out.print("Enter new room ID (leave blank to keep current): ");
            String roomIdInput = scanner.nextLine();
            if (!roomIdInput.isEmpty()) {
                int roomId = Integer.parseInt(roomIdInput);
                Room room = new RoomRepository().findById(roomId);
                reservation.setRoom(room);
            }

            System.out.print("Enter new client ID (leave blank to keep current): ");
            String clientIdInput = scanner.nextLine();
            if (!clientIdInput.isEmpty()) {
                int clientId = Integer.parseInt(clientIdInput);
                Customer customer = new CustomerRepository().findById(clientId);
                reservation.setClient(customer);
            }

            System.out.print("Enter new check-in date (leave blank to keep current, YYYY-MM-DD): ");
            String checkInDate = scanner.nextLine();
            if (!checkInDate.isEmpty()) {
                reservation.setCheck_in_date(LocalDate.parse(checkInDate));
            }

            System.out.print("Enter new check-out date (leave blank to keep current, YYYY-MM-DD): ");
            String checkOutDate = scanner.nextLine();
            if (!checkOutDate.isEmpty()) {
                reservation.setCheck_out_date(LocalDate.parse(checkOutDate));
            }

            // Validate the updated dates and availability
            List<Reservation> existingReservations = reservationRepository.findByRoom(reservation.getRoom().getId());
            if (Validate.interval(existingReservations, reservation.getCheck_in_date(), reservation.getCheck_out_date())) {
                reservationRepository.update(reservation);
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("The room is not available for the selected dates.");
            }
        } catch (Exception e) {
            System.out.println("Failed to update reservation: " + e.getMessage());
        }
    }

    private void deleteReservation() {
        try {
            System.out.print("Enter reservation ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Reservation reservation = reservationRepository.findById(id);
            if (reservation == null) {
                System.out.println("Reservation not found.");
                return;
            }

            reservationRepository.delete(reservation);
            System.out.println("Reservation deleted successfully.");
        } catch (Exception e) {
            System.out.println("Failed to delete reservation: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ReservationService reservationService = new ReservationService();
        reservationService.displayMenu();
    }
}
