package com.oh.openharbor.service;

import com.oh.openharbor.dao.CustomerRepository;
import com.oh.openharbor.model.Customer;
import com.oh.openharbor.model.Token;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final static String USER_NOT_FOUND = "User with email %s not found.";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;
    //    @Autowired
//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer){
        Optional<Customer> OptionalCustomer = customerRepository.findCustomerByEmailAddress(customer.getEmailAddress());
        if (OptionalCustomer.isPresent()) {
            throw new IllegalStateException("Email is taken.");
        }
        else{ customerRepository.save(customer);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository.findByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Customer customer){
        boolean userExisted = customerRepository.findByEmailAddress(customer.getEmailAddress())
                .isPresent();

        if (userExisted){
            throw new IllegalStateException("Email is taken.");
        }

        String encodePassword = bCryptPasswordEncoder.encode(customer.getPassword());

        customer.setPassword(encodePassword);

        customerRepository.save(customer);

        String tk  = UUID.randomUUID().toString();
        Token token = new Token(
                tk,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                customer
        );

        tokenService.saveToken(token);
        return tk;
    }

    public int enableAppUser(String email) {
        return customerRepository.enableAppUser(email);
    }
}
