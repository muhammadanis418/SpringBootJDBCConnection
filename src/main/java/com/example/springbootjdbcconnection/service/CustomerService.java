package com.example.springbootjdbcconnection.service;

import com.example.springbootjdbcconnection.entity.Customer;
import com.example.springbootjdbcconnection.repository.CustomerDao;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    private CustomerDao customerDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Gson gson;

    @Value("${spring.rabbitmq.sms.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.sms.queueRoutingKey}")
    private String routingKey;

//     @Autowired
//     private RabbitTemplate rabbitTemplate;


    // @Autowired
    public CustomerService(CustomerDao customerDao) {

        this.customerDao = customerDao;


    }

    public String saveCustomerData(Customer customer) {
        return customerDao.saveCustomer(customer) + " Record is Saved";
    }

    public String countCustomer() {
        return customerDao.countCustomer() + " Records are present";

    }

    public String updateCustomer(Customer customer) {
        //  if(customer.getId()==) {
        return customerDao.updateCustomer(customer) + " Record is updated";
        //  }
//        else {
//            return "Record is not present";
//        }
    }

    public String deleteCustomer(int id) {
        return customerDao.deleteCustomerById(id) + "Record is deleted";
    }

    public void pushInQueue() {

//        try {

        Customer c = new Customer();
        c.setId(3);
        c.setName("Ozair");
        c.setDesignation("Graphic Designer");

        String smsPush = gson.toJson(c);
        System.out.println(smsPush);
        rabbitTemplate.convertAndSend(exchange, routingKey, smsPush);
//        }
//        catch (Exception e){
//            System.out.println("Error occurs while executing above code");
//        }
    }
    // @RabbitListener(queues = "jdbcTest.Queue")

    public String consumeFromQueue(Message message) {

        LocalDateTime now = LocalDateTime.now();
        byte[] payload = message.getBody();
        String jsonString = new String(payload);

        Customer customer = deserializeCustomer(jsonString);
        System.out.println("Message is " + customer + " " + now);
        return "Message Consume Successfully";
    }

    private Customer deserializeCustomer(String jsonString) {
        // Implement your deserialization logic here
        // You can use JSON libraries like Jackson or Gson to deserialize the JSON string into a Customer object
        // For simplicity, let's assume a basic implementation using Gson
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Customer.class);
    }
}

