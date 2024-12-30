package org.kj6682.calculator;

public interface Operation {
    int apply(int lhs, int rhs);

    boolean handles(char op);
}