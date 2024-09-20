package com.dennis.prestoTech.controller;
import com.dennis.prestoTech.model.Customer;
import com.dennis.prestoTech.service.CustomerService;
import com.dennis.prestoTech.service.SmsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SmsNotificationService smsNotificationService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        smsNotificationService.sendSms(savedCustomer.getPhoneNumber(), "Welcome to Presto Technologies!");
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            Customer updatedCustomer = customer.get();
            updatedCustomer.setName(customerDetails.getName());
            updatedCustomer.setResidentialAddress(customerDetails.getResidentialAddress());
            updatedCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            updatedCustomer.setEmailAddress(customerDetails.getEmailAddress());
            updatedCustomer.setGender(customerDetails.getGender());
            updatedCustomer.setDateOfBirth(customerDetails.getDateOfBirth());

            return new ResponseEntity<>(customerService.updateCustomer(id, updatedCustomer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteCustomer(@PathVariable Long id) {
        customerService.softDeleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
