package toktok.core;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static toktok.core.HttpMethod.GET;

public class HttpMethodTest {

    @Test
    public void itShouldExposeAllMethods() {
        assertEquals(
                Sets.newHashSet(HttpMethod.values()),
                HttpMethod.all()
        );
    }

    @Test
    public void itShouldFindMethodsByName() {
        Arrays.asList("get", "GET", "Get").forEach( name ->
                assertEquals(GET, HttpMethod.byName(name))
        );
    }
}
