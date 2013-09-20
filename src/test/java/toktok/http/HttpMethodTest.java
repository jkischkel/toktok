package toktok.http;

import org.junit.Test;
import com.google.common.collect.Sets;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
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

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionOnByName() {
        HttpMethod.byName("GHOST");
    }
}
