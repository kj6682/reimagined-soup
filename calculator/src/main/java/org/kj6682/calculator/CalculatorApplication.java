package org.kj6682.calculator;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }

    @PostConstruct
    public void sayHello() {
        System.out.println("Just to remember the PostConstruct annotation:\n>>>VigilantMemory is up and running!");
    }


    public ApplicationRunner lister(JdbcTemplate jdbc) {
        return (args) -> {
            jdbc.query("select * from pg_catalog.pg_tables", rs -> {
                System.out.printf("Table: %s.%s%n", rs.getString(1), rs.getString(2));
            });
        };
    }    

}// :)
