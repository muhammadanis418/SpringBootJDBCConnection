package com.example.springbootjdbcconnection.repository;

import com.example.springbootjdbcconnection.entity.Customer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao{

   //@Autowired
   private JdbcTemplate jdbcTemplate;



    @Autowired
    @Qualifier("sbjdbctest")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int countCustomer() {
        return jdbcTemplate.queryForObject("Select Count(*) from customer",Integer.class);
    }

    @Override
    public int saveCustomer(Customer customer) {
        return jdbcTemplate.update("Insert into customer(name,designation) values (?,?)",customer.getName(),customer.getDesignation());
    }

    @Override
    public int updateCustomer(Customer customer) {
        return jdbcTemplate.update("update customer set designation = ? where id = ?",customer.getDesignation(),customer.getId());
    }

    @Override
    public int deleteCustomerById(int id) {
        return jdbcTemplate.update("delete from customer where id = ?",id);
    }

}
