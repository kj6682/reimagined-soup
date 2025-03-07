package org.kj6682.book;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Book API",
                version = "1.0",
                description = "API for managing books"
        )
)
public class OpenAPIConfig {
}
