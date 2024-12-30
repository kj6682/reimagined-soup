package org.kj6682.catalog;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = CatalogApplication.class)
@ActiveProfiles(value = "dev")
public class DevActiveProfileUnitTest {

    @Value("${profile.property.value}")
    private String propertyString;

    @Test
    void whenDevIsActive_thenValueShouldBeKeptFromApplicationYaml() {
        System.out.println(propertyString);
        Assertions.assertEquals("This is the catalog application", propertyString);
    }
}