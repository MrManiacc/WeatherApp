package me.jraynor.context;

import com.google.common.eventbus.Subscribe;
import javafx.scene.Node;
import javafx.scene.control.Control;
import me.jraynor.annotations.Injectable;
import me.jraynor.events.InitializationEvent;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

/**
 * This class will take the main class, and do reflections to find all objects that have {@link me.jraynor.annotations.Injectable} and map them
 */
public class Reflection {
    private Reflections reflections;

    public Reflection(Package aPackage) {
        this.reflections = new Reflections(aPackage.getName());
        Bus.register(this);
        Context.put(this, "reflection");
    }

    @Subscribe
    public void onInitialization(InitializationEvent e) {
        Set<Class<?>> injectables = reflections.getTypesAnnotatedWith(Injectable.class);
        for (Class<?> type : injectables) {
            try {
                Object obj = type.getConstructor().newInstance();
                Field[] fields = type.getDeclaredFields();
                Bus.register(obj);
                boolean allInit = true;
                int count = 0;
                for (Field field : fields) {
                    String fieldName = field.getName();
                    Class<?> fieldType = field.getType();
                    if (Control.class.isAssignableFrom(fieldType) || Node.class.isAssignableFrom(fieldType)) {
                        Optional<?> contextValue = Context.get(fieldType, fieldName);
                        if (contextValue.isPresent()) {
                            field.setAccessible(true);
                            field.set(obj, contextValue.get());
                            count++;
                        } else {
                            allInit = false;
                            System.err.println("Failed to initialized field with name [" + fieldName + "] and type [" + fieldType.getSimpleName() + "] inside class [" + type.getSimpleName() + "], because it wasn't found in the context!");
                        }
                    }
                }
                if (allInit) {
                    System.out.println("Successfully initialized class [" + type.getSimpleName() + "] with [" + count + "] fields!");
                    try {
                        Method init = type.getMethod("init");
                        if (init != null)
                            init.invoke(obj);
                    } catch (NoSuchMethodException event) {
                        /*Ignored, init isn't required*/
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
                System.err.println("Failed to initialize type [" + type.getSimpleName() + "], it must have a zero argument constructor!");
            }
        }
    }


    /**
     * This will find all sub classes and inject them if they have the {@link me.jraynor.annotations.Injectable} annotation.
     */
    public static void walkInjections(Object object) {
        Class<?> type = object.getClass();

    }
}
