package com.oh.openharbor.dao;

import com.oh.openharbor.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmailAddress(String email);
    Optional<Customer> findByEmailAddress(String email);
    @Transactional
    @Modifying
    @Query("UPDATE Customer a " +
            "SET a.enabled = TRUE WHERE a.emailAddress = ?1")
    int enableAppUser(String email);
}
