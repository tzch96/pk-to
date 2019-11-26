package pl.tzch96.string_calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.lang.reflect.*;

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
                try {
                    Method function = Functions.class.getMethod(token, Double.class);
                    Double result = (Double) function.invoke(null, operands.pop());
                    operands.push(result);
                } catch (NoSuchMethodException e) {
                    System.out.println("Undefined function " + token);
                } catch (IllegalAccessException e) {
                    System.out.println("Illegal access to function " + token);
                } catch (InvocationTargetException e) {
                    System.out.println("Invocation target exception");
                }
            }
        }

        return operands.pop();
    }
}