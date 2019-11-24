package pl.tzch96.string_calculator;

import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExpressionTokenizer {

    private static Pattern pattern = Pattern.compile("-?[0-9.]+|[-+*/()]|[A-Za-z]+");

    public static ArrayList<String> tokenize(String input) {
        ArrayList<String> tokenized = new ArrayList<String>();
        Matcher match = pattern.matcher(input);

        while (match.find()) {
            tokenized.add(match.group());
        }

        return tokenized;
    }
}