package toktok.json;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jan Kischkel
 */
public class JsonFactoryTest {

    Json renderer = JsonFactory.jackson();

    @Test
    public void itShouldRenderObjects() {
        TestObject o = new TestObject("Jan", new Date(), "Code", "Drink");
        String json = renderer.render(o);

        assertTestObject(json, o);
    }

    @Test
    public void itShouldRenderArrays() {
        TestObject[] objects = new TestObject[] {
                new TestObject("Dirk", new Date(), "Play"),
                new TestObject("Hans", new Date(), "Read", "Skate")
        };

        String json = renderer.render(objects);
        assertTrue(json.startsWith("["));
        assertTrue(json.endsWith("]"));

        Arrays.asList(objects).forEach(o -> assertTestObject(json, o));
    }

    @Test
    public void itShouldRenderMaps() {
        Map<Long, TestObject> map = Maps.newHashMap();
        map.put(100l, new TestObject("Lisa", new Date(), "Jump"));
        map.put(500l, new TestObject("Nora", new Date(), "Draw"));

        String json = renderer.render(map);
        assertTrue(json.startsWith("{"));
        assertTrue(json.endsWith("}"));

        map.values().forEach(o -> assertTestObject(json, o));
        map.keySet().forEach(k -> assertTrue(json.contains(quote(k.toString()))));
    }

    @Test
    public void itShouldRenderEmptyMaps() {
        assertEquals("{}", renderer.render(Maps.newHashMap()));
    }

    @Test
    public void itShouldRenderIterables() {
        List<TestObject> objects = Arrays.asList(
                new TestObject("Jens", new Date(), "Ruby"),
                new TestObject("Anna", new Date(), "Art", "Music")
        );

        String json = renderer.render(objects);
        assertTrue(json.startsWith("["));
        assertTrue(json.endsWith("]"));

        objects.forEach(o -> assertTestObject(json, o));
    }

    @Test
    public void itShouldRenderEmptyIterables() {
        assertEquals("[]", renderer.render(Sets.newHashSet()));
    }

    private void assertTestObject(String json, TestObject o) {
        assertProperty(json, "name",     quote(o.getName()));
        assertProperty(json, "birthday", o.getBirthday().getTime());
        assertProperty(json, "hobbies",  o.getHobbies().stream()
                .map(this::quote)
                .collect(Collectors.joining(",", "[", "]")));
    }

    private void assertProperty(String json, String key, Object value) {
        String property = String.format("\"%s\":%s", key, value);

        assertTrue(String.format("''%s'' must contain ''%s''", json, property),
                json.contains(property));
    }

    private String quote(String s) {
        return '"' + s + '"';
    }

    static class TestObject {

        final String name;

        final Date birthday;

        final List<String> hobbies;

        TestObject(String name, Date birthday, String...hobbies) {
            this.name = name;
            this.birthday = birthday;
            this.hobbies = Lists.newArrayList(hobbies);
        }

        public String getName() {
            return name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public List<String> getHobbies() {
            return hobbies;
        }
    }
}
