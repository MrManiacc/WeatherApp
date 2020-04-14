package me.jraynor.helpers;

import lombok.Getter;

/**
 * Stores a key value pair
 * @param <T>
 */
public class Pair<T> {
    @Getter private T key, value;

    public Pair(T key, T value) {
        this.key = key;
        this.value = value;
    }

    public static Pair<String> of(String key, String value) {
        return new Pair<>(key, value);
    }


}
