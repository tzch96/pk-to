package pl.tzch96.string_calculator;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Operators {

    private static HashSet<String> supportedOperators = Arrays.asList("+", "-", "*", "/");
    private static Map<String, Integer> operatorPrecedence = new HashMap<String, Integer>() {
        {
            put("*", 3);
            put("/", 3);
            put("+", 2);
            put("-", 2);
            put("(", 1);
            put(")", 0);
        }
    };

    public static boolean isNumeric(String str) {
        try {
            Double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean isOperator(String str) {
        return supportedOperators.contains(str);
    }

    public static boolean isFunction(String str) {
        return str.matches("[A-Za-z]+");
    }

    public static Integer getPrecedence(String op) {
        Integer precedence = operatorPrecedence.get(op);
        return precedence;
    }
}