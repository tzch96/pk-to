package pl.tzch96.string_calculator;

import java.util.ArrayList;
import java.util.Stack;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import java.lang.reflect.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class PostfixEvaluation extends Operators {

    private static Logger rfLogger = LogManager.getLogger("RollingFileLogger");

    public static Double evaluate(ArrayList<String> postfix) {
        URLClassLoader functionPluginLoader = null;

        try {
            functionPluginLoader = URLClassLoader.newInstance(new URL[]{new URL("file:plugins/SingleArgFunctions.jar")});
        } catch (MalformedURLException e) {
            rfLogger.log(Level.getLevel("ERROR"), e);
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
                    String toLoad = token.substring(0, 1).toUpperCase() + token.substring(1).toLowerCase();
                    Class<?> functionClass = functionPluginLoader.loadClass(toLoad);
                    Method method = functionClass.getDeclaredMethod("calculate", Double.class);
                    Object instance = functionClass.newInstance();
                    Object functionResult = method.invoke(instance, operands.pop());
                    operands.push((Double) functionResult);
                } catch (ClassNotFoundException e) {
                    rfLogger.log(Level.getLevel("ERROR"), "Class" + token + " not foud in plugins directory");
                    System.out.println("Class " + token + " not found in plugins directory");
                } catch (NoSuchMethodException e) {
                    rfLogger.log(Level.getLevel("ERROR"), "Method calculate not found in " + token + " class");
                    System.out.println("Method calculate not found in " + token + " class");
                } catch (InstantiationException e) {
                    rfLogger.log(Level.getLevel("ERROR"), e);
                } catch (IllegalAccessException e) {
                    rfLogger.log(Level.getLevel("ERROR"), e);
                } catch (InvocationTargetException e) {
                    rfLogger.log(Level.getLevel("ERROR"), e);
                }
            }
        }

        return operands.pop();
    }
}