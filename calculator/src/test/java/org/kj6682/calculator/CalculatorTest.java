package org.kj6682.calculator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
 
public class CalculatorTest {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorTest.class);

    private Calculator calculator;
    private Operation mockOperation;

    @BeforeEach
    public void setup() {
        mockOperation = Mockito.mock(Operation.class);
        calculator = new Calculator(Collections.singletonList(mockOperation));
    }

    @Test
    public void given_no_suitable_operation__throw_exception() {
        when(mockOperation.handles(anyChar())).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(2, 2, '*'));

    }

    @Test
    public void given_suitable_operation__then_call_apply_method() {
        when(mockOperation.handles(anyChar())).thenReturn(true);
        when(mockOperation.apply(2, 2)).thenReturn(4);

        calculator.calculate(2, 2, '*');

        verify(mockOperation, times(1)).apply(2, 2);

    }

}
