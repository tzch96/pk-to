package pl.tzch96.string_calculator;

import java.util.Scanner;
import java.util.ArrayList;

public class Calculator implements ICalculator
{
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

            if (expression.equals("exit")) {
                break;
            }

            ArrayList<String> tokenized = ExpressionTokenizer.tokenize(expression);
            ArrayList<String> postfixed = ShuntingYard.infixToPostfix(tokenized);

            Double result = PostfixEvaluation.evaluate(postfixed);

            System.out.println("Result: " + result);
        }

        sc.close();
    }
}
