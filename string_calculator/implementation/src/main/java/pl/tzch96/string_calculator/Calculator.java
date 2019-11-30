package pl.tzch96.string_calculator;

import java.util.Scanner;
import java.util.ArrayList;

public class Calculator implements ICalculator
{

    private final static String EXIT_COMMAND = "exit";

    private void startText() {
        System.out.println("Enter a mathematical expression or type \"exit\" to quit the program:");
    }

    public void start()
    {
        Scanner sc = new Scanner(System.in);
        String expression;

        while (true) {
            startText();

            expression = sc.nextLine();

            if (expression.equals(EXIT_COMMAND)) {
                break;
            }

            ArrayList<String> tokenizedExpression = ExpressionTokenizer.tokenize(expression);
            System.out.println(tokenizedExpression);
            ArrayList<String> postfixExpression = ShuntingYard.infixToPostfix(tokenizedExpression);
            System.out.println(postfixExpression);

            Double result = PostfixEvaluation.evaluate(postfixExpression);

            System.out.println("Result: " + result);
        }

        sc.close();
    }
}
