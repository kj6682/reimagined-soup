package org.kj6682.book;

import org.springframework.boot.SpringApplication;

public class BookApplicationTest {
   public static void main(String[] args) {
        SpringApplication.from(BookApplication::main)
          .with(TestContainersConfiguration.class)
          .run(args);
    }
}