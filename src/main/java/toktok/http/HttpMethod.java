package toktok.http;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum HttpMethod {

    GET, POST, PUT;

    public static Set<HttpMethod> all() {
        return new HashSet<>(Arrays.asList(HttpMethod.values()));
    }

    public static HttpMethod byName(String name) {
        return  all().stream()
                .filter(m -> m.name().equals(name.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
