package com.example.springbootjdbcconnection.configuration;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean(name = "sbjdbctest")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public Gson gsonConfig() {
        return new Gson();
    }

//    @Bean
//    public RabbitTemplate rabbitTemplateConfig() {
//        return new RabbitTemplate();
//    }

}
