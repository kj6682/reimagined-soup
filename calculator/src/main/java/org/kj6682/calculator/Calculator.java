package org.kj6682.calculator;

import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
    private final Collection<Operation> operations;

    public Calculator(Collection<Operation> operations) {
        this.operations = operations;
        operations.forEach(op -> System.out.println(op.getClass().getName()));
    }

    public int calculate(int lhs, int rhs, char op){
        return operations.stream()
        .filter((operation)->operation.handles(op))
        .map((operation)->operation.apply(lhs, rhs))
        .peek((result)->System.out.printf("%d %s %d = %s%n", lhs, op, rhs, result))
        .findFirst()
        .orElseThrow( ()-> new IllegalArgumentException("Unknown operator" + op));
        
    }
    
}
