package org.kj6682.greeting;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kj6682.greeting.GreetingApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = GreetingApplication.class)
@ActiveProfiles(value = "prod")
public class ProdActiveProfileUnitTest {

    @Value("${profile.property.value}")
    private String propertyString;

    @Test
    void whenDevIsActive_thenValueShouldBeKeptFromApplicationYaml() {
        System.out.println(propertyString);
        Assertions.assertEquals("This is the catalog application in PROD", propertyString);
    }
}