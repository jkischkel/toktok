package toktok.render;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import org.junit.Test;
import toktok.core.ActionResult;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Jan Kischkel
 */
public class RendererTest {

    TextRenderer text = new TextRenderer() {};

    JsonRenderer json = new JsonRenderer() {};

    NothingRenderer nothing = new NothingRenderer() {};

    @Test
    public void itShouldRenderEmptyActionResult() {
        ActionResult result = nothing.nothing();

        assertNull(result.getContent());
        assertEquals(Integer.valueOf(200), result.getStatus());
    }

    @Test
    public void itShouldRenderEmptyActionResultWithStatus() {
        ActionResult result = nothing.nothing(500);

        assertNull(result.getContent());
        assertEquals(Integer.valueOf(500), result.getStatus());
    }

    @Test
    public void itShouldRenderEmptyActionResultWithNoContentType() {
        assertFalse(nothing.nothing().headers().containsKey("Content-Type"));
    }

    @Test
    public void itShouldRenderPlainText() {
        ActionResult result = text.text("response");

        assertEquals("response", result.getContent());
        assertEquals(Integer.valueOf(200), result.getStatus());
    }

    @Test
    public void itShouldRenderPlainTextWithCustomStatus() {
        ActionResult result = text.text(302, "custom");

        assertEquals("custom", result.getContent());
        assertEquals(Integer.valueOf(302), result.getStatus());
    }

    @Test
    public void itShouldRenderPlainTextWithContentTypeHeader() {
        String contentType = text.text("text").headers().get("Content-Type");

        assertNotNull(contentType);
        assertEquals("text/plain", contentType);
    }

    @Test
    public void itShouldRenderJsonResonses() {
        ActionResult result = json.json(
                ImmutableMap.of("ids", Sets.newHashSet(1,2,3,4,5)));

        assertEquals("{\"ids\":[1,2,3,4,5]}", result.getContent());
        assertEquals(Integer.valueOf(200), result.getStatus());
    }

    @Test
    public void itShouldRenderJsonResponsesWithCustomStatus() {
        ActionResult result = json.json(404, new Object() {
            public String getMetric() { return "any"; }
        });

        assertEquals("{\"metric\":\"any\"}", result.getContent());
        assertEquals(Integer.valueOf(404), result.getStatus());
    }

    @Test
    public void itShouldRenderJsonResponsesWithContentTypeHeader() {
        String contentType = json.json("").headers().get("Content-Type");

        assertNotNull(contentType);
        assertEquals("application/json", contentType);
    }
}
