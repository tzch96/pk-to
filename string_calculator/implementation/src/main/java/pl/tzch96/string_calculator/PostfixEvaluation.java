package pl.tzch96.string_calculator;

import java.util.ArrayList;
import java.util.Stack;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import java.lang.reflect.*;

public class PostfixEvaluation extends Operators {

    public static Double evaluate(ArrayList<String> postfix) {
        URLClassLoader functionPluginLoader = null;

        try {
            functionPluginLoader = URLClassLoader.newInstance(new URL[]{new URL("file:plugins/SingleArgFunctions.jar")});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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
                    // capitalize first letter to reflect class name spelling
                    String toLoad = token.substring(0, 1).toUpperCase() + token.substring(1);
                    Class<?> functionClass = functionPluginLoader.loadClass(toLoad);
                    Method method = functionClass.getDeclaredMethod("calculate", Double.class);
                    Object instance = functionClass.newInstance();
                    Object functionResult = method.invoke(instance, operands.pop());
                    operands.push((Double) functionResult);
                } catch (ClassNotFoundException e) {
                    System.out.println("Class " + toLoad + " not found in plugins directory");
                } catch (NoSuchMethodException e) {
                    System.out.println("Method calculate not found in " + functionClass + " class");
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return operands.pop();
    }
}