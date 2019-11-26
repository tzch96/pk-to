package pl.tzch96.string_calculator;

import java.util.ArrayList;
import java.util.Stack;

public class PostfixEvaluation extends Operators {

    private static Double calculate(String operator, Double firstOperand, Double secondOperand) {
        char op = operator.charAt(0);
        Double result = 0.0;

        switch(op) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = secondOperand - firstOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                try {
                    if (firstOperand == 0) {
                        throw new ArithmeticException();
                    }
                    result = secondOperand / firstOperand;
                    break;
                } catch (Exception e) {
                    System.out.println("Division by 0");
                    break;
                }
            default:
                System.out.println("Unsupported operator");
                break;
        }

        return result;
    }

    public static Double evaluate(ArrayList<String> postfix) {
        Stack<Double> operands = new Stack<Double>();

        for (String token: postfix) {
            if (isNumeric(token)) {
                operands.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                Double firstOperand = operands.pop();
                Double secondOperand = operands.pop();

                Double result = calculate(token, firstOperand, secondOperand);

                operands.push(result);
            } else if (isFunction(token)) {
                // TODO
            }
        }

        return operands.pop();
    }
}