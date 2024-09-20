package com.dennis.prestoTech.repository;
import com.dennis.prestoTech.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByIsDeletedFalse();

    Optional<Customer> findByIdAndIsDeletedFalse(Long id);

    Optional<Customer> findByEmailAddressAndIsDeletedFalse(String emailAddress);

    Optional<Customer> findByIdentificationNumberAndIsDeletedFalse(String identificationNumber);

    Optional<Customer> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);
}

