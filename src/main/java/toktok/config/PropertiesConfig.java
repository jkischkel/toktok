package toktok.config;

import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

/**
 * @author Jan Kischkel
 */
public final class PropertiesConfig implements Config {

    private final Properties config;

    private PropertiesConfig(Properties config) {
        this.config = config;
    }

    @Override
    public Optional<String> get(String key) {
        return Optional.ofNullable(config.getProperty(key));
    }

    @Override
    public <T> Optional<T> get(String key, Function<String, T> convert) {
        Optional<String> value = get(key);

        if (!value.isPresent())
            return Optional.empty();

        return value.map(convert::apply);
    }


    static PropertiesConfig fromPath(String path) throws IOException {
        URL url = Resources.getResource(path);
        InputSupplier<InputStream> in = Resources.newInputStreamSupplier(url);

        Properties properties = new Properties();
        properties.load(in.getInput());

        return new PropertiesConfig(properties);
    }
}
