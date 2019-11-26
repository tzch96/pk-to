package pl.tzch96.string_calculator;

import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExpressionTokenizer {

    private static Pattern mathExpressionPattern = Pattern.compile("-?[0-9.]+|[-+*/()]|[A-Za-z]+");

    public static ArrayList<String> tokenize(String input) {
        List<String> tokenized = new ArrayList<String>();
        Matcher match = mathExpressionPattern.matcher(input);

        while (match.find()) {
            tokenized.add(match.group());
        }

        return tokenized;
    }
}