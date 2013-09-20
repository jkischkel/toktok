package toktok.core;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import toktok.http.HttpMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RouteMatcher {

    private final Map<HttpMethod, Map<Pattern, Action>> registry;

    RouteMatcher() {
        registry = Maps.newHashMap();
        initRegistry();
    }

    public void register(HttpMethod method, String route, Action action) {
        registry.get(method).put(prepareRoute(route), action);
    }

    public Action match(HttpMethod method, String route) {
        Optional<Pattern> pattern = registry.get(method).keySet().stream()
                .filter(e -> e.matcher(route).matches())
                .findFirst();

        return pattern.isPresent() ? registry.get(method).get(pattern.get()) : null;
    }

    void clear() {
        registry.clear();
        initRegistry();
    }


    private void initRegistry() {
        HttpMethod.all().forEach(m -> registry.put(m, Maps.newHashMap()));
    }

    private Pattern prepareRoute(String route) {
        String[] parts = route.split("/");

        if (parts.length == 0) {
            return Pattern.compile(route);
        }

        List<String> encoded = Arrays.asList(parts).stream()
                .map(this::encode)
                .collect(Collectors.<String>toList());

        String encodedRoute = Joiner.on("/").join(encoded);
        return Pattern.compile(encodedRoute);
    }

    private String encode(String part) {
        return Pattern.matches(":(\\w+)", part) ? "(\\w+)" : part;
    }
}
