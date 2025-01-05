package org.kj6682.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


}// :)
