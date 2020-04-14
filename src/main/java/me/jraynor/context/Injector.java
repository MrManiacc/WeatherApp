package me.jraynor.context;

import javafx.fxml.FXML;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * This class helps with all injections that are related to
 */
public final class Injector {
    /**
     * Static use only...
     */
    private Injector() {
    }

    /**
     * This will find all fields annotated with @FXML and automatically upload them to the context
     */
    @SneakyThrows
    public static void inject(Object object) {
        Class<?> type = object.getClass();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FXML.class)) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(object);
                Context.put(value, name);
                System.out.println("Successfully put field [" + name + "] with type of [" + value.getClass().getSimpleName() + "] into the core context");
            }
        }
    }

}
