package toktok.json;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;

/**
 * @author Jan Kischkel
 */
public class JsonFactory {

    private static class JacksonRenderer implements Json {

        private final ObjectMapper mapper = new ObjectMapper();

        public String render(Object object) {
            return map(object);
        }

        public String render(Object[] objects) {
            return map(objects);
        }

        public String render(Map<?, ?> objects) {
            return map(objects);
        }

        public String render(Iterable<?> objects) {
            return map(objects);
        }

        private String map(Object o) {
            try {
                return mapper.writeValueAsString(o);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static Json jackson() {
        return new JacksonRenderer();
    }

    private JsonFactory() {}
}
