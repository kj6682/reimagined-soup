
package org.kj6682.greeting;

import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(HelloControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    /*
     * @BeforeAll
     * public static void setup() {
     * logger.info("Running test with profile: " +
     * System.getProperty("spring.profiles.active"));
     * assumeTrue("prod".equals(System.getProperty("spring.profiles.active")));
     * }
     */

    @Test
    public void indexShouldReturnDefaultMessage() throws Exception {
        logger.info("HelloControllerTest.indexShouldReturnDefaultMessage");
        var expected = "Greetings from Spring Boot!";

        when(helloService.sayHello()).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(expected)))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }
}