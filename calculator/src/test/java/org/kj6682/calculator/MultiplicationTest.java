package org.kj6682.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
public class MultiplicationTest {
    private static final Logger logger = LoggerFactory.getLogger(MultiplicationTest.class);

    private final Multiplication operation = new Multiplication();


    @Test
    public void should_match_sign() {
        logger.info("should_match_sign");
        assertTrue(operation.handles('*'));
        assertFalse(operation.handles('/'));
    }

    @Test
    public void should_correctly_apply_formula() {
        assertEquals(4, operation.apply(2, 2));
        assertEquals(120, operation.apply(10, 12));

    }
}
