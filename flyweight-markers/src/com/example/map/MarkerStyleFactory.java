package com.example.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight factory: returns a shared {@link MarkerStyle} for each unique
 * (shape, color, size, filled) combination.
 */
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public synchronized MarkerStyle get(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        MarkerStyle cached = cache.get(key);
        if (cached != null) return cached;
        MarkerStyle created = new MarkerStyle(shape, color, size, filled);
        cache.put(key, created);
        return created;
    }

    public int cacheSize() {
        return cache.size();
    }
}
