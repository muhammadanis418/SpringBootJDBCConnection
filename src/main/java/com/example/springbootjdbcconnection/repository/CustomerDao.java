package com.example.springbootjdbcconnection.repository;

import com.example.springbootjdbcconnection.entity.Customer;

public interface CustomerDao {

    int countCustomer();
    int saveCustomer(Customer customer);

    int updateCustomer(Customer customer);

    int deleteCustomerById(int id);

  // void pushInQueue();
}
