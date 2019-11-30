package pl.tzch96.string_calculator;

import java.util.ArrayList;
import java.util.Stack;

public class PostfixEvaluation extends Operators {

    public static Double evaluate(ArrayList<String> postfix) {
        Stack<Double> operands = new Stack<Double>();

        for (String token: postfix) {
            if (isNumeric(token)) {
                operands.push(Double.parseDouble(token));
            } else if (isTwoArgumentOperator(token)) {
                Double rightOperand = operands.pop();
                Double leftOperand = operands.pop();

                Double result = calculateTwoArg(token, leftOperand, rightOperand);

                operands.push(result);
            } else if (isFunction(token)) {
                // TODO parse function loaded from jar
            }
        }

        return operands.pop();
    }
}