package pl.tzch96.string_calculator;

import java.util.Scanner;
import java.util.ArrayList;

public class Calculator implements ICalculator
{

    private final static String EXIT_COMMAND = "exit";
    private static Calculator instance = null;


    private Calculator() { // empty constructor just to set it to private
    }

    public static Calculator getInstance() { // if there is already a instance of Calculator, return it
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

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
            ArrayList<String> postfixExpression = ShuntingYard.infixToPostfix(tokenizedExpression);

            Double result = PostfixEvaluation.evaluate(postfixExpression);

            System.out.println("Result: " + result);
        }

        sc.close();
    }
}
