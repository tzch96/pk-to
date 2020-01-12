package pl.tzch96.string_calculator.webapp.calc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.tzch96.string_calculator.ICalculator;
import pl.tzch96.string_calculator.Calculator;

@Controller
public class CalcController {

    private ICalculator calc = Calculator.getInstance();

    @GetMapping("/calc")
    public String calcForm(Model model) {
        model.addAttribute("expression", new Expression());
        return "expression";
    }

    @PostMapping("/calc")
    public String expressionSubmit(@ModelAttribute Expression expression) {
        expression.setResult(calc.calculate(expression.getInput()));
        return "result";
    }
}