package toktok.core;

import com.google.common.collect.Maps;
import toktok.http.HttpMethod;

import java.util.Map;
import java.util.Optional;

public class RouteMatcher {

    private final Map<HttpMethod, Map<Route, Action>> registry;

    public RouteMatcher() {
        registry = Maps.newHashMap();
        HttpMethod.all().forEach(m -> registry.put(m, Maps.newHashMap()));
    }

    public void register(HttpMethod method, String route, Action action) {
        registry.get(method).put(Route.from(route), action);
    }

    public Action match(HttpMethod method, String route) {
        final String r = route.length() > 2 && route.endsWith("/") ?
                route.substring(0, route.length() - 1) :
                route;

        Optional<Route> pattern = registry.get(method).keySet().stream()
                .filter(e -> e.matches(r))
                .findFirst();

        return registry.get(method).get(pattern.orElse(null));
    }

    public static enum DefaultMatcher {

        instance;

        private final RouteMatcher matcher = new RouteMatcher();

        public RouteMatcher matcher() {
            return matcher;
        }
    }
}
