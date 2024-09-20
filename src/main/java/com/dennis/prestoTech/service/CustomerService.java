package com.dennis.prestoTech.service;


import com.dennis.prestoTech.model.Customer;
import com.dennis.prestoTech.repository.CustomerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Customer with the same email, identification number, or phone number already exists.");
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAllByIsDeletedFalse();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findByIdAndIsDeletedFalse(id);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Optional<Customer> customerOptional = customerRepository.findByIdAndIsDeletedFalse(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(customerDetails.getName());
            customer.setResidentialAddress(customerDetails.getResidentialAddress());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setEmailAddress(customerDetails.getEmailAddress());
            customer.setGender(customerDetails.getGender());
            customer.setDateOfBirth(customerDetails.getDateOfBirth());

            try {
                return customerRepository.save(customer);
            } catch (DataIntegrityViolationException e) {
                throw new RuntimeException("Customer with the same email, identification number, or phone number already exists.");
            }
        } else {
            throw new RuntimeException("Customer not found or has been deleted.");
        }
    }


    public void softDeleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        customer.ifPresent(c -> {
            c.setIsDeleted(true);
            customerRepository.save(c);
        });
    }
}