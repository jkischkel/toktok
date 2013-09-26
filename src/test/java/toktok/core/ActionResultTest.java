package toktok.core;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Jan Kischkel
 */
public class ActionResultTest {

    @Test
    public void itShouldHaveStaticConstructor() {
        ActionResult redirect = ActionResult.create(403, "body");

        assertEquals("body", redirect.getContent());
        assertEquals(Integer.valueOf(403), redirect.getStatus());
    }

    @Test
    public void itShouldHaveConstructorDefaultStatus() {
        ActionResult success = ActionResult.create("hello");

        assertEquals("hello", success.getContent());
        assertEquals(Integer.valueOf(200), success.getStatus());
    }

    @Test
    public void itShouldAcceptNullAsContent() {
        ActionResult nothing = ActionResult.create(null);

        assertNotNull(nothing);
        assertNull(nothing.getContent());
    }

    @Test
    public void itShouldEqualByStatusContent() {
        assertEquals(
                ActionResult.create("same"),
                ActionResult.create("same"));

        assertNotEquals(
                ActionResult.create("not"),
                ActionResult.create("same"));

        assertNotEquals(
                ActionResult.create(200, "status"),
                ActionResult.create(302, "status"));
    }
}
