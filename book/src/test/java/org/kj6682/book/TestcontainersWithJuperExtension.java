package org.kj6682.book;

import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestcontainersWithJuperExtension {

    @Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17.2");

    @Test
    void test1() {
        // test talking to postgres
    }

    @Test
    void test2() {
        // test talking to postgres
    }
}