package Services;

import Repositories.CustomerRepository;
import classes.Customer;

import java.util.HashMap;
import java.util.Scanner;

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Scanner scanner;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n═════════════════════ Customer Management ═════════════════════");
            System.out.println("1: Add a new customer");
            System.out.println("2: View all customers");
            System.out.println("3: Update a customer");
            System.out.println("4: Delete a customer");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Enter first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastname = scanner.nextLine();

        Customer customer = new Customer(0, firstname, lastname);
        customerRepository.create(customer);
    }

    private void viewAllCustomers() {
        HashMap<Integer, Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.values().forEach(System.out::println);
        }
    }

    private void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter new first name (leave blank to keep current): ");
        String firstname = scanner.nextLine();
        if (!firstname.isEmpty()) {
            customer.setFirstname(firstname);
        }

        System.out.print("Enter new last name (leave blank to keep current): ");
        String lastname = scanner.nextLine();
        if (!lastname.isEmpty()) {
            customer.setLastname(lastname);
        }

        customerRepository.update(customer);
        System.out.println("Customer updated successfully!");
    }

    private void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        customerRepository.delete(customer);
        System.out.println("Customer deleted successfully!");
    }

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        customerService.displayMenu();
    }
}
