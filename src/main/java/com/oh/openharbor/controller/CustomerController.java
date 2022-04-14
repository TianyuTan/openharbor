package com.oh.openharbor.controller;

import com.oh.openharbor.model.Customer;
import com.oh.openharbor.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    Customer c = new Customer("A", "nick", "@gamil");

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping(path = "/create")
    public void create(@RequestBody Customer customer){
        customerService.addNewCustomer(c);
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }
}
