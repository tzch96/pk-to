package pl.tzch96.string_calculator;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import java.nio.file.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class PostfixEvaluation extends Operators {

    private static Logger fileLogger = LogManager.getLogger("PostfixEvaluation");

    public static Double evaluate(ArrayList<String> postfix) {
        URLClassLoader functionPluginLoader = null;

        List<URL> urls = new ArrayList<>();

        String pluginFolderPath;
        // determine plugin folder relative path depending on run location
        // "../plugins" for web app, "plugins" for cli
        if (System.getProperty("user.dir").matches("string_calculator$")) {
            pluginFolderPath = "plugins";
        } else {
            pluginFolderPath = "../plugins";
        }

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(pluginFolderPath), "*.jar")) {
            for (Path path: dirStream) {
                urls.add(path.toUri().toURL());
            }
        } catch (IOException e) {
            fileLogger.log(Level.getLevel("ERROR"), e);
        }

        functionPluginLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]));

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
                    fileLogger.log(Level.getLevel("ERROR"), "Class " + token + " not found in plugins directory");
                } catch (NoSuchMethodException e) {
                    fileLogger.log(Level.getLevel("ERROR"), "Method calculate not found in " + token + " class");
                } catch (InstantiationException e) {
                    fileLogger.log(Level.getLevel("ERROR"), e);
                } catch (IllegalAccessException e) {
                    fileLogger.log(Level.getLevel("ERROR"), e);
                } catch (InvocationTargetException e) {
                    fileLogger.log(Level.getLevel("ERROR"), e);
                }
            }
        }

        return operands.pop();
    }
}