package com.example.springbootjdbcconnection.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
@RabbitListener
public class CustomerHandlerConsumer {


    @RabbitHandler
    @RabbitListener(queues = "jdbcTest.Queue")
    public void recieveMessageFromQueue(String smsConsumer) {

            LocalDateTime now = LocalDateTime.now();
            System.out.println("Message is {}" + smsConsumer + now);

    }


    }

