package edu.cscc;

import java.util.HashMap;
import java.util.UUID;

public class CustomerRepositoryImpl implements CustomerRepository {
    HashMap<UUID, Customer> customerInfo = new HashMap<UUID, Customer>();

    @Override
    public Customer create(Customer customer) throws DuplicateCustomerException {
        if(findByEmailAddress(customer.getEmailAddress()) != null) {
            throw new DuplicateCustomerException("Customer already has that email address.");
        }
        UUID uuid = UUID.randomUUID();
        customer.setAccountNumber(uuid);
        customerInfo.put(uuid, customer);

        return customer;
    }

    @Override
    public Customer read(UUID accountNumber) {
        return customerInfo.get(accountNumber);
    }

    @Override
    public Customer findByEmailAddress(String emailAddress) {
        for (Customer thisCustomer : customerInfo.values())
        {
            if(thisCustomer.getEmailAddress().equals(emailAddress)) {
                return thisCustomer;
            }
        }
        return null;
    }

    @Override
    public void update(Customer customer) throws CustomerNotFoundException, DuplicateCustomerException {
        if(customerInfo.get(customer.getAccountNumber()) == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }
        Customer customerEmail = findByEmailAddress(customer.getEmailAddress());
        if(customerEmail != null && !customerEmail.getAccountNumber().equals(customer.getAccountNumber())) {
            throw new DuplicateCustomerException("Customer already has that email address.");
        }
        customerInfo.put(customer.getAccountNumber(), customer);
    }

    @Override
    public boolean delete(UUID accountNumber) throws CustomerNotFoundException {
        if(customerInfo.get(accountNumber) == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }

        return (customerInfo.remove(accountNumber) != null);
    }

    @Override
    public int count() {
        return customerInfo.size();
    }
}

