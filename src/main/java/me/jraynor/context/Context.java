package me.jraynor.context;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Stores an arbitrary element with the the specified name
 */
public final class Context {
    private static final Map<Class<?>, HashMap<String, Object>> map = Maps.newConcurrentMap();

    /**
     * Only for static access
     */
    private Context() {
    }

    /**
     * @return Returns the requested type with the requested name, or an optional of empty if it's not present
     */
    public static <T> Optional<T> get(Class<T> type, String name) {
        if (map.containsKey(type)) {
            HashMap<String, Object> namedMap = map.get(type);
            return Optional.ofNullable(type.cast(namedMap.get(name)));
        }
        return Optional.empty();
    }

    /**
     * This will get the correct hashmap according to the object type, and automatically created
     * the map if it's not present, and put the object in with the specified name
     */
    public static void put(Object object, String name) {
        Class<?> type = object.getClass();
        HashMap<String, Object> typeMap = map.computeIfAbsent(type, map -> new HashMap<>());
        typeMap.put(name, object);
    }
}
