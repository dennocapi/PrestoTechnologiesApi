package com.dennis.prestoTech.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dennis.prestoTech.model.Customer;
import com.dennis.prestoTech.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
    }

    @Test
    void testUpdateCustomer() {
        when(customerRepository.findByIdAndIsDeletedFalse(1L)).thenReturn(Optional.of(customer));

        Customer customerDetails = new Customer();
        customerDetails.setName("Jane Doe");

        Customer updatedCustomer = customerService.updateCustomer(1L, customerDetails);

        assertEquals("Jane Doe", updatedCustomer.getName());
        verify(customerRepository).save(customer);
    }


}
