package interfaces;

import classes.Customer;

import java.util.HashMap;

public interface CustomerRepositoryInterface {
    Customer findById(int id);
    void create(Customer customer);
    HashMap<Integer, Customer> findAll();
    void update(Customer customer);
    void delete(Customer customer);

}
