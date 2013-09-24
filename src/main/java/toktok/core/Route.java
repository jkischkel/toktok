package toktok.core;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

final class Route {

    private static final Pattern PATTERN = Pattern.compile("(/(:?\\w+)?)+");

    private final String route;

    private  final Pattern pattern;

    private Route(String route, Pattern pattern) {
        this.route = route;
        this.pattern = pattern;
    }

    boolean matches(String path) {
        return pattern.matcher(path).matches();
    }

    @Override
    public String toString() {
        return String.format("Route(%s)", route);
    }

    @Override
    public int hashCode() {
        return route.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        return Objects.equal(route, ((Route) o).route);
    }


    static Route from(String route) {
        Preconditions.checkArgument(
                route != null && PATTERN.matcher(route).matches(),
                String.format("'%s' is not a valid route", route));

        return new Route(route, encodeRoute(route));

    }

    private static Pattern encodeRoute(String route) {
        String result = route;
        String[] parts = route.split("/");

        if (parts.length > 0) {
            result = Arrays.asList(parts).stream()
                .map(Route::encode)
                .collect(Collectors.joining("/"));
        }

        return Pattern.compile(result);
    }

    private static String encode(String part) {
        return Pattern.matches(":\\w+", part) ? "(\\w+)" : part;
    }
}
