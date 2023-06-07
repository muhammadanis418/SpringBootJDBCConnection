package com.example.springbootjdbcconnection.controller;

import com.example.springbootjdbcconnection.entity.Customer;
import com.example.springbootjdbcconnection.service.CustomerService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private final CustomerService customerService;

//    @Autowired
//    private CustomerHandlerConsumer customerHandlerConsumer;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/saveCustomerData")
    public String saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomerData(customer);
    }

    @GetMapping("/countCustomerData")
    public String countCustomer() {
        return customerService.countCustomer();
    }

    @GetMapping("/updateCustomerData/{id}")
    public String updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomerById(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }


    @GetMapping("/customer")
    private String pushInQueue() {
        customerService.pushInQueue();
        return "Message Sent Successfully";
    }



}
