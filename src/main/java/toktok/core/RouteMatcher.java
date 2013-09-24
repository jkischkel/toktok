package toktok.core;

import com.google.common.collect.Maps;
import toktok.http.HttpMethod;

import java.util.Map;
import java.util.Optional;

public class RouteMatcher {

    private final Map<HttpMethod, Map<Route, Action>> registry;

    public RouteMatcher() {
        registry = Maps.newHashMap();

        HttpMethod.all().forEach(m ->
                registry.put(m, Maps.newHashMap()));
    }

    public void register(HttpMethod method, String route, Action action) {
        registry.get(method).put(Route.from(route), action);
    }

    public Action match(HttpMethod method, String route) {
        Optional<Route> pattern = registry.get(method).keySet().stream()
                .filter(e -> e.matches(route))
                .findFirst();

        return registry.get(method).get(pattern.orElse(null));
    }
}
