package Repositories;

import classes.Customer;
import interfaces.CustomerRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.HashMap;

public class CustomerRepository implements CustomerRepositoryInterface {

    private Connection connection;

    public CustomerRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Customer findById(int id) {
        try {
            String query = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Customer customer) {
        try {
            String query = "INSERT INTO clients (firstname, lastname) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, customer.getFirstname());
            stmt.setString(2, customer.getLastname());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Customer> findAll() {
        HashMap<Integer, Customer> customers = new HashMap<>();
        try {
            String query = "SELECT * FROM clients";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                );
                customers.put(customer.getId(), customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void update(Customer customer) {
        try {
            String query = "UPDATE clients SET firstname = ?, lastname = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, customer.getFirstname());
            stmt.setString(2, customer.getLastname());
            stmt.setInt(3, customer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            String query = "DELETE FROM clients WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}