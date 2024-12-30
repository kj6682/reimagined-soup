package org.kj6682.calculator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    
    @Autowired
    private Calculator calculator;

    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }
    
    @GetMapping("/calculate")
    public int calculate(Calculation calc) {
        return calculator.calculate(calc.lhs(), calc.rhs(), calc.op());
    }

    record Calculation(int lhs, int rhs, char op) {}
}
