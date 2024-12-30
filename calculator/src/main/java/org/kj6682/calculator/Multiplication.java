package org.kj6682.calculator;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
public class Multiplication implements Operation {

    @Override
    public int apply(int lhs, int rhs) {
        return lhs * rhs;
    }

    @Override
    public boolean handles(char op) {
        return '*' == op;
    }

}
