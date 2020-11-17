package edu.cscc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {

    private CustomerRepositoryImpl customerrepo;

    @BeforeEach
    void setUp() {
        customerrepo = new CustomerRepositoryImpl();
    }

    @Test
    void create() throws DuplicateCustomerException {
        Customer newcustomer = new Customer("Paul", "Stevens", "paul.stevens@gmail.com");
        assertEquals(true, customerrepo.create(newcustomer).getAccountNumber() != null);
    }

    @Test
    void read() throws DuplicateCustomerException {
        Customer newcustomer = new Customer("Paul", "Stevens", "paul.stevens@gmail.com");
        Customer returnCustomer = customerrepo.create(newcustomer);
        assertEquals(returnCustomer, customerrepo.read(returnCustomer.getAccountNumber()));
    }

    @Test
    void findByEmailAddress() throws DuplicateCustomerException {
        Customer newcustomer = new Customer("Paul", "Stevens", "paul.stevens@gmail.com");
        Customer returnCustomer = customerrepo.create(newcustomer);
        assertEquals(returnCustomer, customerrepo.findByEmailAddress(returnCustomer.getEmailAddress()));
    }

    @Test
    void update() throws DuplicateCustomerException, CustomerNotFoundException {
        Customer newcustomer = new Customer("Paul", "Stevens", "paul.stevens@gmail.com");
        Customer returnCustomer = customerrepo.create(newcustomer);
        returnCustomer.setFirstName("Charles");
        customerrepo.update(returnCustomer);
        assertEquals("Charles", customerrepo.findByEmailAddress(returnCustomer.getEmailAddress()).getFirstName());
    }

    @Test
    void delete() throws DuplicateCustomerException, CustomerNotFoundException {
        Customer newcustomer = new Customer("Paul", "Stevens", "paul.stevens@gmail.com");
        Customer returnCustomer = customerrepo.create(newcustomer);
        assertEquals(true, customerrepo.delete(returnCustomer.getAccountNumber()));
    }
}