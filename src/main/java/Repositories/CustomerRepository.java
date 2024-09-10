package Repositories;

import classes.Customer;
import interfaces.CustomerRepositoryInterface;
import org.example.DatabaseConnection;

import java.sql.Connection;
import java.util.HashMap;

public class CustomerRepository implements CustomerRepositoryInterface {

    private Connection connection;

    public CustomerRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public HashMap<Integer, Customer> findAll() {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
