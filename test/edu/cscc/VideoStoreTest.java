package edu.cscc;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VideoStoreTest {

    @Test
    void getInstance() {
        assertEquals(false, VideoStore.getInstance() == null);
    }

    @Test
    void createCustomer() throws DuplicateCustomerException {
        assertEquals(false, VideoStore.getInstance().createCustomer("Paul", "Azizeh", "azizep1@nationwide.com") == null);
    }

    @Test
    void findCustomerByEmailAddress() throws CustomerNotFoundException, DuplicateCustomerException {
        VideoStore.getInstance().createCustomer("Mike", "Stevens", "stevensm@gmail.com");
        assertEquals(false, VideoStore.getInstance().findCustomerByEmailAddress("stevensm@gmail.com") == null);
    }

    @Test
    void getCustomer() throws CustomerNotFoundException, DuplicateCustomerException {
            UUID myUuid = VideoStore.getInstance().createCustomer("Sam", "Michaels", "samm@apple.com");
            assertEquals(false, VideoStore.getInstance().getCustomer(myUuid.toString()) == null);
        }

    @Test
    void updateCustomer() throws DuplicateCustomerException, CustomerNotFoundException {
        UUID myUuid = VideoStore.getInstance().createCustomer("Steve", "Martin", "martins@nationwide.com");
        VideoStore.getInstance().updateCustomer(myUuid.toString(), "Steve", "Martin", "martins@nationwide.com");
        assertEquals("Steve", VideoStore.getInstance().getCustomer(myUuid.toString()).getFirstName());
    }

    @Test
    void deleteCustomer() throws DuplicateCustomerException, CustomerNotFoundException {
            UUID myUuid = VideoStore.getInstance().createCustomer("Gary", "Carr", "carrg@hotmail.com");
            assertEquals(true, VideoStore.getInstance().deleteCustomer(myUuid.toString()));
    }
}