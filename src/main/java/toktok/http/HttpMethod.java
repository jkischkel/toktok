package toktok.http;

import com.google.common.collect.Sets;

import java.util.Set;

public enum HttpMethod {

    GET, POST, PUT;

    public static Set<HttpMethod> all() {
        return Sets.newHashSet(HttpMethod.values());
    }

    public static HttpMethod byName(String name) {
        return  all().stream()
                .filter(m -> m.name().equals(name.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
