package toktok.http;

import com.google.common.collect.Sets;
import org.junit.Test;
import toktok.http.HttpMethod;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static toktok.http.HttpMethod.GET;

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
