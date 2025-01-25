package org.kj6682.greetingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.kj6682.greetingbackend"})
public class GreetingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingBackendApplication.class, args);
    }

}
