package toktok.core;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Route {

    private static final Pattern PATTERN = Pattern.compile("(/(:?\\w+)?)+");

    private final String route;

    private  final Pattern pattern;

    private Route(String route, Pattern pattern) {
        this.route = route;
        this.pattern = pattern;
    }

    public boolean matches(String path) {
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Route other = (Route) o;
        return route.equals(other.route);
    }


    public static Route from(String route) {
        Preconditions.checkArgument(
                PATTERN.matcher(route).matches(),
                String.format("'%s' is not a valid route", route));

        return new Route(route, prepareRoute(route));

    }

    private static Pattern prepareRoute(String route) {
        String[] parts = route.split("/");

        if (parts.length == 0) {
            return Pattern.compile(route);
        }

        List<String> encoded = Arrays.asList(parts).stream()
                .map(Route::encode)
                .collect(Collectors.<String>toList());

        String encodedRoute = Joiner.on("/").join(encoded);
        return Pattern.compile(encodedRoute);
    }

    private static String encode(String part) {
        return Pattern.matches(":\\w+", part) ? "(\\w+)" : part;
    }
}
