package org.kj6682.calculator;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorControllerTest.class);

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculator;

    @Test
    void successful_calculation() throws Exception {
        when(calculator.calculate(10, 15, '*')).thenReturn(150);

        var request = MockMvcRequestBuilders.get("/calculate")
                .param("lhs", "10")
                .param("rhs", "15")
                .param("op", "*");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("150"));

        verify(calculator, times(1)).calculate(10, 15, '*');
    }
}
