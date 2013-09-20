package toktok.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Provides type inferred factory methods for typed collections to ease the
 * pain of creating them.<br><br>
 *
 * Example:
 * <pre>
 *   Map&lt;String, Set&lt;Integer&gt;&gt; map =
 *      new HashMap&lt;String, Set&lt;Integer&gt;&gt;();
 *
 *   // vs
 *
 *   Map&lt;String, Set&lt;Integer&gt;&gt; map = New.map();
 * </pre>
 *
 * @author Jan Kischkel
 */
public final class New {

    /**
     * Creates a new {@code Map} of the given types.
     *
     * @return the map
     */
    public static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    /**
     * Creates a new {@code ConcurrentMap} of the given types.
     *
     * @return the concurrent map
     */
    public static <K, V> ConcurrentMap<K, V> concurrentMap() {
        return new ConcurrentHashMap<>();
    }

    /**
     * Creates a new {@code Set} of the given type.
     *
     * @return the set
     */
    public static <T> Set<T> set() {
        return new HashSet<>();
    }

    /**
     * Creates a new {@code List} of the given type.
     *
     * @return the list
     */
    public static <T> List<T> list() {
        return new ArrayList<>();
    }


    private New() { }
}
