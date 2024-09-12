package Services;

import Repositories.HotelRepository;
import Repositories.RoomRepository;
import classes.Hotel;
import classes.Room;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final Scanner scanner;

    public RoomService() {
        this.roomRepository = new RoomRepository();
        this.hotelRepository = new HotelRepository();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n═════════════════════ Room Management ═════════════════════");
            System.out.println("\n║\t1: Add a new room\t\t\t\t║");
            System.out.println("║\t2: View all rooms\t\t\t\t║");
            System.out.println("║\t3: View all rooms by hotel\t\t║");
            System.out.println("║\t4: Update a room\t\t\t\t║");
            System.out.println("║\t5: Delete a room\t\t\t\t║");
            System.out.println("║\t6: Exit\t\t\t\t\t\t\t║");
            System.out.print("\t Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    viewAllRooms();
                    break;
                case 3:
                    viewRoomsByHotel();
                    break;
                case 4:
                    updateRoom();
                    break;
                case 5:
                    deleteRoom();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addRoom() {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine();

        Hotel hotel = hotelRepository.findById(hotelId);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        System.out.print("Enter room price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter room capacity: ");
        int capacity = scanner.nextInt();
        System.out.print("Is the room available (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline

        Room room = new Room(price, capacity, isAvailable, hotel);
        roomRepository.create(room);
        System.out.println("Room added successfully with ID: " + room.getId());
    }

    private void viewAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (Room room : rooms) {
                System.out.println(room.toString());
            }
        }
    }


    private void viewRoomsByHotel() {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine();

        Hotel hotel = hotelRepository.findById(hotelId);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        List<Room> rooms = roomRepository.findByHotel(hotel);
        if (rooms.isEmpty()) {
            System.out.println("No rooms found for this hotel.");
        } else {
            for (Room room : rooms) {
                System.out.println(room.toString());
            }
        }
    }

    private void updateRoom() {
        try {
            System.out.print("Enter room ID to update: ");
            int roomId = scanner.nextInt();
            scanner.nextLine();

            Room room = roomRepository.findById(roomId);
            if (room == null) {
                System.out.println("Room not found.");
                return;
            }

            System.out.println("Current room details: " + room);

            System.out.print("Enter new price (leave blank to keep current): ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                room.setPrice(Double.parseDouble(input));
            }

            System.out.print("Enter new capacity (leave blank to keep current): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                room.setCapacity(Integer.parseInt(input));
            }

            System.out.print("Enter new availability (true/false, leave blank to keep current): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                room.setAvailable(Boolean.parseBoolean(input));
            }

            roomRepository.update(room);
            System.out.println("Room updated successfully!");
            System.out.println("Updated room details: " + room);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Failed to update room: " + e.getMessage());
        }
    }

    private void deleteRoom() {
        System.out.print("Enter room ID to delete: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        Room room = roomRepository.findById(roomId);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        roomRepository.delete(room);
        System.out.println("Room deleted successfully!");
    }

    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        roomService.displayMenu();
    }
}
