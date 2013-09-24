package toktok.core;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RouteTest {

    @Test
    public void itShouldHaveEquals() {
        assertEquals(Route.from("/same"), Route.from("/same"));
    }

    @Test
    public void itShouldHaveToStringMethod() {
        assertEquals("Route(/home)", Route.from("/home").toString());
    }

    @Test
    public void itShouldOnlyAllowValidPathElements() {
        Arrays.asList("/w+", "\\p", "/&/", "/:/", null).forEach(badRoute -> {
            try {
                Route.from(badRoute);
                assertTrue(String.format("%s got accepted", badRoute), false);
            } catch(IllegalArgumentException e) {}
        });
    }
}
