package toktok.http;

import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

public enum HttpMethod {

    GET, POST, PUT;

    private static final Set<HttpMethod> ALL =
            Collections.unmodifiableSet(Sets.newHashSet(HttpMethod.values()));

    public static Set<HttpMethod> all() {
        return ALL;
    }

    public static HttpMethod byName(String name) {
        return HttpMethod.valueOf(name.toUpperCase());
    }
}
