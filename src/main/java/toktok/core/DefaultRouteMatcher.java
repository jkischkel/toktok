package toktok.core;

/**
 * @author Jan Kischkel
 */
public enum DefaultRouteMatcher {

    instance;

    private final RouteMatcher matcher = new RouteMatcher();

    public RouteMatcher matcher() {
        return matcher;
    }
}
