package org.example;

import java.util.Scanner;

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
        do {
            System.out.println("\n║\t1: Hotels Reservation \t\t\t║");
            System.out.println("║\t2: Rooms Managment \t\t\t\t║");
            System.out.println("║\t3: Reservations Managment \t \t║");
            System.out.println("║\t4: Exit\t\t\t\t\t\t\t║");
            System.out.print("\t Enter your choice: ");

            input = sc.nextInt();
            Scanner scanner = new Scanner(System.in);
            switch (input) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("!!!! Invalid choice. Please enter a number between 1 and 5. !!!!");
            }

        } while (input != 6);

        sc.close();
    }
}
