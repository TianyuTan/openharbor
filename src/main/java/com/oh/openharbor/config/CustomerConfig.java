package com.oh.openharbor.config;

import com.oh.openharbor.dao.CustomerRepository;
import com.oh.openharbor.model.Customer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
          Customer c1 = new Customer();

          Customer c2 = new Customer();

          customerRepository.saveAll(List.of(c1,c2));
    };
    }
}
