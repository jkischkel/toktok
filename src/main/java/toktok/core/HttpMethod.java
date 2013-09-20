package toktok.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum HttpMethod {

    GET,

    POST,

    PUT;


    static Set<HttpMethod> all() {
        return new HashSet<>(Arrays.asList(HttpMethod.values()));
    }

    static HttpMethod byName(String name) {
        return  all().stream()
                .filter(m -> m.name().equals(name.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
