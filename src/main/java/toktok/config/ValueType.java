package toktok.config;

import java.util.function.Function;

/**
 * @author Jan Kischkel
 */
public interface ValueType<T> extends Function<String, T> {

    ValueType<Integer> INT = Integer::valueOf;

    ValueType<Boolean> BOOL = Boolean::valueOf;

}
