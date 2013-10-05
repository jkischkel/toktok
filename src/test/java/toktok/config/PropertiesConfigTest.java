package toktok.config;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import static toktok.config.ValueType.*;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Jan Kischkel
 */
public class PropertiesConfigTest {

    static final String EXAMPLE_PATH = "toktok/config/example.properties";

    private PropertiesConfig config;

    @Before
    public void loadProperties() throws IOException {
        config = PropertiesConfig.fromPath(EXAMPLE_PATH);
    }

    @Test
    public void itShouldReturnTextKeys() {
        assertValue(config.get("textKey"), "value");
    }

    @Test
    public void itShouldConvertResults() {
        assertValue(config.get("intKey", INT), 42);
        assertValue(config.get("boolKey", BOOL), true);
    }

    @Test
    public void itReturnsEmptyResultForUndefinedKeys() {
        assertFalse(config.get("noKey").isPresent());
        assertFalse(config.get("noIntKey", INT).isPresent());
    }

    private <T> void assertValue(Optional<T> value, T expected) {
        assertTrue(value.isPresent());
        assertEquals(expected, value.get());
    }
}
