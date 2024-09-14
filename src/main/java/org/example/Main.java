package org.example;

import java.util.Scanner;
import Services.RoomService;
import Services.HotelService;
import Services.ReservationService;
import Services.CustomerService;

public class Main {
    public static void main(String[] args) {

        System.out.println(
                "╔════════════════════════════════════════════════════════════════════════════════════╗\n" +
                        "║                                                                                    ║\n" +
                        "║                                 Hotel Reservation System                           ║\n" +
                        "║                                                                                    ║\n" +
                        "╚════════════════════════════════════════════════════════════════════════════════════╝");

        int input;
        Scanner sc = new Scanner(System.in);

        // Instantiate service classes
        HotelService hotelService = new HotelService();
        RoomService roomService = new RoomService();
        CustomerService customerService = new CustomerService();
        ReservationService reservationService = new ReservationService();

        do {
            System.out.println("\n║\t1: Hotels Management \t\t\t║");
            System.out.println("║\t2: Rooms Management \t\t\t║");
            System.out.println("║\t3: Customers Management \t\t║");
            System.out.println("║\t4: Reservations Management \t\t║");
            System.out.println("║\t5: Exit\t\t\t\t\t\t\t║");
            System.out.print("\t Enter your choice: ");

            input = sc.nextInt();

            switch (input) {
                case 1:
//                    System.out.println("\n═════════════════════ Hotel Management ═════════════════════");
                    hotelService.displayMenu();
                    break;
                case 2:
//                    System.out.println("\n═════════════════════ Rooms Management ═════════════════════");
                    roomService.displayMenu(sc);
                    break;
                case 3:
//                    System.out.println("\n═════════════════════ Customers Management ═════════════════════");
                    customerService.displayMenu();
                    break;
                case 4:
//                    System.out.println("\n═════════════════════ Reservations Management ═════════════════════");
                    reservationService.displayMenu();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("!!!! Invalid choice. Please enter a number between 1 and 5. !!!!");
            }

        } while (input != 5);

        sc.close();
    }
}
