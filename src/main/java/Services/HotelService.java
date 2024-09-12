package Services;

import Repositories.HotelRepository;
import classes.Hotel;

import java.util.HashMap;
import java.util.Scanner;

public class HotelService {
    private final HotelRepository hotelRepository;
    private final Scanner scanner;

    public HotelService() {
        this.hotelRepository = new HotelRepository();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n═════════════════════ Hotel Management ═════════════════════");
            System.out.println("\n║\t1: Add a new hotel\t\t\t\t║");
            System.out.println("║\t2: View all hotels\t\t\t\t║");
            System.out.println("║\t3: Update a hotel\t\t\t\t║");
            System.out.println("║\t4: Delete a hotel\t\t\t\t║");
            System.out.println("║\t5: Exit\t\t\t\t\t\t\t║");
            System.out.print("\t Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addHotel();
                    break;
                case 2:
                    viewAllHotels();
                    break;
                case 3:
                    updateHotel();
                    break;
                case 4:
                    deleteHotel();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addHotel() {
        System.out.print("Enter hotel name: ");
        String name = scanner.nextLine();
        System.out.print("Enter hotel address: ");
        String address = scanner.nextLine();
        System.out.print("Enter hotel phone: ");
        String phone = scanner.nextLine();

        Hotel hotel = new Hotel(name, address, phone);
        hotelRepository.create(hotel);
        System.out.println("Hotel added successfully with ID: " + hotel.getId());
    }

    private void viewAllHotels() {
        HashMap<Integer, Hotel> hotels = hotelRepository.findAll();
        if (hotels.isEmpty()) {
            System.out.println("No hotels found.");
        } else {
            for (Hotel hotel : hotels.values()) {
                System.out.println(hotel.toString());
            }
        }
    }

    private void updateHotel() {
        System.out.print("Enter hotel ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Hotel hotel = hotelRepository.findById(id);

        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        System.out.println("Current hotel details: " + hotel.toString());

        System.out.print("Enter new name : ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            hotel.setName(name);
        }

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            hotel.setAddress(address);
        }

        System.out.print("Enter new phone : ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            hotel.setPhone(phone);
        }

        try {
            hotelRepository.update(hotel);
            System.out.println("Hotel updated successfully!");
            System.out.println("Updated hotel details: " + hotel);
        } catch (Exception e) {
            System.out.println("Failed to update hotel: " + e.getMessage());
        }
    }

    private void deleteHotel() {
        System.out.print("Enter hotel ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Hotel hotel = hotelRepository.findById(id);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        hotelRepository.delete(hotel);
        System.out.println("Hotel deleted successfully!");
    }

    public static void main(String[] args) {
        HotelService hotelService = new HotelService();
        hotelService.displayMenu();
    }
}