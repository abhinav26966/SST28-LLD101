package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe, lazy-initialized Singleton using the Bill Pugh static holder idiom.
 * Hardened against reflection-based and serialization-based duplication.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    private MetricsRegistry() {
        // Reflection guard: once Holder.INSTANCE is initialized, any further
        // constructor call (e.g., via setAccessible + newInstance) is rejected.
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException("MetricsRegistry is a singleton; use getInstance()");
        }
    }

    private static final class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    @Serial
    private Object readResolve() {
        return Holder.INSTANCE;
    }
}
