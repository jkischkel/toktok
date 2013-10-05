package toktok.config;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Jan Kischkel
 */
public interface Config {

    Optional<String> get(String key);

    <T> Optional<T> get(String key, Function<String, T> convert);
}
